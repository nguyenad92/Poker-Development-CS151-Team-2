package edu.sjsu.cs.cs151.model;

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

    private int setRank, quadRank, flushSuit, highestFlushRank, highestRankCard, highestStraightCard;

    public RankedHandChecker(ArrayList<Card> card) {
        handScore = new ArrayList<>();
        pairRank = new ArrayList<>();
        cardToBeAnalyzed.addAll(card);
        Collections.sort(cardToBeAnalyzed);
        initArrayList();

        setRankedHandType();
    }

    private void setRankedHandType() {
        if (isHighCard())           rankedHandType = RankedHandType.HIGH_CARD;
        else if (isOnePair())       rankedHandType = RankedHandType.ONE_PAIR;
        else if (isTwoPair())       rankedHandType = RankedHandType.TWO_PAIRS;
        else if (isSet())           rankedHandType = RankedHandType.THREE_OF_A_KIND;
        else if (isStraight())      rankedHandType = RankedHandType.STRAIGHT;
        else if (isFlush())         rankedHandType = RankedHandType.FLUSH;
        else if (isFullHouse())     rankedHandType = RankedHandType.FULL_HOUSE;
        else if (isFourOfAKind())   rankedHandType = RankedHandType.FOUR_OF_A_KIND;
        else if (isStraightFlush()) rankedHandType = RankedHandType.STRAIGHT_FLUSH;
        else                        rankedHandType = RankedHandType.ROYAL_FLUSH;
    }

    private ArrayList<Integer> getSuitDistributionList() {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(4, 0));
        for (int i = 0; i < cardToBeAnalyzed.size(); i++) {
            int currValue = list.get(i);
            int currSuit = cardToBeAnalyzed.get(i).getSuit();
            list.set(currSuit, ++currValue);
        }
        return list;
    }

    private void initArrayList() {
        suitList = getSuitDistributionList();
        rankList = getRankDistributionList();

        // Find Flush
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

        // Find Straight


        // Find Pair, Set, Quad
        for (int i = 0; i < rankList.size(); i++) {
            if (rankList.get(i) >= 4) quadRank = i;
            else if (rankList.get(i) == 3) setRank = i;
            else if (rankList.get(i) == 2) pairRank.add(i);
        }
    }

    private ArrayList<Integer> getRankDistributionList() {
        ArrayList<Integer> list = new ArrayList<>(Collections.nCopies(13, 0));
        for (int i = 0; i < cardToBeAnalyzed.size(); i++) {
            int currValue = list.get(i);
            int currRank = cardToBeAnalyzed.get(i).getRank();
            list.set(currRank, ++currValue);
        }
        return list;
    }

    /**
     * Check for High Card
     */
    private boolean isHighCard() {
        for (Card c: cardToBeAnalyzed) {


        }
        return false;
    }

    // Check for 1 Pair
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

    // Check for 2 Pairs
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

    // Check for a Set
    private boolean isSet() {
        if (setRank >= 0) {
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

    // Check for a straight
    private boolean isStraight() {
        int size = cardToBeAnalyzed.size();
        int nextRank = cardToBeAnalyzed.get(0).getRank() + 1;
        int count = 1;
        for (int i = 1; i < cardToBeAnalyzed.size(); i++) {
            // Check if there's a Ace Straight
            if (cardToBeAnalyzed.get(size - 1).getRank() == 12) {
                boolean a = cardToBeAnalyzed.get(i).getRank() == 0 &&
                        cardToBeAnalyzed.get(i + 1).getRank() == 1 &&
                        cardToBeAnalyzed.get(i + 2).getRank() == 2 &&
                        cardToBeAnalyzed.get(i + 3).getRank() == 3;

                boolean b = cardToBeAnalyzed.get(i).getRank() == 8 &&
                        cardToBeAnalyzed.get(i + 1).getRank() == 9 &&
                        cardToBeAnalyzed.get(i + 2).getRank() == 10 &&
                        cardToBeAnalyzed.get(i + 3).getRank() == 11;

                highestRankCard = 12;
                return (a || b);
            }

            if (cardToBeAnalyzed.get(i).getRank() == nextRank) {
                nextRank++;
                count++;
                highestStraightCard = cardToBeAnalyzed.get(i).getRank();
            } else {
                count = 1;
                nextRank = cardToBeAnalyzed.get(i).getRank() + 1;
                highestStraightCard = 0;
            }

            if (count >= 5) {
                handScore.add(highestStraightCard);
                return true;
            }
        }

        return false;
    }

    // check for a flush
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

    // check for a full house
    private boolean isFullHouse() {
        if (pairRank.size() == 1 && setRank >= 0) {
            rankedHandType = RankedHandType.FULL_HOUSE;
            handScore.add(setRank);
            handScore.add(pairRank.get(0));
            return true;
        }
        return false;
    }

    // check for 4 of a Kind
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

    // Check for straight flush
    private boolean isStraightFlush() {
        if (highestStraightCard >= 0 && highestFlushRank == highestStraightCard) {

        }
        return false;
    }

    // Check for Royal Flush
    private boolean isRoyalFlush() {
        if (isStraight() && isFlush() && highestRankCard == 12) {
            rankedHandType = RankedHandType.ROYAL_FLUSH;
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
