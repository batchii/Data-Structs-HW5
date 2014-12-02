import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class WordSort {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length == 3) {
            File miniDict = new File(args[0]);
            Scanner dictIn = new Scanner(miniDict);
            File unsorted = new File(args[1]);
            OutputStream out = new BufferedOutputStream(new FileOutputStream(
                    args[2]));

            dictIn.close();
        } else {
            System.out.println("Please enter three arguments");
        }

    }
}
