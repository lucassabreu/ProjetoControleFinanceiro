package controlefinanceiro.control;

import java.util.ArrayList;

import controlefinanceiro.dao.CategoriaDAO;
import controlefinanceiro.dao.DAOFactory;
import controlefinanceiro.dao.entidade.Categoria;
import controlefinanceiro.dao.entidade.Lancamento;
import controlefinanceiro.dao.entidade.Usuario;
import controlefinanceiro.exception.ErroEliminarRNException;

public class CategoriaControl {

    private static CategoriaControl instance;

    public static CategoriaControl getInstance() {
        if (instance == null)
            instance = new CategoriaControl();

        return instance;
    }

    public CategoriaDAO dao = DAOFactory.newCategoriaDAO();

    private CategoriaControl() {}

    public void incluir(Categoria c) {
        c.setUsuario(this.getUsuarioCorrente());
        this.dao.incluir(c);
    }

    public void alterar(Categoria c) {
        this.dao.alterar(c);
    }

    public void eliminar(Categoria c) throws ErroEliminarRNException {
        ArrayList<Lancamento> list = LancamentoControl.getInstance()
                        .buscarPorCategoria(c);

        if (list.isEmpty())
            this.dao.eliminar(c);
        else
            throw new ErroEliminarRNException("A categoria possui lançamentos e não pode ser eliminada !");
    }

    public Categoria buscar(int codigo) {
        Categoria c = this.dao.buscar(codigo);

        if (c.getUsuario().equals(this.getUsuarioCorrente()))
            return c;
        else
            return null;
    }

    public ArrayList<Categoria> buscarTodos() {
        return this.dao.buscarTodos(this.getUsuarioCorrente());
    }

    public Usuario getUsuarioCorrente() {
        return UsuarioControl.getInstance().getUsuario();
    }
}
