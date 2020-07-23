package com.mishamba.day8.controller.command.impl;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.command.tagFinder.TegFinder;
import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.service.exception.ServiceException;
import com.mishamba.day8.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveBookCommand implements Command {
    private static final int COMMAND_LENGTH = 10;

    @Override
    public ArrayList<CustomBook> execute(String parameter)
            throws ControllerException {
        String bookTitle = TegFinder.getInstance().formBookTitle(parameter);
        int pages = TegFinder.getInstance().formPages(parameter);
        ArrayList<String> authors = formAuthors(parameter);
        try {
            LibraryServiceImpl.getInstance().removeBook(bookTitle,
                    pages, authors);
            return LibraryServiceImpl.getInstance().selectAllBooks();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }

    }

    @Contract("_ -> new")
    private @NotNull ArrayList<String> formAuthors(@NotNull String parameters) {
        String[] authors = parameters.
                substring(COMMAND_LENGTH + 1).
                split("\\s");
        return new ArrayList<>(Arrays.asList(authors).
                subList(2, authors.length));
    }
}
