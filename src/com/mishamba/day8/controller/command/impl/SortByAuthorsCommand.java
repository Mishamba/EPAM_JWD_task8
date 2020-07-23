package com.mishamba.day8.controller.command.impl;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;
import com.mishamba.day8.service.exception.ServiceException;
import com.mishamba.day8.service.impl.LibraryServiceImpl;

import java.util.ArrayList;

public class SortByAuthorsCommand implements Command {
    @Override
    public ArrayList<CustomBook> execute(String parameter) throws ControllerException {
        try {
            return LibraryServiceImpl.getInstance().sortByAuthors();
        } catch (ServiceException ex) {
            throw new ControllerException(ex);
        }
    }
}
