package FiloPL;


import FiloPL.background.Solve;
import FiloPL.input.Input;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppLauncher extends Application
{
    public static void main( String[] args )
    {
        System.out.println( "Sudoku Solver v1.00" );
        Solve solve = new Solve();
        solve.solverSoduoku();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Not ready yet");
    }
}
