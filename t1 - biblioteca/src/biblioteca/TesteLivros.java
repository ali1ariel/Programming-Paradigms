package biblioteca;

import java.util.ArrayList;

public class TesteLivros extends Livro {
	
	public static void main(String[] args) {
		Livro novo = cadastrarLivro();
		imprimeLivro(novo);
		ArrayList<Livro> teste = new ArrayList<Livro>();
		teste.add(novo);
	
	}
	

}
