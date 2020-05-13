package com.dawidkapica.engine;

import com.dawidkapica.information.Board;
import com.dawidkapica.information.Node;

import java.util.ArrayList;

public class Connect4MinMax extends AlghoritmChoosePlace {

//    private int searchDepth;
    Connect4Fitness connect4Fitness = new Connect4Fitness();
    int maxColor = 0;
    int minColor = 0;


    public Connect4MinMax(int depth) {
        super(depth);
//        searchDepth = depth;
    }

    @Override
    public int choosePlace (Board board, int color) {
        if (color == -1) {
            maxColor = -1;
            minColor = 1;
        } else {
            maxColor = 1;
            minColor = -1;
        }

        Node node = new Node(board);
        max(node, 0);
        int indexToPlace = 0;

        node.setValue(Integer.MIN_VALUE);
        int value = node.getValue();
        for (int i = 0; i < node.getChild().size(); i++) {
            System.out.println(node.getChild().get(i).getValue());
            int nodeChildValue = 0;
            if (i == 3){
                nodeChildValue  = node.getChild().get(i).getValue() + 4;
            } else {
                nodeChildValue = node.getChild().get(i).getValue();
            }
            if (nodeChildValue > value && node.getBoard().checkIsFullColumn(i) == false) {
                value = nodeChildValue;
                indexToPlace = i;
            }
        }

        return indexToPlace;
    }


//    public int max(Node node, int depth) {
//
//        for (int i = 0; i < 7; i++) {
////            if (node.getBoard().checkIsFullColumn(i) == false) {
//                Node childNode = node.createChild(i, maxColor, false);
//                if (depth < this.depth) {
//                    min(childNode, depth + 1);
//                }
////            }
//        }
//
//        int score = Integer.MIN_VALUE;
//        for (Node nodeChild: node.getChild()) {
//            if (nodeChild.getValue() > score) {
//                score = nodeChild.getValue();
//            }
//        }
//        node.setValue(score);
//
//        return  score;
//    }
//
//    public int min(Node node, int depth) {
//
//
//        for (int i = 0; i < 7; i++) {
////            if (node.getBoard().checkIsFullColumn(i) == false) {
//                Node childNode = node.createChild(i, minColor, true);
//
//                if (depth < this.depth) {
//                    max(childNode, depth + 1);
//                }
////            }
//        }
//
//        int score = Integer.MAX_VALUE;
//        for (Node nodeChild: node.getChild()) {
//
//            if (nodeChild.getValue() < score) {
//                score = nodeChild.getValue();
//            }
//        }
//
//        node.setValue(score);
//        return  score;
//    }
//}





    public int max(Node node, int depth) {

        node.createAllChilds();
        int score = Integer.MIN_VALUE;

//        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), maxColor);
        for (int i = 0; i < node.getChild().size(); i++) {
            boolean isAdd = node.getChild().get(i).getBoard().putDisk(i, maxColor);
//            node.getChild().get(i).setValue(fitnesses.get(i));
            if (depth < this.depth && isAdd == true ) {
                node.getChild().get(i).setValue(min(node.getChild().get(i), depth+1));
            } else if(isAdd == true) {
//                ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), maxColor);
                node.getChild().get(i).setValue(connect4Fitness.calcScore(node.getChild().get(i).getBoard(), maxColor));
                if (node.getChild().get(i).getValue() >= 10000) {
                    score = node.getChild().get(i).getValue();
                    System.out.println(score);
                    node.setValue(score);
                    return score;
                }

            } else {
                node.getChild().get(i).setValue(Integer.MIN_VALUE);

            }
        }

        for (Node nodeChild: node.getChild()) {
            if (nodeChild.getValue() > score) {
                score = nodeChild.getValue();
            }
        }
        node.setValue(score);

        return  score;
    }

    //    int minNumber = 0;
    public int min(Node node, int depth) {
//        minNumber++;
//        System.out.println(minNumber);

        node.createAllChilds();
        int score = Integer.MAX_VALUE;

//        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), minColor);
        for (int i = 0; i < node.getChild().size(); i++) {
            boolean isAdd = node.getChild().get(i).getBoard().putDisk(i, minColor);
//            node.getChild().get(i).setValue(fitnesses.get(i)*-1);
            if (depth < this.depth && isAdd == true) {
                node.getChild().get(i).setValue(max(node.getChild().get(i), depth+1));
            } else if (isAdd == true) {
//                ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), minColor);
//                node.getChild().get(i).setValue(fitnesses.get(i)*-1);
                node.getChild().get(i).setValue(connect4Fitness.calcScore(node.getChild().get(i).getBoard(), minColor)*-1);
//                if (node.getChild().get(i).getValue() <= -10000) {
//                    score = node.getChild().get(i).getValue();
//                    node.setValue(score);
//                    return score;
//                }
            } else {
                node.getChild().get(i).setValue(Integer.MAX_VALUE);
            }
        }

        for (Node nodeChild: node.getChild()) {
            if (nodeChild.getValue() < score) {
                score = nodeChild.getValue();
            }
        }

        node.setValue(score);
        return  score;
    }
}
