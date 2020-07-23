package com.mishamba.day8.model.comparator;

import com.mishamba.day8.model.entity.CustomBook;
import org.jetbrains.annotations.NotNull;

public class Comparator {
    private Comparator() {
    }

    public static int compareById(@NotNull CustomBook firstBook,
                                  @NotNull CustomBook secondBook) {
        int result = (firstBook.getId() > secondBook.getId()) ? 1 : -1;
        if (firstBook.getId() == secondBook.getId()) {
            result = 0;
        }

        return result;
    }

    public static int compareByTitle(@NotNull CustomBook firstBook,
                                     @NotNull CustomBook secondBook) {
        return firstBook.getTitle().compareTo(secondBook.getTitle());
    }

    public static int compareByAuthors(@NotNull CustomBook firstBook,
                                       @NotNull CustomBook secondBook) {
        if (firstBook.getAuthors().size() > secondBook.getAuthors().size()) {
            return 1;
        } else if (firstBook.getAuthors().size() <
                secondBook.getAuthors().size()) {
            return -1;
        }

        int resultCompare = 0;
        for (int i = 0; i < firstBook.getAuthors().size(); i++) {
            resultCompare += firstBook.getAuthors().get(i).
                    compareTo(secondBook.getAuthors().get(i));
        }

        if (resultCompare > 1) {
            resultCompare = 1;
        } else if (resultCompare < -1) {
            resultCompare = -1;
        }

        return resultCompare;
    }

    public static int compareByPages(@NotNull CustomBook firstBook,
                                     @NotNull CustomBook secondBook) {
        int resultCompare = (firstBook.getPages() >= secondBook.getPages()) ?
                1 : -1;
        if (firstBook.getPages() == secondBook.getPages()) {
            resultCompare = 0;
        }

        return resultCompare;
    }
}
