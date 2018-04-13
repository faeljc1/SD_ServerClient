package br.com.unifor.socketcliente.server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  private Scanner scanner;
  private PrintWriter write;
  private String ipAddress;
  private int port;

  public Client(String ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  public void configurarRede() throws Exception {
    Socket socket = new Socket(ipAddress, port);
    scanner = new Scanner(System.in);
    write = new PrintWriter(socket.getOutputStream());
    new Thread(new Client.EscutaServidor()).start();
  }

  private class EscutaServidor implements Runnable {
    public void run() {
      try {
        String texto;
        while ((texto = scanner.nextLine()) != null) {
          write.println(texto);
          write.flush();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}