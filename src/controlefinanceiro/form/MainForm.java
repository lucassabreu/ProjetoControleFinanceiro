package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import controlefinanceiro.control.MainControl;

public class MainForm extends JFrame implements ActionListener {
    private static final long serialVersionUID = -8236789081848420152L;

    // control
    protected MainControl     control;

    // panel
    protected JTabbedPane     tpCenter;
    protected CategoriaForm   pnCategoria;
    protected LancamentoForm  pnLancamento;
    protected ChequeForm      pnCheque;

    // menu
    protected JMenu           mnAcoes;
    protected JMenuItem       miLancamento;
    protected JMenuItem       miCheque;
    protected JMenuItem       miCategoria;
    protected JMenuItem       miRelatorio;
    protected JMenuItem       miSair;

    public MainForm() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Controle Financeiro");
        this.setSize(675, 410);
        this.setLocationRelativeTo(null);

        this.control = new MainControl(this);
        this.control.iniciar();

        doMenu();
        doCenter();

        this.setVisible(true);
    }

    public void logar() {
        new UsuarioLoginForm();
    }

    protected void doMenu() {

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        mnAcoes = new JMenu("Ações");
        menuBar.add(mnAcoes);

        miLancamento = new JMenuItem("Lançamento");
        miLancamento.setName("lancamento");
        miLancamento.addActionListener(this);
        mnAcoes.add(miLancamento);

        miCheque = new JMenuItem("Cheque");
        miCheque.setName("cheque");
        miCheque.addActionListener(this);
        mnAcoes.add(miCheque);

        miCategoria = new JMenuItem("Categoria");
        miCategoria.setName("categoria");
        miCategoria.addActionListener(this);
        mnAcoes.add(miCategoria);

        mnAcoes.addSeparator();

        miRelatorio = new JMenuItem("Gerar Relatório");
        miRelatorio.setName("gerarRelatorio");
        miRelatorio.addActionListener(this);
        mnAcoes.add(miRelatorio);

        mnAcoes.addSeparator();

        miSair = new JMenuItem("Sair");
        miSair.setName("sair");
        miSair.addActionListener(this);
        mnAcoes.add(miSair);
    }

    protected void doCenter() {

        tpCenter = new JTabbedPane(JTabbedPane.TOP);
        tpCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        getContentPane().add(tpCenter, BorderLayout.CENTER);

        pnLancamento = new LancamentoForm();
        pnLancamento.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tpCenter.addTab("Lancamento", null, pnLancamento, null);

        pnCheque = new ChequeForm();
        pnCheque.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tpCenter.addTab("Cheque", null, pnCheque, null);

        pnCategoria = new CategoriaForm();
        pnCategoria.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tpCenter.addTab("Categoria", null, pnCategoria, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Component) {
            Component c = (Component) e.getSource();

            if (c.getName().equals("lancamento")) {
                this.tpCenter.setSelectedIndex(0);
            } else if (c.getName().equals("cheque")) {
                this.tpCenter.setSelectedIndex(1);
            } else if (c.getName().equals("categoria")) {
                this.tpCenter.setSelectedIndex(2);
            } else if (c.getName().equals("gerarRelatorio")) {
                new RelatorioForm();
            } else if (c.getName().equals("sair")) {
                System.exit(0);
            }
        }
    }

}
