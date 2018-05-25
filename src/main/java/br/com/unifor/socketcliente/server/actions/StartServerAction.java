package br.com.unifor.socketcliente.server.actions;

import br.com.unifor.socketcliente.server.App;
import br.com.unifor.socketcliente.server.ExecuteServer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartServerAction implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    App.txtCpuTotal.setText(App.txtCpu.getText());
    App.txtMemoriaTotal.setText(App.txtMemoria.getText());
    App.txtBloqTotal.setText(App.txtBloq.getText());

    new Thread(new ExecuteServer()).start();

    App.btnStart.setEnabled(false);
    App.txtCpu.setEnabled(false);
    App.txtMemoria.setEnabled(false);
    App.txtBloq.setEnabled(false);

    App.txtIp.setEnabled(true);
    App.btnConnect.setEnabled(true);
  }
}
