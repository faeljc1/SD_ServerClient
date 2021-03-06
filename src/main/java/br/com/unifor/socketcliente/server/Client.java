package br.com.unifor.socketcliente.server;

import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  private TableList list = TableList.getInstance();
  private Scanner scanner;
  private PrintWriter write;
  private String ipAddress;
  private Integer port;
  private String ip;

  public Client(String ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }

  public void configurarRede() throws Exception {
    try {
      Socket socket = new Socket(ipAddress, port);
      scanner = new Scanner(socket.getInputStream());
      write = new PrintWriter(socket.getOutputStream());
      ip = socket.getLocalAddress().toString().replace("/", "");
      if (!list.containsIp(ip)) {
        list.getClients().add(new Entity(socket, ip));
      } else {
        list.getEntity(ip).setSocket(socket);
      }
      new Thread(new Client.EscutaServidor()).start();
      list.pesquisar();
    } catch (ConnectException e) {
      App.txtLog.setText(App.txtLog.getText() + ip + ": Connection refused" + "\n");
    }
  }

  private class EscutaServidor implements Runnable {
    public void run() {
      StringBuilder sb = new StringBuilder();
      sb.append(App.txtCpu.getText()).append("|").append(App.txtMemoria.getText()).append("|").append(App.txtBloq.getText());
      write.println(sb.toString());
      write.flush();
    }
  }

  public Scanner getScanner() {
    return scanner;
  }

  public void setScanner(Scanner scanner) {
    this.scanner = scanner;
  }

  public PrintWriter getWrite() {
    return write;
  }

  public void setWrite(PrintWriter write) {
    this.write = write;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }
}