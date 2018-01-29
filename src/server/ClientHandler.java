package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jano1 on 29.01.2018.
 */
public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner input = new Scanner(socket.getInputStream());
            while(true){
                if(input.hasNext()){
                    System.out.println(input.nextLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
