package mediator;

import model.Message;
import model.UserList;
import utility.observer.subject.LocalSubject;
import utility.observer.subject.RemoteSubject;

public interface RemoteChatModel extends RemoteSubject<Message, UserList>{

    void addUser(String name);

    void sendMessage(Message message);

    UserList getUsers();

    void removeUser(String username);

}
