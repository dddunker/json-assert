package com.mbi;

import org.skyscreamer.jsonassert.JSONCompareMode;

interface IAssert {

    <T, U> void assertJsonEquals(T actual, U expected);

    Assert ignore(String[] ignore);

    Assert withMode(JSONCompareMode mode);
}
