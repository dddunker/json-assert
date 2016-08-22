package com.mbi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 8/19/16.
 */
class Asserter {

    void assertEquals(JSONObject expected, JSONObject actual, CompareMode mode, String[] ignore) {
        actual = Cutter.cutFields(actual, ignore);
        expected = Cutter.cutFields(expected, ignore);
        JSONCompareMode jsonCompareMode = CompareMode.getCompareMode(mode);

        try {
            JSONAssert.assertEquals(expected, actual, jsonCompareMode);
        } catch (AssertionError e) {
            String message = e.getMessage()
                    .concat("\n\n")
                    .concat("Was expected: " + expected.toString(4))
                    .concat("\n\n")
                    .concat("But found: " + actual.toString(4));

            throw new AssertionError(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void assertEquals(JSONArray expected, JSONArray actual, CompareMode mode, String[] ignore) {
        actual = Cutter.cutFields(actual, ignore);
        expected = Cutter.cutFields(expected, ignore);
        JSONCompareMode jsonCompareMode = CompareMode.getCompareMode(mode);
        System.out.println(jsonCompareMode);
        if (mode.isExtensibleArray()) {
            actual = Cutter.getEntryArray(expected, actual);
        }

        try {
            JSONAssert.assertEquals(expected, actual, jsonCompareMode);
        } catch (AssertionError e) {
            String message = e.getMessage()
                    .concat("\n\n")
                    .concat("Was expected: " + expected.toString(4))
                    .concat("\n\n")
                    .concat("But found: " + actual.toString(4));

            throw new AssertionError(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
