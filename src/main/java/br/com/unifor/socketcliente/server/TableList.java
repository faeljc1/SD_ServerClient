package br.com.unifor.socketcliente.server;

import java.util.ArrayList;
import java.util.List;

public class TableList {
  private static TableList uniqueInstance = new TableList();
  private List<Entity> clients;

  private TableList() {
    clients = new ArrayList<>();
  }

  public static TableList getInstance() {
    return uniqueInstance;
  }

  public synchronized void pesquisar() {
    App.modelo.setNumRows(0);
    for (Entity entity : clients) {
      App.modelo.addRow(new Object[]{entity.getIp(),
          entity.getSocket().getLocalPort()});
    }
  }

  public void removeItemTabela() {
    int cont = 1;
    int[] selRows = App.tabela.getSelectedRows();
    if (selRows.length != 0) {
      clients.remove(selRows[0]);
      for (int i = 1; i < selRows.length; i++) {
        clients.remove(selRows[i] - cont);
        cont++;
      }
    }
  }

  public void removeItemTabela(List<String> removidos) {
    Entity entity;
    while (!removidos.isEmpty()) {
      for (int i = 0; i < clients.size(); i++) {
        entity = clients.get(i);
        if (removidos.get(0).equals(entity.getIp())) {
          clients.remove(i);
          removidos.remove(0);
          break;
        }
      }
    }

  }

  public void removeAllTabela() {
    App.modelo.setNumRows(0);
    clients.clear();
  }

  public synchronized List<Entity> getClients() {
    return clients;
  }

  public void setClients(List<Entity> clients) {
    this.clients = clients;
  }

  public synchronized Boolean containsIp(String ip) {
    if (ip != null && !ip.equals("")) {
      for (Entity entity : clients) {
        if (entity.getIp().equals(ip)) {
          return true;
        }
      }
    }
    return false;
  }

  public synchronized Entity getEntity(String ip) {
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
