import java.util.*;

public class st2 {

    static void swap(ArrayList<Integer> array, int i, int j) {
        Collections.swap(array, i, j);
    }

    static int random_partition(ArrayList<Integer> arr, int low, int high) {
        // Choosing the pivot middle element
        
        Random rand = new Random();
        int pivot = arr.get(rand.nextInt(low, high));

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (arr.get(j) < pivot) {
                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void random_quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            // sort the left part then right
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }




    static int partition(ArrayList<Integer> arr, int low, int high) {
        // Choosing the pivot middle element
        int pivot = arr.get(high);

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (arr.get(j) < pivot) {
                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    static void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(arr, low, high);
            // sort the left part then right
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    static void merge(ArrayList<Integer> arr, ArrayList<Integer> left, ArrayList<Integer> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) < right.get(j))
                arr.set(k++, left.get(i++));
            else
                arr.set(k++, right.get(j++));
        }
        while (i < left.size())
            arr.set(k++, left.get(i++));
        while (j < right.size())
            arr.set(k++, right.get(j++));

    }

    static void mergesort(ArrayList<Integer> arr) {
        if (arr.size() <= 1)
            return;
        int mid = arr.size() / 2;
        ArrayList<Integer> left = new ArrayList<>(arr.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(arr.subList(mid, arr.size()));
        mergesort(left);
        mergesort(right);

        merge(arr, left, right);
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 500; i++) {
            int temp = rand.nextInt(0, 1000);
            a.add(temp);
            b.add(temp);
        }
        // System.out.println("The unsorted array is \n "+a);
        long start = System.nanoTime();
        quickSort(a, 0, a.size() - 1);
        System.out.println((System.nanoTime() - start + " is the time taken by quick sort"));
        start = System.nanoTime();
        random_quickSort(b,0, b.size()-1);
        System.out.println((System.nanoTime() - start + " is the time taken by random quick sort"));

        // System.out.println("\nThe quick sorted array is \n "+a);
        // System.out.println("\nThe merge sorted array is \n "+b);
    }
}