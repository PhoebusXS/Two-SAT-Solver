import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Arrays;

public class TwoSATSolver {

    private static Graph G;
    private static Tarjan scc;
    private static int[] truthAssignment;

    public static void main(String[] args) throws IOException {
        makeGraph(readFile(new File ("in.cnf")));
        if (checkGraph(G)) {
            printSoln();
        } else {
            noSoln();
        }
    }

    private static String readFile(File cnfFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(cnfFile));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while((line = bufferedReader.readLine())!=null){
            stringBuffer.append(line).append(" ");
        }
        return stringBuffer.toString();
    }

    private static void makeGraph(String cnfString) {
        G = new Graph(2 * getV(cnfString));
        Matcher matchPairs = Pattern.compile("(\\-?\\d+)\\s+(\\-?\\d+)\\s+(0|$)").matcher(cnfString);
        while (matchPairs.find()) {
            int a = Integer.parseInt(matchPairs.group(1));
            int b = Integer.parseInt(matchPairs.group(2));
            G.addEdge(vMap(-a), vMap(b));
            G.addEdge(vMap(-b), vMap(a));
        }
        for (int i = 0; i < G.V(); i++) {
            System.out.println(Arrays.toString(G.adjMat()[i]));
        }
    }
 
    private static int getV(String cnfString) {
        Matcher matchPLine = Pattern.compile("p\\scnf\\s(\\d+)\\s(\\d+)").matcher(cnfString);
        while (matchPLine.find()) {
            int v = Integer.parseInt(matchPLine.group(1));
            return v;
        }
        return 0;
    }

    private static int vMap(int n) {
        // f: [-V, V]\{0} --> [0, 2V-1]
        return n + G.V()/2 - 1 * ((n > 0) ? 1 : 0);
    }

    private static int revMap(int n){
        // f^(-1): [0, 2V-1] --> [-V, V]\{0}
        if (n >= G.V()/2) return n + 1 - G.V()/2;
        return n - G.V()/2;
    }

    private static boolean checkGraph(Graph G) {
        scc = new Tarjan(G);
        System.out.println(Arrays.toString(scc.index()));
        for (int i = G.V()/2; i < G.V(); i++){
            if (scc.isSC(i, vMap(-revMap(i)))) return false; // not solvable
        }
        // solvable
        truthAssignment = new int[G.V()];
        int u, v;
        int[] sccInd = scc.index();
        for (int i = 0; i <= scc.count()-1; i++) {
            while (true) {
                u = findValue(sccInd, i);
                if (u == -1) break;
                sccInd[u] = -1;
                truthAssignment[u] = 1;
                int negSCC = sccInd[vMap(-revMap(u))];
                while (negSCC != -1) {
                    v = findValue(sccInd, negSCC);
                    if (v == -1) break;
                    sccInd[v] = -1;
                    truthAssignment[v] = 0;
                }
            }
        }
        return true;
    }

    private static int findValue(int[] array, int n) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == n) return i;
        }
        return -1;
    }
 
    private static void printSoln() {
        for (int i = G.V()/2; i < G.V(); i++) {
            System.out.print(Integer.toString(truthAssignment[i]) + ' ');
        }
        System.out.println();
    }

    private static void noSoln() {
        System.out.println("oops no solution.");
    }

}

// NOTES
// A or B <==> -A => B, -B => A
// p\s+cnf\s+\d+\s+\d+\s+ for starting of pairs
// (\-)?\d+\s+(\-)?\d+\s+(0|$) for finding pairs
