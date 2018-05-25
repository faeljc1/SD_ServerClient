package br.com.unifor.socketcliente.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
  private TableList list = TableList.getInstance();
  private Scanner scanner;
  private PrintWriter writer;
  private String ip;

  public Server() {
    try {
      ServerSocket server = new ServerSocket(5555);
      while (true) {
        Socket socket = server.accept();
        new Thread(new CheckIp()).start();
        ip = socket.getInetAddress().toString().replace("/", "");
        if (!list.containsIp(ip)) {
          list.getClients().add(new Entity(socket, ip));
        } else {
          list.getEntity(ip).setSocket(socket);
        }
        new Thread(new EscutaCliente(socket)).start();
        list.pesquisar();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private class EscutaCliente implements Runnable {
    public EscutaCliente(Socket socket) {
      try {
        scanner = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void run() {
      try {
        String texto;
        while ((texto = scanner.nextLine()) != null) {
          encaminharParaTodos(texto);
        }

      } catch (NoSuchElementException e) {
        App.txtLog.setText(App.txtLog.getText() + ip + ": Connection crashed" + "\n");
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    private void encaminharParaTodos(String texto) {
      String[] params = texto.split("\\|");

      Integer cpu = !App.txtCpuTotal.getText().equals("") ? Integer.parseInt(App.txtCpuTotal.getText()) : 0;
      Integer cpuTotal = cpu + Integer.parseInt(params[0]);
      App.txtCpuTotal.setText(cpuTotal.toString());

      Integer memoria = !App.txtMemoriaTotal.getText().equals("") ? Integer.parseInt(App.txtMemoriaTotal.getText()) : 0;
      Integer memoriaTotal = memoria + Integer.parseInt(params[0]);
      App.txtMemoriaTotal.setText(memoriaTotal.toString());

      Integer bloq = !App.txtBloqTotal.getText().equals("") ? Integer.parseInt(App.txtBloqTotal.getText()) : 0;
      Integer bloqTotal = bloq + Integer.parseInt(params[0]);
      App.txtBloqTotal.setText(bloqTotal.toString());

      App.txtLog.setText(App.txtLog.getText() + ip + "  -  " + texto + "\n");

      StringBuilder sb = new StringBuilder();
      sb.append(App.txtCpu.getText()).append("|").append(App.txtMemoria.getText()).append("|").append(App.txtBloq.getText());
      writer.println(sb.toString());
      writer.flush();
    }
  }
}