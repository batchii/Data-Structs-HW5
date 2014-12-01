package hw5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Celebrity {

    /** The number of allowable command line arguments. */
    private static final int MAX_NUMBER_ARGS = 1;

    Scanner sc;
    
    public Celebrity(String filename) throws IOException{
        sc = new Scanner(new File(filename));
        
    }
    
    public void run(){
        
    }
    
    public static void main(String[] args) throws IOException {
        String filename = null;
        if (args.length == 1) {
            filename = args[0];
        } else {
            System.out.println("Please specify a filename");
            System.exit(0);
        }
        Celebrity myCelebrity = new Celebrity(filename);
        myCelebrity.run();
    }

}
