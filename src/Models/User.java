/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Heinz
 */
public class User {

    private String name;
    private String ip;
    private int port;
    
    
    public static ArrayList<User> getUsersFromUserTable(String userTable) {
        String[] rows = userTable.split("\n");
        ArrayList<User> users = new ArrayList<>();
        for (String row : rows) {
            String[] userData = row.split(" ");
            User u = new User();
            u.setName(userData[0]);
            u.setIp(userData[1]);
            u.setPort(Integer.parseInt(userData[2]));
            users.add(u);
        }
        return users;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void setIp(String ip) {
        this.ip = ip.trim();
    }

    public void setPort(int port) {
        this.port = port;
    }
}