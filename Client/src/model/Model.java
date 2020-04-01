package model;

import Utility.UnnamedPropertyChangeSubject;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  void sendMessage(String message);
  void getMessageFromServer(Message message);
  void setUsername(String username);
  ArrayList<String> getUserList();
  String getUsername();
  void removeUser();
  void connect() throws RemoteException, NotBoundException, MalformedURLException;
  boolean isConnected();
}
