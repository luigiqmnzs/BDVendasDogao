package model;

import java.sql.Date;

public class Vendas {
	private int numeroVenda;
	private Date dataVenda;
	private Date dataVencimento;
	private String tipoDeVenda;
        private int codigoCliente;

        public Vendas(Date dataVenda, Date dataVencimento, String tipoDeVenda, int codigoCliente) {
		super();
		this.dataVenda = dataVenda;
		this.dataVencimento = dataVencimento;
		this.tipoDeVenda = tipoDeVenda;
                this.codigoCliente = codigoCliente;
	}
        
	public Vendas(int numeroVenda, Date dataVenda, Date dataVencimento, String tipoDeVenda, int codigoCliente) {
		super();
		
		this.numeroVenda = numeroVenda;
		this.dataVenda = dataVenda;
		this.dataVencimento = dataVencimento;
		this.tipoDeVenda = tipoDeVenda;
                this.codigoCliente = codigoCliente;
	}

	public int getNumeroVenda() {
		return numeroVenda;
	}

	public void setNumeroVenda(int numeroVenda) {
		this.numeroVenda = numeroVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getTipoDeVenda() {
		return tipoDeVenda;
	}

	public void setTipoDeVenda(String tipoDeVenda) {
		this.tipoDeVenda = tipoDeVenda;
	}
        public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}
