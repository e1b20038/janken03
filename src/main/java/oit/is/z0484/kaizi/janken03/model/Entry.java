package oit.is.z0484.kaizi.janken03.model;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Entry {
  ArrayList<String> users = new ArrayList<>();

  public Entry() {
  }

  public void addUser(String user) {
    for (String s : this.users) {
      if (user.equals(s)) {
        return;
      }
    }
    this.users.add(user);
  }

  public ArrayList<String> getUsers() {
    return users;
  }

  public void setUsers(ArrayList<String> users) {
    this.users = users;
  }

}
