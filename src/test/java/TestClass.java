import com.mbi.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TestClass {

    private Assert asserter = new Assert();

    @Test
    public void testAssertJsonObjectJsonObject() {
        JSONObject j1 = new JSONObject("{\"q\": 1}");
        JSONObject j2 = new JSONObject("{\"q\": 1}");
        JSONObject j3 = new JSONObject("{\"q\": 2}");

        asserter.assertJsonEquals(j1, j2);

        try {
            asserter.assertJsonEquals(j1, j3);
        } catch (AssertionError assertionError) {
            assertTrue(assertionError.getMessage().contains("Was expected"));
        }
    }

    @Test
    public void testAssertJsonArrayJsonArray() {
        JSONArray j1 = new JSONArray("[{\"q\": 1}]");
        JSONArray j2 = new JSONArray("[{\"q\": 1}]");
        JSONArray j3 = new JSONArray("[{\"q\": 2}]");

        asserter.assertJsonEquals(j1, j2);

        try {
            asserter.assertJsonEquals(j1, j3);
        } catch (AssertionError assertionError) {
            assertTrue(assertionError.getMessage().contains("Was expected"));
        }
    }

    @Test
    public void testAssertWithIgnore() {
        JSONObject j1 = new JSONObject("{\"q\": 2, \"w\":3}");
        JSONObject j2 = new JSONObject("{\"q\": 2}");
        JSONObject j3 = new JSONObject("{\"q\": 2}");
        JSONObject j4 = new JSONObject("{\"q\": 2, \"w\":3}");

        asserter
                .ignore(new String[]{"w"})
                .assertJsonEquals(j1, j2);

        asserter
                .ignore(new String[]{"w"})
                .assertJsonEquals(j3, j4);

        try {
            asserter.assertJsonEquals(j1, j3);
        } catch (AssertionError assertionError) {
            assertTrue(assertionError.getMessage().contains("Was expected"));
        }
    }

    @Test
    public void testAssertWithMode() {
        JSONObject j1 = new JSONObject("{\"q\": 2, \"w\":3}");
        JSONObject j2 = new JSONObject("{\"q\": 2}");

        asserter
                .withMode(JSONCompareMode.LENIENT)
                .assertJsonEquals(j1, j2);

        try {
            asserter
                    .withMode(JSONCompareMode.LENIENT)
                    .assertJsonEquals(j2, j1);
        } catch (AssertionError assertionError) {
            assertTrue(assertionError.getMessage().contains("Was expected"));
        }
    }
}
