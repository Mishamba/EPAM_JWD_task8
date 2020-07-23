package com.mishamba.day8.controller.command.impl;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.service.exception.ServiceException;
import com.mishamba.day8.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FindByAuthorsCommand implements Command {
    private static final int COMMAND_LENGTH = 13;
    @Override
    public ArrayList<CustomBook> execute(@NotNull String parameter) throws ControllerException {
        String[] authors = formAuthors(parameter);
        try {
            return LibraryServiceImpl.getInstance().
                    findByAuthors(authors);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private String[] formAuthors(@NotNull String parameters) {
        if (parameters.length() < COMMAND_LENGTH + 1) {
            return new String[]{};
        } else {
            return parameters.trim().substring(COMMAND_LENGTH + 1, parameters.length()).
                    split("\\s");
        }
    }
}
