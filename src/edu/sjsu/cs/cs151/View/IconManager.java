package edu.sjsu.cs.cs151.View;

import java.net.URL;

import javax.swing.ImageIcon;

import edu.sjsu.cs.cs151.Model.*;

/**
 * Utility for retrieving cards images
 * @author Oscar Stigter
 */
public abstract class IconManager {
	private static final String IMAGE_PATH = "/images/card_%s.png";

    /**
     * Returns the image of a specific card.
     *
     * @param card
     *
     * @return The image.
     */
    public static ImageIcon getCardImage(Card card) {
        // Use image order, which is different from value order.
        int sequenceNr = card.getSuit() * Card.NO_OF_RANKS + card.getRank();
        String sequenceNrString = String.valueOf(sequenceNr);
        if (sequenceNrString.length() == 1)
            sequenceNrString = "0" + sequenceNrString;
        String path = String.format(IMAGE_PATH, sequenceNrString);
        return getIcon(path);
    }

    /**
     * Returns an image resource.
     *
     * @param path
     *
     * @return The image resource.
     *
     */
    public static ImageIcon getIcon(String path) {
        URL url = IconManager.class.getResource(path);
        if (url != null) {
            return new ImageIcon(url);
        }
        else
        	throw new RuntimeException("Image is not found" + path);
    }
}
