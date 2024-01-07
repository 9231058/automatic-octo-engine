/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokekclienttest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Parham Alvani
 */
public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("Connecting to " + serverName + " on port " + port);
        try (Socket client = new Socket(serverName, port)) {
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            while (!client.isClosed()) {
                System.out.println("Server says " + in.readUTF());
            }
        } catch (IOException exception) {
            System.out.println("Something goes wrong");
        }
    }
}
