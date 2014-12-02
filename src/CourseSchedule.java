import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author atab7_000
 *
 */
public class CourseSchedule {

    /**
     * The scanner of the file.
     */
    private Scanner sc;

    /**
     *  file to be read.
     */
    private String filename;

    /**
     * Our datastructure.
     */
    private DirectedAcyclicGraph2<String> myGraph;

    /**
     * 
     * @param filename1 - the filename to be read
     * @throws FileNotFoundException - thrown in file is not found
     */
    public CourseSchedule(String filename1) throws FileNotFoundException {
        this.sc = new Scanner(new File(filename1));
        this.filename = filename1;
        this.myGraph = new DirectedAcyclicGraph2<String>();
    }
    
    /**
     * 
     * @param args - "filename.txt"
     * @throws FileNotFoundException - make sure the file is in the directory
     */
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



    /**
     * Starts the scheduler and does all the work.
     * @throws FileNotFoundException - is the file being read found?
     */
    public void run() throws FileNotFoundException {
        this.parseFile();
        this.generateClassSchedule();
    }

    /**
     * Parses the file with the info in it.
     * @throws FileNotFoundException - is the file being read found?
     */
    private void parseFile() throws FileNotFoundException {
        String currentLine;
        // Create vertices
        while (this.sc.hasNextLine()) {
            currentLine = this.sc.next();
            Scanner lineParser = new Scanner(currentLine);
            // This is the target vertex
            String course = lineParser.next();
            this.myGraph.addVertex(course);

        }
        // Create links
        this.sc = new Scanner(new File(this.filename));
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

    /**
     * Prints to output the entire class schedule.
     */
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
