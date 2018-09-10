package livros;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Livro {
	private EditoraDoLivro editora;
	private String nomeDoLivro;
	ArrayList <AutoresDoLivro> autorArray;
	ArrayList <Exemplar> exemplaresArray;
	static ArrayList <AutoresDoLivro> todosOsAutores = new ArrayList<AutoresDoLivro>();;
	private static Integer totalDeLivros = 0;
	private static Scanner ler;
	
	public static Livro cadastrarLivro() {
		
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();

		NomeAutorComparator organiza = new NomeAutorComparator();
		
		cadastro.exemplaresArray = new ArrayList<Exemplar>();
		cadastro.autorArray = new ArrayList<AutoresDoLivro>();
				
		ler = new Scanner(System.in);
		
		System.out.println("Digite o nome do novo Livro");
		String nome = ler.nextLine();
		cadastro.setNomeDoLivro(nome);
		
				
		AutoresDoLivro autoria = new AutoresDoLivro();
		boolean verificadorAutor = true;
		while(verificadorAutor) {
			System.out.println("Digite o autor do livro");
			autoria.setNomeDoAutor(ler.nextLine());
	
			
			if(!todosOsAutores.contains(autoria)) todosOsAutores.add(autoria);
				
				cadastro.autorArray.add(todosOsAutores.get(todosOsAutores.indexOf(autoria)));
				cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getLivrosDoAutor().add(cadastro);
				cadastro.autorArray.sort(organiza);
			
			System.out.println("Quer adicionar um novo autor?\n 1-Sim outro-nï¿½o");
			Integer resposta = Integer.valueOf(ler.nextLine());
			System.out.println(resposta);
			if (!resposta.equals(1)) {
				verificadorAutor=false;
			}else {
				autoria = new AutoresDoLivro();
			}
			Livro.todosOsAutores.sort(organiza);
		}
		System.out.println("Digite a ediï¿½ï¿½o do novo livro");
		Exemplar novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro(ler.nextLine());
		
		System.out.println("Digite o ano do novo livro");
		novoExemplar.setAnoDoLivro(ler.nextLine());
		
	
		EditoraDoLivro editora = new EditoraDoLivro();
		
		System.out.println("Digite a editora do livro");
		String nomeEditora = ler.nextLine();
		editora.setNomeDaEditora(nomeEditora);
		cadastro.setEditora(editora);
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt(ler.nextLine()));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		System.out.println("Digite o nï¿½mero do cï¿½digo ISBN");
		novoExemplar.setCodigoISBN(Integer.parseInt(ler.nextLine()));
		
		cadastro.exemplaresArray.add(novoExemplar);
		
		return cadastro;
	}
	
	public static void imprimeLivro(Livro imprime) {
		
		
		System.out.print("O Livro "+imprime.getNomeDoLivro());
		if(imprime.autorArray.size()>1) {
			System.out.println(" dos autores:");
			for(Integer a=0; a < imprime.autorArray.size(); a++)
			{
				System.out.println(imprime.autorArray.get(a).getNomeDoAutor());
			}
		} else {
			System.out.println(" do autor: "+imprime.autorArray.get(0).getNomeDoAutor());
		}
		System.out.print("da editora: "+imprime.getEditora().getNomeDaEditora());
		if(imprime.exemplaresArray.size()>1) {
			System.out.println(" e com os seguintes exemplares:");
		} else {
			System.out.println(" e com o exemplar:");
		}
		
		for (Integer a = 0;a<imprime.exemplaresArray.size();a++) {
			System.out.println("Edição: "+imprime.exemplaresArray.get(a).getEdicaoDoLivro()+", do ano: "+imprime.exemplaresArray.get(a).getAnoDoLivro()+" e do código ISBN: "+imprime.exemplaresArray.get(a).getCodigoISBN()+"\n Tem "+imprime.exemplaresArray.get(a).getExemplaresDisponiveis()+" de "+imprime.exemplaresArray.get(a).getNumeroDeExemplares()+" exemplares disponíveis.");
		}
	}
	
	public static void alteraLivro(Livro altera) {
		System.out.println("sobre o livro que deseja alterar: ");
		imprimeLivro(altera);
		
	}
	
	public void excluirLivro(Livro excluir) {
		if (verificaExemplares(excluir.exemplaresArray)) {
			for (Integer a = 0; a < excluir.autorArray.size();a++){
				for(Integer b = 0; b < Livro.todosOsAutores.get(a).getLivrosDoAutor().size();b++) {
					if(Livro.todosOsAutores.get(a).getLivrosDoAutor().get(b).getNomeDoLivro().equals(excluir.getNomeDoLivro())) {
						System.out.println(Livro.todosOsAutores.get(a).getLivrosDoAutor().remove(excluir));
					}
				}
			}
			
		return;
		} 
		System.out.println("Não é possível excluir pois há exemplares emprestados");
		return;
	}

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
	
	public boolean verificaExemplares(ArrayList<Exemplar> exemplares) {
		for (Integer a = 0; a < exemplares.size();a++){
			if(exemplares.get(a).getNumeroDeExemplares()!=exemplares.get(a).getExemplaresDisponiveis()) return false;
		}
		return true;
	}
}

class NomeAutorComparator implements Comparator<AutoresDoLivro>{

	public int compare(AutoresDoLivro autor1, AutoresDoLivro autor2) {
		return autor1.getNomeDoAutor().compareTo(autor2.getNomeDoAutor());
	}
	
	
}

class NomeLivroComparator implements Comparator<Livro>{

	public int compare(Livro livro1, Livro livro2) {
		return livro1.getNomeDoLivro().compareTo(livro2.getNomeDoLivro());
	}
	
	
}