package br.com.unifor.socketcliente.server;

import java.util.ArrayList;
import java.util.List;

public class ListClient {
  private static ListClient instance;

  private List<Entity> clients;

  private ListClient() {
    clients = new ArrayList<>();
  }

  public static ListClient getInstance() {
    if (instance == null) {
      instance = new ListClient();
    }
    return instance;
  }

  public List<Entity> getClients() {
    return clients;
  }

  public void setClients(List<Entity> clients) {
    this.clients = clients;
  }

  public Boolean containsIp(String ip) {
    if (ip != null && !ip.equals("")) {
      for (Entity entity : clients) {
        if (entity.getIp().equals(ip)) {
          return true;
        }
      }
    }
    return false;
  }

  public Entity getEntity(String ip) {
    if (ip != null && !ip.equals("")) {
      for (Entity entity : clients) {
        if (entity.getIp().equals(ip)) {
          return entity;
        }
      }
    }
    return null;
  }
}