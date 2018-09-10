package bliblioteca;

public class Exemplar extends Livro {
	private String edicaoDoLivro;
	private Integer numeroDeExemplares;
	private Integer codigoISBN;
	private Exemplar proximo;
	
	public String getEdicaoDoLivro() {
		return edicaoDoLivro;
	}
	public void setEdicaoDoLivro(String edicao) {
		String numeroDaEdicao = (edicao+"ª");
		System.out.println(numeroDaEdicao);
		this.edicaoDoLivro = numeroDaEdicao;
	}
	public Exemplar getProximo() {
		return proximo;
	}
	public void setProximo(Exemplar proximo) {
		this.proximo = proximo;
	}
	public Integer getNumeroDeExemplares() {
		return numeroDeExemplares;
	}
	public void setNumeroDeExemplares(Integer numeroDeExemplares) {
		this.numeroDeExemplares = numeroDeExemplares;
	}
	public Integer getCodigoISBN() {
		return codigoISBN;
	}
	public void setCodigoISBN(Integer codigoISBN) {
		this.codigoISBN = codigoISBN;
	}
}
