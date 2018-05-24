package br.com.unifor.socketcliente.server.actions;

import br.com.unifor.socketcliente.server.App;
import br.com.unifor.socketcliente.server.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConectarAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      App.txtCpuTotal.setText(App.txtCpu.getText());
      App.txtMemoriaTotal.setText(App.txtMemoria.getText());
      App.txtBloqTotal.setText(App.txtBloq.getText());

      String[] adress = App.txtIp.getText().split(":");
      Client client = new Client(adress[0], Integer.parseInt(adress[1]));
      client.configurarRede();
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }
}
