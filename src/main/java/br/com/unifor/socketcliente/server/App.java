package br.com.unifor.socketcliente.server;

import br.com.unifor.socketcliente.server.actions.ConectarAction;
import br.com.unifor.socketcliente.server.actions.StartServerAction;

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

  public static JButton btnStart;
  public static JButton btnConnect;

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

    btnStart = new JButton("Start");
    btnStart.setBounds(431, 3, 80, 25);
    btnStart.addActionListener(new StartServerAction());
    contentPane.add(btnStart);

    JLabel lblNewLabel = new JLabel("LOG");
    lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel.setBounds(91, 16, 61, 16);
    contentPane.add(lblNewLabel);

    JLabel lblNewLabel_1 = new JLabel("LOCAL");
    lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel_1.setBounds(91, 225, 61, 16);
    contentPane.add(lblNewLabel_1);

    JLabel lblNewLabel_2 = new JLabel("CPU");
    lblNewLabel_2.setBounds(6, 249, 61, 16);
    contentPane.add(lblNewLabel_2);

    JLabel lblNewLabel_3 = new JLabel("MEM");
    lblNewLabel_3.setBounds(6, 277, 61, 16);
    contentPane.add(lblNewLabel_3);

    JLabel lblNewLabel_4 = new JLabel("BLOQ");
    lblNewLabel_4.setBounds(6, 305, 61, 16);
    contentPane.add(lblNewLabel_4);

    txtCpu = new JTextField();
    txtCpu.setBounds(59, 244, 201, 26);
    txtCpu.setText("100");
    contentPane.add(txtCpu);
    txtCpu.setColumns(10);

    txtMemoria = new JTextField();
    txtMemoria.setBounds(59, 272, 201, 26);
    txtMemoria.setText("100");
    contentPane.add(txtMemoria);
    txtMemoria.setColumns(10);

    txtBloq = new JTextField();
    txtBloq.setBounds(59, 300, 201, 26);
    txtBloq.setText("100");
    contentPane.add(txtBloq);
    txtBloq.setColumns(10);

    criaJTable();

    txtLog = new JTextPane();
    txtLog.setEditable(false);
    barraRolagemTexto = new JScrollPane(txtLog);
    barraRolagemTexto.setBounds(6, 33, 254, 187);
    contentPane.add(barraRolagemTexto);

    barraRolagemTabela = new JScrollPane(tabela);
    barraRolagemTabela.setBounds(269, 33, 248, 155);
    contentPane.add(barraRolagemTabela);

    btnConnect = new JButton("Connect");
    btnConnect.setFont(new Font("Lucida Grande", Font.BOLD, 11));
    btnConnect.setBounds(426, 195, 90, 25);
    contentPane.add(btnConnect);

    JLabel lblNewLabel_5 = new JLabel("SISTEMA");
    lblNewLabel_5.setFont(new Font("Lucida Grande", Font.BOLD, 13));
    lblNewLabel_5.setBounds(357, 225, 85, 16);
    contentPane.add(lblNewLabel_5);

    JLabel label = new JLabel("CPU");
    label.setBounds(269, 249, 61, 16);
    contentPane.add(label);

    JLabel label_1 = new JLabel("MEM");
    label_1.setBounds(269, 277, 61, 16);
    contentPane.add(label_1);

    JLabel label_2 = new JLabel("BLOQ");
    label_2.setBounds(269, 305, 61, 16);
    contentPane.add(label_2);

    txtCpuTotal = new JTextField();
    txtCpuTotal.setEnabled(false);
    txtCpuTotal.setColumns(10);
    txtCpuTotal.setBounds(311, 244, 205, 26);
    contentPane.add(txtCpuTotal);

    txtMemoriaTotal = new JTextField();
    txtMemoriaTotal.setEnabled(false);
    txtMemoriaTotal.setColumns(10);
    txtMemoriaTotal.setBounds(311, 272, 205, 26);
    contentPane.add(txtMemoriaTotal);

    txtBloqTotal = new JTextField();
    txtBloqTotal.setEnabled(false);
    txtBloqTotal.setColumns(10);
    txtBloqTotal.setBounds(311, 300, 205, 26);
    contentPane.add(txtBloqTotal);

    txtIp = new JTextField();
    txtIp.setColumns(10);
    txtIp.setBounds(269, 195, 150, 26);
    contentPane.add(txtIp);

    btnConnect.addActionListener(new ConectarAction());

    btnConnect.setEnabled(false);
    txtIp.setEnabled(false);
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