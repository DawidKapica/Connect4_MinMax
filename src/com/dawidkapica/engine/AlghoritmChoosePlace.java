package com.dawidkapica.engine;

import com.dawidkapica.information.Board;

public abstract class AlghoritmChoosePlace {

    int depth;

    AlghoritmChoosePlace(int depth) {
        this.depth = depth;
    }

    public abstract int choosePlace (Board board, int color);
}