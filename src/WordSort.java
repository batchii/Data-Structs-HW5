import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSort {

    private String[] dict;
    private String[] unsorted;

    private String first;
    private String second;
    private char[] order;

    public WordSort(String[] dict1, String[] unsorted1) {
        this.dict = dict1;
        this.unsorted = unsorted1;
    }

    public void readTwoWords() {
        int i = 0;
        while (i < dict.length - 1) {
            for (int j = 1; j < dict.length; j++) {
                first = dict[i];
                second = dict[j];
                i++;

            }

        }

    }

    public void generateOrder() {
        int i = 0;
        String firstChar = "";
        String secondChar = "";
        DirectedGraphPt2<String> graph = new DirectedGraphPt2<String>();
        while (i < first.length()) {
            for (int j = 0; j < second.length(); j++) {
                if (i < first.length()) {
                    firstChar += first.charAt(i);
                    System.out.println(firstChar);
                    secondChar += second.charAt(j);
                    System.out.println(secondChar);
                    i++;
                    firstChar = "";
                    secondChar = "";
                    /*
                     * if (firstChar != secondChar) {
                     * graph.addVertex(firstChar); graph.addEdge(firstChar,
                     * secondChar); graph.addVertex(secondChar); }
                     */

                }

            }

        }
        // graph.printGraph();
        // this.order = graph.returnOrder();
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 3) {
            File miniDict = new File(args[0]);
            Scanner dictIn = new Scanner(miniDict);
            String[] dictArray = new String[10];
            int i = 0;
            while (dictIn.hasNext()) {
                if (i == dictArray.length) {
                    String[] temp = new String[2 * dictArray.length];
                    System.arraycopy(dictArray, 0, temp, 0, dictArray.length);
                    dictArray = temp;
                }
                dictArray[i] = dictIn.next();
                i++;
            }
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
                }
                unsortedArray[i] = unsortedIn.next();
                i++;
            }
            String[] sizedUnsortedArray = new String[i];
            System.arraycopy(unsortedArray, 0, sizedUnsortedArray, 0, i);

            WordSort sorted = new WordSort(sizedDictArray, sizedUnsortedArray);
            sorted.readTwoWords();
            sorted.generateOrder();
            // for (int c = 0; c < sorted.order.length; c++) {
            // System.out.println(sorted.order[c]);
            // }

            unsortedIn.close();
            dictIn.close();
        } else {
            System.out.println("Please enter three arguments");
        }
    }
}
