package FiloPL.model;

import FiloPL.controller.Solve;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void show() throws IOException {
        Solve solver = new Solve();
        prepareWindow();
        primaryStage.show();
    }

    private void prepareWindow() throws IOException {
        HBox background = new HBox();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("FiloPL/model/window.fxml"));
        Button button = new Button("Przycik");
        StackPane layout = loader.load();
        layout.getChildren();


        Scene scene = new Scene(layout, 500, 600);
        primaryStage.setScene(scene);
    }
}
