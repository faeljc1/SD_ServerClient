package br.com.unifor.socketcliente.server;

public class Program {
  public static void main(String[] args) {
    new Thread(new ExecutaServer()).start();

    /*try {
      Client client = new Client("localhost", 5555);
      client.configurarRede();
    } catch (Exception e) {
      e.printStackTrace();
    }*/
  }

  private static class ExecutaServer implements Runnable {
    public ExecutaServer() {
    }

    public void run() {
      try {
        new Server();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
