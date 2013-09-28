package controlefinanceiro.form;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controlefinanceiro.dao.Categoria;

public class CategoriaTableModel extends AbstractTableModel {
    private static final long      serialVersionUID = -8364167098125153147L;

    protected ArrayList<Categoria> models;
    protected String[]             columnNames      = new String[] { "Código", "Descrição", "Tipo"};

    public void setModels(ArrayList<Categoria> models) {
        this.models = models;
    }
    
    public ArrayList<Categoria> getModels() {
        return models;
    }
    
    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }
    
    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public int getRowCount() {
        if (this.models == null)
            return 0;

        return this.models.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return this.models.get(row).getCodigo();
            case 1:
                return this.models.get(row).getDescricao();
            case 2:
                if (this.models.get(row).getTipo() != null)
                    return this.models.get(row).getTipo().name();
                else
                    return "";
        }

        return null;
    }

}
