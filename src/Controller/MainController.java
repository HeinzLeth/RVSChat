/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.User;
import Views.MainView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Heinz
 */
public class MainController implements MainView.TestListener {

    private final int SERVERPORT = 2534;
    private MainView mainView;
    private User user;
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private ArrayList<User> users;
    private boolean connected = false;

    public void start() {
        mainView = new MainView();
        mainView.setVisible(true);
        mainView.setLocationRelativeTo(null);
        mainView.registerListener(this);
        createUser();
    }

    private void createUser(){
        this.user = new User();
        user.setIp(mainView.getTf_ipText());
        user.setName(mainView.getTf_userText());
        user.setPort(Integer.parseInt(mainView.getTf_portText()));
    }
    
    private void connect() {
        try {

            String ipServer = mainView.getTf_ipText();
            String ipClient = ipServer;
            int portClient = Integer.parseInt(mainView.getTf_portText());

            Socket mySocket = new Socket();
            mySocket.connect(new InetSocketAddress(ipServer, SERVERPORT));

            ServerSocket ssocket = new ServerSocket();
            ssocket.bind(new InetSocketAddress(portClient));

            printWriter = new PrintWriter(mySocket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));

            BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
            String userInput = "";

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loginToServer() {
        try {
            printWriter.write(String.format("%s %s %s\n", "n", user.getName(),user.getPort()));
            printWriter.flush();
            String answer = bufferedReader.readLine();
            if (answer.equals("s")) {
                connected = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void askForClientTable() {
        try {
            printWriter.write("t\n");
            printWriter.flush();
            String answer = bufferedReader.readLine();
            String userTable = "";
            for (int i = 0; i < Integer.valueOf(String.valueOf(answer.charAt(2))); i++) {
                userTable += bufferedReader.readLine() + "\n";
            }
            this.users = User.getUsersFromUserTable(userTable);
            mainView.getChat().setUsers(users);

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void connectToServer() {
        connect();
        loginToServer();
        if(connected)
            askForClientTable();
    }

    @Override
    public void sendMessage(String message) {
        
    }

}
