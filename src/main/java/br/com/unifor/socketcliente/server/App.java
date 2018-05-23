package br.com.unifor.socketcliente.server;

import br.com.unifor.socketcliente.server.actions.ConectarAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class App extends JFrame {

  public static JPanel contentPane;
  public static JTextField txtCpu;
  public static JTextField txtMemoria;
  public static JTextField txtBloq;
  public static JTextField txtCpuTotal;
  public static JTextField txtMemoriaTotal;
  public static JTextField txtBloqTotal;
  public static JTextField txtIp;

  public static JTable tabela;
  public static JScrollPane barraRolagemTabela;
  public static DefaultTableModel modelo = new DefaultTableModel();

  public static JTextPane txtLog;
  public static JScrollPane barraRolagemTexto;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          App frame = new App();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public App() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 533, 343);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    JLabel lblNewLabel = new JLabel("LOG");
    lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel.setBounds(91, 16, 61, 16);
    contentPane.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("LOCAL");
    lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel_1.setBounds(91, 192, 61, 16);
    contentPane.add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("CPU");
    lblNewLabel_2.setBounds(6, 216, 61, 16);
    contentPane.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("MEM");
    lblNewLabel_3.setBounds(6, 244, 61, 16);
    contentPane.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("BLOQ");
    lblNewLabel_4.setBounds(6, 272, 61, 16);
    contentPane.add(lblNewLabel_4);

    txtCpu = new JTextField();
    txtCpu.setBounds(59, 211, 201, 26);
    txtCpu.setText("100");
    contentPane.add(txtCpu);
    txtCpu.setColumns(10);

    txtMemoria = new JTextField();
    txtMemoria.setBounds(59, 239, 201, 26);
    txtMemoria.setText("100");
    contentPane.add(txtMemoria);
    txtMemoria.setColumns(10);

    txtBloq = new JTextField();
    txtBloq.setBounds(59, 267, 201, 26);
    txtBloq.setText("100");
    contentPane.add(txtBloq);
    txtBloq.setColumns(10);

    criaJTable();

    txtLog = new JTextPane();
    txtLog.setEditable(false);
    barraRolagemTexto = new JScrollPane(txtLog);
    barraRolagemTexto.setBounds(6, 33, 254, 148);
    contentPane.add(barraRolagemTexto);

    barraRolagemTabela = new JScrollPane(tabela);
    barraRolagemTabela.setBounds(272, 33, 240, 120);
    contentPane.add(barraRolagemTabela);

    JButton btnSubmit = new JButton("OK");
    btnSubmit.setBounds(431, 157, 80, 29);
    contentPane.add(btnSubmit);

    JLabel lblNewLabel_5 = new JLabel("SISTEMA");
    lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel_5.setBounds(357, 192, 85, 16);
    contentPane.add(lblNewLabel_5);

    JLabel label = new JLabel("CPU");
    label.setBounds(269, 216, 61, 16);
    contentPane.add(label);

    JLabel label_1 = new JLabel("MEM");
    label_1.setBounds(269, 244, 61, 16);
    contentPane.add(label_1);

    JLabel label_2 = new JLabel("BLOQ");
    label_2.setBounds(269, 272, 61, 16);
    contentPane.add(label_2);

    txtCpuTotal = new JTextField();
    txtCpuTotal.setEnabled(false);
    txtCpuTotal.setColumns(10);
    txtCpuTotal.setBounds(311, 211, 201, 26);
    contentPane.add(txtCpuTotal);

    txtMemoriaTotal = new JTextField();
    txtMemoriaTotal.setEnabled(false);
    txtMemoriaTotal.setColumns(10);
    txtMemoriaTotal.setBounds(311, 239, 201, 26);
    contentPane.add(txtMemoriaTotal);

    txtBloqTotal = new JTextField();
    txtBloqTotal.setEnabled(false);
    txtBloqTotal.setColumns(10);
    txtBloqTotal.setBounds(311, 267, 201, 26);
    contentPane.add(txtBloqTotal);

    txtIp = new JTextField();
    txtIp.setColumns(10);
    txtIp.setBounds(272, 158, 150, 26);
    contentPane.add(txtIp);

    btnSubmit.addActionListener(new ConectarAction());

    new Thread(new ExecuteServer()).start();
  }

  private void criaJTable() {
    modelo = new DefaultTableModel() {
      private static final long serialVersionUID = 1L;

      public boolean isCellEditable(int row, int col) {
        return false;
      }
    };
    tabela = new JTable(modelo);
    modelo.addColumn("IP");
    modelo.addColumn("Porta");
    tabela.getColumnModel().getColumn(0).setPreferredWidth(80);
    tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
    //tabela.addMouseListener(new ActionSelecionarTabela());
  }
}