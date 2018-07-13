package com.minkov.commands;

import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.List;

public class Command {

    private CommandType type;
    List<String> args;

    Command(){
      args = new ArrayList<>();
    }

    public CommandType getType() {
        return type;
    }

     void setType(CommandType type) {
        this.type = type;
    }

    public List<String> getArgs() {
        return new ArrayList<>(args);
    }
}
