package com.mishamba.day8.validator;

public class NegativePagesValidator {
    private final int MAX_COUNT_OF_PAGES = Integer.MAX_VALUE;
    private final int MIN_COUNT_OF_PAGES = 0;

    public boolean isNegative(int pages) {
        return (pages < MAX_COUNT_OF_PAGES) && (pages > MIN_COUNT_OF_PAGES);
    }

}
