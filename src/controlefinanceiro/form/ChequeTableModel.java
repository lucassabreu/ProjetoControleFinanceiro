package controlefinanceiro.form;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import controlefinanceiro.dao.entidade.Cheque;

public class ChequeTableModel extends AbstractTableModel {
    private static final long   serialVersionUID = -8364167098125153147L;

    protected ArrayList<Cheque> models;
    protected String[]          columnNames      = new String[] {
            "Número Cheque", "Banco", "Conta", "Número", "Valor", "Favorecido",
            "Data Cadastro", "Data Compensação", "Situação" };
    protected NumberFormat      nf;
    protected DateFormat        df;

    public ChequeTableModel() {
        this.nf = NumberFormat.getCurrencyInstance();
        this.df = DateFormat.getDateInstance();
    }

    public void setModels(ArrayList<Cheque> models) {
        this.models = models;
    }

    public ArrayList<Cheque> getModels() {
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
        // Número Cheque|Banco|Conta|Número|Valor|Favorecido|Data Cadastro|Data
        // Compensação|Situação
            case 0:
                return this.models.get(row).getNumeroCheque();
            case 1:
                return this.models.get(row).getBanco();
            case 2:
                return this.models.get(row).getConta();
            case 3:
                return this.models.get(row).getNumero();
            case 4:
                return this.nf.format(this.models.get(row).getValor());
            case 5:
                return this.models.get(row).getFavorecido();
            case 6:
                return this.df.format(this.models.get(row).getDataCadastro());
            case 7:
                if (this.models.get(row).getDataCompensado() != null)
                    return this.df.format(this.models.get(row).getDataCompensado());
                else
                    return "";
            case 8:
                if (this.models.get(row).getSituacao() != null)
                    return this.models.get(row).getSituacao().name();
                else
                    return "";
        }

        return null;
    }

}
