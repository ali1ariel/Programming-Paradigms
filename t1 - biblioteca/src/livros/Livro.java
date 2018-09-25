package livros;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Livro {
	
	private String nomeDoLivro;
	private EditoraDoLivro editora;
	ArrayList <AutoresDoLivro> autorArray;
	ArrayList <Exemplar> exemplaresArray;
	static ArrayList <AutoresDoLivro> todosOsAutores = new ArrayList<AutoresDoLivro>();
	static ArrayList <EditoraDoLivro> todasAsEditoras = new ArrayList<EditoraDoLivro>();
	static ArrayList<Livro> todosOsLivros = new ArrayList<Livro>();
	private static Integer totalDeLivros = 0;
	static Scanner ler = new Scanner(System.in);
	
	public static void cadastrarLivro() {
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
		
		
		System.out.println("Digite o nome do novo Livro");
		String nome = ler.nextLine();
		cadastro.setNomeDoLivro(nome);
		
		AutoresDoLivro.adicionarAutor(cadastro);		
		
		System.out.println("Digite a editora do livro");
		String nomeEditora = ler.nextLine();
		EditoraDoLivro.adicionarEditora(cadastro, nomeEditora);
		
		Exemplar.adicionarExemplar(cadastro);
		
		todosOsLivros.add(cadastro);
		
		return;
	}
	
	public static void menu(ArrayList<Livro> livroArray) {
		
		do {
			System.out.println("O que gostaria de fazer? \n (1) - Adicionar um novo livro. (2) - Lidar com um existente. (3) - Imprimir livros.");
			switch (Integer.parseInt(ler.nextLine())) {
			case 1:
				cadastrarLivro();
				break;
			case 2:
				Livro selecionado = buscarLivro(livroArray);
				if(selecionado == null) {
					break;
				}
					System.out.println("O livro selecionado foi:");
					imprimeLivro(selecionado);
					System.out.println("O que deseja fazer com o livro selecionado? (1) - Excluir, (2) - Editar");
					switch (Integer.parseInt(ler.nextLine())) {
					
					case 1:					
						if(livroArray.size() > 0) {
							excluirLivro(selecionado);
						} else {
							System.out.println("Não há livros para serem excluidos!");
						}
						break;
					case 2:
						if(livroArray.size() > 0) {
							alteraLivro(selecionado);
						}else {
							System.out.println("Não há livros para serem editados!");
						}
						break;
						default:
							System.out.println("Opção inválida!");
				}
			case 3: 
				if(livroArray.size()>0) imprimeTodosOsLivros(livroArray);
				else System.out.println("Não há elementos a imprimir");
				break;
			}
			System.out.println("deseja algo a mais?\n (1) - SIM. (2) - Nao.");
		}while(Integer.parseInt(ler.nextLine())==1);
		
	}
	
	public static Livro buscarLivro (ArrayList<Livro> busca) {
		
		Livro selecionado = null;
		System.out.println("Deseja buscar o livro por: (1) - Nome, (2) - Editora, (3) - Código ISBN, (4) - Por índice de todos os livros.");
		
		switch (Integer.parseInt(ler.nextLine())) {
		case 1: 
			selecionado = buscaPorNomeDoLivro(busca);
			if(selecionado == null) {
				System.out.println("O livro com esse nome não foi encontrado.");
			}
			break;
		case 2:
			
			selecionado = EditoraDoLivro.listarLivrosEditora();
			if(selecionado == null) {
				System.out.println("nenhum livro retornado dessa busca por editora.");
			}
			break;
			
		case 3:
			selecionado = buscaPorISBN(todosOsLivros);
			if(selecionado == null) {
				System.out.println("nenhum livro retornado dessa busca por ISBN.");
			}
			break;
			
		case 4:
			selecionado = buscaPorOrdem(todosOsLivros);
			if(selecionado == null) {
				System.out.println("nenhum livro retornado dessa busca.");
			}
			break;
		}
		
		return selecionado;
	}
	public static Livro buscaPorISBN (ArrayList<Livro> busca) {
		Livro selecionado = null;
		
		System.out.println("Quer ver todos os livros antes de digitar o ISBN? (1) - Sim, (outro) - Não" );
		if(Integer.parseInt(ler.nextLine())==1) {
			imprimeTodosOsLivros(todosOsLivros);
		}
		System.out.print("digite o ISBN: ");
		Integer buscando = Integer.parseInt(ler.nextLine());
		for(Integer a = 0; a.intValue() < busca.size();a++) {
			for(Integer b = 0; b.intValue() < busca.get(a).exemplaresArray.size();b++) {
				if(busca.get(a).exemplaresArray.get(b).getCodigoISBN().equals(buscando)) return busca.get(a);
			}
		}
		
		return selecionado;
	}
	public static Livro buscaPorNomeDoLivro (ArrayList<Livro> busca) {
		
		ArrayList<Livro> buscas = new ArrayList<Livro>();
		
		System.out.println("Qual o nome do Livro?");
		
		String buscador = ler.nextLine();
				
		for (Integer a = 0; a.intValue() < busca.size();a++) {
			if(busca.get(a).getNomeDoLivro().contains(buscador)) {
				buscas.add(busca.get(a));
			}
		}
		if (buscas.size()<1) return null;
		if(buscas.size()==1) return buscas.get(0);
		else {
			imprimeTodosOsLivros(buscas);
			System.out.println("selecione o índice do livro buscado");
			Integer selecionado = Integer.parseInt(ler.nextLine());
			if(!(selecionado<buscas.size())) {
				System.out.println("Livro Inválido");
				return null;
			}
			return buscas.get(selecionado);
		}
	}
	
	public static Livro buscaPorOrdem (ArrayList<Livro> busca) {
		
		
		imprimeTodosOsLivros(busca);
		
		System.out.println("Qual o índice do livro deseja selecionar?");
		Integer selecionar = Integer.parseInt(ler.nextLine());
		if (!(selecionar < busca.size())) return null;
		Livro selecionado = busca.get(selecionar);
		return selecionado;
		
	}
	
	public static void imprimeLivro(Livro imprime) {
		
		System.out.print(imprime.getNomeDoLivro());
		
		AutoresDoLivro.imprimeAutorDoLivro(imprime);
		
		System.out.print("da editora: "+imprime.getEditora().getNomeDaEditora()+".");
		
		Exemplar.imprimeExemplaresDoLivro(imprime);
		System.out.println("_______________________");
		
	}

	public static void imprimeTodosOsLivros(ArrayList<Livro> imprime) {
		for(Integer a = 0; a.intValue() < imprime.size();a++) {
			System.out.print("O livro ("+a+") - ");
			imprimeLivro(imprime.get(a));
		}
	}
	
	public static void alteraLivro(Livro altera) {
		
		while(true) {
			System.out.println(" o que deseja alterar?");
			System.out.println("1 - nome do livro");
			System.out.println("2 - excluir algum autor");
			System.out.println("3 - adicionar algum autor");
			System.out.println("4 - a editora");
			System.out.println("5 - alguma edição");
			
			
			ler = new Scanner(System.in);
			Integer option = Integer.parseInt(ler.nextLine());
			switch (option) {
			case 1: 
				System.out.println("digite o novo nome do livro:");
				altera.setNomeDoLivro(ler.nextLine());
				break;
			case 2:
				AutoresDoLivro.excluiAutor(altera);
				break;
			case 3:
				AutoresDoLivro.adicionarAutor(altera);
				break;
			case 4:
				EditoraDoLivro.alteraEditora(altera);
				break;
			case 5:
				Exemplar.alteraExemplar(altera);
				break;
				
			}if(Livro.todosOsLivros.contains(altera)) {
				System.out.println("As alterações resultaram em:");
				imprimeLivro(altera);
				System.out.println("deseja modificar mais alguma coisa? 1 - sim, outro - não");
				if(Integer.parseInt(ler.nextLine())!=1) break;
			}else {
				break;
			}
		}
		
		
		
	}
		
	public static void excluirLivro(Livro excluir) {
		
		
		if (Exemplar.verificaExemplares(excluir.exemplaresArray)) { // verifica se há livros emprestados.
			
			for (Integer a = 0; a < excluir.autorArray.size();a++){ //mexe com os autores do livro, 1 de cada vez.
				for(Integer b = 0; b < Livro.todosOsAutores.get(a).getLivrosDoAutor().size();b++) { //acha o livro dentro do vetor de livros do autor.
					if(Livro.todosOsAutores.get(a).getLivrosDoAutor().get(b).getNomeDoLivro().equals(excluir.getNomeDoLivro())) {
						if(Livro.todosOsAutores.get(a).getLivrosDoAutor().size()==1) {
							Livro.todosOsAutores.remove(excluir.autorArray.get(a));
						} else {
							Livro.todosOsAutores.get(a).getLivrosDoAutor().remove(excluir);
						}
					}
				}
			}
			
			if(Livro.todasAsEditoras.get(Livro.todasAsEditoras.indexOf(excluir.editora)).getLivrosDaEditora().size()<=1) {
				Livro.todasAsEditoras.remove(excluir.editora);
			} else {
				Livro.todasAsEditoras.get(Livro.todasAsEditoras.indexOf(excluir.editora)).getLivrosDaEditora().remove(excluir);
			}
			Livro.todosOsLivros.remove(excluir);
			Livro.setTotalDeLivros(Livro.getTotalDeLivros()-1);
			
			
		return;
		} 
		System.out.println("Não é possível excluir pois há exemplares emprestados");
		return;
	}

	
	//SETTERS AND GETTERS
	public EditoraDoLivro getEditora() {
		return editora;
	}
	public void setEditora(EditoraDoLivro editora) {
		this.editora = editora;
	}
	public String getNomeDoLivro() {
		return nomeDoLivro;
	}	
	public void setNomeDoLivro(String nomeDoLivro) {
		this.nomeDoLivro = nomeDoLivro;
	}
	public static int getTotalDeLivros() {
		return totalDeLivros;
	}
	public static void setTotalDeLivros(int totalDeLivros) {
		Livro.totalDeLivros = totalDeLivros;
	}
}

class NomeLivroComparator implements Comparator<Livro>{

	public int compare(Livro livro1, Livro livro2) {
		return livro1.getNomeDoLivro().compareTo(livro2.getNomeDoLivro());
	}
	
	
}




