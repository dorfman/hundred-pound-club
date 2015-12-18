package hundred;

import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 * @author hdorfman
 */
public class HundredPoundClub {
    
    private static double maxBench = 0;
    private static double maxSquat = 0;
    private static double maxDL = 0;
    private static double club = 100;
    private static double sum = 0;
    private static String milestones = "<html>";
    
    public static void main(String[] args) {
        try{
            // input file
            Scanner inputStream = new Scanner(new File("FitNotes_Export.csv"));
            inputStream.useDelimiter(","); // csv files are comma separated
            inputStream.nextLine(); // First line has column names

            // output file for the stacked grap
            FileWriter threeFile = new FileWriter("Three_Graph.csv");
            threeFile.write("Date,Bench,Squat,Deadlift\n"); // column headers
            
            // output file for the sum
            FileWriter sumFile = new FileWriter("Sum_Graph.csv");
            sumFile.write("Date,Sum\n"); // column headers
            
            String oldDate = "";
            while(inputStream.hasNextLine() && inputStream.hasNext()){
                inputStream.nextLine();
                
                String date = inputStream.next();
                if (date.charAt(0) == '\n')
                    date = date.substring(1);
                String exercise = inputStream.next();
                String weight = GetWeightSkipUnused(inputStream);

                CheckMaxAndClub(weight, exercise, date, oldDate, threeFile, sumFile);
                oldDate = date;
            }
            inputStream.close();
            threeFile.close();
            sumFile.close();
            
            milestones += "</html>";
            JOptionPane.showMessageDialog(null, milestones);
            JOptionPane.showMessageDialog(null, HowToGraph());
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } 
    }
    
    /** Gets the weight from the row and skips unused columns */
    private static String GetWeightSkipUnused(Scanner inputStream) {
        inputStream.next(); // Category
        String weight = inputStream.next();
        inputStream.next(); // Reps
        inputStream.next(); // Distance (for running)
        inputStream.next(); // Time (for running)
        
        return weight;
    }
    
    /** Bulk of the logic. Validates data and send to other help functions*/
    private static void CheckMaxAndClub(String weight, String exercise, 
            String date, String oldDate, FileWriter threeFile, FileWriter sumFile) {
        
        int change = 0;
        if (weight.length() > 0)
            change = updateMax(exercise, weight);
        CheckClub(date);
        if (!date.equals(oldDate) || change != 0)
            WriteGraph(threeFile, sumFile, date);
    }
    
    /* Checks if any maxes were improved upon. */
    private static int updateMax(String exercise, String weight) {
        double w = Double.parseDouble(weight);
        
        if (exercise.equals("Flat Barbell Bench Press") && w > maxBench) {
            maxBench = w;
            return 1;
        }
        if (exercise.equals("Barbell Squat") && w > maxSquat) {
            maxSquat = w;
            return 1;
        }
        if (exercise.equals("Deadlift") && w > maxDL) {
            maxDL = w;
            return 1;
        }
        return 0;
    }
    
    /** Checks if a milestone was reached */
    private static void CheckClub(String date) {
        sum = maxBench + maxSquat + maxDL;
        if (sum > club) {
            milestones += "<h2>You reached the " + club + " club on " + date + "</h2><br/>";
            club += 100;	    
        }
    }
    
    /** Writes the date and the three max values to csv file */
    public static void WriteGraph(FileWriter threeFile, FileWriter sumFile, String date) {
        try {
            // for the stacked graph
            threeFile.write(date + ",");
            threeFile.write(maxBench + "," + maxSquat + "," + maxDL + "\n");
            
            // for the sum file
            sumFile.write(date + ",");
            sumFile.write(sum + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /** Display message on how to graph results */
    public static String HowToGraph() {
        return "<html><h3>Open \"Three_Graph.csv\" in Microsoft Excel (Preferably 2013)</h3>"
                + "<h3>Click on the Insert tab</h3>"
                + "<h3>Click on the Recommended Charts button</h3>"
                + "<h3>Click on the third option</h3>"
                + "<h3>\"Sum_Graph.csv\" holds just the dates and sums of the three</h3></html>";
    }
}
