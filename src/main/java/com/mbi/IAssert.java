package com.mbi;

interface IAssert {

    <T, U> void jsonEquals(T actual, U expected);

    Assert ignore(String[] ignore);

    Assert withMode(CompareMode mode);
}
