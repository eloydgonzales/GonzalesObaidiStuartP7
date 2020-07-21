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

    public String getRules()
    {
        return "The player with the highest ranking hand wins. The rankings from highest to least are: Three 3's or an Ace, 2 or 3 of the same card or suit, a King Queen or Jack ";
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
                hand[0].getSuit() == hand[1].getSuit() ||
                hand[0].getSuit() == hand[2].getSuit())
        {
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
