package biblioteca;

public class Exemplar extends Livro {
	private String edicaoDoLivro;
	private int numeroDeExemplares;
	private int codigoISBN;
	private Exemplar proximo;
	
	public String getEdicaoDoLivro() {
		return edicaoDoLivro;
	}
	public void setEdicaoDoLivro(String edicao) {
		String numeroDaEdicao = (edicao+"ª");
		System.out.println(numeroDaEdicao);
		this.edicaoDoLivro = numeroDaEdicao;
	}
	public int getNumeroDeExemplares() {
		return numeroDeExemplares;
	}
	public void setNumeroDeExemplares(int numeroDeExemplares) {
		super.setTotalDeLivros(super.getTotalDeLivros()+numeroDeExemplares);
		this.numeroDeExemplares = numeroDeExemplares;
	}
	public Exemplar getProximo() {
		return proximo;
	}
	public void setProximo(Exemplar proximo) {
		this.proximo = proximo;
	}
	public int getCodigoISBN() {
		return codigoISBN;
	}
	public void setCodigoISBN(int codigoISBN) {
		this.codigoISBN = codigoISBN;
	}

}
