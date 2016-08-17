package com.mbi;

import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 * Created by mbi on 8/17/16.
 */
interface Assert {

    <T> void doAssertion(T actual, T expected, JSONCompareMode mode);
}
