package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controlefinanceiro.control.ChequeControl;
import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.exception.CampoInvalidoRNException;
import controlefinanceiro.exception.ErroEliminarRNException;

public class ChequeForm extends JPanel implements ActionListener {
    private static final long   serialVersionUID = 9118117547832372220L;

    // control
    private ChequeControl       control;

    // panels
    protected JScrollPane       spCenter;
    protected JPanel            pnActions;

    // tables
    protected JTable            tbModels;
    protected ChequeTableModel  tmModel;
    protected ArrayList<Cheque> models;

    // actions
    protected JButton           btIncluir, btModificar;
    protected JButton           btDetalhar, btRemover;
    protected JButton           btnEstornar;

    public ChequeForm() {
        super(new BorderLayout());

        this.control = ChequeControl.getInstance();

        this.doCenter();
        this.doSouth();

        this.setVisible(true);
    }

    protected void doSouth() {
        this.pnActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.pnActions.setBackground(Color.gray);

        this.btIncluir = new JButton("Incluir");
        this.btIncluir.setName("incluir");
        this.btIncluir.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btIncluir);

        this.btModificar = new JButton("Modificar");
        this.btModificar.setName("modificar");
        this.btModificar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btModificar);

        this.btDetalhar = new JButton("Detalhar");
        this.btDetalhar.setName("detalhar");
        this.btDetalhar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btDetalhar);

        this.btnEstornar = new JButton("Estornar");
        this.btnEstornar.setName("estornar");
        this.btnEstornar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(btnEstornar);

        this.btRemover = new JButton("Remover");
        this.btRemover.setName("remover");
        this.btRemover.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btRemover);

        this.btIncluir.addActionListener(this);
        this.btModificar.addActionListener(this);
        this.btDetalhar.addActionListener(this);
        this.btRemover.addActionListener(this);

        this.add(this.pnActions, BorderLayout.SOUTH);
    }

    protected void doCenter() {
        this.tmModel = new ChequeTableModel();
        this.tbModels = new JTable(this.tmModel);
        tbModels.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.spCenter = new JScrollPane(this.tbModels);
        this.spCenter.setBorder(BorderFactory.createEtchedBorder());

        this.models = this.control.buscarTodos();
        this.tmModel.setModels(this.models);

        this.add(this.spCenter, BorderLayout.CENTER);
    }

    public void incluir() {
        ChequeSimplesForm frm = new ChequeSimplesForm(new Cheque(), true);

        if (frm.getModel() != null) {
            try {
                this.control.incluir(frm.getModel());
                this.models.add(frm.getModel());
                this.tbModels.setRowSelectionInterval(this.models.size() - 1, //
                                this.models.size() - 1);
                this.tbModels.updateUI();
            } catch (CampoInvalidoRNException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao Salvar !", JOptionPane.ERROR_MESSAGE);
                // e.printStackTrace();
            }
        }
    }

    public void alterar() {
        ChequeSimplesForm frm = new ChequeSimplesForm(this.getSelectedModel(), true);

        if (frm.getModel() != null) {
            try {
                this.control.alterar(frm.getModel());
                this.tbModels.updateUI();
            } catch (CampoInvalidoRNException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro ao Salvar !", JOptionPane.ERROR_MESSAGE);
                // e.printStackTrace();
            }
        }
    }

    public void detalhar() {
        new ChequeSimplesForm(this.getSelectedModel(), false);
    }

    public void eliminar() {
        int opt = JOptionPane
                        .showConfirmDialog(this, "Tem certeza que deseja eliminar?", "Atenção", JOptionPane.YES_NO_OPTION);

        if (opt == JOptionPane.YES_OPTION) {
            try {
                this.control.eliminar(this.getSelectedModel());
                this.models.remove(this.getSelectedModel());
                this.tbModels.updateUI();
            } catch (ErroEliminarRNException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Falha ao Eliminar !", JOptionPane.ERROR_MESSAGE);
                // e.printStackTrace();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Component) {
            Component c = (Component) e.getSource();

            if (c.getName().equals("incluir")) {
                this.incluir();
            } else if (c.getName().equals("modificar")) {
                this.alterar();
            } else if (c.getName().equals("detalhar")) {
                this.detalhar();
            } else if (c.getName().equals("remover")) {
                this.eliminar();
            }
        }
    }

    protected Cheque getSelectedModel() {
        if (this.tbModels.getSelectedRow() >= 0) {
            return this.models.get(this.tbModels.getSelectedRow());
        }

        return null;
    }

}
