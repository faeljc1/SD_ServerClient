package br.com.unifor.socketcliente.server;

import java.net.Socket;

public class Entity {
  private Socket socket;
  private String ip;

  public Entity(Socket socket, String ip) {
    this.socket = socket;
    this.ip = ip;
  }

  public Entity() {
  }

  public Socket getSocket() {
    return socket;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }
}
