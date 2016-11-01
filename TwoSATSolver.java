import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TwoSATSolver {

    private static Graph G;

    public static void main (String[] args) throws IOException {
        // System.out.println(readFile(new File ("in.cnf"))); // tests readFile
        makeGraph(readFile(new File ("in.cnf")));

    }

    private static String readFile (File cnfFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(cnfFile));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line).append(" ");
        }
        return stringBuffer.toString();
    }

    private static void makeGraph (String cnfString) {
        G = new Graph(getV(cnfString));
    }
 
    private static int getV (String cnfString) {
        int indTmp = cnfString.indexOf('p');
        return Integer.valueOf(Character.getNumericValue(cnfString.charAt(indTmp + 6)));
        // +6:#vars, +8:#clauses
    }
    
// A or B <==> -A => B, -B => A

}
