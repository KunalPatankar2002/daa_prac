import java.util.*;

public class st6 {

    public static void placeQueens(final int queens) {
        List<List<Integer>> arrangements = new ArrayList<List<Integer>>();
        getSolution(queens, arrangements, new int[queens], 0);
        if (arrangements.isEmpty()) {
            System.out.println(
                    "There is no way to place " + queens + " queens on board of size " + queens + "x" + queens);
        } else {
            System.out.println("There are " + arrangements.size() + " arrangements for placing " + queens + " queens");
        }
    }

    private static void getSolution(int boardSize, List<List<Integer>> solutions, int[] columns, int columnIndex) {
        if (columnIndex == boardSize) {
            List<Integer> sol = new ArrayList<Integer>();
            for (int n : columns)
                sol.add(n);
            // System.out.println(sol);
            solutions.add(sol);
            return;
        }
        for (int rowIndex = 0; rowIndex < boardSize; rowIndex++) {
            columns[columnIndex] = rowIndex;
            if (isPlacedCorrectly(columns, rowIndex, columnIndex)) {
                getSolution(boardSize, solutions, columns, columnIndex + 1);
            }
        }
    }

    private static boolean isPlacedCorrectly(int[] columns, int rowIndex, int columnIndex) {
        for (int i = 0; i < columnIndex; i++) {
            int diff = Math.abs(columns[i] - rowIndex);
            if (diff == 0 || columnIndex - i == diff) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 9; i++) {
            long start = System.nanoTime();
            placeQueens(i);
            System.out.println((System.nanoTime() - start + " is the time taken by " + i + " queens"));
        }

    }
}