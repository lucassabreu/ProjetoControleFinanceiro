package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.NumberFormatter;

import ui.util.layouts.ParagraphLayout;
import controlefinanceiro.control.ChequeControl;
import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.dao.entidade.SituacaoCheque;
import controlefinanceiro.exception.CampoInvalidoRNException;

public class ChequeSimplesForm extends JDialog implements ActionListener {

    private static final long     serialVersionUID = 646370277184134473L;

    // panels
    protected JPanel              pnData;
    protected JPanel              pnActions;

    // inputs
    protected JLabel              lbNumeroCheque;
    protected JTextField          txNumeroCheque;

    protected JLabel              lbBanco;
    protected JTextField          txBanco;

    protected JLabel              lbConta;
    protected JTextField          txConta;

    protected JLabel              lbNumero;
    protected JFormattedTextField txNumero;

    protected JLabel              lbValor;
    protected JFormattedTextField txValor;

    protected JLabel              lbFavorecido;
    protected JTextField          txFavorecido;

    protected JLabel              lbSituacao;
    protected JComboBox<String>   cbSituacao;

    protected JLabel              lbDataCadastro;
    protected JFormattedTextField txDataCadastro;

    protected JLabel              lbDataCompensacao;
    protected JFormattedTextField txDataCompensacao;

    // actions
    protected JButton             btSalvar, btCancelar;

    // controls
    protected ChequeControl       control;
    protected NumberFormatter     nfn;
    protected NumberFormatter     nfi;
    protected DateFormatter       df;
    protected boolean             editavel;
    protected Cheque              model;
    protected boolean             efetivar         = false;

    public ChequeSimplesForm(Cheque model, boolean editavel) {
        this.model = model;
        this.editavel = editavel;
        this.control = ChequeControl.getInstance();

        this.setTitle("Detalhe Cheque");
        this.setSize(530, 290);
        this.setResizable(false);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        this.doData();
        this.doActions();

        this.setVisible(true);

        if (!this.efetivar)
            this.model = null;
    }

    public Cheque getModel() {
        return model;
    }

    public boolean isEditavel() {
        return editavel;
    }

    protected void doData() {
        this.pnData = new JPanel(new ParagraphLayout());
        this.nfn = new NumberFormatter(NumberFormat.getNumberInstance());
        this.nfi = new NumberFormatter(NumberFormat.getIntegerInstance());
        this.df = new DateFormatter(DateFormat.getDateInstance());

        if (this.model.getNumeroCheque() > 0) {
            this.lbNumeroCheque = new JLabel("Número Cheque:");
            this.txNumeroCheque = new JTextField();
            this.txNumeroCheque.setColumns(14);
            this.txNumeroCheque.setEditable(false);
            this.pnData.add(this.lbNumeroCheque, ParagraphLayout.NEW_PARAGRAPH);
            this.pnData.add(this.txNumeroCheque);
        }

        this.lbBanco = new JLabel("Banco:");
        this.txBanco = new JTextField(35);
        this.pnData.add(this.lbBanco, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txBanco);

        this.lbConta = new JLabel("Conta:");
        this.txConta = new JTextField(35);
        this.pnData.add(this.lbConta, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txConta);

        this.lbNumero = new JLabel("Número:");
        this.txNumero = new JFormattedTextField(this.nfi);
        this.txNumero.setColumns(14);
        this.pnData.add(this.lbNumero, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txNumero);

        this.lbValor = new JLabel("Valor:");
        this.txValor = new JFormattedTextField(this.nfn);
        this.txValor.setColumns(14);
        this.pnData.add(this.lbValor); // , ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txValor);

        this.lbFavorecido = new JLabel("Favorecido:");
        this.txFavorecido = new JTextField(35);
        this.pnData.add(this.lbFavorecido, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txFavorecido);

        this.lbSituacao = new JLabel("Situação:");

        this.cbSituacao = new JComboBox<String>();

        for (int i = 0; i < SituacaoCheque.values().length; i++)
            this.cbSituacao.addItem(SituacaoCheque.values()[i].name());

        this.cbSituacao.setBackground(this.pnData.getBackground());
        this.pnData.add(this.lbSituacao, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.cbSituacao);

        this.lbDataCadastro = new JLabel("Data Cadastro:");
        this.txDataCadastro = new JFormattedTextField(this.df);
        this.txDataCadastro.setColumns(12);
        this.pnData.add(this.lbDataCadastro, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txDataCadastro);

        this.lbDataCompensacao = new JLabel("Compensado em:");
        this.txDataCompensacao = new JFormattedTextField(this.df);
        this.txDataCompensacao.setColumns(12);
        this.pnData.add(this.lbDataCompensacao); // ,
                                                 // ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txDataCompensacao);

        this.txDataCadastro.setEditable(false);

        this.txBanco.setEditable(this.editavel);
        this.txConta.setEditable(this.editavel);
        this.txNumero.setEditable(this.editavel);
        this.txFavorecido.setEditable(this.editavel);
        this.txValor.setEditable(this.editavel);
        this.txDataCompensacao.setEditable(this.editavel);

        this.cbSituacao.setEnabled(this.editavel);

        try {
            if (this.model.getNumeroCheque() > 0)
                this.txNumeroCheque.setText(Integer.toString(this.model
                                .getNumeroCheque()));

            this.txBanco.setText(String
                            .valueOf(this.model.getBanco() == null ? "" : this.model
                                            .getBanco()));

            this.txConta.setText(String
                            .valueOf(this.model.getConta() == null ? "" : this.model
                                            .getConta()));

            this.txNumero.setText(this.nfi
                            .valueToString(this.model.getNumero()));

            this.txValor.setText(this.nfn.valueToString(this.model.getValor()));

            this.txFavorecido
                            .setText(String.valueOf(this.model.getFavorecido() == null ? "" : this.model
                                            .getFavorecido()));

            if (this.model.getSituacao() != null)
                this.cbSituacao.setSelectedIndex(this.model.getSituacao()
                                .ordinal());
            else
                this.cbSituacao.setSelectedIndex(0);

            if (this.model.getDataCadastro() != null)
                this.txDataCadastro.setText(this.df.valueToString(this.model
                                .getDataCadastro()));
            else
                this.txDataCadastro.setText(this.df
                                .valueToString(GregorianCalendar.getInstance()
                                                .getTime()));

            if (this.model.getDataCompensado() != null)
                this.txDataCompensacao.setText(this.df.valueToString(this.model
                                .getDataCompensado()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        getContentPane().add(this.pnData);
    }

    protected void doActions() {
        this.pnActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.pnActions.setBackground(Color.gray);

        if (this.editavel) {
            this.btSalvar = new JButton("Salvar");
            this.btSalvar.setName("salvar");
            this.btSalvar.setBackground(this.pnActions.getBackground());
            this.btSalvar.addActionListener(this);
            this.pnActions.add(this.btSalvar);
        }

        this.btCancelar = new JButton(this.editavel ? "Cancelar" : "Fechar");
        this.btCancelar.setName("cancelar");
        this.btCancelar.setBackground(this.pnActions.getBackground());
        this.btCancelar.addActionListener(this);
        this.pnActions.add(this.btCancelar);

        getContentPane().add(this.pnActions, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() instanceof Component) {
            Component c = (Component) evt.getSource();

            if (c.getName().equals("salvar")) {
                this.salvar();
            } else if (c.getName().equals("cancelar")) {
                this.model = null;
                this.dispose();
            }
        }
    }

    public void salvar() {
        try {
            this.model.setSituacao(SituacaoCheque.values()[this.cbSituacao
                            .getSelectedIndex()]);
            this.model.setBanco(this.txBanco.getText().trim());
            this.model.setConta(this.txConta.getText().trim());
            this.model.setFavorecido(this.txFavorecido.getText().trim());

            long num = (long) this.nfi.stringToValue(this.txNumero.getText());
            this.model.setNumero((int) num);
            if (!this.txDataCompensacao.getText().isEmpty())
                this.model.setDataCompensado((Date) this.df
                                .stringToValue(this.txDataCompensacao.getText()));
            else
                this.model.setDataCompensado(null);

            if (this.model.getDataCadastro() == null)
                this.model.setDataCadastro(GregorianCalendar.getInstance()
                                .getTime());

            Number valor = (Number) this.nfn.stringToValue(this.txValor
                            .getText());

            this.model.setValor(valor.floatValue());

            this.control.validar(model);
            this.efetivar = true;
            this.dispose();
        } catch (CampoInvalidoRNException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao Salvar !", JOptionPane.ERROR_MESSAGE);
            // e.printStackTrace();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao Salvar !", JOptionPane.ERROR_MESSAGE);
            // e.printStackTrace();
        }
    }
}
