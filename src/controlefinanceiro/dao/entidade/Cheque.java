package controlefinanceiro.dao.entidade;

import java.util.Date;

public class Cheque {
    private int            numeroCheque;
    private Usuario        usuario;
    private String         banco;
    private String         conta;
    private int            numero;
    private float          valor;
    private String         favorecido;
    private Date           dataCadastro;
    private Date           dataCompensado;
    private SituacaoCheque situacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + numeroCheque;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cheque other = (Cheque) obj;
        if (numeroCheque != other.numeroCheque)
            return false;
        return true;
    }

    public Cheque() {}

    public Cheque(int numeroCheque, Usuario usuario, String banco,
                    String conta, int numero, float valor, String favorecido,
                    Date dataCadastro, SituacaoCheque situacao,
                    Date dataCompensado) {
        super();
        this.numeroCheque = numeroCheque;
        this.usuario = usuario;
        this.banco = banco;
        this.conta = conta;
        this.numero = numero;
        this.valor = valor;
        this.favorecido = favorecido;
        this.dataCadastro = dataCadastro;
        this.situacao = situacao;
        this.dataCompensado = dataCompensado;
    }

    public Cheque(int numeroCheque, Usuario usuario, String banco,
                    String conta, int numero, float valor, String favorecido,
                    Date dataCadastro, SituacaoCheque situacao) {
        super();
        this.numeroCheque = numeroCheque;
        this.usuario = usuario;
        this.banco = banco;
        this.conta = conta;
        this.numero = numero;
        this.valor = valor;
        this.favorecido = favorecido;
        this.dataCadastro = dataCadastro;
        this.situacao = situacao;
    }

    public int getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(int numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataCompensado() {
        return dataCompensado;
    }

    public void setDataCompensado(Date dataCompensado) {
        this.dataCompensado = dataCompensado;
    }

    public SituacaoCheque getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoCheque situacao) {
        this.situacao = situacao;
    }

}
