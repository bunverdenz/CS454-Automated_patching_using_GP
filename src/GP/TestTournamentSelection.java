package GP;

import General.CompiledClassLoader;
import junit.framework.TestResult;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

public class TestTournamentSelection extends TestResult {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);
    public static int numberOfTests = 1;

    public static Class gpClass = null;
    public static Method[] allMethods = null;
    public static Method gpMethod = null;
    public static String testedMethodName = "tournamentSelection";

    @BeforeClass
    public static void setupEnvironment() {
        //Get access to a recompiled class during the runtime
        gpClass = GeneticOperations.class;
        allMethods = CompiledClassLoader.getRecompiledMethods(gpClass);

        for (Method m : allMethods) {
            String methodName = m.getName();
            if (methodName.startsWith(testedMethodName)) {
                m.setAccessible(true);
                gpMethod = m;
            }
        }
    }

    @Test
    public void tesTournamentSelection1() {
        try {
            Patch p1 = new Patch(1,22,5);
            Patch p2 = new Patch(0, 23, 5);
            Patch p3 = new Patch(1,22,5);
            Patch p4 = new Patch(0, 23, 5);
            Individual in1 = new Individual(Arrays.asList(p1),10.5);
            Individual in2 = new Individual(Arrays.asList(p2),20.5);
            Individual in3 = new Individual(Arrays.asList(p1),10.5);
            Individual in4 = new Individual(Arrays.asList(p2),20.5);
            List<Individual> pop = new ArrayList<Individual>();
            pop.add(in1);
            pop.add(in2);
            pop.add(in3);
            pop.add(in4);
            out.format("Invoking %s()%n", testedMethodName, " from tesTournamentSelection1...");
            Object o = gpMethod.invoke(null, pop);
            Assert.assertEquals(in2, o);
            out.format("%s() returned %b%n", testedMethodName, o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}