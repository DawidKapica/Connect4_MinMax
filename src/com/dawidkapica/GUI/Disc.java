package com.dawidkapica.GUI;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Disc extends Circle {
    private final boolean red;
    public static final int TILE_SIZE = 80;

    public Disc(boolean isRed) {
        super(TILE_SIZE / 2, isRed ? Color.RED : Color.YELLOW);
        red = isRed;

        setCenterX(TILE_SIZE / 2);
        setCenterY(TILE_SIZE / 2);
    }

    public boolean isRed () {
        return red;
    }
}
