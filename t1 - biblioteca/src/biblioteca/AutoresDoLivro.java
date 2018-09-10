package biblioteca;
import java.util.ArrayList;

public class AutoresDoLivro {
	private String nomeDoAutor;
//	static ArrayList<Livro>

	public String getNomeDoAutor() {
		return nomeDoAutor;
	}

	public void setNomeDoAutor(String nomeDoAutor) {
		this.nomeDoAutor = nomeDoAutor;
	}
	
	@Override
	public boolean equals(Object aux) {
		AutoresDoLivro autor = (AutoresDoLivro) aux;
		return this.nomeDoAutor.equals(autor.nomeDoAutor);
	}
}
