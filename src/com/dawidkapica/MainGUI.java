package com.dawidkapica;

import com.dawidkapica.GUI.Connect4GUI;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;


import java.util.function.UnaryOperator;

import static javafx.application.Application.launch;

public class MainGUI extends Application {

    Stage window;
    Scene mainMenu, gameScreen;
    Pane boardPane = new Pane();

    ToggleGroup difficultyAI1;
    ToggleGroup difficultyAI2;

    RadioButton heuristicFirstAI1;
    RadioButton heuristicFirstAI2;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start (Stage primaryStage) throws Exception {
        window = primaryStage;

        gameScreen = new Scene(boardPane);
        boardPane.setBackground(new Background(new BackgroundFill(Color.rgb(30, 143, 255), CornerRadii.EMPTY, Insets.EMPTY)));


        Label pickDifficulty1 = new Label("AI1 difficulty (Yellow):");
        Label pickDifficulty2 = new Label("AI2 difficulty (Red):");

        difficultyAI1 = new ToggleGroup();
        heuristicFirstAI1 = new RadioButton("heuristicFirstAI1");
        heuristicFirstAI1.setToggleGroup(difficultyAI1);
        heuristicFirstAI1.setSelected(true);


        difficultyAI2 = new ToggleGroup();
        heuristicFirstAI2 = new RadioButton("heuristicFirstAI2");
        heuristicFirstAI2.setToggleGroup(difficultyAI2);
        heuristicFirstAI2.setSelected(true);


        TextField ai1DepthTextField = new TextField();
        TextField ai2DepthTextField = new TextField();


        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) {
                return change;
            }
            return null;
        };

        ai1DepthTextField.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), 5, integerFilter));

        ai2DepthTextField.setTextFormatter(
                new TextFormatter<Integer>(new IntegerStringConverter(), 5, integerFilter));


        Button startGameHuman = new Button("AI1 vs Human");
        startGameHuman.setOnAction(e -> {
            Connect4GUI gameGUI = new Connect4GUI(false,false, (Integer.parseInt(ai1DepthTextField.getText())));
            gameGUI.startGameMethod();
        });
        Button startGameComputer = new Button("AI1 vs AI2");

        startGameComputer.setOnAction(e -> {
            System.out.println("XXX" + ai1DepthTextField.getText());
            System.out.println("YYY" + ai2DepthTextField.getText());

            Connect4GUI gameGUI = new Connect4GUI(true, Integer.parseInt(ai1DepthTextField.getText()), Integer.parseInt(ai2DepthTextField.getText()));
            gameGUI.startGameMethod();

        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        GridPane.setConstraints(startGameHuman, 1, 7);
        GridPane.setConstraints(startGameComputer, 3, 7);
        GridPane.setHalignment(pickDifficulty1, HPos.RIGHT);
        GridPane.setConstraints(pickDifficulty1, 1, 9);
        GridPane.setHalignment(pickDifficulty2, HPos.RIGHT);
        GridPane.setConstraints(pickDifficulty2, 1, 11);
        GridPane.setConstraints(heuristicFirstAI1, 3, 9);
        GridPane.setConstraints(heuristicFirstAI2, 3, 11);
        GridPane.setConstraints(ai1DepthTextField, 7, 9);
        GridPane.setConstraints(ai2DepthTextField, 7, 11);

        grid.getChildren().addAll(startGameHuman, startGameComputer,
                pickDifficulty1, pickDifficulty2, heuristicFirstAI1, heuristicFirstAI2, ai1DepthTextField, ai2DepthTextField);

        window.setTitle("SI3.2_Connect4");
        mainMenu = new Scene(grid, 500, 500);
        window.setScene(mainMenu);
        window.show();

    }



}
