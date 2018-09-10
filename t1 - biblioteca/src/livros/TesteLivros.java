package livros;

import java.util.ArrayList;
import java.lang.String;;

public class TesteLivros extends Livro {
	
	public static void main(String[] args) {
		ArrayList<Livro> teste = new ArrayList<Livro>();
		while(true) {
			Livro novo = cadastrarLivro();
			/*imprimeLivro(novo);*/
			System.out.println(Livro.todosOsAutores.size());
			for (Integer a = 0; a < Livro.todosOsAutores.size();a++) {
				System.out.println(Livro.todosOsAutores.get(a).getNomeDoAutor());
			}
			
			for (Integer a = 0; a < novo.autorArray.size();a++) {
				System.out.println("autor: "+novo.autorArray.get(a).getNomeDoAutor());
			}
			
			teste.add(novo);
		}
	}
	

}
