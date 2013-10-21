package controlefinanceiro.dao;

public abstract class DAOFactory {
    public static CategoriaDAO newCategoriaDAO() {
        return CategoriaDAO.getInstance();
    }

    public static UsuarioDAO newUsuarioDAO() {
        return UsuarioDAO.getInstance();
    }

    public static LancamentoDAO newLancamentoDAO() {
        return LancamentoDAO.getInstance();
    }

    public static ChequeDAO newChequeDAO() {
        return ChequeDAO.getInstance();
    }
}
