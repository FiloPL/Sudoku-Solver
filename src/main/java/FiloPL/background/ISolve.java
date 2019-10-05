package FiloPL.background;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 03.10.2019 21:01
 */
public interface ISolve {
    boolean solverSoduoku();
    boolean can_insert(int x, int y, int value);
    boolean next(int x, int y);
    boolean solve(int x, int y);

}
