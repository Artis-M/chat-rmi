package mediator;

import model.Message;
import model.UserList;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface ChatClientModel{
    public void connect() throws RemoteException, MalformedURLException, NotBoundException;
    public void sendMessage(Message message);
    public UserList getUserList();
    void removeUser(String username);
    void close();
}
