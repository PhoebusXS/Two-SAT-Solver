import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

public class TwoSATSolver {

    private static Graph G;

    public static void main (String[] args) throws IOException {
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
        G = new Graph(2 * getV(cnfString));
        // Matcher matchStart = Pattern.compile("p\\s+cnf\\s+\\d+\\s+\\d+\\s+").matcher(cnfString);
        Matcher matchPairs = Pattern.compile("(\\-?\\d+)\\s+(\\-?\\d+)\\s+(0|$)").matcher(cnfString);
        while (matchPairs.find()) {
            int a = Integer.parseInt(matchPairs.group(1));
            int b = Integer.parseInt(matchPairs.group(2));
            G.addEdge(vMap(-a), vMap(b));
            G.addEdge(vMap(-b), vMap(a));
        }
    }
 
    private static int getV (String cnfString) {
        int indTmp = cnfString.indexOf('p');
        // +6:#vars, +8+trunc(lg_10 #vars):#clauses
        return Character.getNumericValue(cnfString.charAt(indTmp + 6));
    }

    private static int vMap (int n) {
        // f: [-V, V]\{0} --> [0, 2V-1]
        return n + G.V() / 2 - 1 * ((n > 0) ? 1 : 0);
    }
 
}

// NOTES
// A or B <==> -A => B, -B => A
// p\s+cnf\s+\d+\s+\d+\s+ for starting of pairs
// (\-)?\d+\s+(\-)?\d+\s+(0|$) for finding pairs
