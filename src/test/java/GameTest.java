import org.junit.jupiter.api.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @BeforeAll
    static void setup(){
        test1 = new Game();
        test2 = new Game();
        test3 = new Game();
        test4 = new Game();
    }
    @AfterAll
    static void restore(){
        System.setIn(inputs);
        System.setOut(outputs);
    }
    @Test
    void allWin() throws Exception {
        Method whoWin = test1.getClass().getDeclaredMethod("whoWin", String.class, String.class);
        whoWin.setAccessible(true);
        for(String[] test: testAllWin){
            int ret = (int)whoWin.invoke(test1, test[0], test[1]);
            Assertions.assertEquals(1, ret);
        }
    }
    @Test
    void allLose() throws Exception {
        Method whoWin = test2.getClass().getDeclaredMethod("whoWin", String.class, String.class);
        whoWin.setAccessible(true);
        for(String[] test: testAllLose){
            int ret = (int)whoWin.invoke(test2, test[0], test[1]);
            Assertions.assertEquals(2, ret);
        }
    }
    @Test
    void allDraw() throws Exception {
        Method whoWin = test3.getClass().getDeclaredMethod("whoWin", String.class, String.class);
        whoWin.setAccessible(true);
        for(String[] test: testAllDraw){
            int ret = (int)whoWin.invoke(test3, test[0], test[1]);
            Assertions.assertEquals(-1, ret);
        }
    }
    @Test
    void invalidInput() throws Exception {
        System.setIn(new ByteArrayInputStream(invalidInputs.getBytes()));
        //testOutput = new ByteArrayOutputStream();
        //System.setOut(new PrintStream(testOutput));
        Method getAction = test4.getClass().getDeclaredMethod("getAction");
        getAction.setAccessible(true);
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                getAction.invoke(test4);
            } catch (InvocationTargetException in){
                throw new IllegalArgumentException();
            }
        });

    }
    private final String[][] testAllWin = {{"rock", "scissors"}, {"paper", "rock"}, {"scissors", "paper"}};
    private final String[][] testAllLose = {{"rock", "paper"}, {"paper", "scissors"}, {"scissors", "rock"}};
    private final String[][] testAllDraw = {{"rock", "rock"}, {"paper", "paper"}, {"scissors", "scissors"}};
    private final String invalidInputs = "hello";
    private static Game test1;
    private static Game test2;
    private static Game test3;
    private static Game test4;
    private static final InputStream inputs = System.in;
    private static final PrintStream outputs = System.out;
    private ByteArrayOutputStream testOutput;
    private ByteArrayInputStream testInput;
}