package com.dawidkapica.engine;

import com.dawidkapica.information.Board;
import com.dawidkapica.information.Node;

import java.util.ArrayList;

public class Connect4BetaAlpha extends AlghoritmChoosePlace {

    //    private int searchDepth;
    Connect4Fitness connect4Fitness = new Connect4Fitness();
    int maxColor = 0;
    int minColor = 0;


    public Connect4BetaAlpha (int depth) {
        super(depth);
//        searchDepth = depth;
    }

    @Override
    public int choosePlace (Board board, int color) {
        if (color == - 1) {
            maxColor = - 1;
            minColor = 1;
        } else {
            maxColor = 1;
            minColor = - 1;
        }

        Node node = new Node(board);
        max(node, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        int indexToPlace = 0;

        node.setValue(Integer.MIN_VALUE);
        int value = node.getValue();
        for (int i = 0; i < node.getChild().size(); i++) {
            System.out.println(node.getChild().get(i).getValue());

//            if (node.getChild().get(i).getValue() > value && node.getBoard().checkIsFullColumn(i) == false) {
                int nodeChildValue = 0;
                if (i == 3){
//                    System.out.println("i" + i);

                    nodeChildValue  = node.getChild().get(i).getValue() + 4;
                } else {
                    nodeChildValue = node.getChild().get(i).getValue();
                }
                if (nodeChildValue > value && node.getBoard().checkIsFullColumn(i) == false) {
//                    System.out.println(node.getChild().get(i).getValue());

                    value = nodeChildValue;
                    indexToPlace = i;
                }
//            }
        }
        System.out.println(indexToPlace);
        return indexToPlace;
    }

//    int alpha = Integer.MIN_VALUE;
//    int beta = Integer.MAX_VALUE;


    public int max (Node node, int depth, int alpha, int beta) {

        node.createAllChilds();

        int score = Integer.MIN_VALUE;

        for (int i = 0; i < node.getChild().size(); i++) {
            boolean isAdd = node.getChild().get(i).getBoard().putDisk(i, maxColor);
            if (depth < this.depth && isAdd == true) {
                node.getChild().get(i).setValue(min(node.getChild().get(i), depth + 1, alpha, beta));
            } else if (isAdd == true) {
                node.getChild().get(i).setValue(connect4Fitness.calcScore(node.getChild().get(i).getBoard(), maxColor));

            } else {
                node.getChild().get(i).setValue(Integer.MIN_VALUE);

            }

            if (node.getChild().get(i).getValue() > score) {
                score = node.getChild().get(i).getValue();
                alpha = Math.max(alpha, score);
                if (beta <= alpha) {
                    node.setValue(score);
                    return score;
                }
            }
        }

        node.setValue(score);

        return score;
    }


    public int min (Node node, int depth, int alpha, int beta) {

        node.createAllChilds();

        int score = Integer.MAX_VALUE;


        for (int i = 0; i < node.getChild().size(); i++) {
            boolean isAdd = node.getChild().get(i).getBoard().putDisk(i, minColor);
            if (depth < this.depth && isAdd == true) {
                node.getChild().get(i).setValue(max(node.getChild().get(i), depth + 1, alpha, beta));
            } else if (isAdd == true) {
                node.getChild().get(i).setValue(connect4Fitness.calcScore(node.getChild().get(i).getBoard(), minColor) * -1);
            } else {
                node.getChild().get(i).setValue(Integer.MAX_VALUE);
            }

            if (node.getChild().get(i).getValue() < score) {
                score = node.getChild().get(i).getValue();
                beta = Math.min(beta, score);
                if (beta <= alpha) {
                    node.setValue(score);
                    return score;
                }
            }
        }

        node.setValue(score);
        return score;
    }
}

//    public int max(Node node, int depth) {
//
//        for (int i = 0; i < 7; i++) {
////            if (node.getBoard().checkIsFullColumn(i) == false) {
//                 Node childNode = node.createChild(i, maxColor, false);
//                if (depth < this.depth) {
//                    min(node.getChild().get(i), depth + 1);
//                }
////            }
//        }
//
//        int score = Integer.MIN_VALUE;
////        System.out.println(node.getChild().size());
//        for (Node nodeChild: node.getChild()) {
//            nodeChild.setValue(connect4Fitness.calcScore(nodeChild.getBoard(), maxColor));
//            if (nodeChild.getValue() > score) {
//                score = nodeChild.getValue();
//                alpha = Math.max(alpha, score);
//                if (beta <= alpha) {
////                    System.out.println("CHOCIAZ RAZ");
//                    node.setValue(score);
//                    return score;
//                }
//            }
//        }
//        node.setValue(score);
//
//        return  score;
//    }
//
//    public int min(Node node, int depth) {
//
//        for (int i = 0; i < 7; i++) {
////            if (node.getBoard().checkIsFullColumn(i) == false) {
//                 Node childNode = new Node(node.createChild(i, minColor, true));
//                if (depth < this.depth) {
//                    max(node.getChild().get(i), depth + 1);
//                }
////            }
//        }
//
//        int score = Integer.MAX_VALUE;
//        for (Node nodeChild: node.getChild()) {
//            nodeChild.setValue(connect4Fitness.calcScore(nodeChild.getBoard(), minColor)*-1);
//            if (nodeChild.getValue() < score) {
//                score = nodeChild.getValue();
//                beta = Math.min(beta, score);
//                if (beta <= alpha) {
////                    System.out.println("Chociaz raz");
//                    node.setValue(score);
//                    return score;
//                }
//            }
//        }
//
//        node.setValue(score);
//        return  score;
//    }
//}



//    public int max(Node node, int depth, int alpha, int beta) {
//
//        node.createAllChilds();
//        Node nodeCopy = new Node(node);
//        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), maxColor);
//        for (int i = 0; i< node.getChild().size(); i++) {
//            node.getChild().get(i).getBoard().putDisk(i, maxColor);
//            node.getChild().get(i).setValue(fitnesses.get(i));
//            if (depth < this.depth) {
//                min(node.getChild().get(i), depth+1, alpha, beta);
//            }
//        }
//
//        int score = Integer.MIN_VALUE;
//        for (Node nodeChild: node.getChild()) {
//            nodeChild.setValue(connect4Fitness.calcScore(nodeChild.getBoard(), maxColor));
//            if (nodeChild.getValue() > score) {
//                score = nodeChild.getValue();
//                alpha = Math.max(alpha, score);
//                if (beta <= alpha) {
//                                        System.out.println("CHOCIAZ RAZ");
//
//                    node.setValue(score);
//                    return score;
//                }
//            }
//        }
//
//        node.setValue(score);
//
//        return  score;
//    }
//
//    public int min(Node node, int depth, int alpha, int beta) {
//
//        node.createAllChilds();
//        Node nodeCopy = new Node(node);
//        ArrayList<Integer> fitnesses = connect4Fitness.calculateFitnesses(node.getBoard(), minColor);
//        for (int i = 0; i< node.getChild().size(); i++) {
//            node.getChild().get(i).getBoard().putDisk(i, minColor);
//            node.getChild().get(i).setValue(fitnesses.get(i)*-1);
//            if (depth < this.depth) {
//                max(node.getChild().get(i), depth+1, alpha, beta);
//            }
//        }
//
//        int score = Integer.MAX_VALUE;
//        for (Node nodeChild: node.getChild()) {
//            nodeChild.setValue(connect4Fitness.calcScore(nodeChild.getBoard(), maxColor)*-1);
//
//            if (nodeChild.getValue() < score) {
//                score = nodeChild.getValue();
//                beta = Math.min(beta, score);
//                if (beta <= alpha) {
//                    System.out.println("CHOCIAZ RAZ");
//
//                    node.setValue(score);
//                    return score;
//                }
//            }
//        }
//
//        node.setValue(score);
//        return  score;
//    }
//}
