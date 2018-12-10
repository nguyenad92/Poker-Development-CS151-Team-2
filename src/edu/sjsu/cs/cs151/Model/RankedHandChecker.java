package edu.sjsu.cs.cs151.Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to store the rank of the card
 */
public class RankedHandChecker {

    private int rankedHandScore;
    private ArrayList<Card> cardToBeAnalyzed = new ArrayList<>();
    private RankedHandType rankedHandType;
    private ArrayList<Integer> suitList, rankList, handScore, pairRank;
    private boolean straightAceBottom, straightAceTop;

    /** The ranking factors (powers of 13, the number of ranks). */
    private static final int[] RANKING_FACTORS = {28561, 2197, 169, 13, 1};

    private int setRank = -1, quadRank = -1, flushSuit = -1, highestFlushRank = -1, highestStraightCard = -1;

    /**
     * calculate the rank score
     * @param card
     */
    public RankedHandChecker(ArrayList<Card> card) {
        handScore = new ArrayList<>();
        pairRank = new ArrayList<>();
        cardToBeAnalyzed.addAll(card);
        Collections.sort(cardToBeAnalyzed);
        initArrayList();

        setRankedHandType();
        rankedHandScore += rankedHandType.getScore() * 371293;
        for (int i = 0; i < handScore.size(); i++) {
            rankedHandScore += handScore.get(i) * RANKING_FACTORS[i];
        }
    }

    /**
     * define the ranks based on types of cards.
     */
    private void setRankedHandType() {
        if (isRoyalFlush())    rankedHandType = RankedHandType.ROYAL_FLUSH;
        else if (isStraightFlush()) rankedHandType = RankedHandType.STRAIGHT_FLUSH;
        else if (isFourOfAKind())   rankedHandType = RankedHandType.FOUR_OF_A_KIND;
        else if (isFullHouse())     rankedHandType = RankedHandType.FULL_HOUSE;
        else if (isFlush())         rankedHandType = RankedHandType.FLUSH;
        else if (isStraight())      rankedHandType = RankedHandType.STRAIGHT;
        else if (isSet())           rankedHandType = RankedHandType.THREE_OF_A_KIND;
        else if (isTwoPair())       rankedHandType = RankedHandType.TWO_PAIRS;
        else if (isOnePair())            rankedHandType = RankedHandType.ONE_PAIR;
        else                        getHighCard();

    }

    /**
     * the suit cards list
     * @return
     */
    private ArrayList<Integer> getSuitDistributionList() {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(4, 0));
        for (int j = 0; j < cardToBeAnalyzed.size(); j++) {
            int currSuit = cardToBeAnalyzed.get(j).getSuit();
            int currVal = list.get(currSuit);
            list.set(currSuit, ++currVal);
        }
        return list;
    }

    /**
     * print out the suit list and rank list
     */
    private void initArrayList() {
        suitList = getSuitDistributionList();
        rankList = getRankDistributionList();

        System.out.println("Suit List: " + suitList);
        System.out.println("Rank List: " + rankList);

        // Find Flush
        getFlush();

        // Find Straight
        getStraight();

        // Find Pair, Set, Quad
        getDuplicateCard();
    }

    /**
     * the list of rank cards
     * @return
     */
    private ArrayList<Integer> getRankDistributionList() {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(13, 0));
        for (int j = 0; j < cardToBeAnalyzed.size(); j++) {
            int currRank = cardToBeAnalyzed.get(j).getRank();
            int currValue = list.get(currRank);
            list.set(currRank, ++currValue);
        }
        return list;
    }

    /**
     * check for the same suits
     */
    private void getFlush() {
        for (int i = 0; i < suitList.size(); i++) {
            if (suitList.get(i) >= 5) {
                flushSuit = i;
                for (Card c : cardToBeAnalyzed) {
                    if (c.getSuit() == flushSuit) {
                        highestFlushRank = c.getRank();
                        break;
                    }
                }
            }
        }
    }

    /**
     * check if 5 cards in sequence
     */
    private void getStraight() {
        boolean inProcessStraight = false;
        int size = rankList.size();
        int continuousCardCount = 0;

        for (int i = size - 1; i >= 0; i--) {
            if (rankList.get(i) >= 1) {
                if (!inProcessStraight) {
                    // First card of the potential Straight.
                    inProcessStraight = true;
                    highestStraightCard = i;
                }
                continuousCardCount++;
                if (continuousCardCount > 4) {
//                    highestStraightCard = i;
                    break;
                }
            } else {
                inProcessStraight = false;
                continuousCardCount = 0;
            }
        }

        if (continuousCardCount == 4
                && (highestStraightCard == Card.DEUCE)
                && (rankList.get(12) >= 1)) {
            straightAceBottom = true;
            highestStraightCard = Card.FIVE;
        }

        if (continuousCardCount < 5) {
            highestStraightCard = -1;
        }

        if (continuousCardCount >= 5 && highestStraightCard == Card.ACE) {
            straightAceTop = true;
        }


    }

    /**
     * checks duplicate cards
     */
    private void getDuplicateCard() {
        for (int i = 0; i < rankList.size(); i++) {
            if (rankList.get(i) >= 4) quadRank = i;
            else if (rankList.get(i) == 3) setRank = i;
            else if (rankList.get(i) == 2) pairRank.add(i);
        }
        System.out.println("Quad found " + quadRank);
        System.out.println("Set found " + setRank);
        System.out.println("Pair found " + pairRank.toString());
    }

    /**
     * Check for High Card
     */
    private void getHighCard() {
        rankedHandType = RankedHandType.HIGH_CARD;
        for (int i = 0; i < 5; i++) {
            handScore.add(cardToBeAnalyzed.get(i).getRank());
        }
    }

    /**
     * Check for 1 Pair
     */
    private boolean isOnePair() {
        if (pairRank.size() == 1) {
            rankedHandType = RankedHandType.ONE_PAIR;
            handScore.add(pairRank.get(0));
            int count = 0;
            for (Card c : cardToBeAnalyzed) {
                if (c.getRank() != pairRank.get(0) && count < 3 ) {
                    handScore.add(c.getRank());
                    count++;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for 2 Pairs
     */
    private boolean isTwoPair() {
        if (pairRank.size() == 2) {
            rankedHandType = RankedHandType.TWO_PAIRS;
            handScore.addAll(pairRank);
            int count = 0;
            for (Card c : cardToBeAnalyzed) {
                if (c.getRank() != pairRank.get(0) && c.getRank() != pairRank.get(1) && count < 1) {
                    handScore.add(c.getRank());
                    count++;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for a Set
     */
    private boolean isSet() {
        if (setRank > 0) {
            rankedHandType = RankedHandType.THREE_OF_A_KIND;
            handScore.add(setRank);
            int count = 0;
            for (Card c : cardToBeAnalyzed) {
                if (c.getRank() != setRank && count < 2) {
                    handScore.add(c.getRank());
                    count++;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for a straight
     */
    private boolean isStraight() {
        if (highestStraightCard >= 0) {
            handScore.add(highestStraightCard);
            return true;
        }
        return false;
    }

    /**
     * check for a flush
     */
    private boolean isFlush() {
        if (flushSuit >= 0) {
            rankedHandType = RankedHandType.FLUSH;
            for (Card c: cardToBeAnalyzed) {
                if (c.getSuit() == flushSuit && handScore.size() < 5) {
                    if (handScore.size() < 1) highestFlushRank = c.getRank();
                    handScore.add(c.getSuit());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * check for a full house
     */
    private boolean isFullHouse() {
        if (pairRank.size() == 1 && setRank >= 0) {
            rankedHandType = RankedHandType.FULL_HOUSE;
            handScore.add(setRank);
            handScore.add(pairRank.get(0));
            return true;
        }
        return false;
    }

    /**
     * check for 4 of a Kind
     */
    private boolean isFourOfAKind() {
        if (quadRank > 0) {
            rankedHandType = RankedHandType.FOUR_OF_A_KIND;
            handScore.add(setRank);
            int count = 0;
            for (Card c : cardToBeAnalyzed) {
                if (c.getRank() != setRank && count < 1) {
                    handScore.add(c.getRank());
                    count++;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Check for straight flush
     */
    private boolean isStraightFlush() {
        if (highestFlushRank == highestStraightCard && isStraight() && isFlush() && !straightAceTop) {
            rankedHandType = RankedHandType.STRAIGHT_FLUSH;
            handScore.clear();
            handScore.add(highestStraightCard);
            return true;
        }
        return false;
    }

    /**
     * Check for royal flush
     */
    private boolean isRoyalFlush() {
        if (highestFlushRank == highestStraightCard && isStraight() && isFlush()
                && straightAceTop) {
            rankedHandType = RankedHandType.ROYAL_FLUSH;
            handScore.clear();
            handScore.add(highestStraightCard);
            return true;
        }
        return false;
    }


    /**
     * Set/Get Method
     */

    public RankedHandType getRankedHandType() {
        return rankedHandType;
    }

    public int getRankedHandScore() {
        return rankedHandScore;
    }

    public ArrayList<Card> getCardToBeAnalyzed() {
        return cardToBeAnalyzed;
    }

    public void setCardToBeAnalyzed(ArrayList<Card> cardToBeAnalyzed) {
        this.cardToBeAnalyzed = cardToBeAnalyzed;
    }

    public ArrayList<Integer> getSuitList() {
        return suitList;
    }
}
