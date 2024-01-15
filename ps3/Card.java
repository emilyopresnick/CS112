/*
 * Card.java
 *
 * Computer Science 112, Boston University
 * 
 * A blueprint class for objects that represent a single playing card.
 */

public class Card {
    // constants for the ranks of non-numeric cards
    public static final int ACE = 1;
    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    
    // other constants for the ranks
    public static final int FIRST_RANK = 1;
    public static final int LAST_RANK = 13;
    
    // Arrays of strings for the rank names and abbreviations.
    // The name of the rank r is given by RANK_NAMES[r].
    // The abbreviation of the rank r is given by RANK_ABBREVS[r].
    private static final String[] RANK_NAMES = {
      null, "Ace", "2", "3", "4", "5", "6", 
      "7", "8", "9", "10", "Jack", "Queen", "King"
    };
    private static final String[] RANK_ABBREVS = {
      null, "A", "2", "3", "4", "5", "6",
      "7", "8", "9", "10", "J", "Q", "K"
    };
    
    // constants for the suits
    public static final int FIRST_SUIT = 0;
    public static final int LAST_SUIT = 3;
    public static final int CLUBS = 0;
    public static final int DIAMONDS = 1;
    public static final int HEARTS = 2;
    public static final int SPADES = 3;
    
    // Arrays of strings for the suit names and abbreviations.
    // The name of the suit s is given by SUIT_NAMES[s].
    // The abbreviation of the suit s is given by SUIT_ABBREVS[s].
    private static final String[] SUIT_NAMES = {
      "Clubs", "Diamonds", "Hearts", "Spades"
    };
    private static final String[] SUIT_ABBREVS = {
      "C", "D", "H", "S"
    };
    
    /***** part 2: getSuitNum *****/
    private static int getSuitNum(String suit) {
        for (int i = 0; i < SUIT_NAMES.length; i++) {
            if (SUIT_NAMES[i].equalsIgnoreCase(suit)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /***** part 3: fields for this Card *****/
    private int rank;
    private int suitNum;

    /***** part 4: Card constructors *****/
    /*
     * constructor that takes integer values for the rank and suit
     */
    public Card(int rank, int suitNum) {
        if (rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("invalid rank value: " + rank);
        }
        if (suitNum < FIRST_SUIT || suitNum > LAST_SUIT) {
            throw new IllegalArgumentException("invalid suit number: " + suitNum);
        }
        
        this.rank = rank;
        this.suitNum = suitNum;
    }

    /*
     * constructor that takes an integer for the rank and a String for
     * the suit
     */
    public Card(int rank, String suit) {
        if (rank < FIRST_RANK || rank > LAST_RANK) {
            throw new IllegalArgumentException("invalid rank value: " + rank);
        }

	int suitNum = getSuitNum(suit);
        if (suitNum < FIRST_SUIT || suitNum > LAST_SUIT) {
            throw new IllegalArgumentException("invalid suit number: " + suitNum);
        }
        
        this.rank = rank;
        this.suitNum = suitNum;
    }

    /***** part 5: basic accessor methods *****/
    /*
     * getRank - returns the integer representation of the card's rank
     */
    public int getRank() {
        return this.rank;
    }
    
    /*
     * getRankName - returns the String representation of the card's rank
     */
    public String getRankName() {
        return RANK_NAMES[this.rank];
    }
    
    /*
     * getSuitNum - returns the integer representation of the card's suit
     */
    public int getSuitNum() {
        return this.suitNum;
    }
    
    /*
     * getSuitName - returns the String representation of the card's suit
     */
    public String getSuitName() {
        return SUIT_NAMES[this.suitNum];
    }
    
    /*
     * getName - returns the name of the card in the form
     * "rank-name of suit-name" (e.g., "King of Hearts")
     */
    public String getName() {
        return RANK_NAMES[this.rank] + " of " + SUIT_NAMES[this.suitNum];
    }
    
    /*
     * isAce - returns true if the card is an Ace, and false otherwise
     */
    public boolean isAce() {
        return (this.rank == ACE);
    }
    
    /*
     * isFaceCard - returns true if the card is a face card,
     * and false otherwise
     */
    public boolean isFaceCard() {
        return (this.rank == JACK 
             || this.rank == QUEEN
             || this.rank == KING);
    }
    
    /*
     * getValue - returns the value of the card.
     * 
     * Returns a 10 for face cards, and the rank for all other cards.
     */
    public int getValue() {
        if (this.isFaceCard()) {
            return 10;
        } else {
            return this.rank;
        }
    }
    
    /*
     * *** part 6: toString - returns a String representation of this card.
     * For example, "2C" for a 2 of clubs, or "KH" for a King of hearts.
     */
    public String toString() {
        return (RANK_ABBREVS[this.rank] + SUIT_ABBREVS[this.suitNum]);
    }
    
    /*** part 7: methods for comparing Card objects ***/
    
    /*
     * sameSuitAs - returns true if the called object has the same suit
     * as the object represented by the parameter, and false otherwise.
     */
    public boolean sameSuitAs(Card other) {
        return (other != null && this.suitNum == other.suitNum);
    }
    
    /*
     * equals - returns true if the calling object has the same rank
     * and suit as the object represented by the parameter, and
     * false otherwise.
     */
    public boolean equals(Card other) {
        return (other != null
          && this.rank == other.rank
          && this.sameSuitAs(other));
    }
}