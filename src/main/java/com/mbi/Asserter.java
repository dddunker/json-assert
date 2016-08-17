package com.mbi;

import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 8/17/16.
 */
class Asserter implements Assert {

    public <T> void doAssertion(T actual, T expected, JSONCompareMode mode) {
        if (actual instanceof JSONArray)
        try {
            JSONAssert.assertEquals((JSONObject) expected.toString(), (JSONObject) actual, mode);
        } catch (AssertionError e) {
            String message = e.getMessage()
                    .concat("\n\n")
                    .concat("Was expected: " + expected.toString())
                    .concat("\n\n")
                    .concat("But found: " + actual.toString());

            throw new AssertionError(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
