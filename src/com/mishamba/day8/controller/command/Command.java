package com.mishamba.day8.controller.command;

import com.mishamba.day8.controller.exception.ControllerException;
import com.mishamba.day8.model.entity.CustomBook;

import java.util.ArrayList;

public interface Command {
    ArrayList<CustomBook> execute(String parameter) throws ControllerException;
}
