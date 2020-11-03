import java.io.File;
import java.util.Scanner;

public class Main {
    private static final String fileName = "Unit4/ReadingLevelProject/samplePassage.txt";
    private static String kinder = "";
    private static String elementary = "";
    private static String middle = "";
    private static String high = "";
    private static String advanced = "";
    private static final int[] count = new int[5];
    private static double avg = 0;
    public enum Grade {
        KINDERGARTEN,
        ELEMENTARY,
        MIDDLE,
        HIGH,
        ADVANCED
    }

    public static void main(String[] args) {
        final String file = textToString(fileName);
        System.out.println("Original Text:");
        System.out.println(file + "\n");
        searchFile(file);
        System.out.println("Levels:");
        System.out.println("Kindergarten-level words: " + count[0]);
        System.out.println("Elementary-school-level words: " + count[1]);
        System.out.println("Middle-school-level words: " + count[2]);
        System.out.println("High-school-level words: " + count[3]);
        System.out.println("Advanced words: " + count[4] + "\n");
        System.out.println("Words in each category: ");
        System.out.println("Kindergarten-level words: " + kinder);
        System.out.println("Elementary-school-level words: " + elementary);
        System.out.println("Middle-school-level words: " + middle);
        System.out.println("High-school-level words: " + high);
        System.out.println("Advanced words: " + advanced + "\n");
        System.out.println("Reading Level:");
        avg /= (count[0] + count[1] + count[2] + count[3] + count[4]);
        System.out.println("The average word length in this text is " + avg + " letters, so it appears to be of " +
                gradeToString(findReadingLevel((int) Math.round(avg))) + " reading level.");

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
                Grade grade = findReadingLevel(word.length());
                saveWord(grade, word);
                lastSpace = i;
            }
        }
    }

    public static void saveWord(final Grade grade, final String word) {
        switch (grade) {
            case KINDERGARTEN:
                kinder += word + " ";
                count[0]++;
                break;
            case ELEMENTARY:
                elementary += word + " ";
                count[1]++;
                break;
            case MIDDLE:
                middle += word + " ";
                count[2]++;
                break;
            case HIGH:
                high += word + " ";
                count[3]++;
                break;
            default:
                advanced += word + " ";
                count[4]++;
        }
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
    private static String textToString(String fileName) {
        String temp = "";
        try {
            Scanner input = new Scanner(new File(fileName));

            //add 'words' in the file to the string, separated by a single space
            while (input.hasNext()) {
                temp = temp + input.next() + " ";
            }
            input.close();

        } catch (Exception e) {
            System.out.println("Unable to locate " + fileName);
        }
        //make sure to remove any additional space that may have been added at the end of the string.
        return temp.trim();
    }

    /**
     * Returns the ending punctuation of a string, or the empty string if there is none
     */
    public static String getPunctuation(String word) {
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
