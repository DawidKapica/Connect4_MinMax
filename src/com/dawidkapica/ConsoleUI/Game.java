package com.dawidkapica.ConsoleUI;

import com.dawidkapica.engine.AlghoritmChoosePlace;
import com.dawidkapica.engine.Connect4Fitness;
import com.dawidkapica.engine.Connect4Logic;
import com.dawidkapica.engine.Connect4MinMax;
import com.dawidkapica.information.Board;

import java.util.Scanner;

public class Game {

    AlghoritmChoosePlace alghoritmFirstChoosePlace;
    AlghoritmChoosePlace alghoritmSecondChoosePlace;

    public Game (AlghoritmChoosePlace alghoritmFirstChoosePlace) {
        this.alghoritmFirstChoosePlace = alghoritmFirstChoosePlace;
    }

    public Game (AlghoritmChoosePlace alghoritmFirstChoosePlace, AlghoritmChoosePlace alghoritmSecondChoosePlace) {
        this.alghoritmFirstChoosePlace = alghoritmFirstChoosePlace;
        this.alghoritmSecondChoosePlace = alghoritmSecondChoosePlace;
    }

//    public void playerVsPlayer() {
//        Board board = new Board();
//
//        Connect4Logic connect4Logic = new Connect4Logic(board);
//
//        Connect4Fitness connect4Fitness = new Connect4Fitness();
//
//        System.out.println(board.toString());
//
//        Scanner scanner = new Scanner(System.in);
//
//        int column = scanner.nextInt();
//
//        connect4Logic.addField(1, 1);
//        connect4Logic.addField(4, -1);
//        connect4Logic.addField(0, 1);
//        connect4Logic.addField(4, -1);
//        connect4Logic.addField(column, 1);
////        connect4Logic.addField(1, 1);
//
//        System.out.println(board.toString());
//
////        System.out.println(connect4Fitness.calcScoreOnPosition(board, 5, 0, 0, 1, 1));
//        System.out.println();
////        System.out.println(connect4Fitness.calcScore(board, -1));
//        System.out.println();
//        System.out.println(connect4Fitness.calculateFitnesses(board, -1));
//
//
////        Connect4MinMax connect4MinMax = new Connect4MinMax(5);
//
//        int i = alghoritmFirstChoosePlace.choosePlace(board, 1);
//        System.out.println(i);
////        connect4Fitness.calcScore(board, 1);
//    }

    public void playerVsAi() {
        boolean hasWinner = false;
        boolean isFull = false;
        int winnerColor = 1;

        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Connect4Logic connect4Logic = new Connect4Logic(board);
        Connect4Fitness connect4Fitness = new Connect4Fitness();
//        Connect4MinMax connect4MinMax = new Connect4MinMax(level);

        while (hasWinner == false && isFull == false) {
            System.out.println("TWOJ RUCH");
            System.out.println(board.toString());
            int columnPlayer = -1;

            while (columnPlayer < 0 || columnPlayer > 6) {
                columnPlayer = scanner.nextInt();
                if (columnPlayer < 0 || columnPlayer > 6) {
                    System.out.println("bledna wartosc, podaj ponownie");
                }
            }
            int rowPlayer = connect4Logic.addField(columnPlayer, -1);
//            System.out.println(rowPlayer);
//            System.out.println(columnPlayer);

            isFull = connect4Logic.getBoard().checkIsFull();

            hasWinner = (connect4Logic.checkWin(rowPlayer, columnPlayer));

            if (hasWinner == false) {

                int columnAI = alghoritmFirstChoosePlace.choosePlace(board, 1);
                System.out.println("RUCH AI");
                System.out.println(board.toString());
                int rowAI = connect4Logic.addField(columnAI, 1);

                hasWinner = connect4Logic.checkWin(rowAI, columnAI);

            } else {
                winnerColor = -1;
            }


        }

        System.out.println("KONIEC ZWYCIĘZCA: " + winnerColor);
        System.out.println(board.toString());
    }

    public void aiVsAi() {
        boolean hasWinner = false;
        boolean isFull = false;
        int winnerColor = 1;

        boolean firstAiMove = true;

        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        Connect4Logic connect4Logic = new Connect4Logic(board);
        Connect4Fitness connect4Fitness = new Connect4Fitness();
//        Connect4MinMax connect4MinMax = new Connect4MinMax(level);

        while (hasWinner == false && isFull == false) {

            if (hasWinner == false && firstAiMove == true) {

                int columnAI = alghoritmFirstChoosePlace.choosePlace(board, -1);
                System.out.println("RUCH AI 1");
                System.out.println(board.toString());
                int rowAI = connect4Logic.addField(columnAI, -1);

                hasWinner = connect4Logic.checkWin(rowAI, columnAI);
                firstAiMove = !firstAiMove;

            } else {
                winnerColor = 1;
            }
            isFull = connect4Logic.getBoard().checkIsFull();


            if (hasWinner == false && firstAiMove == false) {

                int columnAI = alghoritmSecondChoosePlace.choosePlace(board, 1);
                System.out.println("RUCH AI 2");
                System.out.println(board.toString());
                int rowAI = connect4Logic.addField(columnAI, 1);

                hasWinner = connect4Logic.checkWin(rowAI, columnAI);

                if (hasWinner == true) {
                    winnerColor = 1;
                }

                firstAiMove = !firstAiMove;

            } else {
                winnerColor = -1;
            }


        }

        System.out.println("KONIEC ZWYCIĘZCA: " + winnerColor);
        System.out.println(board.toString());
    }
}
