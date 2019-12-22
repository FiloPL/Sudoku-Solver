package FiloPL.model;

import java.io.*;
import java.util.Scanner;

public class Sudoku {

    private int[][] grid;
    private int size, sizec, sizer;
    private String filePath;

    public Sudoku(int[][] grid, int size, int sizec, int sizer) {
        grid = this.grid;
        size = this.size;
        sizec = this.sizec;
        sizer = this.sizer;
    }

    public Sudoku() {
    }

    public void countLine() {
        this.size = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {
            // unused
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                this.size++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initGridFile() {
        this.grid = new int[this.size][this.size];

        try {
            File file = new File(this.filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                for (int i = 0; i < this.size; i++) {
                    for (int j = 0; j < this.size; j++) {
                        this.grid[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initGrid() {
        this.grid = new int[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.grid[i][j] = 0;
            }
        }
    }

    public void resetGrid() {
        if (grid != null) {
            for (int i = 0; i < this.size; i++) {
                for (int j = 0; j < this.size; j++) {
                    this.grid[i][j] = 0;
                }
            }
        }
    }
    public void diplayGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                System.out.println(this.grid[i][j]);
            }
        }
    }

    public boolean validGrid() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.grid[i][j] > getSize() || this.grid[i][j] < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setSizeCell() {
        if (this.size == 9) {
            this.sizec = 3;
            this.sizer = 3;
        } else {
            System.out.println("Error -> setSizeCell");
        }
    }

    public boolean notInColumn(int k, int j) {
        int i;
        for (i = 0; i < this.size; i++) {
            if (this.grid[i][j] == k) {
                return false;
            }
        }
        return true;
    }

    public boolean notInRow(int k, int i) {
        int j;
        for (j = 0; j < this.size; j++) {
            if (this.grid[i][j] == k)
                return false;
        }
        return true;
    }

    public boolean notInCell(int k, int i, int j) {
        int i2 = i - (i % this.sizec);
        int j2 = j - (j % this.sizer);
        for (i = i2; i < i2 + this.sizec; i++)
            for (j = j2; j < j2 + this.sizer; j++)
                if (this.grid[i][j] == k)
                    return false;
        return true;
    }

    public boolean isValid(int position) {
        if (position == this.size * this.size)
            return true;

        int i = position / this.size;
        int j = position % this.size;

        if (this.grid[i][j] != 0) {
            return isValid(position + 1);
        }

        int k;
        for (k = 1; k <= this.size; k++) {
            if (notInColumn(k, j) == true && notInRow(k, i) == true && notInCell(k, i, j) == true) {
                this.grid[i][j] = k;
                if (isValid(position + 1))
                    return true;
            }
        }
        this.grid[i][j] = 0;

        return false;
    }

    public int[][] getGrid() {
        return grid;
    }

    public boolean isEmpty() {
        int a = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                a = a + this.grid[i][j];
            }
        }
        if (a == 0) {
            return true;
        }
        return false;
    }

    public void setGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }


    public int getSizec() {
        return sizec;
    }

    public void setSizec(int sizec) {
        this.sizec = sizec;
    }

    public int getSizer() {
        return sizer;
    }

    public void setSizer(int sizer) {
        this.sizer = sizer;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSize() {
        return size;
    }
}
