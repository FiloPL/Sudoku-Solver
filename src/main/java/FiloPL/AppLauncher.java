package FiloPL;


import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class AppLauncher extends Application {

    public static void main(String[] args) {
        final String nameOfApp = "Sudoku Solver v1.00";
        System.out.println(nameOfApp);
        //Solve solve = new Solve();
        //solve.solverSoduoku();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //WindowSolver windowedSolver = new WindowSolver(primaryStage);
        //windowedSolver.show();
        GridPane board = new GridPane();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.pseudoClassStateChanged(right, col == 2 || col == 5);
                cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);

                cell.getChildren().add(createTextField());

                board.add(cell, col, row);
            }
        }


        Scene scene = new Scene(board);
        scene.getStylesheets().add("styleheat/sudokuView.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createTextField() {
        TextField textField = new TextField();

        // restrict input to integers:
        textField.setTextFormatter(new TextFormatter<Integer>(c -> {
            if (c.getControlNewText().matches("\\d?")) {
                return c ;
            } else {
                return null ;
            }
        }));
        return textField ;
    }


}

