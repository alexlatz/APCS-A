public class Main {

    public static void main(String[] args) {
        //warming up the JVM to prevent time discrepancies
        Sorter.bubbleSort(getRandomNumbers(5000));
        int[] numElements = {1, 10, 100, 1000, 5000, 10000, 25000, 50000, 75000, 100000};
        for (int size : numElements) {
            for (int i = 1; i < 4; i++) {
                System.out.println("Sorting with " + size + " elements, pass " + i);
                double time = Sorter.selectionSort(getRandomNumbers(size));
                System.out.printf("%.4f\n", time);
            }
        }
    }

    private static int[] getRandomNumbers(int quantity) {
        int[] randomNumbers = new int[quantity];
        int maxValue = quantity * 10;
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = (int) (Math.random() * maxValue);
        }
        return randomNumbers;
    }

}
