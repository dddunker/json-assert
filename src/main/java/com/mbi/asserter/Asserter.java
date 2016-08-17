package com.mbi.asserter;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 6/6/16.
 */
public class Asserter extends AsserterHelper implements Assert {

    @Override
    public void assertJsonEquals(JSONObject actual, JSONObject expected) {
        doAssertion(expected, actual);
    }

    @Override
    public void assertJsonEquals(JSONObject actual, String[] ignore, JSONObject expected) {
        // Remove fields not included to response/expected
        expected = removeFields(expected, ignore);
        actual = removeFields(actual, ignore);

        doAssertion(expected, actual);
    }

    @Override
    public void assertJsonEquals(Response actualResponse, String[] ignore, JSONObject expected) {
        JSONObject actual = new JSONObject(actualResponse.asString());

        // Remove fields not included to response/expected
        expected = removeFields(expected, ignore);
        actual = removeFields(actual, ignore);

        doAssertion(expected, actual);
    }

    @Override
    public void assertJsonEquals(Response actualResponse, JSONObject expected) {
        JSONObject actual = new JSONObject(actualResponse.asString());

        doAssertion(expected, actual);
    }

    @Override
    public void assertJsonEquals(JSONArray actual, String[] ignore, JSONObject... expected) {
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : expected) {
            expectedArray.put(j);
        }

        // Remove fields not included to response/expected
        expectedArray = removeFields(expectedArray, ignore);
        actual = removeFields(actual, ignore);

        doAssertion(expectedArray, actual);
    }

    @Override
    public void assertJsonEquals(JSONArray actual, JSONObject... expected) {
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : expected) {
            expectedArray.put(j);
        }

        doAssertion(expectedArray, actual);
    }

    @Override
    public void assertJsonEquals(Response actual, String[] ignore, JSONCompareMode mode, JSONObject... expected) {
        JSONArray actualArray = new JSONArray(actual.asString());
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : expected) {
            expectedArray.put(j);
        }

        // Remove fields not included to response/expected
        expectedArray = removeFields(expectedArray, ignore);
        actualArray = removeFields(actualArray, ignore);

        doAssertion(expectedArray, actualArray, mode);
    }

    @Override
    public void assertJsonEquals(Response actual, JSONCompareMode mode, JSONObject... expected) {
        JSONArray actualArray = new JSONArray(actual.asString());
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : expected) {
            expectedArray.put(j);
        }

        doAssertion(expectedArray, actualArray, mode);
    }

    @Override
    public void assertJsonEquals(Response actual, String[] ignore, JSONArrayCompareMode mode, JSONObject... expected) {
        JSONArray actualArray = new JSONArray(actual.asString());
        JSONArray expectedArray = new JSONArray();

        for (JSONObject j : expected) {
            expectedArray.put(j);
        }

        // Remove fields not included to response/expected
        expectedArray = removeFields(expectedArray, ignore);
        actualArray = removeFields(actualArray, ignore);

        doAssertion(expectedArray, actualArray, mode);
    }

    @Override
    public void assertJsonEquals(JSONArray actual, JSONArray expected) {
        doAssertion(expected, actual);
    }
}
