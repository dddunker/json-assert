package com.mbi.asserter;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 6/6/16.
 */
interface Assert {

    /**
     * Method to assert two jsons are equal. Test will throw AssertionError exception (with both expected and actual
     * jsons) if jsons are not equal.
     *
     * @param expected Json object.
     * @param actual   Json object.
     */
    void assertJsonEquals(JSONObject actual, JSONObject expected);

    /**
     * Method to assert two jsons are equal. Test will throw AssertionError exception (with both expected and actual
     * jsons) if jsons are not equal.
     *
     * @param expected       Json object.
     * @param actualResponse Rest-assured response.
     */
    void assertJsonEquals(Response actualResponse, JSONObject expected);

    /**
     * Method to assert two json arrays are equal. Expected json array is created with passed json objects. Test will
     * throw AssertionError exception (with both expected and actual arrays) if arrays are not equal.
     *
     * @param expected Array of json objects.
     * @param actual   Json array.
     */
    void assertJsonEquals(JSONArray actual, JSONObject... expected);

    /**
     * Method to assert two json arrays are equal. Test will throw AssertionError exception (with both expected and
     * actual arrays) if arrays are not equal.
     *
     * @param expected Json array
     * @param actual   Json array.
     */
    void assertJsonEquals(JSONArray actual, JSONArray expected);

    /**
     * Method to assert two json arrays are equal. Expected json array is created with passed json objects. Test will
     * throw AssertionError exception (with both expected and actual arrays) if arrays are not equal.
     *
     * @param actual   Actual json array.
     * @param ignore   Array of fields in expected and actual results to be ignored on assertion.
     * @param expected Expected json objects to become a json array
     */
    void assertJsonEquals(JSONArray actual, String[] ignore, JSONObject... expected);

    /**
     * Method to assert two jsons are equal. Test will throw AssertionError exception (with both expected and actual
     * jsons) if jsons are not equal.
     *
     * @param expected Json object.
     * @param ignore   Array of fields in expected and actual results to be ignored on assertion.
     * @param actual   Json object.
     */
    void assertJsonEquals(JSONObject actual, String[] ignore, JSONObject expected);

    /**
     * Method to assert two json arrays are equal. Expected json array is created with passed json objects. Test will
     * throw AssertionError exception (with both expected and actual arrays) if arrays are not equal.
     *
     * @param actual   Actual response.
     * @param ignore   Array of fields in expected and actual results to be ignored on assertion.
     * @param mode     json compare mode.
     * @param expected Expected json objects to become a json array.
     */
    void assertJsonEquals(Response actual, String[] ignore, JSONCompareMode mode, JSONObject... expected);

    /**
     * Method to assert two json arrays are equal. Expected json array is created with passed json objects. Test will
     * throw AssertionError exception (with both expected and actual arrays) if arrays are not equal.
     *
     * @param actual   Actual response.
     * @param mode     json compare mode.
     * @param expected Expected json objects to become a json array.
     */
    void assertJsonEquals(Response actual, JSONCompareMode mode, JSONObject... expected);

    /**
     * Method to assert two jsons are equal. Test will throw AssertionError exception (with both expected and actual
     * jsons) if jsons are not equal.
     *
     * @param expected       Json object.
     * @param ignore         Array of fields in expected and actual results to be ignored on assertion.
     * @param actualResponse Rest-assured response.
     */
    void assertJsonEquals(Response actualResponse, String[] ignore, JSONObject expected);

    /**
     * Method to assert two json arrays are equal. Expected json array is created with passed json objects. Test will
     * throw AssertionError exception (with both expected and actual arrays) if arrays are not equal.
     *
     * @param actualResponse Rest-assured response.
     * @param ignore         Array of fields in expected and actual results to be ignored on assertion.
     * @param mode           Json array compare mode.
     * @param expected       Expected json objects to become a json array.
     */
    void assertJsonEquals(Response actualResponse, String[] ignore, JSONArrayCompareMode mode, JSONObject... expected);
}
