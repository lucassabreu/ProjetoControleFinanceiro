package controlefinanceiro.control;

import java.util.ArrayList;
import java.util.Date;

import controlefinanceiro.dao.ChequeDAO;
import controlefinanceiro.dao.DAOFactory;
import controlefinanceiro.dao.entidade.Cheque;
import controlefinanceiro.dao.entidade.SituacaoCheque;
import controlefinanceiro.dao.entidade.Usuario;
import controlefinanceiro.exception.CampoInvalidoRNException;
import controlefinanceiro.exception.ErroEliminarRNException;

public class ChequeControl {

    private static ChequeControl instance;

    public static ChequeControl getInstance() {
        if (instance == null)
            instance = new ChequeControl();

        return instance;
    }

    protected ChequeDAO dao = DAOFactory.newChequeDAO();

    private ChequeControl() {}

    public void validar(Cheque c) throws CampoInvalidoRNException {
        if (c.getBanco() == null || c.getBanco().trim().isEmpty())
            throw new CampoInvalidoRNException("Campo Banco precisa ser informadao !");

        if (c.getConta() == null || c.getConta().trim().isEmpty())
            throw new CampoInvalidoRNException("Campo Conta precisa ser informadao !");

        if (c.getDataCadastro() == null)
            throw new CampoInvalidoRNException("Campo Data de Cadastro precisa ser informadao !");

        if (c.getNumero() <= 0)
            throw new CampoInvalidoRNException("Campo Número precisa ser informadao !");

        if (c.getValor() <= 0)
            throw new CampoInvalidoRNException("Campo Valor precisa ser maior que zero !");

        if (c.getSituacao() == null) 
            throw new CampoInvalidoRNException("Campo Situação precisar ser informado !");
        
        if (c.getSituacao() == SituacaoCheque.COMPENSADO && c.getDataCompensado() == null)
            throw new CampoInvalidoRNException("Para o Cheque entrar em compensação é preciso informar a Data de Compensação !");
    
        if (c.getDataCompensado() != null && c.getSituacao() == SituacaoCheque.CADASTRADO)
            throw new CampoInvalidoRNException("Se a data de Compensação foi informada a situação do Cheque deve ser \"Compensado\" ou \"Estornado\" !");
    }

    public void incluir(Cheque c) throws CampoInvalidoRNException {
        c.setUsuario(this.getUsuarioCorrente());
        c.setDataCadastro(new Date());
        this.validar(c);

        this.dao.incluir(c);
    }

    public void alterar(Cheque c) throws CampoInvalidoRNException {
        this.validar(c);
        this.dao.alterar(c);
    }

    public void eliminar(Cheque c) throws ErroEliminarRNException {
        this.dao.eliminar(c);
    }

    public Cheque buscar(int numeroCheque) {
        Cheque c = this.dao.buscar(numeroCheque);

        if (c.getUsuario().equals(this.getUsuarioCorrente()))
            return c;
        else
            return null;
    }

    public ArrayList<Cheque> buscarTodos() {
        return this.dao.buscarTodos(this.getUsuarioCorrente());
    }

    public Usuario getUsuarioCorrente() {
        return UsuarioControl.getInstance().getUsuario();
    }
}
