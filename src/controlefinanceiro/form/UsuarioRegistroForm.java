package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ui.util.layouts.ParagraphLayout;

public class UsuarioRegistroForm extends JDialog {
    private static final long serialVersionUID = -4286045236590371512L;

    // panels
    protected JPanel          pnData;
    protected JPanel          pnActions;

    // inputs
    protected JLabel          lbLogin;
    protected JTextField      txLogin;

    protected JLabel          lbNome;
    protected JTextField      txNome;

    protected JLabel          lbSenha;
    protected JPasswordField  txSenha;

    // actions
    protected JButton         btCancelar, btRegistrar;

    public UsuarioRegistroForm() {
        this.setTitle("Registra-se");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setSize(300, 160);
        this.setModal(true);
        this.setResizable(false);

        this.doData();
        this.doActions();

        this.setVisible(true);
    }

    protected void doData() {
        this.pnData = new JPanel(new ParagraphLayout());

        this.lbNome = new JLabel("Nome:");
        this.txNome = new JTextField(20);

        this.pnData.add(this.lbNome, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txNome);

        this.lbLogin = new JLabel("Login:");
        this.txLogin = new JTextField(20);

        this.pnData.add(this.lbLogin, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txLogin);

        this.lbSenha = new JLabel("Senha:");
        this.txSenha = new JPasswordField(20);

        this.pnData.add(this.lbSenha, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txSenha);

        this.add(this.pnData);
    }

    protected void doActions() {
        this.pnActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.pnActions.setBackground(Color.GRAY);

        this.btRegistrar = new JButton("Registrar");
        this.btRegistrar.setName("registrar");
        this.btRegistrar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btRegistrar);

        this.btCancelar = new JButton("Cancelar");
        this.btCancelar.setName("cancelar");
        this.btCancelar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btCancelar);

        this.add(this.pnActions, BorderLayout.SOUTH);
    }
}
