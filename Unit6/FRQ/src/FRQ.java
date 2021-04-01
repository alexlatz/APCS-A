import java.util.ArrayList;
import java.util.Arrays;

public class FRQ {
    public static String[] notInVocabNoList(final String[] vocab, final String[] wordArray) {
        final String[] tmpResult = new String[wordArray.length];
        int count = 0;
        for (final String str : wordArray) {
            boolean found = false;
            for (final String voc : vocab) {
                if (str.equals(voc)) {
                    found = true;
                    break;
                }
            }
            if (!found) tmpResult[count++] = str;
        }
        final String[] result = new String[count];
        System.arraycopy(tmpResult, 0 , result, 0, count);
        return result;
    }

    public static String[] notInVocab(final String[] vocab, final String[] wordArray) {
        final ArrayList<String> result = new ArrayList<>();
        for (final String str : wordArray) {
            boolean found = false;
            for (final String voc : vocab) {
                if (str.equals(voc)) {
                    found = true;
                    break;
                }
            }
            if (!found) result.add(str);
        }
        final String[] resultArr = new String[result.size()];
        return result.toArray(resultArr);
    }

    public static void main(final String[] args) {
        final String[] vocab = {"time", "food", "dogs", "cats", "health", "plants", "sports"};
        final String[] wordArray = {"dogs", "toys", "sun", "plants", "time"};
        System.out.println(Arrays.toString(notInVocabNoList(vocab, wordArray)));
        System.out.println(Arrays.toString(notInVocab(vocab, wordArray)));
    }
}