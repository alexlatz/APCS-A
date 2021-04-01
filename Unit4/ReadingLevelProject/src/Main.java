import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String fileName = "Unit4/ReadingLevelProject/samplePassage.txt";
    //I probably should have used StringBuilder but that would require a List and I like the simplicity
    private static final String[] words = new String[5];
    private static final int[] count = new int[5];
    private static double avg = 0;
    public enum Grade {
        KINDERGARTEN,
        ELEMENTARY,
        MIDDLE,
        HIGH,
        ADVANCED
    }

    public static void main(final String[] args) {
        //This should be pretty fast even with the String concatenation due to a single loop approach for indexing
        final String file = textToString(fileName);
        System.out.println("Original Text:");
        System.out.println(file + "\n");
        Arrays.fill(words, "");
        searchFile(file);
        //This could be nicer if Java supported generic arrays...
        System.out.println("Levels:");
        System.out.println("Kindergarten-level words: " + count[Grade.KINDERGARTEN.ordinal()]);
        System.out.println("Elementary-school-level words: " + count[Grade.ELEMENTARY.ordinal()]);
        System.out.println("Middle-school-level words: " + count[Grade.MIDDLE.ordinal()]);
        System.out.println("High-school-level words: " + count[Grade.HIGH.ordinal()]);
        System.out.println("Advanced words: " + count[Grade.ADVANCED.ordinal()] + "\n");
        System.out.println("Words in each category: ");
        System.out.println("Kindergarten-level words: " + words[Grade.KINDERGARTEN.ordinal()]);
        System.out.println("Elementary-school-level words: " + words[Grade.ELEMENTARY.ordinal()]);
        System.out.println("Middle-school-level words: " + words[Grade.MIDDLE.ordinal()]);
        System.out.println("High-school-level words: " + words[Grade.HIGH.ordinal()]);
        System.out.println("Advanced words: " + words[Grade.ADVANCED.ordinal()] + "\n");
        System.out.println("Reading Level:");
        int sum = 0;
        for (final int i : count) sum += i;
        avg /= sum;
        System.out.printf("The average word length in this text is %.2f letters, so it appears to be of " +
                gradeToString(findReadingLevel((int) Math.round(avg))) + " reading level.", avg);

    }

    public static String gradeToString(final Grade grade) {
        switch (grade) {
            case KINDERGARTEN:
                return "kindergarten";
            case ELEMENTARY:
                return "elementary school";
            case MIDDLE:
                return "middle school";
            case HIGH:
                return "high school";
            default:
                return "advanced";
        }
    }

    public static void searchFile(String file) {
        file += " ";
        int lastSpace = -1;
        for (int i = 0; i < file.length(); i++) {
            if (Character.isWhitespace(file.charAt(i))) {
                String word = file.substring(lastSpace+1, i);
                word = removePunctuation(word);
                final Grade grade = findReadingLevel(word.length());
                saveWord(grade, word);
                lastSpace = i;
            }
        }
    }

    public static void saveWord(final Grade grade, final String word) {
        //This leaves a hanging space but it's a waste of time to remove it due to the time complexity of String concatenation
        words[grade.ordinal()] += word + " ";
        count[grade.ordinal()]++;
        avg += word.length();
    }

    public static Grade findReadingLevel(final int length) {
        if (length < 5) return Grade.KINDERGARTEN;
        else if (length < 7) return Grade.ELEMENTARY;
        else if (length < 11) return Grade.MIDDLE;
        else if (length < 15) return Grade.HIGH;
        else return Grade.ADVANCED;
    }

    // ------------------- PROVIDED HELPER METHODS -------------------
    // ------------ DO NOT EDIT ANYTHING BELOW THIS POINT ------------

    /**
     * returns a string containing all of the text in fileName (including punctuation),
     * with words separated by a single space
     */
    private static String textToString(final String fileName) {
        String temp = "";
        try {
            final Scanner input = new Scanner(new File(fileName));

            //add 'words' in the file to the string, separated by a single space
            while (input.hasNext()) {
                temp = temp + input.next() + " ";
            }
            input.close();

        } catch (final Exception e) {
            System.out.println("Unable to locate " + fileName);
        }
        //make sure to remove any additional space that may have been added at the end of the string.
        return temp.trim();
    }

    /**
     * Returns the ending punctuation of a string, or the empty string if there is none
     */
    public static String getPunctuation(final String word) {
        String punc = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            if (!Character.isLetterOrDigit(word.charAt(i))) {
                punc = punc + word.charAt(i);
            } else {
                return punc;
            }
        }
        return punc;
    }

    /**
     * Returns the word after removing any beginning or ending punctuation
     */
    public static String removePunctuation(String word) {
        while (word.length() > 0 && !Character.isAlphabetic(word.charAt(0))) {
            word = word.substring(1);
        }
        while (word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length() - 1))) {
            word = word.substring(0, word.length() - 1);
        }

        return word;
    }
}
