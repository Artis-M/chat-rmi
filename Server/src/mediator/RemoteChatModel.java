package mediator;

import model.Message;
import model.UserList;
import utility.observer.subject.RemoteSubject;

import java.rmi.RemoteException;

public interface RemoteChatModel extends RemoteSubject<Message, UserList> {

    void addUser(String name) throws RemoteException;

    void sendMessage(Message message) throws RemoteException;

    UserList getUsers() throws RemoteException;

    void removeUser(String username) throws RemoteException;

}
