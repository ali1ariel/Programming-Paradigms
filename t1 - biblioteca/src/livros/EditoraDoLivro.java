package livros;

import java.util.ArrayList;

public class EditoraDoLivro {
	private String nomeDaEditora;
	private ArrayList<Livro> livrosDaEditora;

	public String getNomeDaEditora() {
		return nomeDaEditora;
	}

	public void setNomeDaEditora(String nomeDaEditora) {
		this.nomeDaEditora = nomeDaEditora;
	}

	public ArrayList<Livro> getLivrosDaEditora() {
		return livrosDaEditora;
	}

	public void setLivrosDaEditora(ArrayList<Livro> livrosDaEditora) {
		this.livrosDaEditora = livrosDaEditora;
	}
}
