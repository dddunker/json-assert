package com.mbi;

import org.skyscreamer.jsonassert.JSONCompareMode;

public class Assert {

    private Object actual;
    private Object expected;
    private JSONCompareMode mode = JSONCompareMode.LENIENT;
    private String[] ignore = {""};

    // Private constructor
    private Assert() {
    }

    public static Builder newBuilder() {
        return new Assert().new Builder();
    }

    private void jsonEquals() {
        new Assertion(this.actual, this.expected, this.mode, this.ignore);
    }

    public class Builder {

        // Private constructor
        private Builder() {
        }

        public Builder setMode(JSONCompareMode mode) {
            Assert.this.mode = mode;

            return this;
        }

        public Builder ignore(String[] ignore) {
            Assert.this.ignore = ignore;

            return this;
        }

        public <T, U> Builder setJsons(T expected, U actual) {
            Assert.this.expected = expected;
            Assert.this.actual = actual;

            return this;
        }

        public void assertEquals() {
            Assert.this.jsonEquals();
        }
    }
}