package livros;

import java.util.ArrayList;
//import java.util.Scanner;
import java.lang.String;;

public class TesteLivros extends Livro {
	
	public static void main(String[] args) {
		
		criaLivro();
		menu(todosOsLivros);
	}
	
	
	public static void criaLivro() {
		
		NomeAutorComparator organiza = new NomeAutorComparator();
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		String nome = "Amoedo";
		cadastro.setNomeDoLivro(nome);
						
		AutoresDoLivro autoria = new AutoresDoLivro();
	
		autoria.setNomeDoAutor("Rodolfo");
			

		Livro.todosOsAutores.add(autoria);
		cadastro.autorArray.add(Livro.todosOsAutores.get(Livro.todosOsAutores.indexOf(autoria)));
		cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getLivrosDoAutor().add(cadastro);
		cadastro.autorArray.sort(organiza);
					
		Livro.todosOsAutores.sort(organiza);
		
		String nomeEditora = "Perseus";
				
		EditoraDoLivro.adicionarEditora(cadastro, nomeEditora);
		
		
		Exemplar novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro("4");
		
		novoExemplar.setAnoDoLivro("2010");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt("10"));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		novoExemplar.setCodigoISBN(Integer.parseInt("34464"));
		
		cadastro.exemplaresArray.add(novoExemplar);	
		
		todosOsLivros.add(cadastro);
		//____________1
		
		cadastro = new Livro();
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		nome = "SCharls";
		cadastro.setNomeDoLivro(nome);
						
		autoria = new AutoresDoLivro();
	
		autoria.setNomeDoAutor("Chains");
		
		Livro.todosOsAutores.add(autoria);			
		cadastro.autorArray.add(Livro.todosOsAutores.get(Livro.todosOsAutores.indexOf(autoria)));
		cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getLivrosDoAutor().add(cadastro);
		cadastro.autorArray.sort(organiza);
					
		Livro.todosOsAutores.sort(organiza);
		
		nomeEditora = "Perseus";
				
		EditoraDoLivro.adicionarEditora(cadastro, nomeEditora);
		
		
		novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro("4");
		
		novoExemplar.setAnoDoLivro("2012");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt("14"));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		novoExemplar.setCodigoISBN(Integer.parseInt("3454"));
		
		cadastro.exemplaresArray.add(novoExemplar);	
		
		todosOsLivros.add(cadastro);Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		// ------ Livro com 2 exemplares
		cadastro = new Livro();
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		nome = "Silvan";
		cadastro.setNomeDoLivro(nome);
						
		autoria = new AutoresDoLivro();
	
		autoria.setNomeDoAutor("Chains");
			
		cadastro.autorArray.add(Livro.todosOsAutores.get(Livro.todosOsAutores.indexOf(autoria)));
		cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getLivrosDoAutor().add(cadastro);
		cadastro.autorArray.sort(organiza);
					
		Livro.todosOsAutores.sort(organiza);
		
		nomeEditora = "Perseus";
				
		EditoraDoLivro.adicionarEditora(cadastro, nomeEditora);
		
		
		novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro("4");
		
		novoExemplar.setAnoDoLivro("2012");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt("14"));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		novoExemplar.setCodigoISBN(Integer.parseInt("34454"));
		
		cadastro.exemplaresArray.add(novoExemplar);	
		
		novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro("2");
		
		novoExemplar.setAnoDoLivro("2007");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt("7"));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		novoExemplar.setCodigoISBN(Integer.parseInt("34419"));
		
		cadastro.exemplaresArray.add(novoExemplar);	
		
		
		todosOsLivros.add(cadastro);
	
		//--------------------3
		cadastro = new Livro();
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		nome = "Loraab";
		cadastro.setNomeDoLivro(nome);
						
		autoria = new AutoresDoLivro();
	
		autoria.setNomeDoAutor("resans");
		

		Livro.todosOsAutores.add(autoria);
		cadastro.autorArray.add(Livro.todosOsAutores.get(Livro.todosOsAutores.indexOf(autoria)));
		cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getLivrosDoAutor().add(cadastro);
		cadastro.autorArray.sort(organiza);
					
		Livro.todosOsAutores.sort(organiza);
		
		nomeEditora = "Persefone";
				
		EditoraDoLivro.adicionarEditora(cadastro, nomeEditora);
		
		
		novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro("9");
		
		novoExemplar.setAnoDoLivro("2018");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt("12"));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		novoExemplar.setCodigoISBN(Integer.parseInt("344454"));
		
		cadastro.exemplaresArray.add(novoExemplar);	
		
		todosOsLivros.add(cadastro);
		
		return;
		
	}
	

}
