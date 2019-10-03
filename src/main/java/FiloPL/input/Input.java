package FiloPL.input;

import java.util.Scanner;

/**
 * @author Tomasz Filo Zegarlicki
 * [https://github.com/FiloPL]
 * @date : 29.09.2019 21:53
 */
public class Input implements IInput {

    public Input() {

    }

    @Override
    public int[][] inputDataForUser() {
        Scanner scanner = new Scanner(System.in);
        int[][] tab = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tab[i][j] = scanner.nextInt();
            }
            System.out.println();
        }
        return tab;
    }
}
