package controlefinanceiro.control;

import java.util.ArrayList;

import controlefinanceiro.dao.entidade.Categoria;
import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.dao.entidade.Lancamento;
import controlefinanceiro.dao.entidade.Usuario;

public class RelatorioControl {
    private static RelatorioControl instance;

    private UsuarioControl          uc;
    private CategoriaControl        ctc;
    private LancamentoControl       lc;
    private ChequeControl           chc;

    public static RelatorioControl getInstance() {
        if (instance == null)
            instance = new RelatorioControl();

        return instance;
    }

    private RelatorioControl() {};

    public Usuario getUsuario() {
        return this.uc.getUsuario();
    }

    public ArrayList<Cheque> buscarCheques() {
        return this.chc.buscarTodos();
    }

    public ArrayList<Lancamento> buscarLancamentos() {
        return this.lc.buscarTodos();
    }

    public ArrayList<Categoria> buscarCategorias() {
        return this.ctc.buscarTodos();
    }
}
