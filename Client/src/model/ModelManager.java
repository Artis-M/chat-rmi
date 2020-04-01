package model;

import Utility.UnnamedPropertyChangeSubject;
import mediator.ChatClient;
import mediator.RemoteChatModel;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;


public class ModelManager implements Model, UnnamedPropertyChangeSubject {
    private PropertyChangeSupport property;
    private ChatClient client;
    private String username;
    private boolean isConnected;

    public ModelManager(String username) throws RemoteException, NotBoundException, MalformedURLException {
        this.username = username;
        property = new PropertyChangeSupport(this);
        client = new ChatClient(this);
        this.isConnected = false;
    }

    @Override
    public void sendMessage(String message) {
        Message message1 = new Message(username, message);
        System.out.println("Message sent to client");
        client.sendMessage(message1);
    }

    @Override
    public void getMessageFromServer(Message message) {
        property.firePropertyChange("NewMessageFromServer", null, message);
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public ArrayList<String> getUserList() {
        return client.getUserList().getUserList();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void removeUser() {
        client.removeUser(username);
    }

    @Override
    public void connect() throws RemoteException, NotBoundException, MalformedURLException {
        client.connect();
        isConnected = true;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

}
