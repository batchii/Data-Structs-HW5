import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author bhu9
 * A driver program to find the celebrity given relationships between people.
 */
final class Celebrity {

    /** Prevents unwanted instantiation. */
    private Celebrity() {
    } // private Celebrity()

    /**
     * @author bhu9
     * The main method.
     * @param args          the text files of players that will be read
     * @throws IOException  occurs when text files are not properly provided
     */
    public static void main(String[] args) throws IOException {
        Scanner inputFile = new Scanner(new FileInputStream(args[0]));
        int size = inputFile.nextInt();
        Task1DS graph = new Task1DS(size);
        while (inputFile.hasNext()) {
            int source = inputFile.nextInt();
            int destination = inputFile.nextInt();
            graph.addEdge(source, destination);
        } // while (inputFile.hasNext())
        /** Stores the value of the celebrity. */
        int celebrity = -1;
        /** Checks if multiple people could be the celebrity. */
        boolean multiplePeople = false;
        for (int i = 0; i < size; i++) {
            if (!(graph.hasEdge(i))) {
                if (celebrity != -1) {
                    multiplePeople = true;
                } // if (i != -1)
                celebrity = i;
            } // if (!(graph.hasEdge(i)))
        } // for (int i = 0; i < size; i++)
        if (celebrity == -1 || multiplePeople) {
            System.out.println("The celebrity could not be determined"
                    + " because either everyone knows each other (the celebrity"
                    + " cannot know anyone) or not everyone knows the"
                    + " celebrity (everyone must know him/her).");
        } else {
            System.out.println("The celebrity person is " + celebrity + ".");
        } // else
        inputFile.close();

    } //public static void main(String[] args) throws IOException

} // public class Celebrity