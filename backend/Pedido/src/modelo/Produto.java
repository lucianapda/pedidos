package modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produto {
	
	private String nome;
	private int codigoProduto;
	private double valorProduto;
	
	
	public Produto() {
		super();
	}
	public Produto(String nome, int codigoProduto, double valorProduto) {
		super();
		this.nome = nome;
		this.codigoProduto = codigoProduto;
		this.valorProduto = valorProduto;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigoProduto() {
		return codigoProduto;
	}
	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	public double getValorProduto() {
		return valorProduto;
	}
	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}
	
	

}
