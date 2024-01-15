/*
 * Card.java
 * 
 * A blueprint class to represent an individual playing card.
 * 
 * CS 112, Boston University
 * 
 * completed by: Emily Opresnick, eopres@bu.edu
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
      for(int i=0;i<SUIT_NAMES.length;i++){
        if(SUIT_NAMES[i].equalsIgnoreCase(suit)){
          return i;
        }
      }
      return -1;
    }

    /***** Implement parts 3-7 below. *****/
    private int rank;
    private int suitNum;

    //constructor when given int rank and int suit parameter
    public Card(int rank, int suitNum){
      if (rank >LAST_RANK || rank < FIRST_RANK || suitNum > LAST_SUIT|| suitNum< FIRST_SUIT){
        throw new IllegalArgumentException();
      }

      this.rank=rank;
      this.suitNum=suitNum;
    }

    //constructor when given int rank and string suit parameter
    public Card(int rank, String suit){
      if (rank >LAST_RANK || rank < FIRST_RANK){
        throw new IllegalArgumentException();
      }

      this.rank=rank;
      suitNum=getSuitNum(suit);

      if(suitNum==-1){
        throw new IllegalArgumentException();
      }
    }

    //accessor method to get rank
    public int getRank(){
      return rank;
    }

    //accessor method to get rank name
    public String getRankName(){
      return RANK_NAMES[getRank()];
    }

    //accessor method to get suit num
    public int getSuitNum(){
      return suitNum;
    }

    //accessor method to get suit name
    public String getSuitName(){
      return SUIT_NAMES[getSuitNum()];
    }

    //accessor method to get name
    public String getName(){
      return (getRankName() + " of " + getSuitName());
    }

    //accessor method that returns true if the card is an ace
    public boolean isAce(){
      if(getRank()==1){
        return true;
      }
      return false;
    }

    //accessor method that returns true if the card is a face card
    public boolean isFaceCard(){
      if(getRank()==11 || getRank()==12 || getRank()==13){
        return true;
      }
      return false;
    }

    //accessor method that returns the value of the card, 10 if face card or rank otherwise
    public int getValue(){
      if(isFaceCard()){
        return 10;
      }
      return getRank();
    }

    //to string method
    public String toString(){
      int rank=getRank();
      int suit=getSuitNum();
      return RANK_ABBREVS[rank]+""+ SUIT_ABBREVS[suit];
    }

    //instance method that returns true if card object has the same suit as the called object
    public boolean sameSuitAs(Card c){
      if(c==null){
        return false;
      }
      int suitSelf=getSuitNum();
      int suitC=c.getSuitNum();
      if (suitSelf==suitC){
        return true;
      }
      return false;
    }

    //instance method that returns true if card object is equal to the called object
    public boolean equals(Card c){
      if(c==null){
        return false;
      }
      int suitSelf=getSuitNum();
      int rankSelf=getRank();
      int suitC=c.getSuitNum();
      int rankC=c.getRank();
      if((suitSelf==suitC) && (rankSelf==rankC)){
        return true;
      }
      return false;
    }



}
