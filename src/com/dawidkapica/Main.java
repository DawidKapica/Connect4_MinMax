package com.dawidkapica;

import com.dawidkapica.ConsoleUI.Game;
import com.dawidkapica.engine.Connect4BetaAlpha;
import com.dawidkapica.engine.Connect4MinMax;

public class Main {

    public static void main(String[] args) {
//	Game game = new Game(new Connect4MinMax(7), new Connect4MinMax(3));
        Game game = new Game(new Connect4BetaAlpha(6));
        game.playerVsAi();
//        game.aiVsAi();
    }
}
