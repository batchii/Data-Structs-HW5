import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSort {

    private String[] dict;
    private String[] unsorted;

    private char[] order;
    DirectedGraphPt2<String> graph = new DirectedGraphPt2<String>();

    public WordSort(String[] dict1, String[] unsorted1) {
        this.dict = dict1;
        this.unsorted = unsorted1;
    }

    public void smallestChar(int firstIndex, int secondIndex) {
        // see how many characters you can read
        int numFirstIndices = dict[firstIndex].length();
        int numSecondIndices = dict[secondIndex].length();
        int index;
        if (numFirstIndices > numSecondIndices) {
            index = numSecondIndices;
        } // if (i > j)
        else {
            index = numFirstIndices;
        } // else
        for (int i = 0; i < index; i++) {
            String firstString = "";
            String secondString = "";
            char firstChar = dict[firstIndex].charAt(i);
            char secondChar = dict[secondIndex].charAt(i);
            if (firstChar != secondChar) {
                firstString += firstChar;
                secondString += secondChar;
                graph.addVertex(firstString);
                graph.addEdge(firstString, secondString);
                graph.addVertex(secondString);
                firstString = "";
                secondString = "";
                return;
            } // if statement
        } // for loop

    } // smallestChar

    public void generateOrder() {
        int i = 0;
        int j = 1;
        while (j <= dict.length - 1) {
            smallestChar(i, j);
            i++;
            j++;
        }
        this.order = graph.returnOrder();
        System.out.println(this.order);
    }

    public static void main(String args[]) throws FileNotFoundException {
        if (args.length != 3) {
            throw new FileNotFoundException();
        } // if
        Scanner dictIn = new Scanner(new FileInputStream(args[0]));
        String[] dictArray = new String[10];
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
        String[] unsortedArray = new String[10];
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
        WordSort sorted = new WordSort(sizedDictArray, sizedUnsortedArray);
        sorted.generateOrder();
        unsortedIn.close();
        dictIn.close();
    } // main method
} // WordSort
