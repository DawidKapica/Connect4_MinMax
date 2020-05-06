package com.dawidkapica;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;


import static javafx.application.Application.launch;

public class MainGUI extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    Stage window;
    Scene mainMenu, gameScreen;
    Pane boardPane = new Pane();

    ToggleGroup difficultyAI1;
    ToggleGroup difficultyAI2;

    RadioButton randomDifficultyAI1;
    RadioButton basicDifficultyAI1;

    RadioButton randomDifficultyAI2;
    RadioButton basicDifficultyAI2;

    boolean AIvsAI = false;
    boolean AIvsHuman = false;

    Label topLabel = new Label("Welcome to JavaFX Connect-4");



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        window = primaryStage;

        boardPane.setBackground(new Background(new BackgroundFill(Color.rgb(30, 143, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        Label pickOponent = new Label("Pick your opponent");

        Label pickDifficulty1 = new Label("AI1 difficulty:");
        Label pickDifficulty2 = new Label("AI2 difficulty:");


        //Checks for mouse click on the button and starts the game
        Button startGameHuman = new Button("AI1 vs Human");
        startGameHuman.setOnAction(e -> {
            startGameMethod();
        });
        Button startGameComputer = new Button("AI1 vs AI2");
        startGameComputer.setOnAction(e -> {
//            againstComputer = true;
            startGameMethod();
        });

        difficultyAI1 = new ToggleGroup();
        randomDifficultyAI1 = new RadioButton("Random1");
        randomDifficultyAI1.setToggleGroup(difficultyAI1);
        randomDifficultyAI1.setSelected(true);
        basicDifficultyAI1 = new RadioButton("Basic1");
        basicDifficultyAI1.setToggleGroup(difficultyAI1);

        difficultyAI2 = new ToggleGroup();
        randomDifficultyAI2 = new RadioButton("Random2");
        randomDifficultyAI2.setToggleGroup(difficultyAI2);
        randomDifficultyAI2.setSelected(true);
        basicDifficultyAI2 = new RadioButton("Basic2");
        basicDifficultyAI2.setToggleGroup(difficultyAI2);


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Add Main Menu Objects to GridPane
        GridPane.setConstraints(topLabel, 1, 1, 3, 1);
        GridPane.setConstraints(pickOponent, 1, 6, 3, 1);
        GridPane.setHalignment(pickOponent, HPos.CENTER);

        GridPane.setConstraints(startGameHuman, 1, 7);
        GridPane.setConstraints(startGameComputer, 3, 7);
        GridPane.setHalignment(pickDifficulty1, HPos.RIGHT);
        GridPane.setConstraints(pickDifficulty1, 1, 9);
        GridPane.setHalignment(pickDifficulty2, HPos.RIGHT);
        GridPane.setConstraints(pickDifficulty2, 1, 11);
        GridPane.setConstraints(randomDifficultyAI1, 3, 9);
        GridPane.setConstraints(basicDifficultyAI1, 5, 9);
        GridPane.setConstraints(randomDifficultyAI2, 3, 11);
        GridPane.setConstraints(basicDifficultyAI2, 5, 11);
        grid.getChildren().addAll(topLabel, pickOponent, startGameHuman, startGameComputer,
                pickDifficulty1, pickDifficulty2, randomDifficultyAI1, basicDifficultyAI1, randomDifficultyAI2, basicDifficultyAI2);

        window.setTitle("JavaFX Connect 4");
        mainMenu = new Scene(grid, 500, 500);
        window.setScene(mainMenu);
        window.show();
    }

    private void startGameMethod() {

    }

//    @Override
//    public void start (Stage stage) throws Exception {
//        stage.setScene(new Scene(createContent()));
//        stage.show();
//    }
//
//    private Parent createContent() {
//        Pane root = new Pane();
//
//        Shape gridShape = makeGrid();
//        root.getChildren().add(gridShape);
//
//        return root;
//    }
//
//    private Shape makeGrid() {
//        Shape shape = new Rectangle((COLUMNS +1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
//
//        for (int i = 0; i < ROWS; i++) {
//            for (int j = 0; j < COLUMNS; j++) {
//                Circle circle = new Circle(TILE_SIZE / 2);
//                circle.setCenterX(TILE_SIZE / 2);
//                circle.setCenterY(TILE_SIZE / 2);
//                circle.setTranslateX((j * (TILE_SIZE + 5) + TILE_SIZE / 4));
//                circle.setTranslateY((i * (TILE_SIZE + 5) + TILE_SIZE / 4));
//
//                shape = Shape.subtract(shape, circle);
//
//            }
//        }
//
//        shape.setFill(Color.GREY);
//        return shape;
//    }
}
