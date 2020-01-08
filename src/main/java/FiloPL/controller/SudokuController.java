package FiloPL.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import FiloPL.AppLauncher;
import FiloPL.model.Sudoku;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class SudokuController {
    @FXML
    private Button buttonRest;
    @FXML
    private Button buttonSolve;
    @FXML
    private BorderPane borderPane;
    private Sudoku sudoku = new Sudoku();

    public SudokuController() {
    }

    @FXML
    private void handleOpenAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text", "*.txt"));
        fileChooser.setTitle("Open file with Sudoku");
        File selecctFile = fileChooser.showOpenDialog(AppLauncher.getPrimaryStage());

        if(selecctFile != null) {
            String filePath = selecctFile.getPath();
            sudoku.setFilePath(filePath);

            sudoku.countLine();
            try {
                sudoku.initGridFile();
            } catch (Exception e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Errpr -> invalid file");
                alert.setContentText("Please select a other file or check file's syntax");
                alert.showAndWait();
            }
            sudoku.setSizeCell();

            if(sudoku.getSize() == 9) {
                SwitchScene("/view/9x9.fxml");
            } else {
                System.out.println("Error");
            }

            Pane pane = getPane();
            int i = 0, j = 0;
            for (Node node3 : pane.getChildren()) {
                if (node3 instanceof TextField) {
                    if (sudoku.getGrid()[i][j] == 0) {
                        ((TextField) node3).setText("");
                        j++;
                    } else {
                        ((TextField) node3).setText(String.valueOf((sudoku.getGrid()[i][j])));
                        // Mettre en rouge//((TextField)
                        (node3).setStyle("-fx-text-inner-color: red;");
                        j++;
                    }
                }
                if (j == sudoku.getGrid().length) {
                    i++;
                    j = 0;
                }
            }
        }

    }

    public void SwitchScene(String fxml) throws IOException {
        AnchorPane anchorPane = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppLauncher.class.getResource(fxml));
        anchorPane = (AnchorPane)loader.load();
        borderPane.setCenter(anchorPane);
        AppLauncher.getPrimaryStage().setHeight(570);
        AppLauncher.getPrimaryStage().setWidth(550);
    }

    /**
//    @FXML
//    private void handleSizeAction( ActionEvent event) throws IOException {
//        MenuItem menuItem = (MenuItem) event.getSource();
//        String label = menuItem.getText();
//        if (label.equalsIgnoreCase("9x9")) {
//            SwitchScene("/view/9x9.fxml");
//            sudoku.setSize(9);
//        } else  {
//            System.out.println("Error - handleSizeAction");
//        }
//    }
//
     */
    @FXML
    private void show9x9field() throws IOException {
        try{
            SwitchScene("/view/9x9.fxml");
            sudoku.setSize(9);
        } catch (Exception e) {
            System.out.println("Error - handleSizeAction");
        }
    }


    private Pane getPane() {
        AnchorPane anchorPane = null;
        for (Node node : borderPane.getChildren()) {
            if (node instanceof AnchorPane) {
                anchorPane = ((AnchorPane) node);
            }
        }

        // get Pane from AnchorPane
        Pane pane = null;
        for (Node node2 : anchorPane.getChildren()) {
            if (node2 instanceof Pane) {
                pane = ((Pane) node2);
            }

        }
        return pane;
    }

    @FXML
    private void handleResetAction(ActionEvent event) {
        sudoku.resetGrid();
        Pane pane = getPane();
        for (Node node3 : pane.getChildren()) {
            if (node3 instanceof TextField) {
                // clear
                ((TextField) node3).setText("");
            }
        }
    }

    @FXML
    private void handleSolveAction(ActionEvent event) {
        AnchorPane anchorpane = null;
        for (Node node : borderPane.getChildren()) {
            if (node instanceof AnchorPane) {
                anchorpane = ((AnchorPane) node);
            }
        }
        if (anchorpane != null) {
            sudoku.initGrid();
            sudoku.setSizeCell();
            Pane p = getPane();
            int[][] tmp = new int[sudoku.getSize()][sudoku.getSize()];
            int i = 0, j = 0;
            boolean charDetect = false;
            for (Node node3 : p.getChildren()) {
                if (node3 instanceof TextField) {
                    String value = ((TextField) node3).getText();
                    if (value.isEmpty()) {
                        tmp[i][j] = 0;
                        j++;
                    } else {
                        try {
                            tmp[i][j] = Integer.parseInt(value);
                        } catch (NumberFormatException e) {
                            charDetect = true;
                        }
                        j++;
                    }
                }
                if (j == tmp.length) {
                    i++;
                    j = 0;
                }
            }
            sudoku.setGrid(tmp);
            if (charDetect == true) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error : Character detected");
                alert.setContentText("You can't insert character");
                alert.showAndWait();
            } else if (sudoku.isEmpty() == true || sudoku.validGrid() == true) {

                if (sudoku.isValid(0)) {
                    i = 0;
                    j = 0;
                    for (Node node4 : p.getChildren()) {
                        if (node4 instanceof TextField) {
                            if (sudoku.getGrid()[i][j] == 0) {
                                ((TextField) node4).setText("");
                                j++;
                            } else {
                                ((TextField) node4).setText(String.valueOf((sudoku.getGrid()[i][j])));

                                j++;
                            }
                        }
                        if (j == sudoku.getGrid().length) {
                            i++;
                            j = 0;
                        }
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error : Sudoku isn't solvable");
                    alert.setContentText("Please check your grid");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error : Number too high or too low");
                alert.setContentText("One value in the grid isn't between 1 and " + sudoku.getSize());
                alert.showAndWait();
            }

        } else {

            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error : Can't solve the grid");
            alert.setContentText("Please select size or open one file to solve a grid");
            alert.showAndWait();

        }

    }

    @FXML
    private void handleSaveAction() throws IOException {

        AnchorPane anchorpane = null;
        for (Node node : borderPane.getChildren()) {
            if (node instanceof AnchorPane) {
                anchorpane = ((AnchorPane) node);
            }
        }
        if (anchorpane != null) {
            FileChooser fileChooser = new FileChooser();
            // Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            // Show save file dialog
            File file = fileChooser.showSaveDialog(AppLauncher.getPrimaryStage());

            if (file != null) {
                String path = file.getPath();

                Pane p = getPane();

                sudoku.initGrid();
                sudoku.setSizeCell();

                int[][] savegrid = new int[sudoku.getSize()][sudoku.getSize()];
                int i = 0, j = 0;
                for (Node node3 : p.getChildren()) {
                    if (node3 instanceof TextField) {
                        String value = ((TextField) node3).getText();
                        if (value.isEmpty()) {
                            savegrid[i][j] = 0;
                            j++;
                        } else {
                            savegrid[i][j] = Integer.parseInt(value);
                            j++;
                        }
                    }
                    if (j == savegrid.length) {
                        i++;
                        j = 0;
                    }
                }
                // WRITE TO FILE
                BufferedWriter outputWriter = null;
                outputWriter = new BufferedWriter(new FileWriter(path));
                for (i = 0; i < savegrid.length; i++) {
                    for (j = 0; j < savegrid.length; j++) {
                        outputWriter.write(savegrid[i][j] + "");
                        outputWriter.write(" ");
                    }
                    outputWriter.newLine();

                }
                outputWriter.flush();
                outputWriter.close();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please select size or open one file to save a grid");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleAboutAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(AppLauncher.class.getResource("/view/About.fxml"));
        BorderPane page = (BorderPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("About Sudoku Solver");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(AppLauncher.getPrimaryStage());
        dialogStage.setResizable(false);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

    }
}
