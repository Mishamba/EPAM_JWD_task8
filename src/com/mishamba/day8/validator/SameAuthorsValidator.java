package com.mishamba.day8.validator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SameAuthorsValidator {
    public boolean haveNoSame(@NotNull ArrayList<String> authors) {
        for (String firstAuthor : authors) {
            for (String secondAuthor : authors) {
                if (firstAuthor.equals(secondAuthor)) {
                    return false;
                }
            }
        }

        return true;
    }
}
