import java.util.ArrayList;
import java.util.Arrays;

public class FRQ {
    public static String[] notInVocabNoList(String[] vocab, String[] wordArray) {
        String[] tmpResult = new String[wordArray.length];
        int count = 0;
        for (String str : wordArray) {
            boolean found = false;
            for (String voc : vocab) {
                if (str.equals(voc)) {
                    found = true;
                    break;
                }
            }
            if (!found) tmpResult[count++] = str;
        }
        String[] result = new String[count];
        System.arraycopy(tmpResult, 0 , result, 0, count);
        return result;
    }

    public static String[] notInVocab(String[] vocab, String[] wordArray) {
        ArrayList<String> result = new ArrayList<>();
        for (String str : wordArray) {
            boolean found = false;
            for (String voc : vocab) {
                if (str.equals(voc)) {
                    found = true;
                    break;
                }
            }
            if (!found) result.add(str);
        }
        String[] resultArr = new String[result.size()];
        return result.toArray(resultArr);
    }

    public static void main(String[] args) {
        String[] vocab = {"time", "food", "dogs", "cats", "health", "plants", "sports"};
        String[] wordArray = {"dogs", "toys", "sun", "plants", "time"};
        System.out.println(Arrays.toString(notInVocabNoList(vocab, wordArray)));
        System.out.println(Arrays.toString(notInVocab(vocab, wordArray)));
    }
}