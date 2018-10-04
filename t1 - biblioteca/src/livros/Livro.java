package livros;
import java.util.ArrayList;
import java.util.Scanner;

public class Livro {
	
	private String nomeDoLivro;
	private EditoraDoLivro editora;
	private ArrayList <AutoresDoLivro> autorArray;
	private ArrayList <Exemplar> exemplaresArray;
	private static ArrayList <AutoresDoLivro> todosOsAutores = new ArrayList<AutoresDoLivro>();
	private static ArrayList <EditoraDoLivro> todasAsEditoras = new ArrayList<EditoraDoLivro>();
	private static ArrayList<Livro> todosOsLivros = new ArrayList<Livro>();
	private static Integer totalDeLivros = 0;
	static Scanner ler = new Scanner(System.in);
	
	public static void cadastrarLivro() {
		
		Livro.setTotalDeLivros(getTotalDeLivros()+1);
		Livro cadastro = new Livro();
		
		cadastro.setExemplaresArray(new ArrayList<Exemplar>());
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
			System.out.println("A biblioteca contém "+totalDeLivros.intValue()+" livros.");
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
					imprimeInformaçõesLivro(selecionado);
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
		
		ArrayList<Livro> buscas = new ArrayList<Livro>();
		String buscador = new String();
		
		Integer option = Integer.parseInt(ler.nextLine());
		
		if(option<1||option>4) {
			System.out.println("Opção inválida");
			return null;
		}else if (option < 4) {
		
			switch(option) {
				case 1: {
					System.out.println("digite o nome do livro:");
					break;
				}
				case 2: {
					System.out.println("digite a editora do livro:");
					break;
				}
				case 3: {
					System.out.println("digite o código ISBN do livro:");
					break;
				}
			}
			buscador = ler.nextLine();
		
		
			for(Integer a = 0; a.intValue() < busca.size();a++) {
				switch (option) {
					case 1:{ 
						selecionado = buscaPorNomeDoLivro(busca.get(a), buscador);
						if(selecionado != null) {
							buscas.add(selecionado);
						}
						break;
					}
					case 2:{					
						selecionado = EditoraDoLivro.listarLivrosEditora(busca.get(a), buscador);
						if(selecionado != null) {
							buscas.add(selecionado);
						}
						break;
					}
					case 3:{
						for(Integer b = 0; b.intValue() < busca.get(a).getExemplaresArray().size();b++) {
							selecionado = busca.get(a);
							if(buscaPorISBN(selecionado.getExemplaresArray().get(b), Integer.parseInt(buscador))) {
								buscas.add(selecionado);
							}
						}
						break;
					}	
				}
				if ((option==3) && (!(buscas.isEmpty()))) break;
			}
			
		}
		else {
			selecionado = buscaPorOrdem(todosOsLivros);
			if(selecionado == null) {
				System.out.println("nenhum livro retornado dessa busca.");
			}
			return selecionado;
		}
		
		if (buscas.size()<1) return null;
		if(buscas.size()==1) {
			return buscas.get(0);
		}
		else {
			imprimeTodosOsLivros(buscas);
			System.out.println("selecione o índice do livro buscado");
			Integer selecao = Integer.parseInt(ler.nextLine());
			if(!(selecao<buscas.size())) {
				System.out.println("Livro Inválido");
				return null;
			}
			return buscas.get(selecao);
		}
	}


	public static boolean buscaPorISBN (Exemplar confere, Integer buscando) {
				
		if(confere.getCodigoISBN().equals(buscando)) return true;
		
		return false;
	}
	
	public static Livro buscaPorNomeDoLivro (Livro confere, String buscador) {
	
		if(confere.getNomeDoLivro().contains(buscador))
		{
				return confere;
		}
		else return null;
	}
	
	public static Livro buscaPorOrdem (ArrayList<Livro> busca) {
		
		
		imprimeTodosOsLivros(busca);
		
		System.out.println("Qual o índice do livro deseja selecionar?");
		Integer selecionar = Integer.parseInt(ler.nextLine());
		if (!(selecionar < busca.size())) return null;
		Livro selecionado = busca.get(selecionar);
		return selecionado;
		
	}
	
	
	public static void imprimeInformaçõesLivro (Livro imprime) {
		
		System.out.print(imprime.getNomeDoLivro());
		
		AutoresDoLivro.imprimeAutorDoLivro(imprime);
		
		System.out.println("da editora: "+imprime.getEditora().getNomeDaEditora()+".");
	}

	public static void imprimeTodosOsLivros(ArrayList<Livro> imprime) {
		for(Integer a = 0; a.intValue() < imprime.size();a++) {
			
			System.out.print("O livro ("+a+") - ");
			imprimeInformaçõesLivro(imprime.get(a));
			Exemplar.imprimeExemplaresDoLivro(imprime.get(a));
			System.out.println("_______________________");	
			
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
				imprimeInformaçõesLivro(altera);
				System.out.println("deseja modificar mais alguma coisa? 1 - sim, outro - não");
				if(Integer.parseInt(ler.nextLine())!=1) break;
			}else {
				break;
			}
		}
		
		
		
	}
		
	public static void excluirLivro(Livro excluir) {
		
		
		if (Exemplar.verificaExemplares(excluir.getExemplaresArray())) { // verifica se há livros emprestados.
			
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

	public ArrayList <Exemplar> getExemplaresArray() {
		return exemplaresArray;
	}

	public void setExemplaresArray(ArrayList <Exemplar> exemplaresArray) {
		this.exemplaresArray = exemplaresArray;
	}

	public ArrayList<AutoresDoLivro> getAutorArray() {
		return autorArray;
	}

	public void setAutorArray(ArrayList<AutoresDoLivro> autorArray) {
		this.autorArray = autorArray;
	}

	public static ArrayList<AutoresDoLivro> getTodosOsAutores() {
		return todosOsAutores;
	}

	public static void setTodosOsAutores(ArrayList<AutoresDoLivro> todosOsAutores) {
		Livro.todosOsAutores = todosOsAutores;
	}

	public static ArrayList<EditoraDoLivro> getTodasAsEditoras() {
		return todasAsEditoras;
	}

	public static void setTodasAsEditoras(ArrayList<EditoraDoLivro> todasAsEditoras) {
		Livro.todasAsEditoras = todasAsEditoras;
	}

	public static ArrayList<Livro> getTodosOsLivros() {
		return todosOsLivros;
	}

	public static void setTodosOsLivros(ArrayList<Livro> todosOsLivros) {
		Livro.todosOsLivros = todosOsLivros;
	}

	public static void setTotalDeLivros(Integer totalDeLivros) {
		Livro.totalDeLivros = totalDeLivros;
	}
	
}




