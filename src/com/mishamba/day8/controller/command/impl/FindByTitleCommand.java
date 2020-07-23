package com.mishamba.day8.controller.command.impl;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.service.exception.ServiceException;
import com.mishamba.day8.service.impl.LibraryServiceImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FindByTitleCommand implements Command {
    private static final int COMMAND_LENGTH = 11;

    @Override
    public ArrayList<CustomBook> execute(String parameter)
            throws ControllerException {
        String title = formTitle(parameter);
        try {
            return LibraryServiceImpl.getInstance().findByTitle(title);
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }

    @Contract(pure = true)
    private @NotNull String formTitle(@NotNull String parameter)
            throws ControllerException {
        try {
            return parameter.substring(COMMAND_LENGTH + 1);
        } catch (Exception ex) {
            throw new ControllerException(ex);
        }
    }
}
