import mediator.ChatClientConnector;
import mediator.ChatServer;
import model.Model;
import model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class Server
{
    public static void main(String[] args) throws MalformedURLException, RemoteException {
        Model model = new ModelManager();
        ChatServer chatServer = new ChatServer(model);
    }
}
