/**************************
 * Michael Stuart         *
 * mstuart3@cnm.edu       *
 * GonzalesStuartObaidiP7 *
 * CardDeck.java          *
 * ************************/

package sample;

import java.util.Random;

public class CardDeck {
    private Card[] cardArray = new Card[52];
    private boolean[] cardDealt = new boolean[52];
    private int rankArray[] = {14,2,3,4,5,6,7,8,9,10,11,12,13};
    private int counter = 0;
    private int lastCardReturned;

    public CardDeck() {
        for(int i = 0; i < 52 ; i++){
            cardArray[i] = new Card();
            cardArray[i].setRank(rankArray[i%13]);
            cardArray[i].setImageName( i + 1 + ".png");

            if(i<13) {
                cardArray[i].setSuit("spade");
            } else if (i >= 13 && i < 26) {
                cardArray[i].setSuit("heart");
            } else if (i >= 26 && i < 39) {
                cardArray[i].setSuit("diamond");
            } else {
                cardArray[i].setSuit("club");
            }
            cardDealt[i] = false;
        }

    }

    public Card getNextCard(){
        //check to see if any cards remain in the deck
        if( counter < 52) {
            Random rand = new Random();
            //loop until a card is found that has not been dealt yet
            while(true){
                int randomIndex = rand.nextInt(52);
                //check to see if card dealt
                if(!cardDealt[randomIndex]){
                    counter++;  //increase number of cards dealt
                    cardDealt[randomIndex] = true; //set this card as dealt
                    lastCardReturned = randomIndex; //set last card returned as this card index
                    return cardArray[randomIndex];
                }
            }
        } else {
            return cardArray[lastCardReturned];
        }
    }

    public boolean isDeckGood()
    {
        if (counter < 46)
        {
            return true;
        }
        return false;
    }
}
