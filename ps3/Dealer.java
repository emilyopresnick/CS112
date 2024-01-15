/**
 * Dealer.java
 * 
 * Computer Science 112, Boston University
 * 
 * The dealer class for a program that plays the game of Blackjack.
 *
 * 
 * modified by: Emily Opresnick, eopres@bu.edu
 */

import java.util.*;

public class Dealer extends Player {
    private boolean revealCard;

    public Dealer(){
        super("dealer");
        this.revealCard=false;
    }

    //changes the value of the field revealCard
    public void revealFirstCard(){
        revealCard=true;
    }
    
    //prints the dealers hand dependent on the revealCard variable
    public void printHand(){
        int start=0;
        if(revealCard==false){
            start=1;
            System.out.print("XX  ");

        }
        for(int i=start;i<getNumCards();i++){
            System.out.print(getCard(i) + "  ");
            
            
        }
        if(revealCard==true){
            System.out.print("(value = " + getHandValue() + ")");

        }
        System.out.println();
    }


    //returns true if the dealer wants hit and false otherwise
    public boolean wantsHit(Scanner r, Player p){
        int playerVal=p.getHandValue();
        if(playerVal<17){
            if(this.getHandValue()<=playerVal){
                return true;
            }
            return false;
        }
        else {
            if(playerVal==21){
                return false;
            }
            if(this.getHandValue()<playerVal){
                return true;
            }
            return false;
           
        }
    }


    //clears dealers hand and changes revealCard to false
    public void discardCards(){
        super.discardCards();
        revealCard=false;

    }
    
    
}
