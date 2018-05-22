package br.com.unifor.socketcliente.server.actions;

import br.com.unifor.socketcliente.server.App;
import br.com.unifor.socketcliente.server.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConectarAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Client client = new Client(App.txtIp.getText(), 5555);
      client.configurarRede();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}
