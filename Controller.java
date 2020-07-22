/**************************
 * Ali Obaidi             *
 * aobaidi@cnm.edu        *
 * GonzalesStuartObaidiP7 *
 * Controller.java        *
 * ************************/


package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    CardDeck deck;
    ThreeCardBrag game;
    Card[] hand1;
    Card[] hand2;
    int player1Score;
    int player2Score;
    String player1Name;
    String player2Name;
    String resultsWinner;

    @FXML private ImageView imgHeader;

    @FXML private TextArea taHeader;

    @FXML private TextArea taResults;

    @FXML private Button bntPlayAgain;

    @FXML private ImageView img1Card1P1;

    @FXML private ImageView img1Card3P1;

    @FXML private ImageView img1Card2P1;

    @FXML private TextField tfPlayer1;

    @FXML private Button bntDealCards;

    @FXML private ImageView img2Card1P2;

    @FXML private ImageView img2Card2P2;

    @FXML private ImageView img2Card3P2;

    @FXML private TextField tfPlayer2;

    @FXML private Button bntNoneDealAgain;

    @FXML
    void DealCardsOnAction(ActionEvent event) {
        dealTheCards();
        determineWinner();
        if (!deck.isDeckGood())
        {
            bntDealCards.setDisable(true);
        }
    }

    @FXML
    void GameRulesOnAction(ActionEvent event) {                  // Game Rules Message
        //MS pull rules from ThreeCardBrag class per specs
        JOptionPane.showMessageDialog(null,
                game.getRules());
        /*JOptionPane.showMessageDialog(null,"3 Cards are dealt to each player.\n" +
                "A player who holds any of the following cards wins:\n" +
                "Three 3's or an Ace\n" +
                "2 or 3 of the same card or suit\n" +
                "King, Queen or Jack\n" +
                "But if two players both have winning hands,\n" +
                "Three 3's or an Ace beat the 2 or 3 cards of same suit or number which in turn beat a King,\n" +
                "Queen or Jack. If there is a tie between players, no one wins.\n","Game Rules", JOptionPane.INFORMATION_MESSAGE ); */
    }

    @FXML
    void NoneDealAgainOnAction(ActionEvent event) {
        displayResults();
    }

    @FXML
    void PlayAgainOnAction(ActionEvent event) {
        deck = new CardDeck();
        game = new ThreeCardBrag();
        player1Score = 0;
        player2Score = 0;
        resultsWinner = "No game has been played.";
        bntDealCards.setDisable(false);
        taResults.clear();

        Image placeholder = new Image(this.getClass().getResource("images/b2fv.png").toExternalForm());                   // Game back cards images
        img1Card1P1.setImage(placeholder);
        img1Card2P1.setImage(placeholder);
        img1Card3P1.setImage(placeholder);
        img2Card1P2.setImage(placeholder);
        img2Card2P2.setImage(placeholder);
        img2Card3P2.setImage(placeholder);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfPlayer1.setPromptText("Player 1 Name");                  // Player1 name hint when game started
        tfPlayer2.setPromptText("Player 2 Name");                                // Player2 name hint when game started
        Image Icon = new Image(this.getClass().getResource("images/Icon.png").toExternalForm());                   // Game header image
        imgHeader.setImage(Icon);
        Image placeholder = new Image(this.getClass().getResource("images/b2fv.png").toExternalForm());                   // Game back cards images
        img1Card1P1.setImage(placeholder);
        img1Card2P1.setImage(placeholder);
        img1Card3P1.setImage(placeholder);
        img2Card1P2.setImage(placeholder);
        img2Card2P2.setImage(placeholder);
        img2Card3P2.setImage(placeholder);

        deck = new CardDeck();
        game = new ThreeCardBrag();
        player1Score = 0;
        player2Score = 0;

        /*player1Name = player1Name;
        player2Name = player2Name;*/
        resultsWinner = "No game has been played.";
    }

    private void dealTheCards()
    {
        //  1. Create two arrays of three cardNames, one for each player. These will get values soon.
        hand1 = new Card[3];
        hand2 = new Card[3];

        //  2. In a loop of 3
        for (int i = 0; i < 3; i++)
        {
            //    a. Call deck.getNextCard() to fill the Card arrays.
            hand1[i] = deck.getNextCard();
            hand2[i] = deck.getNextCard();

            // test code
            //System.out.println(hand1[i].getImageName());

            //    b. Now create the corresponding card names with the getImageName method.
            Image cardImg1 = new Image(this.getClass().getResource("images/" + hand1[i].getImageName()).toExternalForm());
            Image cardImg2 = new Image(this.getClass().getResource("images/" + hand2[i].getImageName()).toExternalForm());

            //    c. Set new card images into the ImageView objects using the card names
            switch (i)
            {
                case 0:
                    img1Card1P1.setImage(cardImg1);
                    img2Card1P2.setImage(cardImg2);
                    break;
                case 1:
                    img1Card2P1.setImage(cardImg1);
                    img2Card2P2.setImage(cardImg2);
                    break;
                case 2:
                    img1Card3P1.setImage(cardImg1);
                    img2Card3P2.setImage(cardImg2);
                    break;
            }
        }
    }

    private void determineWinner()
    {
        player1Name = tfPlayer1.getText();
        player2Name = tfPlayer2.getText();
        //1. Set both player hands into the ThreeCardBrag object, brag.
        game.setPlayerHands(hand1, hand2);
        //2. int winner = brag.getWinningHand();
        int winner = game.getWinningHand();
        //3. display the information that corresponds to the 4 cases., 0 - 3
        switch (winner)
        {
            case 0:// no winner
                resultsWinner = "No Winner";
                break;
            case 1:// player 1 wins
                player1Score++;
                resultsWinner = player1Name + " Wins";
                break;
            case 2:// player 2 wins
                player2Score++;
                resultsWinner = player2Name + " Wins";
                break;
            case 3:// tie
                resultsWinner = "Tie";
                break;
        }
    }

    private void displayResults()
    {
        taResults.setText(resultsWinner + "\n" +
                player1Name + " Score: " + player1Score + "\n" +
                player2Name + " Score: " + player2Score);
    }

    /*private void updateNames()
    {
        player1Name = tfPlayer1.getText();
        player2Name = tfPlayer2.getText();
    }*/
}