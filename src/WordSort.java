import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSort {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length > 0) {
            File miniDict = new File(args[0]);
            Scanner in = new Scanner(miniDict);

            String line = in.nextLine();
            System.out.println(line);
            in.close();
        }

    }
}
