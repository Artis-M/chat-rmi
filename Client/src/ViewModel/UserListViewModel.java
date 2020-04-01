package ViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class UserListViewModel
{
  private Model model;
  private ObservableList<String> items;
  public UserListViewModel(Model model)
  {
    this.model = model;
    this.items = FXCollections.observableArrayList();
  }

  public ObservableList<String> getItems()
  {
    System.out.println("Property change for user list fired");
    items.clear();
    ArrayList<String> list = model.getUserList();
    for(int i=0;i<list.size();i++)
      items.add((list.get(i)));
    return items;
  }
}