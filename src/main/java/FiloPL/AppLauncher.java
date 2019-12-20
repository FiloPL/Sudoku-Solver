package FiloPL;


import FiloPL.model.Sudoku;
import javafx.application.Application;
import javafx.css.PseudoClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import jdk.nashorn.internal.codegen.types.BooleanType;

import java.awt.*;
import java.io.IOException;

public class AppLauncher extends Application {

    private static Stage primaryStage;
    private static BorderPane rootLayout;
    private Sudoku sudoku = new Sudoku();

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
        AppLauncher.primaryStage = primaryStage;
        AppLauncher.primaryStage.setTitle("Sudoku Solver");
        AppLauncher.primaryStage.getIcons().add(new Image("img/githubphoto.jpg"));
        initLayout();
    }

    public void initLayout() {
        try {
            // Load FML file
            FXMLLoader loaderFXML = new FXMLLoader();
            loaderFXML.setLocation(AppLauncher.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loaderFXML.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Sudoku getSudoku() {
        return sudoku;
    }
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}

