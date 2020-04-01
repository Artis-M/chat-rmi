package mediator;

import model.Message;
import model.Model;
import model.UserList;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeAction;
import utility.observer.subject.PropertyChangeProxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer implements RemoteChatModel {
    private PropertyChangeAction<Message, UserList> property;
    private Model model;
    public ChatServer(Model model) throws RemoteException, MalformedURLException {
        this.model = model;
        startRegistry();
        UnicastRemoteObject.exportObject(this, 1099);
        Naming.rebind("chat", this);
        this.property= new PropertyChangeProxy<>(this, true);
        System.out.println("Server started...");
    }

    public static void startRegistry() throws RemoteException {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started......");
        } catch (java.rmi.server.ExportException e) {
            System.out.println("Registry already started perhaps?....." + e.getMessage());
        }
    }


    @Override
    public boolean addListener(GeneralListener<Message, UserList> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<Message, UserList> listener, String... propertyNames) throws RemoteException {
        return property.removeListener(listener, propertyNames);
    }

    @Override
    public void addUser(String name) {
        model.addUser(name);
    }

    @Override
    public void sendMessage(Message message) {
        property.firePropertyChange("message", message, null);
    }

    @Override
    public UserList getUsers() {
        return model.getUsers();
    }

    @Override
    public void removeUser(String username) {
        model.removeUser(username);
    }
}
