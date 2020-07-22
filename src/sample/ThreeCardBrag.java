/**************************
 * Eloyd Gonzales         *
 * egonzales164@cnm.edu   *
 * GonzalesStuartObaidiP7 *
 * ThreeCardBrag.java     *
 * ************************/

package sample;
// TODO check hand sizes
public class ThreeCardBrag
{
    //-------------------------
    //fields
    //-------------------------
    private Card[] player1Hand;
    private Card[] player2Hand;
    private int winningHand;

    //-------------------------
    // Constructors
    //-------------------------

    public ThreeCardBrag()
    {}

    //-------------------------
    // Methods
    //-------------------------
    public void setPlayerHands(Card [] hand1, Card [] hand2)
    {
        player1Hand = hand1;
        player2Hand = hand2;
        whoWon();
    }

    public void whoWon()
    {
        int player1Rank = checkHandRank(player1Hand);
        int player2Rank = checkHandRank(player2Hand);

        if (player1Rank > player2Rank)
        {
            winningHand = 1;
        }
        else if (player1Rank < player2Rank)
        {
            winningHand = 2;
        }
        else
        {
            if (player1Rank == 0)
            {
                winningHand = 0;
            }
            else
            {
                winningHand = 3;
            }
        }
    }

    public int getWinningHand()
    {
        return winningHand;
    }

    public String getRules() //MS added new lines so formats better on JOptionPane in Controller
    {
        return "The player with the highest ranking hand wins.\nThe rankings from highest to lowest are:\n" +
                "Three 3's or an Ace\n2 or 3 of the same card or suit\nKing Queen or Jack ";
    }

    private int checkHandRank(Card [] hand)
    {
        // check for Three 3's or an Ace
        for (Card c : hand)
        {
            if (c.getRank() == 14)
            {
                return 3;
            }
        }
        if (hand[0].getRank() == 3 &&
                hand[1].getRank() == 3 &&
                hand[2].getRank() == 3)
        {
            return 3;
        }

        // check for 2 or 3 of the same card or suit
        if (hand[0].getRank() == hand[1].getRank() ||
                hand[0].getRank() == hand[2].getRank() ||
                hand[1].getRank() == hand[2].getRank() || //MS add a check for cards 2 and 3 rank
                hand[0].getSuit() == hand[1].getSuit() ||
                hand[0].getSuit() == hand[2].getSuit() ||
                hand[1].getSuit() == hand[2].getSuit()){ //MS add a check for cards 2 and 3 suit

            return 2;
        }

        // check for King, Queen or Jack
        for (Card c : hand)
        {
            if (c.getRank() > 10)
            {
                return 1;
            }
        }

        // if the hand has no rank
        return 0;
    }

}
