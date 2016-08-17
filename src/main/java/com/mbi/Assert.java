package com.mbi;

/**
 * Created by mbi on 8/17/16.
 */
public interface Assert {

    <T> void assertJsonEquals(T actual, T expected, String[] ignore, CompareMode mode);
}
