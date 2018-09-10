package livros;

import java.util.ArrayList;
import java.lang.String;;

public class TesteLivros extends Livro {
	
	public static void main(String[] args) {
		ArrayList<Livro> teste = new ArrayList<Livro>();
		while(true) {
			Livro novo = cadastrarLivro();
			imprimeLivro(novo);
			
			teste.add(novo);
		}
	}
	

}
