package com.mbi.asserter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by mbi on 6/6/16.
 */
class AsserterHelper {

    JSONObject removeFields(JSONObject jsonObject, String... fields) {
        for (String field : fields) {
            jsonObject.remove(field);
        }

        return jsonObject;
    }

    JSONArray removeFields(JSONArray jsonArray, String[] fields) {
        JSONArray result = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());

            for (String f : fields) {
                jsonObject.remove(f);
            }

            result.put(jsonObject);
        }

        return result;
    }

    void doAssertion(JSONObject expected, JSONObject actual) {
        try {
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
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

    void doAssertion(JSONArray expected, JSONArray actual) {
        try {
            JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
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

    void doAssertion(JSONArray expected, JSONArray actual, JSONCompareMode mode) {
        try {
            JSONAssert.assertEquals(expected, actual, mode);
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

    void doAssertion(JSONArray expected, JSONArray actual, JSONArrayCompareMode mode) {
        JSONCompareMode compareMode = getCompareMode(mode);

        JSONArray newArray = new JSONArray();
        HashSet<MyJSONObject> actualSet = new LinkedHashSet<>();

        for (Object ao : actual) {
            MyJSONObject actualJson = new MyJSONObject(ao);
            for (Object eo : expected) {
                MyJSONObject expectedJson = new MyJSONObject(eo);
                if (actualJson.equals(expectedJson)) {
                    actualSet.add(expectedJson);
                }
            }
        }
        for (MyJSONObject o : actualSet) {
            newArray.put(o.toJSONObject());
        }

        JSONAssert.assertEquals(expected, newArray, compareMode);
    }

    private JSONCompareMode getCompareMode(JSONArrayCompareMode mode) {
        JSONCompareMode compareMode = null;

        if (mode.isExtensible() && mode.isStrictOrder()) {
            compareMode = JSONCompareMode.STRICT_ORDER;
        } else if (mode.isExtensible() && !mode.isStrictOrder()) {
            compareMode = JSONCompareMode.LENIENT;
        } else if (!mode.isExtensible() && mode.isStrictOrder()) {
            compareMode = JSONCompareMode.STRICT;
        } else if (!mode.isExtensible() && !mode.isStrictOrder()) {
            compareMode = JSONCompareMode.NON_EXTENSIBLE;
        }

        return compareMode;
    }

    private class MyJSONObject {

        private Object o;

        MyJSONObject(Object o) {
            this.o = o;
        }

        JSONObject toJSONObject() {
            return new JSONObject(this.o.toString());
        }

        @Override
        public int hashCode() {
            return this.o.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj != null
                    && obj instanceof MyJSONObject
                    && ((MyJSONObject) obj).o.toString().equals(this.o.toString());
        }

        @Override
        public String toString() {
            return this.o.toString();
        }
    }
}
