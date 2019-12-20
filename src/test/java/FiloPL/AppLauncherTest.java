package FiloPL;

import FiloPL.controller.Solve;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppLauncherTest
{
    @Test
    public void shouldSolveSimpleSoduoku() {
        int[][] expertLevel = {
                {0,1,0,0,5,6,2,7,0},
                {0,0,0,0,8,0,0,0,9},
                {0,7,8,0,0,3,6,0,5},
                {0,0,0,0,0,4,5,0,1},
                {8,5,2,0,0,0,7,3,4},
                {6,0,1,7,0,0,0,0,0},
                {1,0,6,4,0,0,9,5,0},
                {3,0,0,0,6,0,0,0,0},
                {0,2,7,3,9,0,0,8,0} };
        Solve solve = new Solve();
        assertTrue( solve.solverSoduoku() );
    }
}
