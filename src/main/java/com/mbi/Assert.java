package com.mbi;

public class Assert implements IAssert {

    private CompareMode mode;
    private String[] ignore;

    public Assert() {
        // Default mode
        mode = CompareMode.NOT_ORDERED;
        // Default fields to ignore
        ignore = new String[]{""};
    }

    public <T, U> void jsonEquals(T expected, U actual) {
        BuilderAssertion.Builder builder = new BuilderAssertion().newBuilder(mode, ignore);

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

    public Assert withMode(CompareMode mode) {
        this.mode = mode;

        return this;
    }
}