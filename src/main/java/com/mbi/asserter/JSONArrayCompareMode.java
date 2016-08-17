package com.mbi.asserter;

/**
 * Created by mbi on 8/15/16.
 */
public enum JSONArrayCompareMode {

    // extensible
    EXTENSIBLE_ARRAY(true, false),

    // strict
    STRICT_ARRAY(false, true),

    // ext + str
    EXTENSIBLE_STRICT_ARRAY(true, true),

    // not ext + not strict
    NOT_EXT_NOT_STR_ARRAY(false, false);

    private final boolean extensible;
    private final boolean strictOrder;

    JSONArrayCompareMode(boolean extensible, boolean strictOrder) {
        this.extensible = extensible;
        this.strictOrder = strictOrder;
    }

    public boolean isExtensible() {
        return extensible;
    }

    public boolean isStrictOrder() {
        return strictOrder;
    }
}
