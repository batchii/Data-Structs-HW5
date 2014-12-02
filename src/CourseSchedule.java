import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CourseSchedule {

    // Scanner for the file
    private Scanner sc;

    // filename
    private String filename;

    private DirectedAcyclicGraph2<String> myGraph;

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

    public CourseSchedule(String filename1) throws FileNotFoundException {
        this.filename = filename1;
        this.sc = new Scanner(new File(filename1));
        this.myGraph = new DirectedAcyclicGraph2<String>();
    }

    public void run() throws FileNotFoundException {
        this.parseFile();
        this.generateClassSchedule();
    }

    private void parseFile() throws FileNotFoundException {
        String currentLine;
        // Create vertices
        while (sc.hasNextLine()) {
            currentLine = sc.next();
            Scanner lineParser = new Scanner(currentLine);
            // This is the target vertex
            String course = lineParser.next();
            this.myGraph.addVertex(course);

        }
        // Create links
        sc = new Scanner(new File(this.filename));
        while (this.sc.hasNextLine()) {
            currentLine = this.sc.nextLine();
            Scanner lineParser = new Scanner(currentLine);
            // This is the target vertex
            String course = lineParser.next();
            while (lineParser.hasNext()) {
                String prereq = lineParser.next();
                this.myGraph.addEdge(course, prereq);
            }
        }

    }

    private void generateClassSchedule() {
        // Need to run thru
        String schedule = "";
        int numSemester = 0;
        while (this.myGraph.getNumberVertices() > 0) {
            numSemester++;
            schedule += "Semester " + numSemester + ":\n";
            schedule += this.myGraph.getSemester();
        }
        System.out.println(schedule);
        System.out
                .println("Total number of semesters required: " + numSemester);
    }

}
