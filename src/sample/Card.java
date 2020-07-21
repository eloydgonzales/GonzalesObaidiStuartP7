/**************************
 * Michael Stuart         *
 * mstuart3@cnm.edu       *
 * GonzalesStuartObaidiP7 *
 * Card.java              *
 * ************************/

package sample;

public class Card {
    private int rank;
    private String suit;
    private String imageName;


    public Card() {
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
