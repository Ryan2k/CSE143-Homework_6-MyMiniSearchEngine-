import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MyMiniSearchEngineTest {
    private List<String> documents() {
        return new ArrayList<String>(
                Arrays.asList(
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "seattle rains hello abc world",
                        "sunday hello world fun"));
    }

    @Test
    public void testOneWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("seattle");

        assertEquals(1, result.size());

        assertEquals(Integer.valueOf(4), result.get(0));
    }

    @Test
    public void testTwoWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("hello world");

        assertEquals(2, result.size());

        assertEquals(List.of(0, 5), result);
    }

    @Test
    /*Test method to see if search method in MyMiniSearchEngine Works with 3 word keyPhrase's. Two Strings created (one with 
     * upper case however everything is translated to lower case in the other file using toLowerCase()) then created a for each
     * loop calling the method and assert statement with the actual value.
     */
    public void testThreeWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "rains hello abc",
                "rains Hello abc",
        };

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        }
    }

    @Test
    /*Same as method above however using all 3 combinations of 4 word keyPhrase's
     */
    public void testFourWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "seattle rains hello abc",
                "rains hello abc world",
               
        };

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        }
        assertEquals(List.of(5), engine.search("sunday hello world fun"));
    }

    @Test
    /*Testing an empty string as well as a string not contained not contained withing "douments". Assert Equals an empty List
     */
    public void testWordNotFound() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        assertEquals(List.of(), engine.search(""));
        assertEquals(List.of(), engine.search("pizza"));
         
    }
}