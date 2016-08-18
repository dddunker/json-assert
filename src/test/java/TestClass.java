import com.mbi.Assert;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.annotations.Test;

public class TestClass {

    @Test
    public void test() {
//        Assert asserter = Assert.newBuilder().assertEquals();
        JSONObject j1 = new JSONObject("{\"q\": 1}");
        JSONObject j2 = new JSONObject("{\"q\": 1}");
        JSONObject j3 = new JSONObject("{\"q\": 1}");
        JSONArray jsonObject2 = new JSONArray("[{\"q\": 1}]");
        JSONArray jsonObject1 = new JSONArray("[{\"q\": 1}]");
        String string = "string";

        Assert.Builder asserter = Assert.newBuilder();

        asserter
                .ignore(new String[]{"aaa"})
                .setMode(JSONCompareMode.LENIENT)
                .setJsons(j1, j2)
                .assertEquals();


//        Account account = Account.newBuilder()
//                .setToken("hello")
//                .setUserId("habr")
//                .assertEquals();
//
//        Account.Builder accountBuilder = Account.newBuilder();
//
//        accountBuilder.setToken("hello");
//
//        accountBuilder..setUserId("habr");
//
//        return accountBuilder.assertEquals();


//        asserter.assertEquals(jsonObject1, jsonObject2, JSONCompareMode.LENIENT);
//        asserter.assertEquals(jsonObject1, new JSONObject[]{j1, j2}, JSONCompareMode.LENIENT);


    }

}
