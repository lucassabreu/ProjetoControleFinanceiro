package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import ui.util.layouts.ParagraphLayout;
import controlefinanceiro.dao.Categoria;
import controlefinanceiro.dao.Natureza;

public class CategoriaSimplesForm extends JDialog {

    private static final long     serialVersionUID = 646370277184134473L;

    // panels
    protected JPanel              pnData;
    protected JPanel              pnActions;

    // inputs
    protected JLabel              lbCodigo;
    protected JFormattedTextField txCodigo;

    protected JLabel              lbDescricao;
    protected JTextField          txDescricao;

    protected JLabel              lbTipo;
    protected JComboBox<String>   cbTipo;

    // actions
    protected JButton             btSalvar, btCancelar;

    // controls
    protected NumberFormatter     nf;
    protected boolean             editavel;
    protected Categoria           model;

    public CategoriaSimplesForm(Categoria model, boolean editavel) {
        this.model = model;
        this.editavel = editavel;

        this.setTitle("Detalhe Categoria");
        this.setSize(300, 150);
        this.setResizable(false);
        this.setModal(true);

        this.doData();
        this.doActions();

        this.setVisible(true);
    }

    public Categoria getModel() {
        return model;
    }

    public boolean isEditavel() {
        return editavel;
    }

    protected void doData() {
        this.pnData = new JPanel(new ParagraphLayout());

        this.nf = new NumberFormatter(NumberFormat.getIntegerInstance());

        this.lbCodigo = new JLabel("Código:");
        this.txCodigo = new JFormattedTextField(this.nf);
        this.txCodigo.setColumns(14);
        this.pnData.add(this.lbCodigo, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txCodigo);

        this.lbDescricao = new JLabel("Descrição:");
        this.txDescricao = new JTextField(25);
        this.pnData.add(this.lbDescricao, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.txDescricao);

        this.lbTipo = new JLabel("Tipo:");

        String[] naturezas = new String[Natureza.values().length];

        for (int i = 0; i < Natureza.values().length; i++)
            naturezas[i] = Natureza.values()[i].name();

        this.cbTipo = new JComboBox<String>(naturezas);
        this.cbTipo.setBackground(this.pnData.getBackground());
        this.pnData.add(this.lbTipo, ParagraphLayout.NEW_PARAGRAPH);
        this.pnData.add(this.cbTipo);

        this.txCodigo.setEditable(this.editavel);
        this.txDescricao.setEditable(this.editavel);
        this.cbTipo.setEnabled(this.editavel);

        try {
            this.txCodigo.setText(this.nf.valueToString(new Integer(this.model.getCodigo())));
            this.txDescricao.setText(String.valueOf(this.model.getDescricao()));

            if (this.model.getTipo() != null)
                this.cbTipo.setSelectedIndex(this.model.getTipo().ordinal());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.add(this.pnData);
    }

    protected void doActions() {
        this.pnActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.pnActions.setBackground(Color.gray);

        if (this.editavel) {
            this.btSalvar = new JButton("Salvar");
            this.btSalvar.setName("salvar");
            this.btSalvar.setBackground(this.pnActions.getBackground());
            this.pnActions.add(this.btSalvar);
        }

        this.btCancelar = new JButton(this.editavel ? "Cancelar" : "Fechar");
        this.btCancelar.setName("cancelar");
        this.btCancelar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btCancelar);

        this.add(this.pnActions, BorderLayout.SOUTH);
    }
}
