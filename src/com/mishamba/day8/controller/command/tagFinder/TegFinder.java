package com.mishamba.day8.controller.command.tagFinder;

import com.mishamba.day8.controller.exception.ControllerException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TegFinder {
    private static final String PAGES_REGEX = "(?<=\\s)\\d+(?!>\\s)";
    private static final String BOOK_NAME_REGEX = "(?<=\\w{7}\\s)\\w+";
    private static TegFinder instance;

    private TegFinder() {}

    public static TegFinder getInstance() {
        if (instance == null) {
            instance = new TegFinder();
        }

        return instance;
    }

    public String formBookTitle(String command)
            throws ControllerException {
        Pattern pattern = Pattern.compile(BOOK_NAME_REGEX);
        Matcher matcher = pattern.matcher(command);
        try {
            matcher.find();
            return matcher.group(0);
        } catch (IllegalStateException | IndexOutOfBoundsException ex) {
            throw new ControllerException("can't match book title", ex);
        }
    }

    public int formPages(String command) throws ControllerException {
        Pattern pattern = Pattern.compile(PAGES_REGEX);
        Matcher matcher = pattern.matcher(command);
        try {
            matcher.find();
            return Integer.parseInt(matcher.group(0));
        } catch (IllegalStateException | IndexOutOfBoundsException ex) {
            throw new ControllerException("can't match pages" ,ex);
        }
    }

}
