package com.mbi;

import com.jayway.restassured.response.Response;
import com.oracle.javafx.jmx.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 8/19/16.
 */

class Assertion extends Assert {

    private Object actual;
    private Object expected;
    private JSONCompareMode mode;
    private String[] ignore;


    Builder newBuilder(JSONCompareMode mode, String[] ignore) {
        this.mode = mode;
        this.ignore = ignore;

        return new Assertion().new Builder();
    }

    class Builder {

        private Builder() {
        }

        Builder setActual(Object actual) {
            Assertion.this.actual = actual;

            return this;
        }

        Builder setExpected(Object expected) {
            Assertion.this.expected = expected;

            return this;
        }

        Builder setMode(JSONCompareMode mode) {
            Assertion.this.mode = mode;

            return this;
        }

        Builder setIgnore(String[] ignore) {
            Assertion.this.ignore = ignore;

            return this;
        }

        Assertion build() {
            return Assertion.this;
        }
    }

    void doAssertion() {
        try {
            // JSONArray - JSONArray
            if (actual instanceof JSONArray && expected instanceof JSONArray) {
                actual = FieldsCutter.cutFields((JSONArray) actual, ignore);
                expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

                JSONAssert.assertEquals((JSONArray) expected, (JSONArray) actual, mode);

                // JSONObject - JSONObject
            } else if (actual instanceof JSONObject && expected instanceof JSONObject) {
                actual = FieldsCutter.cutFields((JSONObject) actual, ignore);
                expected = FieldsCutter.cutFields((JSONObject) expected, ignore);

                JSONAssert.assertEquals((JSONObject) expected, (JSONObject) actual, mode);

                // JSONArray - JSONObject[]
            } else if (actual instanceof JSONArray && expected instanceof JSONObject[]) {
                expected = objectsToArray((JSONObject[]) expected);
                actual = FieldsCutter.cutFields((JSONArray) actual, ignore);
                expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

                JSONAssert.assertEquals((JSONArray) expected, (JSONArray) actual, mode);

                // Response - JSONArray
            } else if (actual instanceof Response && expected instanceof JSONArray) {
                actual = FieldsCutter.cutFields(new JSONArray(actual.toString()), ignore);
                expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

                JSONAssert.assertEquals((JSONArray) expected, new JSONArray(actual.toString()), mode);

                // Response - JSONObject
            } else if (actual instanceof Response && expected instanceof JSONObject) {
                actual = FieldsCutter.cutFields(new JSONObject(actual.toString()), ignore);
                expected = FieldsCutter.cutFields((JSONObject) expected, ignore);

                JSONAssert.assertEquals((JSONObject) expected, new JSONObject(actual.toString()), mode);

                // Response - JSONObject[]
            } else if (actual instanceof Response && expected instanceof JSONObject[]) {
                expected = objectsToArray((JSONObject[]) expected);
                actual = FieldsCutter.cutFields(new JSONArray(actual.toString()), ignore);
                expected = FieldsCutter.cutFields((JSONArray) expected, ignore);

                JSONAssert.assertEquals((JSONArray) expected, new JSONArray(actual.toString()), mode);
            } else {
                throw new Error("Error arguments passed");
            }
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
