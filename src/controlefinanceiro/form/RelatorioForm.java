package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import ui.util.layouts.ParagraphLayout;
import controlefinanceiro.control.RelatorioControl;
import controlefinanceiro.dao.entidade.Categoria;
import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.dao.entidade.Lancamento;

public class RelatorioForm extends JDialog implements ActionListener {

    private static final long                         serialVersionUID = 646370277184134473L;

    // panels
    protected JPanel                                  pnData;
    protected JPanel                                  pnActions;

    // inputs
    protected JLabel                                  lbSaida;
    protected JRadioButton                            rbTerminal;
    protected JRadioButton                            rbArquivo;

    protected JLabel                                  lbDescricao;
    protected JTextField                              txArquivo;

    // actions
    protected JButton                                 btGerar, btCancelar,
                    btProcurar;

    // controls
    protected RelatorioControl                        control;
    protected File                                    file;

    protected ArrayList<Categoria>                    categorias;
    protected ArrayList<Lancamento>                   lancamentos;
    protected ArrayList<Cheque>                       cheques;
    protected HashMap<Integer, ArrayList<Lancamento>> lancamentosPorCategoria;

    public RelatorioForm() {
        this.control = RelatorioControl.getInstance();

        this.setTitle("Geração Relatório");
        this.setSize(450, 170);
        this.setResizable(false);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        this.doData();
        this.doActions();

        this.setVisible(true);
    }

    protected void doData() {
        this.pnData = new JPanel(new ParagraphLayout());

        this.lbSaida = new JLabel("Saída:");
        this.rbTerminal = new JRadioButton("Terminal");
        this.rbTerminal.setName("terminal");
        this.rbTerminal.addActionListener(this);
        this.rbArquivo = new JRadioButton("Arquivo");
        this.rbArquivo.setName("arquivo");
        this.rbArquivo.addActionListener(this);
        this.pnData.add(this.lbSaida, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.rbTerminal);
        this.pnData.add(this.rbArquivo);

        ButtonGroup bg = new ButtonGroup();
        bg.add(this.rbTerminal);
        bg.add(this.rbArquivo);

        this.rbArquivo.setSelected(true);

        this.lbDescricao = new JLabel("Arquivo:");
        this.txArquivo = new JTextField(25);
        this.txArquivo.setEditable(false);
        this.pnData.add(this.lbDescricao, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txArquivo);

        this.btProcurar = new JButton("Procurar");
        this.btProcurar.setName("procurar");
        this.btProcurar.addActionListener(this);
        this.pnData.add(this.btProcurar);

        getContentPane().add(this.pnData);
    }

    protected void doActions() {
        this.pnActions = new JPanel(new BorderLayout());
        this.pnActions.setBackground(Color.gray);
        this.pnActions.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.btCancelar = new JButton("Cancelar");
        this.btCancelar.setName("cancelar");
        this.btCancelar.setBackground(this.pnActions.getBackground());
        this.btCancelar.addActionListener(this);
        this.pnActions.add(this.btCancelar, BorderLayout.WEST);

        this.btGerar = new JButton("Gerar");
        this.btGerar.setName("salvar");
        this.btGerar.setBackground(this.pnActions.getBackground());
        this.btGerar.addActionListener(this);
        this.pnActions.add(this.btGerar, BorderLayout.EAST);

        getContentPane().add(this.pnActions, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof Component) {
            Component c = (Component) evt.getSource();

            if (c.getName().equals("salvar")) {
                this.imprimir();
            } else if (c.getName().equals("procurar")) {

                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Relatório", "rpt", "txt");
                fc.addChoosableFileFilter(filter);
                fc.setAcceptAllFileFilterUsed(false);

                if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    this.file = fc.getSelectedFile();
                    this.txArquivo.setText(this.file.getAbsolutePath());
                }

                fc.setVisible(false);
            } else if (c.getName().equals("terminal")) {
                if (this.rbTerminal.isSelected()) {
                    this.txArquivo.setText("");
                    this.btProcurar.setEnabled(false);
                }
            } else if (c.getName().equals("arquivo")) {
                if (this.rbArquivo.isSelected()) {
                    if (this.file != null)
                        this.txArquivo.setText(this.file.getAbsolutePath());
                    this.btProcurar.setEnabled(true);
                }
            } else if (c.getName().equals("cancelar")) {
                this.dispose();
            }
        }
    }

    public void imprimir() {

        if (this.rbArquivo.isSelected() && this.file.getAbsolutePath()
                        .isEmpty()) {
            JOptionPane.showMessageDialog(this, "Não foi selecionado um diretório de saída !", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (this.rbTerminal.isSelected())
                this.file = File.createTempFile("rel_orcamento", ".txt");

            this.file.createNewFile();

            FileWriter fw = new FileWriter(this.file);

            fw.write("---------------------------------------------------------------------------------------------------------------\n");
            fw.write(String.format("Lucas dos Santos Abreu                                                                     - 19/10/2013 - 22:00\n", ));
            fw.write("---------------------------------------------------------------------------------------------------------------\n");
            
            fw.write("---------------------------------------------------------------------------------------------------------------\n");

            fw.close();

            if (this.rbTerminal.isSelected())
                Desktop.getDesktop().open(this.file);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ocorreu algum erro e não foi possível gerar o relatório !", "Erro Exportação", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return;
        } finally {}

        this.dispose();
    }
}
