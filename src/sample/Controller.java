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
import javafx.scene.text.Font;
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
    private int score1 , score2;
    int dealCount = 0; //MS add counter for number of deals
    int winner;

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
        //MS increment deal hand
        dealCount ++;
        //MS set player names on first deal
        if(dealCount <= 1){
            if(tfPlayer1.getText().equals("It's a Tie!") || tfPlayer1.getText().equals("")) {
                player1Name = "Player 1";
                tfPlayer1.setText(player1Name);
            } else {
                player1Name = tfPlayer1.getText();
            }
            if(tfPlayer2.getText().equals("It's a Tie!") || tfPlayer2.getText().equals("")) {
                player2Name = "Player 2";
                tfPlayer2.setText(player2Name);
            } else {
                player2Name = tfPlayer2.getText();
            }
        } else {
            //MS return tf values to player names on each deal
            tfPlayer1.setFont(Font.font ("System", 12));
            tfPlayer2.setFont(Font.font ("System", 12));
            tfPlayer1.setText(player1Name);
            tfPlayer2.setText(player2Name);

        }
        if (dealCount >= 8) //MS check on how many times cards dealt
        {
            bntDealCards.setDisable(true);
           /* if (score1 > score2)
            {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n"+ player2Name + " Won :" +score2 + " times" +"\n" + player1Name +" is the winner" +
                        "\nPress OK to continue");
            }
            else if (score2 > score1)
            {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n"+ player2Name + " Won :" + score2 + " times"+ "\n" + player2Name + " is the winner" +
                        "\nPress OK to continue");
            }else {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n" + player2Name + " Won :" + score2 + " times" + "\n" + "No winner is a tie" +
                        "\nPress OK to continue");
            }*/
        }
        dealTheCards();
        determineWinner();

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

        //MS move JOPtionPane on final hand to check winner button
        if (bntNoneDealAgain.getText().equals("Display Overall Results")){
            if (score1 > score2) {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n" + player2Name + " Won :" + score2 + " times" + "\n" + player1Name + " is the winner" +
                        "\nPress OK to continue");
            } else if (score2 > score1) {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n" + player2Name + " Won :" + score2 + " times" + "\n" + player2Name + " is the winner" +
                        "\nPress OK to continue");
            } else {
                JOptionPane.showMessageDialog(null, player1Name + " Won: " + score1 +
                        " times\n" + player2Name + " Won :" + score2 + " times" + "\n" + "No winner is a tie" +
                        "\nPress OK to continue");
            }
        }
        //MS change button option to display the overall winner in a JOptionPane
        if (dealCount >= 8) //MS check on how many times cards dealt
        {
            bntNoneDealAgain.setText("Display Overall Results");
        }


    }

    @FXML
    void PlayAgainOnAction(ActionEvent event) {
        deck = new CardDeck();
        game = new ThreeCardBrag();
        dealCount = 0; //MS set deal count to zero on each new game
        player1Score = 0;
        player2Score = 0;
        resultsWinner = "No game has been played.";
        bntDealCards.setDisable(false);
        taResults.clear();
        //MS reset fonts and player names
        tfPlayer1.setFont(Font.font ("System", 12));
        tfPlayer2.setFont(Font.font ("System", 12));
        tfPlayer1.clear();
        tfPlayer2.clear();
        bntNoneDealAgain.setText("Display Winner");
        winner = 0;

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
        //MS Move set players to first deal
        //player1Name = tfPlayer1.getText();
        //player2Name = tfPlayer2.getText();
        //1. Set both player hands into the ThreeCardBrag object, brag.
        game.setPlayerHands(hand1, hand2);
        //2. int winner = brag.getWinningHand();
        winner = game.getWinningHand();
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
        score1 =+ player1Score;
        score2 =+ player2Score;
    }

    private void displayResults()
    {
        taResults.setText(resultsWinner + "\n\n" +
                player1Name + " Score: " + player1Score + "\n" +
                player2Name + " Score: " + player2Score);

        //MS set textfields to react to win/loss/tie
        switch (winner)
        {
            case 0:// no winner
                tfPlayer1.setText("It's a Tie!");
                tfPlayer2.setText("It's a Tie!");
                break;
            case 1:// player 1 wins
                tfPlayer1.setFont(Font.font ("Serif", 16));
                break;
            case 2:// player 2 wins
                tfPlayer2.setFont(Font.font ("Serif", 16));
                break;
            case 3:// tie
                tfPlayer1.setText("It's a Tie!");
                tfPlayer2.setText("It's a Tie!");
                break;
        }
    }

    /*private void updateNames()
    {
        player1Name = tfPlayer1.getText();
        player2Name = tfPlayer2.getText();
    }*/
}