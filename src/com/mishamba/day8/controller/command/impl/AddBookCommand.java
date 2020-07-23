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

public class AddBookCommand implements Command {
    private static final int COMMAND_LENGTH = 7;

    @Override
    public ArrayList<CustomBook> execute(@NotNull String command)
                throws ControllerException {
        String bookTitle = TegFinder.getInstance().formBookTitle(command);
        int pages = TegFinder.getInstance().formPages(command);
        ArrayList<String> authors = formAuthors(command);
        try {
            LibraryServiceImpl.getInstance().addBook(bookTitle,
                    pages, authors);
            return LibraryServiceImpl.getInstance().selectAllBooks();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Contract("_ -> new")
    private @NotNull ArrayList<String> formAuthors (@NotNull String command) {
        String[] authors = command.
                substring(COMMAND_LENGTH + 1).
                split("\\s");
        return new ArrayList<>(Arrays.asList(authors).
                subList(2, authors.length));
    }
}
