package br.com.unifor.socketcliente.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CheckIp implements Runnable {
  TableList list = TableList.getInstance();

  public void run() {
    try {
      while (true) {
        for (Entity entity : list.getClients()) {
          ping(entity.getIp());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void ping(String ip) {
    InetAddress address;
    try {
      address = InetAddress.getByName(ip);
    } catch (UnknownHostException e) {
      System.out.println("Cannot lookup host " + ip);
      return;
    }
    try {
      if (address.isReachable(5000)) {
        long nanos = 0;
        long millis = 0;
        long iterations = 0;
        while (true) {
          iterations++;
          try {
            nanos = System.nanoTime();
            address.isReachable(500); // this invocation is the offender
            nanos = System.nanoTime() - nanos;
          } catch (IOException e) {
            System.out.println("Failed to reach host");
          }
          millis = Math.round(nanos / Math.pow(10, 6));
          System.out.println("Resposta do IP: " + address.getHostAddress() + " com de tempo=" + millis + " ms");
          try {
            Thread.sleep(Math.max(0, 1000 - millis));
          } catch (InterruptedException e) {
            break;
          }
        }
        System.out.println("Iterations: " + iterations);
      } else {
        System.out.println("Host " + address.getHostName() + " is not reachable even once.");
      }
    } catch (IOException e) {
      System.out.println("Network error.");
    }
  }
}
