package com.mishamba.day8.controller.command.factory;

import com.mishamba.day8.controller.command.Command;
import com.mishamba.day8.controller.command.impl.*;
import com.mishamba.day8.controller.command.variant.CommandName;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static CommandProvider instance;

    private CommandProvider() {
        repository.put(CommandName.ADDBOOK, new AddBookCommand());
        repository.put(CommandName.REMOVEBOOK, new RemoveBookCommand());
        repository.put(CommandName.FINDBYAUTHORS, new FindByAuthorsCommand());
        repository.put(CommandName.FINDBYPAGES, new FindByPagesCommand());
        repository.put(CommandName.FINDBYTITLE, new FindByTitleCommand());
        repository.put(CommandName.SORTBYPAGES, new SortByPagesCommand());
        repository.put(CommandName.SORTBYTITLE, new SortByTitleCommand());
        repository.put(CommandName.WRONGCOMMAND, new WrongRequest());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }

        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException ex) {
            command = repository.get(CommandName.WRONGCOMMAND);
        }

        return command;
    }
}
