package controlefinanceiro.dao;

public abstract class DAOFactory {
    public static CategoriaDAO newCategoriaDAO() {
        return new CategoriaDAO();
    }

    public static UsuarioDAO newUsuarioDAO() {
        return new UsuarioDAO();
    }

    public static LancamentoDAO newLancamentoDAO() {
        return new LancamentoDAO();
    }

    public static ChequeDAO newChequeDAO() {
        return new ChequeDAO();
    }
}
