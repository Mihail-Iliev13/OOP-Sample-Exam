package com.minkov.commands;

import com.minkov.commands.base.CommandParser;

import java.util.Arrays;

public class CommandParserImpl implements CommandParser {

  @Override
    public Command parse(String commandString) {
        String[] commands = commandString.split(" ");
        Command command = new Command();
        switch (commands[0]){
          case "add":
            if(Character.isDigit(commands[1].charAt(0))){
              command.setType(CommandType.ADD_CATEGORY_TO_PRODUCT);
            } else {
              command.setType(CommandType.ADD_PRODUCT);
            }
            break;
          case "list":
            if(commands[1].equals("products")) {
              command.setType(CommandType.LIST_PRODUCTS);
            } else {
              command.setType(CommandType.LIST_PRODUCTS_BY_CATEGORY);
            }
            break;
          case "find":
            command.setType(CommandType.FIND_PRODUCT_BY_ID);
            break;
          case "filter":
            command.setType(CommandType.FILTER_PRODUCT_BY_NAME);
            break;
          case "exit":
            command.setType(CommandType.EXIT);
            break;
            default:
              break;
        }

      command.args.addAll(Arrays.asList(commands).subList(1, commands.length));

        return command;
    }
}
