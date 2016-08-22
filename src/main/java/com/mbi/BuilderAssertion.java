package com.mbi;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mbi on 8/19/16.
 */

class BuilderAssertion {

    private Object actual;
    private Object expected;
    private CompareMode mode;
    private String[] ignore;
    private Asserter asserter = new Asserter();

    Builder newBuilder(CompareMode mode, String[] ignore) {
        this.mode = mode;
        this.ignore = ignore;

        return new BuilderAssertion().new Builder();
    }

    class Builder {

        private Builder() {
        }

        Builder setActual(Object actual) {
            BuilderAssertion.this.actual = actual;

            return this;
        }

        Builder setExpected(Object expected) {
            BuilderAssertion.this.expected = expected;

            return this;
        }

        Builder setMode(CompareMode mode) {
            BuilderAssertion.this.mode = mode;

            return this;
        }

        Builder setIgnore(String[] ignore) {
            BuilderAssertion.this.ignore = ignore;

            return this;
        }

        BuilderAssertion build() {
            BuilderAssertion builderAssertion = new BuilderAssertion();
            builderAssertion.actual = BuilderAssertion.this.actual;
            builderAssertion.expected = BuilderAssertion.this.expected;
            builderAssertion.mode = BuilderAssertion.this.mode;
            builderAssertion.ignore = BuilderAssertion.this.ignore;

            return BuilderAssertion.this;
        }
    }

    void doAssertion() {
        // JSONArray - JSONArray
        if (actual instanceof JSONArray && expected instanceof JSONArray) {
            asserter.assertEquals((JSONArray) expected, (JSONArray) actual, mode, ignore);

            // JSONObject - JSONObject
        } else if (actual instanceof JSONObject && expected instanceof JSONObject) {
            asserter.assertEquals((JSONObject) expected, (JSONObject) actual, mode, ignore);

            // JSONArray - JSONObject[]
        } else if (actual instanceof JSONArray && expected instanceof JSONObject[]) {
            asserter.assertEquals(objectsToArray((JSONObject[]) expected), (JSONArray) actual, mode, ignore);

            // Response - JSONArray
        } else if (actual instanceof Response && expected instanceof JSONArray) {
            asserter.assertEquals((JSONArray) expected, new JSONArray(actual.toString()), mode, ignore);

            // Response - JSONObject
        } else if (actual instanceof Response && expected instanceof JSONObject) {
            asserter.assertEquals((JSONObject) expected, new JSONObject(actual.toString()), mode, ignore);

            // Response - JSONObject[]
        } else if (actual instanceof Response && expected instanceof JSONObject[]) {
            asserter.assertEquals(objectsToArray((JSONObject[]) expected), new JSONArray(actual.toString()), mode, ignore);
        } else {
            throw new Error("Error arguments passed");
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