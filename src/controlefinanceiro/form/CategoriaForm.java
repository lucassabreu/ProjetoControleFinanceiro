package controlefinanceiro.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controlefinanceiro.dao.Categoria;
import controlefinanceiro.dao.Natureza;

public class CategoriaForm extends JFrame {
    private static final long      serialVersionUID = 9118117547832372220L;

    // panels
    protected JScrollPane          spCenter;
    protected JPanel               pnActions;

    // tables
    protected JTable               tbModels;
    protected CategoriaTableModel  tmModel;
    protected ArrayList<Categoria> models;

    // actions
    protected JButton              btIncluir, btModificar, btDetelhar,
                    btRemover;

    public CategoriaForm() {
        this.setTitle("Manutenção Categorias");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 400);
        
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

        this.btDetelhar = new JButton("Detalhar");
        this.btDetelhar.setName("detalhar");
        this.btDetelhar.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btDetelhar);

        this.btRemover = new JButton("Remover");
        this.btRemover.setName("remover");
        this.btRemover.setBackground(this.pnActions.getBackground());
        this.pnActions.add(this.btRemover);

        this.add(this.pnActions, BorderLayout.SOUTH);
    }

    protected void doCenter() {
        this.tmModel = new CategoriaTableModel();
        this.tbModels = new JTable(this.tmModel);
        this.spCenter = new JScrollPane(this.tbModels);
        
        this.spCenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.models = new ArrayList<Categoria>();
        this.tmModel.setModels(this.models);

        this.models.add(new Categoria(null, 45, "Bananas", Natureza.DESPESA));
        this.models.add(new Categoria(null, 2, "Morangos", Natureza.RECEITA));

        this.add(this.spCenter, BorderLayout.CENTER);
    }

}
