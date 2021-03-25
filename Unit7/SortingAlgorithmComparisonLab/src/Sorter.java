public class Sorter {

    public static double bubbleSort(int[] arr) {
        long startTime = System.nanoTime();
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 1; i < arr.length; i++) {
                if (arr[i-1] > arr[i]) {
                    swapped = true;
                    swap(arr, i, i-1);
                }
            }
        }
        return (System.nanoTime() - startTime) / 1000000.0;
    }

    public static double insertionSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j-1] > arr[j]) {
                swap(arr, j, j-1);
                j--;
            }
        }
        return (System.nanoTime() - startTime) / 1000000.0;
    }

    public static double selectionSort(int[] arr) {
        long startTime = System.nanoTime();
        for (int i = 0; i < arr.length-1; i++) {
            int min = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) min = j;
            }
            if (min != i) {
                swap(arr, i, min);
            }
        }
        return (System.nanoTime() - startTime) / 1000000.0;
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[b];
        arr[b] = arr[a];
        arr[a] = tmp;
    }
}
