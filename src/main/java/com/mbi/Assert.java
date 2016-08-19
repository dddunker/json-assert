package com.mbi;

import com.sun.istack.internal.Nullable;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class Assert implements IAssert {

    private JSONCompareMode mode;
    private String[] ignore;

    public Assert() {
        mode = JSONCompareMode.LENIENT;
        ignore = new String[]{""};
    }

    public <T, U> void jsonEquals(T actual, U expected) {
        Assertion.Builder builder = new Assertion().newBuilder(mode, ignore);

        builder
                .setActual(actual)
                .setExpected(expected)
                .setMode(mode)
                .setIgnore(ignore)
                .build()
                .doAssertion();
    }

    @Nullable
    public Assert ignore(String[] ignore) {
        this.ignore = ignore;

        return this;
    }

    @Nullable
    public Assert withMode(JSONCompareMode mode) {
        this.mode = mode;

        return this;
    }
}