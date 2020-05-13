package com.dawidkapica.GUI;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class Connect4GUI extends Thread {

    //Kolor czerwony = 1, żółty = -1

    private static final int COLOR_RED = 1;
    private static final int COLOR_YELLOW = -1;
    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    private Disc[][] grid = new Disc[COLUMNS][ROWS];
    private Pane discPane = new Pane();
    private boolean redMove = false;

    private boolean humanTurn = true;
    private boolean isAiVsAi;
    private int ai1Depth;
    private int ai2Depth;

    private boolean humanStart = true;

    Text text = new Text("Zwycięzca: ---");

    GameTurnEngineForGUI gameGui = new GameTurnEngineForGUI();

    public Connect4GUI (boolean isAiVsAi, boolean humanStart, int ai1Depth) {
        this.isAiVsAi = isAiVsAi;
        this.ai1Depth = ai1Depth;
//        this.humanStart = humanStart;
//        humanTurn = humanStart;
//        redMove = true;

    }

    public Connect4GUI (boolean isAiVsAi, int ai1Depth, int ai2Depth) {
        this.isAiVsAi = isAiVsAi;
        this.ai1Depth = ai1Depth;
        this.ai2Depth = ai2Depth;
    }

    public void startGameMethod () {
        Stage window = new Stage();
        Pane paneRoot = new Pane();

        paneRoot.getChildren().add(discPane);
        paneRoot.setBackground(new Background(new BackgroundFill(Color.rgb(200, 200, 200), CornerRadii.EMPTY, Insets.EMPTY)));
        Scene game = new Scene(paneRoot);
        window.setResizable(false);

        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE + 50);


        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX((j * (TILE_SIZE + 5) + TILE_SIZE / 4));
                circle.setTranslateY((i * (TILE_SIZE + 5) + TILE_SIZE / 4));

                shape = Shape.subtract(shape, circle);

            }
        }

        text.setTranslateX( 100);
        text.setTranslateY( COLUMNS * TILE_SIZE + 20);
        text.setFont(Font.font ("Verdana", 20));
        text.setFill(Color.WHITE);
//        shape = Shape.subtract(shape, text);

        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.GREY);

        shape.setEffect(lighting);



        paneRoot.getChildren().add(shape);
        paneRoot.getChildren().add(text);


        if (! isAiVsAi) {
            paneRoot.getChildren().addAll(makeColums());

        }

        window.setScene(game);
        window.show();


        if (isAiVsAi) {
            aiPlaceDisc(ai1Depth);
        }
    }


    private List<Rectangle> makeColums () {
        List<Rectangle> list = new ArrayList<>();

        for (int i = 0; i < COLUMNS; i++) {
            Rectangle rectangle = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rectangle.setTranslateX(i * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rectangle.setFill(Color.TRANSPARENT);

            rectangle.setOnMouseEntered(e -> rectangle.setFill(Color.rgb(200, 200, 50, 0.3)));
            rectangle.setOnMouseExited(e -> rectangle.setFill(Color.TRANSPARENT));

            final int column = i;

            rectangle.setOnMouseClicked(e -> placeHumanDisc(new Disc(redMove), column));

            list.add(rectangle);
        }

        return list;
    }

    public void placeHumanDisc (Disc disc, int column) {

        if (gameGui.isHasWinner() == false) {
            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask () {
                    return new Task<Void>() {
                        @Override
                        protected Void call () throws Exception {
                            //Background work
                            final CountDownLatch latch = new CountDownLatch(1);

                            if (humanTurn) {
                                humanTurn = ! humanTurn;
                                gameGui.putDiscToEngine(column, - 1);

                            }
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run () {
                                    try {
                                        //FX Stuff done here
                                            placeDisc(disc, column);
                                    } finally {
                                        latch.countDown();
                                    }
                                }
                            });
                            latch.await();
                            if (gameGui.isHasWinner() == false) {
                                aiPlaceDisc(ai1Depth);
                            } else {
                                text.setText("Zwycięzca: gracz");
                            }
                            return null;
                        }
                    };
                }
            };

            service.start();
        }


    }

    boolean ai1Turn = true;

    public void aiPlaceDisc (int depth) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask () {
                return new Task<Void>() {
                    @Override
                    protected Void call () throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);

                        int color = 0;
                        if (redMove == true) {
                            color = 1;
                        } else {
                            color = - 1;
                        }

                        final int choosenColor = color;
                        final int uiColumn = gameGui.aiTurn(depth, choosenColor);
                        System.out.println(uiColumn);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run () {
                                try {
                                    //FX Stuff done here
                                    placeDisc(new Disc(redMove), uiColumn);
                                } finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        if (gameGui.isHasWinner() == false && isAiVsAi == true) {
                            ai1Turn = !ai1Turn;
                            if (ai1Turn) {

                                aiPlaceDisc(ai1Depth);
                            } else {
                                aiPlaceDisc(ai2Depth);
                            }
                        } else if (gameGui.isHasWinner() == false && isAiVsAi == false) {
                            humanTurn = !humanTurn;
                        } else if (gameGui.isHasWinner() == true) {
                            if (redMove) {
                                text.setText("Zwycięzca: AI zolty");
                            } else  {
                                text.setText("Zwycięzca: AI czerwony");
                            }
                        }
                        return null;
                    }
                };
            }
        };
        service.start();
    }


    public void placeDisc (Disc disc, int column) {

        int color = 0;
        if (disc.isRed() == true) {
            color = COLOR_RED;
        } else {
            color = COLOR_YELLOW;
        }

        int row = ROWS - 1;
        boolean findPlace = false;

        while (row >= 0 && findPlace == false) {
            if (Optional.ofNullable(grid[column][row]).isPresent()) {
                row--;
            } else {
                findPlace = true;
            }
        }


        grid[column][row] = disc;
        discPane.getChildren().add(disc);

        redMove = ! redMove;
        disc.setTranslateX((column * (TILE_SIZE + 5) + TILE_SIZE / 4));
        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY((row * (TILE_SIZE + 5) + TILE_SIZE / 4));

        animation.play();
    }

}