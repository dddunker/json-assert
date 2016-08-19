import com.mbi.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class TestClass {

    private Assert asserter = new Assert();

    @Test
    public void testAssertJsonObjectJsonObject() {
        JSONObject j1 = new JSONObject("{\"q\": 1}");
        JSONObject j2 = new JSONObject("{\"q\": 1}");

        asserter
                .ignore(new String[]{"asd"})
                .withMode(JSONCompareMode.LENIENT)
                .jsonEquals(j1, j2);
    }

    @Test
    public void testAssertJsonArrayJsonArray() {
        JSONArray j1 = new JSONArray("[{\"q\": 1}]");
        JSONArray j2 = new JSONArray("[{\"q\": 1}]");

        asserter
                .ignore(new String[]{"asd"})
                .withMode(JSONCompareMode.LENIENT)
                .jsonEquals(j1, j2);
    }

    @Test
    public void testAssertWithIgnore() {
        JSONObject j2 = new JSONObject("{\"q\": 2, \"w\":3}");
        JSONObject j1= new JSONObject("{\"q\": 2, \"w\":3}");

        asserter
//                .ignore(new String[]{"w"})
//                .withMode(JSONCompareMode.STRICT)
                .jsonEquals(j1, j2);
    }
}
