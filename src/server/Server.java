package server;

import jdk.jfr.internal.LogLevel;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jano1 on 29.01.2018.
 */
public class Server {
    private List<ServerNode> nodes;
    private Map<ServerNode,Thread> threads;

    public ServerNode createNode(int port){
        ServerNode node = new ServerNode(port,false);
        nodes.add(node);
        Thread thread = new Thread(node);
        threads.put(node,thread);
        return node;
    }

    public Thread getThreadFor(ServerNode node){
        return threads.get(node);
    }

    public static void main(String[] args){
        Server server = new Server();
        server.getThreadFor(server.createNode(80)).start();
        Logger.getGlobal().log(Level.INFO,"moin");
    }
}
