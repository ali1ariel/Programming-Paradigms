package biblioteca;

public class AutoresDoLivro {
	private String nomeDoAutor;
	private AutoresDoLivro proximo;

	public String getNomeDoAutor() {
		return nomeDoAutor;
	}

	public void setNomeDoAutor(String nomeDoAutor) {
		this.nomeDoAutor = nomeDoAutor;
	}

	public AutoresDoLivro getProximo() {
		return proximo;
	}

	public void setProximo(AutoresDoLivro proximo) {
		this.proximo = proximo;
	}
}
