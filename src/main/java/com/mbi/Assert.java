package com.mbi;

import org.skyscreamer.jsonassert.JSONCompareMode;

public class Assert implements IAssert {

    private JSONCompareMode mode;
    private String[] ignore;

    public Assert() {
        mode = JSONCompareMode.STRICT;
        ignore = new String[]{""};
    }

    public <T, U> void assertJsonEquals(T actual, U expected) {
        Assertion.Builder builder = new Assertion().newBuilder(mode, ignore);

        builder
                .setActual(actual)
                .setExpected(expected)
                .setMode(mode)
                .setIgnore(ignore)
                .build()
                .doAssertion();
    }

    public Assert ignore(String[] ignore) {
        this.ignore = ignore;

        return this;
    }

    public Assert withMode(JSONCompareMode mode) {
        this.mode = mode;

        return this;
    }
}