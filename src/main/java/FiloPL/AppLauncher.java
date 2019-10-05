package FiloPL;


import FiloPL.window.WindowSolver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

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
        WindowSolver windowedSolver = new WindowSolver(primaryStage);
        windowedSolver.show();

    }
}
