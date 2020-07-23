package com.mishamba.day8.controller.command.impl;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.service.exception.ServiceException;
import com.mishamba.day8.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FindByPagesCommand implements Command {
    private static final int COMMAND_LENGTH = 11;

    @Override
    public ArrayList<CustomBook> execute(@NotNull String parameter)
            throws ControllerException {
        int pages = formPages(parameter);
        try {
            return LibraryServiceImpl.getInstance().
                    findByPages(pages);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    private int formPages(@NotNull String parameters)
            throws ControllerException {
        try {
            return Integer.parseInt(parameters.trim().
                    substring(COMMAND_LENGTH + 1, parameters.length()));
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ControllerException(ex);
        }
    }
}
