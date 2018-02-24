package io.zipcoder.casino.games.blackjack;

import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.games.Card;
import io.zipcoder.casino.games.Deck;
import io.zipcoder.casino.games.Rank;
import io.zipcoder.casino.utils.IOHandler;

import java.util.ArrayList;
import java.util.Arrays;


public class BlackJack {

    public BlackJack(){}
    private int playerBet;
    static long playerWallet;
    private Player player;
    private ArrayList<Card> playerValue;
    private ArrayList<Card> dealerValue;
    private ArrayList<Card> splitHandLeft;
    private ArrayList<Card> splitHandRight;
    private int splitHandScoreLeft;
    private int splitHandScoreRight;
    private int playerHandScore;
    private int dealerHandScore;
    private int insuranceBet;

    Deck playingDeck;
    static String output;

    public BlackJack(Player player) {
        this.player = player;
    }

    public void play(Player player) {
    }


//    public static void welcomeToBlackJack() {
//        String intro = "Welcome to BlackJack!\n" +
//                "Press ANY KEY to start the game.\n";
//        output = IOHandler.promptForStringWithMessage(intro);
//        if (!output.equalsIgnoreCase(null)) {
//
//        }
//    }

    public void beginGame(){
        new BlackJack(playingDeck);
        displayPlayerHand();
        displayDealerHand();
        playerHitOrStand();
        comparable(dealerHandScore, playerHandScore);
    }

    public BlackJack(Deck playingDeck) {
        this.playingDeck = playingDeck;
        playerValue = new ArrayList<>();
        dealerValue = new ArrayList<>();
        splitHandLeft = new ArrayList<>();
        splitHandRight = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            playerValue.add(playingDeck.pull(1)[0]);
            dealerValue.add(playingDeck.pull(1)[0]);
        }

    }

    //    public Integer placeBet(){
//
//    }

    public Integer getPlayerCardValue() {
        playerHandScore = 0;
        for (Card pCard : playerValue) {
            playerHandScore += pCard.getIntValue();
        }
        return playerHandScore;
    }

    public Integer getDealerCardValue() {
        dealerHandScore = 0;
        for (Card dCard : dealerValue) {
            dealerHandScore += dCard.getIntValue();
        }
        return dealerHandScore;
    }

    public String displayPlayerHand() {
        String pHandCards = "";
        for (Card card : playerValue) {
            pHandCards += card.toString() + " ";
        }
        return pHandCards;
    }

    public String displayDealerHand() {
        String dHandCards = "";
        for (Card card : dealerValue) {
            dHandCards += card.toString() + " ";
        }
        return dHandCards;
    }

//    public boolean isHandSplitable() {
//            if (playerValue.get(0).getIntValue().equals(playerValue.get(1).getIntValue())) {
//                splitHandPrompt();
//            }
//
//        return false;
//    }

    public String comparable(int dealerHandScore, int playerHandScore) {
        Integer cardVal1 = dealerHandScore;
        Integer cardVal2 = playerHandScore;
        if (cardVal1 > cardVal2) {
            return "You lost!";
        }
        if (cardVal1 < cardVal2) {
            return "You won!";
        }
        return "You tied!";
    }

    public void splitHandPrompt() {
        String prompt = "Your split value is " + playerValue.get(0).getIntValue() + ". Do you want to split?";
        output = IOHandler.promptForStringWithMessage(prompt);
        if (output.equalsIgnoreCase("yes")) {
            System.out.println(displaySplitHands());
        }
        playerHitOrStand();
    }

    public String displaySplitHands() {
        splitHandLeft.addAll(Arrays.asList(playerValue.get(0), playingDeck.pull(1)[0]));
        splitHandRight.addAll(Arrays.asList(playerValue.get(0), playingDeck.pull(1)[0]));

        String leftHand = splitHandLeft.toString();
        String rightHand = splitHandRight.toString();
        String splitHands = leftHand + rightHand;
        return splitHands;
    }

    public void dealerHitOrStand() {
        while (dealerHandScore < 17) {
            dealerValue.add(playingDeck.pull(1)[0]);
            dealerHandScore = getDealerCardValue();
            System.out.println(dealerHandScore);
        }
        if (dealerHandScore > 17) {
            System.out.println(dealerHandScore);
        }
    }

    public String playerHitOrStand() {
        while (playerHandScore < 21) {
            String prompt = "Do you want to hit or stand?";
            output = IOHandler.promptForStringWithMessage(prompt);
            if (output.equalsIgnoreCase("hit")) {
                playerValue.add(playingDeck.pull(1)[0]);
                playerHandScore = getPlayerCardValue();
                System.out.println(playerHandScore);
            }
            if (output.equalsIgnoreCase("stand")) {
                playerHandScore = getPlayerCardValue();
                System.out.println(playerHandScore);
                return "You stand at " + playerHandScore;
            }
        }
        return bust();
    }

    public String bust() {
        return "Over 21, you bust!";
    }
//
//    public Integer playerLeftHandScore(){
//        splitHandScoreLeft = 0;
//        for (Card cardLeft : splitHandLeft){
//            splitHandScoreLeft += cardLeft.getIntValue();
//        }
//        return splitHandScoreLeft;
//    }
//    public Integer playerRightHandScore(){
//        splitHandScoreRight = 0;
//        for (Card cardRight : splitHandRight){
//            splitHandScoreRight += cardRight.getIntValue();
//        }
//        return splitHandScoreRight;
//    }
//    public void rightHitOrStand() {
//        while (splitHandScoreRight < 21) {
//            String prompt = "Do you want to hit?";
//            output = IOHandler.promptForStringWithMessage(prompt);
//            if (output.equalsIgnoreCase("hit")) {
//                splitHandRight.add(playingDeck.pull(1)[0]);
//                splitHandScoreRight = playerRightHandScore();
//                System.out.println(splitHandScoreRight);
//            }
//        }
//        bust();
//    }
//    public String leftHitOrStand(){
//        while(splitHandScoreLeft < 21){
//            splitHandLeft.add(playingDeck.pull(1)[0]);
//            splitHandScoreLeft = playerLeftHandScore();
//            System.out.println(splitHandScoreLeft);
//        }
//        return bust();
//    }
//
//    public void acePropertiesForDealer() {
//        if (dealerValue.get(0).getRank().equals(Rank.ACE)) {
//            String prompt = "The dealer has an Ace, do you want to put down insurance?\n" +
//                    " Yes or No";
//            output = IOHandler.promptForStringWithMessage("Yes");
//            if (output.equalsIgnoreCase("Yes")) {
//                System.out.println("You put down insurance");
//            }
//            displayDealerHand();
//
//        }
//    }

        public void blackJackProperties () {
            /**
             * BlackJack is any combination of Ace and 10, Jack, Queen, King on first deal.
             */
        }

        public void bettingRules () {
            /**
             * Before cards deal, players must make bet.
             * A regular win returns the bet 1x.
             * A lose returns -1x bet.
             * A natural BlackJack gives the bet 1.5x.
             */
        }

//        public static void main (String[]args){
//
//            String output;
//            Deck playingDeck = new Deck();
//            BlackJack blackJack = new BlackJack(playingDeck);
//            int playerBet;
//            long playerWallet;
//            ArrayList<Card> playerValue;
//            ArrayList<Card> dealerValue;
//            int playerHandScore;
//            int dealerHandScore;
//            int insuranceBet;
//            Integer splitNum = 0;
//
//            playerValue = new ArrayList<>();
//            dealerValue = new ArrayList<>();
//            for (int i = 0; i < 2; i++) {
//                playerValue.add(playingDeck.pull(1)[0]);
//                dealerValue.add(playingDeck.pull(1)[0]);
//            }
//            System.out.println(blackJack.displayPlayerHand());
//            System.out.println(blackJack.getPlayerCardValue());
////        System.out.println(blackJack.playerHitOrStand());
//            //     System.out.println(blackJack.isHandSplitable());
//        }
    }
//
//    public void putInsurance(){
//
//    }


//    public void playerDoubleDown(){
//
//    }


//    public void isBlackJack(){
//
//    }
//    public void whoWon(){
//
//    }

//    public void tie(){
//
//    }
//    public void wagerMultiplier(){
//
//    }
