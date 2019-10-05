package FiloPL.window;

import FiloPL.background.Solve;
import javafx.stage.Stage;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 05.10.2019 17:40
 */
public class WindowSolver {

    public WindowSolver(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        primaryStage.setTitle("Sudoku solver v." + VERSION);
    }

    private final Stage primaryStage;
    private final String VERSION = "1.00";

    public void show() {
        Solve solver = new Solve();
        primaryStage.show();
    }
}
