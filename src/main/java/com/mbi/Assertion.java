package com.mbi;

import com.jayway.restassured.response.Response;
import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class Assertion {

    Assertion(Object expected, Object actual, JSONCompareMode mode, String[] ignore) {
        if (actual instanceof JSONArray && expected instanceof JSONArray) {
            actual = FieldsCutter.cutFields((JSONArray) actual, ignore);
            expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

            doAssertion((JSONArray) expected, (JSONArray) actual, mode);
            //
        } else if (actual instanceof JSONObject && expected instanceof JSONObject) {
            actual = FieldsCutter.cutFields((JSONObject) actual, ignore);
            expected = FieldsCutter.cutFields((JSONObject) expected, ignore);

            doAssertion((JSONObject) expected, (JSONObject) actual, mode);
            //
        } else if (actual instanceof JSONArray && expected instanceof JSONObject[]) {
            expected = objectsToArray((JSONObject[]) expected);
            actual = FieldsCutter.cutFields((JSONArray) actual, ignore);
            expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

            doAssertion((JSONArray) expected, (JSONArray) actual, mode);
            //
        } else if (actual instanceof Response && expected instanceof JSONArray) {
            actual = FieldsCutter.cutFields(new JSONArray(actual.toString()), ignore);
            expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

            doAssertion((JSONArray) expected, new JSONArray(actual.toString()), mode);
            //
        } else if (actual instanceof Response && expected instanceof JSONObject) {
            actual = FieldsCutter.cutFields(new JSONObject(actual.toString()), ignore);
            expected = FieldsCutter.cutFields((JSONObject) expected, ignore);

            doAssertion((JSONObject) expected, new JSONObject(actual.toString()), mode);
            //
        } else if (actual instanceof Response && expected instanceof JSONObject[]) {
            expected = objectsToArray((JSONObject[]) expected);
            actual = FieldsCutter.cutFields(new JSONArray(actual.toString()), ignore);
            expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

            doAssertion((JSONArray) expected, new JSONArray(actual.toString()), mode);
        } else {
            throw new Error("Error arguments passed");
        }
    }

    private void doAssertion(JSONArray expected, JSONArray actual, JSONCompareMode mode) {
        try {
            JSONAssert.assertEquals(expected, actual, mode);
        } catch (AssertionError exception) {
            String message = exception.getMessage()
                    .concat("\n\n")
                    .concat("Was expected: " + expected.toString())
                    .concat("\n\n")
                    .concat("But found: " + actual.toString());

            throw new AssertionError(message);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private void doAssertion(JSONObject expected, JSONObject actual, JSONCompareMode mode) {
        try {
            JSONAssert.assertEquals(expected, actual, mode);
        } catch (AssertionError exception) {
            String message = exception.getMessage()
                    .concat("\n\n")
                    .concat("Was expected: " + expected.toString())
                    .concat("\n\n")
                    .concat("But found: " + actual.toString());

            throw new AssertionError(message);
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private JSONArray objectsToArray(JSONObject[] jsonObjects) {
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : jsonObjects) {
            expectedArray.put(j);
        }

        return expectedArray;
    }
}
