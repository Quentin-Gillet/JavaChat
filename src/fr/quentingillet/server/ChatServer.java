package fr.quentingillet.server;

import fr.quentingillet.server.thread.UserThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public ChatServer(int port){
        this.port = port;
    }

     public void execute(){
        try(ServerSocket serverSocket = new ServerSocket(port)){

            System.out.println("Chat Server is listening on port " + port);

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();
            }

        }catch (IOException e){
            System.out.println("Error in the server: " + e.getMessage());
            e.printStackTrace();
        }
     }
    /**
     * Returns true if there are other users connected (not count the actually connected user)
     */
    public boolean hasUsers() {
        return !this.userNames.isEmpty();
    }

    /**
     * Returns name of all users
     */
    public Set<String> getUserNames() {
        return this.userNames;
    }

    /**
     * Stores username of the newly connected client.
     */
    public void addUserName(String userName) {
        this.userNames.add(userName);
    }

    /**
     * Delivers a message from one user to others (broadcasting)
     */
    public void broadcast(String message, UserThread excludeUser) {
        for(UserThread aUser :  userThreads){
            if(aUser != excludeUser){
                aUser.sendMessage(message);
            }
        }
    }
    /**
     * When a client is disconnected, removes the associated username and UserThread
     */
    public void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed){
            userThreads.remove(aUser);
            System.out.println("User " + userName + " left");
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java ChatServer <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.execute();
    }

}
