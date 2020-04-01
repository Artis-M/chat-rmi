package ViewModel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Message;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatViewModel implements PropertyChangeListener
{
  private ObservableList<String> listView;
  private Model model;

  public ChatViewModel(Model model)
  {
    this.model = model;
    this.listView = FXCollections.observableArrayList();
    this.model.addListener(this);
  }

public boolean isConnected(){
  return model.isConnected();
}

  public void sendMessage(String message){
model.sendMessage(message);
  }

  public void connect() throws RemoteException, NotBoundException, MalformedURLException {
    model.connect();
  }
  public ObservableList<String> getItems()
  {
    return listView;
  }
  public void setUsername(String usernameString){
model.setUsername(usernameString);
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->
            {
              if(evt.getPropertyName().equals("NewMessageFromServer"))
              {
                listView.add((((Message) evt.getNewValue()).getFullMessage()));
              }
            }
    );
  }
}
