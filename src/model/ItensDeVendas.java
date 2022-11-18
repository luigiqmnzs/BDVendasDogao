package model;

public class ItensDeVendas {
	private int quantidadeVendida;
	private double precoPago;
        private int numeroVenda;
        private int codigoProduto;

	public ItensDeVendas(int quantidadeVendida, double precoPago, int numeroVenda, int codigoProduto) {
		super();
		this.quantidadeVendida = quantidadeVendida;
		this.precoPago = precoPago;
                this.numeroVenda = numeroVenda;
                this.codigoProduto = codigoProduto;
	}

	public int getQuantidadeVendida() {
		return quantidadeVendida;
	}

	public void setQuantidadeVendida(int quantidadeVendida) {
		this.quantidadeVendida = quantidadeVendida;
	}

	public double getPrecoPago() {
		return precoPago;
	}

	public void setPrecoPago(double precoPago) {
		this.precoPago = precoPago;
	}
        public int getNumeroVenda() {
		return numeroVenda;
	}

	public void setNumeroVenda(int numeroVenda) {
		this.numeroVenda = numeroVenda;
	}
        public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
