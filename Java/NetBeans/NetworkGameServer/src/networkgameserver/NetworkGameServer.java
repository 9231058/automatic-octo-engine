/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkgameserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Parham Alvani
 */
public class NetworkGameServer extends Thread {

    private final ServerSocket serverSocket;
    private String player1LastMove;
    private String player2LastMove;
    private boolean isPlayer1Turn;

    public NetworkGameServer() throws IOException {
        this.serverSocket = new ServerSocket(1373);
        serverSocket.setSoTimeout(10000);
        isPlayer1Turn = true;
    }

    @Override
    public void run() {
        System.out.println("Waiting for first player ...");
        try (Socket player1 = serverSocket.accept()) {
            System.out.println("Waiting for second player ...");
            try (Socket player2 = serverSocket.accept()) {
                while (true) {
                    if (isPlayer1Turn) {
                        DataInputStream in = new DataInputStream(player1.getInputStream());
                        player1LastMove = in.readUTF();
                        DataOutputStream out = new DataOutputStream(player2.getOutputStream());
                        out.writeUTF(player1LastMove);
                        if (in.readUTF().equals("Player1Win")) {
                            break;
                        }
                        isPlayer1Turn = false;
                    } else {
                        DataInputStream in = new DataInputStream(player2.getInputStream());
                        player2LastMove = in.readUTF();
                        DataOutputStream out = new DataOutputStream(player1.getOutputStream());
                        out.writeUTF(player2LastMove);
                        if (in.readUTF().equals("Player2Win")) {
                            break;
                        }
                        isPlayer1Turn = true;
                    }
                }
            } catch (IOException ex) {
                System.out.println("second player joining process failed");
            }
        } catch (IOException ex) {
            System.out.println("first player joining process failed");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Thread t = new NetworkGameServer();
            t.start();
        } catch (IOException e) {
            System.out.println("Something goes wrong");
        }
    }

}
