import java.util.Random;

public class ordenacion {

    public static void main(String[] args) throws Exception {
        int[] sizes = {10000, 20000, 25000};
        for (int size : sizes) {
            System.out.println("Testing with array size: " + size);

            Integer[] originalArray = generateRandomArray(size);

            long startTime, endTime;

            Integer[] arrayForShellSort = originalArray.clone();
            startTime = System.nanoTime();
            new SortingMethods<Integer>().shellSort("campo", 1); 
            endTime = System.nanoTime();
            long shellSortTime = endTime - startTime;

            // QuickSort
            Integer[] arrayForQuickSort = originalArray.clone();
            startTime = System.nanoTime();
            new SortingMethods<Integer>().quickSort("campo", 1); 
            endTime = System.nanoTime();
            long quickSortTime = endTime - startTime;
            Integer[] arrayForMergeSort = originalArray.clone();
            startTime = System.nanoTime();
            new SortingMethods<Integer>().mergeSort("campo", 1); 
            endTime = System.nanoTime();
            long mergeSortTime = endTime - startTime;

            System.out.println("ShellSort time: " + shellSortTime / 1000000.0 + " ms");
            System.out.println("QuickSort time: " + quickSortTime / 1000000.0 + " ms");
            System.out.println("MergeSort time: " + mergeSortTime / 1000000.0 + " ms");
            System.out.println("-------------------------------");
        }
    }

   
    public static Integer[] generateRandomArray(int size) {
        Random rand = new Random();
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(100000); 
        }
        return array;
    }
}
