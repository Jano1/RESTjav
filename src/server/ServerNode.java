package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jano1 on 29.01.2018.
 */
public class ServerNode implements Runnable{

    private int port;
    private ServerSocket socket;
    private List<ClientHandler> clients;
    private Map<ClientHandler,Thread> threads;

    public ServerNode(int port, boolean stopped){
        this.port = port;
        clients = new ArrayList<>();
        threads = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            openSocket();
            while(true){
                listen();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() throws IOException {
        Socket client = socket.accept();
        getThreadFor(createClient(client)).start();
    }

    private ClientHandler createClient(Socket socket) {
        ClientHandler client = new ClientHandler(socket);
        clients.add(client);
        Thread thread = new Thread(client);
        threads.put(client,thread);
        return client;
    }

    private void openSocket() throws IOException {
        socket = new ServerSocket(port);
    }

    public Thread getThreadFor(ClientHandler handler){
        return threads.get(handler);
    }
}
