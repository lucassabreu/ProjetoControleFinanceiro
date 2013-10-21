package controlefinanceiro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.dao.entidade.SituacaoCheque;
import controlefinanceiro.dao.entidade.Usuario;

public class ChequeDAO extends AbstractDAO<Cheque> {

    private UsuarioDAO       usuarioDAO = DAOFactory.newUsuarioDAO();
    private static String[]  campos     = { "usuario", "banco", "conta",
            "numero", "valor", "favorecido", "dataCadastro", "dataCompensacao",
            "situacao"                 };

    private static ChequeDAO instance;

    public static ChequeDAO getInstance() {
        if (instance == null)
            instance = new ChequeDAO();

        return instance;
    }

    public ChequeDAO() {
        super("cheque", "numeroCheque", "usuario", "banco", "conta", "numero", "valor", "favorecido", "dataCadastro", "dataCompensacao", "situacao");
    }

    @Override
    protected Cheque montarObjeto(ResultSet rs) {

        Cheque c = new Cheque();

        try {
            c.setNumeroCheque(rs.getInt(1));

            if (this.buffer.get(c.hashCode()) != null)
                c = this.buffer.get(c.hashCode());

            c.setBanco(rs.getString(3));
            c.setConta(rs.getString(4));
            c.setNumero(rs.getInt(5));
            c.setValor(rs.getFloat(6));
            c.setFavorecido(rs.getString(7));
            c.setDataCadastro(rs.getDate(8));
            c.setDataCompensado(rs.getDate(9));

            int situacao = rs.getInt(10);

            if (situacao > SituacaoCheque.values().length || situacao <= 0)
                c.setSituacao(null);
            else
                c.setSituacao(SituacaoCheque.values()[situacao - 1]);

            c.setUsuario(this.usuarioDAO.buscar(rs.getString(2)));

            this.buffer.put(c.hashCode(), c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public void incluir(Cheque ch) {
        int numeroCheque = this.incluirPorCampos(campos, //
                        ch.getUsuario().getLogin(), //
                        ch.getBanco(), //
                        ch.getConta(), //
                        ch.getNumero(), //
                        ch.getValor(), //
                        ch.getFavorecido(), //
                        ch.getDataCadastro(), //
                        ch.getDataCompensado(), //
                        ch.getSituacao().ordinal() + 1);
        ch.setNumeroCheque(numeroCheque);
        this.buffer.put(ch.hashCode(), ch);
    }

    @Override
    public void alterar(Cheque ch) {
        this.alterarPorCampos(new String[] { "numeroCheque" }, //
                        new Object[] { ch.getNumeroCheque() }, campos, //
                        ch.getUsuario().getLogin(), //
                        ch.getBanco(), //
                        ch.getConta(), //
                        ch.getNumero(), //
                        ch.getValor(), //
                        ch.getFavorecido(), //
                        ch.getDataCadastro(), //
                        ch.getDataCompensado(), //
                        ch.getSituacao().ordinal() + 1);
    }

    @Override
    public void eliminar(Cheque entidade) {
        this.eliminarPorCampos(new String[] { "numeroCheque" }, entidade
                        .getNumeroCheque());
    }

    @Override
    public Cheque buscar(int numeroCheque) {

        int hashCode = 31 * 1 + numeroCheque;

        if (this.buffer.containsKey(hashCode))
            return this.buffer.get(hashCode);

        ArrayList<Cheque> list = this
                        .buscarPorCampos(new String[] { "numeroCheque" }, numeroCheque);

        if (list.isEmpty())
            return null;
        else
            return list.get(0);
    }

    @Override
    public ArrayList<Cheque> buscarTodos(Usuario usuario) {
        return this.buscarPorCampos(new String[] { "usuario" }, //
                        usuario.getLogin());
    }

}
