package FiloPL.background;

import FiloPL.input.Input;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 30.09.2019 18:48
 */
public class Solve {

    int[][] sudoku = {
            {0, 1, 0, 0, 0, 6, 2, 7, 0},
            {0, 0, 0, 0, 8, 0, 0, 0, 9},
            {0, 7, 8, 0, 0, 3, 6, 0, 5},
            {0, 0, 0, 0, 0, 4, 5, 0, 1},
            {0, 0, 2, 0, 0, 0, 7, 3, 4},
            {6, 0, 1, 7, 0, 0, 0, 0, 0},
            {1, 0, 6, 4, 0, 0, 9, 5, 0},
            {3, 0, 0, 0, 6, 0, 0, 0, 0},
            {0, 2, 7, 3, 9, 0, 0, 8, 0}};

    int[][] sudokuS = new int[9][9];

    int[][] current = new int[9][9];

    public int solverSoduoku() {
        Input inputDate = new Input();
        //sudoku = inputDate.inputDataForUser();
        long start=System.currentTimeMillis();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                current[i][j] = sudoku[i][j];
        if (solve(0, 0)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(current[i][j] + " ");
                } System.out.println();

            }
        } else {
            System.out.println("impossible\n");
        }
        long stop=System.currentTimeMillis();
        System.out.println("Czas wykonania (nanosekundach): "+(stop-start));

        return 0;
    }

    boolean can_insert(int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (value == current[x][i] || value == current[i][y] ||
                    value == current[x / 3 * 3 + i % 3][y / 3 * 3 + i / 3]) return false;
        }
        return true;
    }

    boolean next(int x, int y) {
        if (x == 8 && y == 8) return true;
        else if (x == 8) return solve(0, y + 1);
        else return solve(x + 1, y);
    }

    boolean solve(int x, int y) {
        if (sudoku[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (can_insert(x, y, i)) {
                    current[x][y] = i;
                    if (next(x, y)) return true;
                }
            }
            current[x][y] = 0;
            return false;
        }
        return next(x, y);
    }

}
