package mediator;

import model.Message;
import model.Model;
import model.UserList;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient implements ChatClientModel, RemoteListener<Message, UserList> {
private RemoteChatModel server;
    private Model model;
public ChatClient(Model model){
    this.model = model;
}
    @Override
    public void connect() throws RemoteException, MalformedURLException, NotBoundException {
        server = (RemoteChatModel) Naming.lookup("rmi://localhost:1099/chat");
        UnicastRemoteObject.exportObject(this, 0);
        server.addListener(this, "message");
        server.addUser(model.getUsername());
    }

    @Override
    public void sendMessage(Message message) {
    server.sendMessage(message);
    }

    @Override
    public UserList getUserList() {
    return server.getUsers();
    }

    @Override
    public void removeUser(String username) {
    server.removeUser(username);
    }

    @Override
    public void close() {
    }

    @Override
    public void propertyChange(ObserverEvent<Message, UserList> event) throws RemoteException {
model.getMessageFromServer(event.getValue1());
    }
}