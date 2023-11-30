import java.util.Arrays;

public class st12 {
    private static final int MATRIX_SIZE = 3;

    static int[][] multiplication(int[][] mat1, int[][] mat2) {
        int i = 0, j = 0, k = 0;
        int[][] C = new int[MATRIX_SIZE][MATRIX_SIZE];
        for (i = 0; i < MATRIX_SIZE; i++) {
            for (j = 0; j < MATRIX_SIZE; j++) {
                for (k = 0; k < MATRIX_SIZE; k++)
                    C[i][j] += mat1[i][k] * mat2[k][j];
            }
        }
        return C;
    }

    public static void main(String[] args) {
        int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        int[][] matrix2 = { { 9, 8, 7 }, { 6, 5, 4 }, { 3, 2, 1 } };
        int[][] result = new int[MATRIX_SIZE][MATRIX_SIZE];
        long start = System.nanoTime();
        result = multiplication(matrix1, matrix2);
        System.out.println((System.nanoTime() - start + " is the time taken by iterative"));
        for (int[] a : result)
            System.out.println(Arrays.toString(a));
        result = new int[MATRIX_SIZE][MATRIX_SIZE];       

        Thread[] threads = new Thread[MATRIX_SIZE];
        start = System.nanoTime();
        for (int i = 0; i < MATRIX_SIZE; i++) {
            threads[i] = new Thread(new Thread_multiplication(matrix1, matrix2,result, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println((System.nanoTime() - start + " is the time taken by threaded"));
        for (int[] a : result)
            System.out.println(Arrays.toString(a));


    }
}

class Thread_multiplication implements Runnable {
    private static final int MATRIX_SIZE = 3;
    int[][] matrix1 = new int[MATRIX_SIZE][MATRIX_SIZE];
    int[][] matrix2 = new int[MATRIX_SIZE][MATRIX_SIZE];
    int[][] result = new int[MATRIX_SIZE][MATRIX_SIZE];
    int i;

    Thread_multiplication(int[][] mat1, int[][] mat2,int[][] result, int i) {
        matrix1 = mat1;
        matrix2 = mat2;
        this.result = result;
        this.i = i;
    }

    @Override
    public void run() {
        for (int j = 0; j < MATRIX_SIZE; j++) {
            for (int k = 0; k < MATRIX_SIZE; k++) {
                result[i][j] += matrix1[i][k] * matrix2[k][j];
            }
        }
    }
}