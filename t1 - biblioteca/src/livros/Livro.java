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
	static Scanner ler;
	
	public static void cadastrarLivro() {
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		ler = new Scanner(System.in);
		
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
	
	public static void menu() {
		
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
	
	public static void alteraLivro(ArrayList<Livro> alteraArray) {
		
		ler = new Scanner(System.in);
		
		System.out.println("Qual livro deseja alterar?");
		imprimeTodosOsLivros(alteraArray);
		Integer alterar = Integer.parseInt(ler.nextLine());
		if (!(alterar < alteraArray.size())) return;
		Livro altera = alteraArray.get(alterar);
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
			
			
		return;
		} 
		System.out.println("Não é possível excluir pois há exemplares emprestados");
		return;
	}

	public static void buscarLivro( ) {
		
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




