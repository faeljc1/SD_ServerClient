package br.com.unifor.socketcliente.server;

public class ExecuteServer implements Runnable {
  public ExecuteServer() {
  }

  public void run() {
    try {
      new Server();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}