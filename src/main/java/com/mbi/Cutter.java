package com.mbi;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by mbi on 8/18/16.
 */
class Cutter {

    static JSONObject cutFields(JSONObject json, String... fields) {
        for (String field : fields) {
            json.remove(field);
        }

        return json;
    }

    static JSONArray cutFields(JSONArray json, String[] fields) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < json.length(); i++) {
            JSONObject jsonObject = new JSONObject(json.get(i).toString());
            for (String f : fields) {
                jsonObject.remove(f);
            }
            result.put(jsonObject);
        }

        return result;
    }

    static JSONArray getEntryArray(JSONArray expected, JSONArray actual) {
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

        return newArray;
    }

    private static class MyJSONObject {

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
