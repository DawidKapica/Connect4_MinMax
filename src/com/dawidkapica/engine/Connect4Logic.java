package com.dawidkapica.engine;

import com.dawidkapica.information.Board;

public class Connect4Logic {

    private Board board = null;

    public Connect4Logic(Board board) {
        this.board = board;
    }

    public int addField(int columnIndex, int color) {
        if (board.getFieldValue(0, columnIndex) != 0) {
            return -1;
        } else {
            for (int i = 5; i >= 0; i--) {
                if (board.getFieldValue(i, columnIndex) == 0) {
                    board.setFieldValue(i, columnIndex, color);
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean checkWin(int indexH, int indexV) {
        if (check4LogicLine(indexH, indexV) >= 4) {
            return true;
        } else if (check4LogicColumn(indexH, indexV) >= 4) {
            return true;
        } else if (check4LogicDiagonallyLeftDownToRightUp(indexH, indexV) >= 4 ) {
            return true;
        } else if (check4LogicDiagonallyLeftUpToRightDown(indexH, indexV) >= 4) {
            return true;
        } else {
            return false;
        }
    }

    private int check4LogicLine(int indexH, int indexV) {
        boolean leftIndexCorrect = true;
        int leftIndexStep = -1;
        boolean rightIndexCorrect = true;
        int rightIndexStep = 1;
        int pointInLine = 1;

        while (leftIndexCorrect || rightIndexCorrect) {
            if (leftIndexCorrect && (indexV + leftIndexStep) >= 0) {
                if(board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH, indexV + leftIndexStep)) {
                    leftIndexStep = leftIndexStep -1;
                    pointInLine++;
                } else {
                    leftIndexCorrect = false;
                }
            } else {
                leftIndexCorrect = false;
            }
            if (rightIndexCorrect && (indexV + rightIndexStep) <= 6) {
                if(board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH, indexV + rightIndexStep)) {
                    rightIndexStep = rightIndexStep + 1;
                    pointInLine++;
                } else {
                    rightIndexCorrect = false;
                }
            } else {
                rightIndexCorrect = false;
            }
        }

        return pointInLine;
    }

    private int check4LogicColumn(int indexH, int indexV) {
        boolean upIndexCorrect = true;
        int upIndexStep = -1;
        boolean downIndexCorrect = true;
        int downIndexStep = 1;

        int pointInColumn = 1;

        while (upIndexCorrect && downIndexCorrect) {

            if (downIndexCorrect && (indexH + downIndexStep) <= 5) {
                if(board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + downIndexStep, indexV)) {
                    downIndexStep = downIndexStep + 1;
                    pointInColumn++;
                } else {
                    downIndexCorrect = false;
                }
            } else {
                downIndexCorrect = false;
            }
        }

        downIndexCorrect = true;
        upIndexCorrect = true;

        while (upIndexCorrect && downIndexCorrect) {

            if (upIndexCorrect && (indexH + upIndexStep) >= 0) {
                if(board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + upIndexStep, indexV)) {
                    upIndexStep = upIndexStep - 1;
                    pointInColumn++;
                } else {
                    upIndexCorrect = false;
                }
            } else {
                upIndexCorrect = false;
            }
        }

        return pointInColumn;
    }

    private int check4LogicDiagonallyLeftDownToRightUp(int indexH, int indexV) {
        boolean leftDownIndexCorrect = true;
        boolean rightUpIndexCorrect = true;

        int upDownIndexStep = -1;
        int rightLeftIndexStep = 1;

        int pointInLeftDownToRightUp = 1;

        while (leftDownIndexCorrect && rightUpIndexCorrect) {
            if(leftDownIndexCorrect && (indexH + upDownIndexStep) >= 0 && (indexV + (rightLeftIndexStep) <= 6)) {
                if (board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + upDownIndexStep, indexV + rightLeftIndexStep)) {
                    upDownIndexStep = upDownIndexStep - 1;
                    rightLeftIndexStep++;
                    pointInLeftDownToRightUp++;
                } else {
                    leftDownIndexCorrect = false;
                }
            } else {
                leftDownIndexCorrect = false;
            }
        }

        upDownIndexStep = 1;
        rightLeftIndexStep = -1;
        leftDownIndexCorrect = true;
        rightUpIndexCorrect = true;

        while (leftDownIndexCorrect && rightUpIndexCorrect) {
            if(leftDownIndexCorrect && (indexH + upDownIndexStep) <= 5 && (indexV + (rightLeftIndexStep) >= 0)) {
                if (board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + upDownIndexStep, indexV + rightLeftIndexStep)) {
                    upDownIndexStep = upDownIndexStep + 1;
                    rightLeftIndexStep--;
                    pointInLeftDownToRightUp++;
                } else {
                    leftDownIndexCorrect = false;
                }
            } else {
                leftDownIndexCorrect = false;
            }
        }

        return pointInLeftDownToRightUp;
    }

    private int check4LogicDiagonallyLeftUpToRightDown(int indexH, int indexV) {
        boolean leftUpIndexCorrect = true;
        boolean rightDownIndexCorrect = true;

        int upDownIndexStep = -1;
        int rightLeftIndexStep = -1;

        int pointInLeftUpToRightDown = 1;

        while (leftUpIndexCorrect && rightDownIndexCorrect) {
            if(leftUpIndexCorrect && (indexH + upDownIndexStep) >= 0 && (indexV + (rightLeftIndexStep) >= 0)) {
                if (board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + upDownIndexStep, indexV + rightLeftIndexStep)) {
                    upDownIndexStep = upDownIndexStep - 1;
                    rightLeftIndexStep--;
                    pointInLeftUpToRightDown++;
                } else {
                    leftUpIndexCorrect = false;
                }
            } else {
                leftUpIndexCorrect = false;
            }

        }

        leftUpIndexCorrect = true;
        rightDownIndexCorrect = true;

        upDownIndexStep = 1;
        rightLeftIndexStep = 1;

        while (leftUpIndexCorrect && rightDownIndexCorrect) {
            if(leftUpIndexCorrect && (indexH + upDownIndexStep) <= 5 && (indexV + (rightLeftIndexStep) <= 6)) {
                if (board.getFieldValue(indexH, indexV) == board.getFieldValue(indexH + upDownIndexStep, indexV + rightLeftIndexStep)) {
                    upDownIndexStep = upDownIndexStep + 1;
                    rightLeftIndexStep++;
                    pointInLeftUpToRightDown++;
                } else {
                    leftUpIndexCorrect = false;
                }
            } else {
                leftUpIndexCorrect = false;
            }

        }

        return pointInLeftUpToRightDown;
    }

    public Board getBoard () {
        return board;
    }

    public void setBoard (Board board) {
        this.board = board;
    }
}
