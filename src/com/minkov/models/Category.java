package com.minkov.models;

public class Category {
    private String name;
    private  int id;

    public Category(String name, int id){
      setName(name);
      setId(id);
    }
  public String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  private void setId(int id) {
    this.id = id;
  }
}
