import org.junit.jupiter.api.Test;
;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class LinksTest {
    public static final String testsDirectory = "C:/reps/first-task-third-variant-LeeKnow032518/tests";
    @Test
    public static void check(int test_number) {

        try {
            String filePath = testsDirectory + "/" + Integer.toString(test_number) + ".in";
            String outFilePath = testsDirectory + "/" + Integer.toString(test_number) + ".out";
            File f = new File(filePath);
            File outF = new File(outFilePath);
            System.out.println(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(new FileReader(f));
            BufferedReader outBr = new BufferedReader(new FileReader(outF));
            String line;
            line = br.readLine();
            String[] numbers = line.split(" ");
            int n = Integer.parseInt(numbers[0]);
            int m = Integer.parseInt(numbers[1]);

            List<Edges> edges = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                numbers = line.split(" ");
                    Edges next_one = new Edges(Integer.parseInt(numbers[0]),
                            Integer.parseInt(numbers[1]), Integer.parseInt(numbers[2]));
                    edges.add(next_one);
            }

            String outLine;
            outLine = outBr.readLine();

            assertEquals(outLine, Links.KruskalAlg(edges, n, m));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for(int i=1;i<11;i++)
        {
            check(i);
        }
        System.out.println("All tests passed");
    }

}