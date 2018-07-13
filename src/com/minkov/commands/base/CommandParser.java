package com.minkov.commands.base;

import com.minkov.commands.Command;

public interface CommandParser {
    Command parse(String commandString);
}
