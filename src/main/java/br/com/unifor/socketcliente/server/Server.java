package br.com.unifor.socketcliente.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

  public Server() {
    try {
      ServerSocket server = new ServerSocket(6688);
      while (true) {
        Socket socket = server.accept();
        new Thread(new EscutaCliente(socket)).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class EscutaCliente implements Runnable {
    Scanner leitor;
    PrintWriter escritor;
    String enderecoCliente;
    List<PrintWriter> escritores = new ArrayList<>();
    List<Scanner> leitores = new ArrayList<>();

    public EscutaCliente(Socket socket) {
      try {
        enderecoCliente = socket.getInetAddress().toString() + ":" + socket.getPort();
        enderecoCliente = enderecoCliente.replace("/", "");
        leitores.add(new Scanner(socket.getInputStream()));
        escritores.add(new PrintWriter(socket.getOutputStream()));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void run() {
      try {
        String texto;
        while ((texto = leitor.nextLine()) != null) {
          encaminharParaTodos(texto);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void encaminharParaTodos(String texto) {
      System.out.println(texto);
      escritor.println(texto);
      escritor.flush();
    }
  }
}