/**
 * Player.java
 * 
 * Computer Science 112, Boston University
 * 
 * The player class for a program that plays the game of Blackjack.
 *
 * 
 * modified by: Emily Opresnick, eopres@bu.edu
 */

import java.util.*;

public class Player{
    private String name;
    private Card hand[];
    private int numCards;

    public Player(String name){
        this.name=name;
        hand=new Card[Blackjack.MAX_CARDS_PER_PLAYER];
    }

    //returns the player name
    public String getName(){
        return name;
    }


    //returns the num cards a player has
    public int getNumCards(){
        return numCards;
    }


    //adds the card c to players hand
    public void addCard(Card c){
        if(c==null){
            throw new IllegalArgumentException("invalid card:   " + c);
        }

        else if(numCards==Blackjack.MAX_CARDS_PER_PLAYER){
            throw new IllegalArgumentException("player has max amount of card");
        }

        else {
            hand[numCards]=c;
            numCards++;
        }
    }


    //returns the card at a certain index 
    public Card getCard(int index){
        if(index>Blackjack.MAX_CARDS_PER_PLAYER){
            throw new IllegalArgumentException("index " + index +" does not correspond to card");
        }
        else if(hand[index]==null){
            throw new IllegalArgumentException("index " + index +" does not correspond to card");
        }

        return hand[index];
    }


    //returns the value of a players hand
    public int getHandValue(){
        int total=0;
        boolean hasAce=false;
        for(int i=0;i<numCards;i++){
            if(hand[i].getValue()==1){
                hasAce=true;
                total+=1;
            }
            else {
                total+=hand[i].getValue();
            }

        }
        if((total+10)<22 && hasAce==true){
        
            total+=10;

        }
        return total;
    }


    //prints the hand value of a player
    public void printHand(){
        for(int i=0;i<numCards;i++){
            System.out.print(hand[i] + "  ");
        }
        System.out.println("(value = " + getHandValue() + ")");
    }


    //returns true if a player has blackjack and false otherwise
    public boolean hasBlackjack(){
        if (numCards==2 && getHandValue()==21){
            return true;
        }
        else{
            return false;
        }
    }


    //returns true if player wants a hit and false otherwise
    public boolean wantsHit(Scanner r, Player p){
        System.out.print("Want another hit, "+ getName() + " (y/n)? ");
        String reply= r.nextLine();
        if(reply.equalsIgnoreCase("Y")){
            return true;
        }
        else {
            return false;
        }

    }


    //gets rid of the cards in the hand
    public void discardCards(){
        numCards=0;
        for(int i=0;i<numCards;i++){
            hand[i]=null;
        }
    }


}
    
