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
      ServerSocket server = new ServerSocket(55555);
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
        while ((texto = leitores.get(0).nextLine()) != null) {
          encaminharParaTodos(texto);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void encaminharParaTodos(String texto) {
      String[] params = texto.split("\\|");

      Integer cpuTotal = Integer.parseInt(App.txtCpuTotal.getText()) + Integer.parseInt(params[0]);
      App.txtCpuTotal.setText(cpuTotal.toString());
      Integer memoriaTotal = Integer.parseInt(App.txtMemoriaTotal.getText()) + Integer.parseInt(params[0]);
      App.txtMemoriaTotal.setText(cpuTotal.toString());
      Integer bloqTotal = Integer.parseInt(App.txtBloqTotal.getText()) + Integer.parseInt(params[0]);
      App.txtBloqTotal.setText(cpuTotal.toString());

      System.out.println(texto);
      escritores.get(0).println(texto);
      escritores.get(0).flush();
    }
  }
}