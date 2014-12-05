import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/** The WordSort class used to sort words based on a given dictionary. */
public class WordSort {

    /** The required number of arguments. */
    private static final int NUM_ARGS = 3;
    /** Thes size of the initial array for reading in inputs. */
    private static final int ARRAY_SIZE = 10;
    /** Graph object data structure used to solve order. */
    DirectedGraphPt2<String> graph = new DirectedGraphPt2<String>();
    /** String array of input sorted dictionary. */
    private String[] dict;
    /** String array of unsorted input that will be sorted. */
    private String[] unsorted;
    /** Character array of alphabetical order of the characters. */
    private char[] order;

    /**
     * Constructor taking in the dictionary and unsorted files.
     *
     * @param dict1
     *            ` the input dictionary file
     * @param unsorted1
     *            the input unsorted file
     */
    public WordSort(String[] dict1, String[] unsorted1) {
        this.dict = dict1;
        this.unsorted = unsorted1;
    }

    /**
     * Determines the alphabetical order based on the dictionary.
     *
     * @param firstIndex
     *            index of first String to be compared
     * @param secondIndex
     *            index of second String to be compared
     */
    public void smallestChar(int firstIndex, int secondIndex) {
        // see how many characters you can read
        int numFirstIndices = this.dict[firstIndex].length();
        int numSecondIndices = this.dict[secondIndex].length();
        int index;
        if (numFirstIndices > numSecondIndices) {
            index = numSecondIndices;
        } else {
            index = numFirstIndices;
        } // else
        for (int i = 0; i < index; i++) {
            String firstString = "";
            String secondString = "";
            char firstChar = this.dict[firstIndex].charAt(i);
            char secondChar = this.dict[secondIndex].charAt(i);
            if (firstChar != secondChar) {
                firstString += firstChar;
                secondString += secondChar;
                this.graph.addVertex(firstString);
                this.graph.addEdge(firstString, secondString);
                this.graph.addVertex(secondString);
                firstString = "";
                secondString = "";
                return;
            } // if statement
        } // for loop

    } // smallestChar

    /** Generates the alphabetical order of the characters. */
    public void generateOrder() {
        int i = 0;
        int j = 1;
        while (j <= this.dict.length - 1) {
            this.smallestChar(i, j);
            i++;
            j++;
        }
        this.order = this.graph.returnOrder();
    }

    /**
     * Sorts the unsorted Strings alphabetically via bubble sorting.
     *
     * @return the sorted String list
     */
    public String[] bubbleSort() {
        String temp;
        for (int i = 1; i < this.unsorted.length; i++) {
            for (int j = 0; j < this.unsorted.length - i; j++) {
                if (this.compareTo(this.unsorted[j], this.unsorted[j + 1]) > 0) {
                    temp = this.unsorted[j];
                    this.unsorted[j] = this.unsorted[j + 1];
                    this.unsorted[j + 1] = temp;
                }
            }
        }
        return this.unsorted;
    }

    /**
     * Compares one String to another based on the alphabetical order determined
     * via the dictionary file.
     *
     * @param a
     *            the first String to be compared alphabetically
     * @param b
     *            the second String to be compared alphabetically
     * @return 1 if a comes before b, -1 if b comes before a, 0 if they're the
     *         same String
     */
    public int compareTo(String a, String b) {
        int numFirstIndices = a.length();
        int numSecondIndices = b.length();
        int index;
        if (numFirstIndices > numSecondIndices) {
            index = numSecondIndices;
        } else {
            index = numFirstIndices;
        }
        int i;
        for (i = 0; i < index; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                break;
            } // if
        } // for
        int orderOfA = -1;
        int orderOfB = -1;
        for (int j = 0; j < this.order.length; j++) {
            if (a.charAt(i) == this.order[j]) {
                orderOfA = j;
            } // if
            if (b.charAt(i) == this.order[j]) {
                orderOfB = j;
            } // if
        } // for
        if (orderOfA > orderOfB) {
            return 1;
        } else if (orderOfA < orderOfB) {
            return -1;
        } else {
            return 0;
        }
    } // compareTo

    /**
     * The main method, which recieves three arguments, the dictionary file
     * name, the unsorted file name, and the sorted file name, and produces a
     * sorted file of the given name.
     * 
     * @param args
     *            the names of the dictionary, unsorted, and sorted files
     * @throws FileNotFoundException
     *             if the text files were not properly provided
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != NUM_ARGS) {
            throw new FileNotFoundException();
        } // if
        Scanner dictIn = new Scanner(new FileInputStream(args[0]));
        String[] dictArray = new String[ARRAY_SIZE];
        int i = 0;
        while (dictIn.hasNext()) {
            if (i == dictArray.length) {
                String[] temp = new String[2 * dictArray.length];
                System.arraycopy(dictArray, 0, temp, 0, dictArray.length);
                dictArray = temp;
            } // if statement
            dictArray[i] = dictIn.next();
            i++;
        } // while loop
        String[] sizedDictArray = new String[i];
        System.arraycopy(dictArray, 0, sizedDictArray, 0, i);

        File unsorted = new File(args[1]);
        Scanner unsortedIn = new Scanner(unsorted);
        String[] unsortedArray = new String[ARRAY_SIZE];
        i = 0;
        while (unsortedIn.hasNext()) {
            if (i == unsortedArray.length) {
                String[] temp = new String[2 * unsortedArray.length];
                System.arraycopy(unsortedArray, 0, temp, 0,
                        unsortedArray.length);
                unsortedArray = temp;
            } // if statement
            unsortedArray[i] = unsortedIn.next();
            i++;
        } // while loop
        String[] sizedUnsortedArray = new String[i];
        System.arraycopy(unsortedArray, 0, sizedUnsortedArray, 0, i);
        WordSort toSort = new WordSort(sizedDictArray, sizedUnsortedArray);
        toSort.generateOrder();
        String[] result = toSort.bubbleSort();
        PrintWriter sorted = new PrintWriter(args[2]);
        for (int x = 0; x < result.length; x++) {
            sorted.println(result[x]);
        }
        unsortedIn.close();
        sorted.close();
        dictIn.close();
    } // main method
} // WordSort
