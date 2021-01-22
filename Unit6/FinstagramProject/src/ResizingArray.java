import java.util.Arrays;

public class ResizingArray {
    private FinstaUser[] arr;
    private int end;

    public ResizingArray() {
        arr = new FinstaUser[1];
        end = 0;
    }

    public void add(final FinstaUser user) {
        arr[end] = user;
        end++;
        if (end >= arr.length) {
            final FinstaUser[] newArr = new FinstaUser[arr.length * 2];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
    }

    public void remove(final FinstaUser user) {
        if (end == 0) throw new IllegalArgumentException();
        for (int i = 0; i < end; i++) {
            if (arr[i].equals(user)) {
                arr[i] = arr[end - 1];
                arr[end - 1] = null;
                end--;
                if (end < arr.length / 4) {
                    final FinstaUser[] newArr = new FinstaUser[arr.length / 2];
                    System.arraycopy(arr, 0, newArr, 0, end + 1);
                    arr = newArr;
                }
            }
        }
    }

    public boolean contains(final FinstaUser user) {
        for (int i = 0; i < end; i++) {
            if (arr[i].equals(user)) return true;
        }
        return false;
    }

    private String[] getArr() {
        final String[] newArr = new String[end];
        for (int i = 0; i < end; i++) {
            newArr[i] = arr[i].getHandle();
        }
        return newArr;
    }

    @Override
    public String toString() {
        return Arrays.toString(getArr());
    }

}
