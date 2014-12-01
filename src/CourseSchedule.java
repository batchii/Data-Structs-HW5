import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class CourseSchedule {

    //Scanner for the file
    private Scanner sc;
    
    private DirectedGraph myGraph;
    
    public static void main(String[] args) throws FileNotFoundException {
        String filename = null;
        if (args.length == 1){
            filename = args[0];
        } else {
            System.out.println("Please specify a filename");
            System.exit(0);
        }
        CourseSchedule myCourseSchedule = new CourseSchedule(filename);
        myCourseSchedule.run();
        
    }

    public CourseSchedule(String filename) throws FileNotFoundException{
        sc = new Scanner(new File(filename));
    }
    
    public void run(){
        
    }
    
}
