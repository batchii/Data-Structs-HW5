import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CourseSchedule {

    // Scanner for the file
    private Scanner sc;

    private DirectedAcyclicGraph<String> myGraph;

    public static void main(String[] args) throws FileNotFoundException {
        String filename = null;
        if (args.length == 1) {
            filename = args[0];
        } else {
            System.out.println("Please specify a filename");
            System.exit(0);
        }
        CourseSchedule myCourseSchedule = new CourseSchedule(filename);
        myCourseSchedule.run();

    }

    public CourseSchedule(String filename) throws FileNotFoundException {
        sc = new Scanner(new File(filename));
        myGraph = new DirectedAcyclicGraph<String>();
    }

    public void run() {
        parseFile();
        generateClassSchedule();
    }
    
    private void parseFile(){
         String currentLine;
        while (sc.hasNextLine()) {
            currentLine = sc.next();
            Scanner lineParser = new Scanner(currentLine);
            //This is the target vertex
            String course = lineParser.next();
            myGraph.addVertex(course);
            while(lineParser.hasNext()){
                myGraph.addEdge(course, lineParser.next());
            }
        }
    }
    
    private void generateClassSchedule(){
        
    }

}
