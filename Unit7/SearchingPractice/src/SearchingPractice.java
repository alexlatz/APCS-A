public class SearchingPractice {

    public static int linearSearch(int[] arr, int search) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == search) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int search) {
        int low = 0;
        int high = arr.length-1;
        while (low <= high) {
            int mid = (high+low)/2;
            if (arr[mid] > search) high = mid-1;
            else if (arr[mid] < search) low = mid+1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
        System.out.println(linearSearch(arr, 9));
        System.out.println(linearSearch(arr, 99));
        System.out.println(binarySearch(arr, 9));
        System.out.println(binarySearch(arr, 99));
    }

}
