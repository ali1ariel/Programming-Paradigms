package bliblioteca;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Livro {
	private EditoraDoLivro editora;
	private String nomeDoLivro;
	private String anoDoLivro;	
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
		/*
		System.out.println("Digite o nome do novo Livro");
		String nome = ler.nextLine();
		cadastro.setNomeDoLivro(nome);
		*/
				
		AutoresDoLivro autoria = new AutoresDoLivro();
		boolean verificadorAutor = true;
		while(verificadorAutor) {
			System.out.println("Digite o autor do livro");
			autoria.setNomeDoAutor(ler.nextLine());
	
			
			if(todosOsAutores.contains(autoria)) {
				System.out.println("funciou!!");
				cadastro.autorArray.add(todosOsAutores.get(todosOsAutores.indexOf(autoria)));
				System.out.println(cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getNomeDoAutor());
			}else {
				todosOsAutores.add(autoria);
				cadastro.autorArray.add(todosOsAutores.get(todosOsAutores.indexOf(autoria)));
				System.out.println(cadastro.autorArray.get(cadastro.autorArray.indexOf(autoria)).getNomeDoAutor());
			}
			System.out.println("Quer adicionar um novo autor?\n 1-Sim outro-n�o");
			Integer resposta = Integer.valueOf(ler.nextLine());
			System.out.println(resposta);
			if (!resposta.equals(1)) {
				verificadorAutor=false;
			}else {
				autoria = new AutoresDoLivro();
			}
			Livro.todosOsAutores.sort(organiza);
		}
		/*
		System.out.println("Digite a edi��o do novo livro");
		Exemplar novoExemplar = new Exemplar();
		
		novoExemplar.setEdicaoDoLivro(ler.nextLine());
		
		System.out.println("Digite o ano do novo livro");
		String ano = ler.nextLine();
		cadastro.setAnoDoLivro(ano);

		
		
		EditoraDoLivro editora = new EditoraDoLivro();
		
		System.out.println("Digite a editora do livro");
		String nomeEditora = ler.nextLine();
		editora.setNomeDaEditora(nomeEditora);
		cadastro.setEditora(editora);
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt(ler.nextLine()));
		
		System.out.println("Digite o n�mero do c�digo ISBN");
		novoExemplar.setCodigoISBN(Integer.parseInt(ler.nextLine()));
		
		cadastro.exemplaresArray.add(novoExemplar);
		*/
		return cadastro;
	}
	
	public static void imprimeLivro(Livro imprime) {
		
		System.out.println("O Livro "+imprime.getNomeDoLivro()+" do autor "+imprime.autorArray.get(0).getNomeDoAutor()+" e do ano "+imprime.getAnoDoLivro()+" � da editora "
		+imprime.getEditora().getNomeDaEditora()+" e � a "+imprime.exemplaresArray.get(0).getEdicaoDoLivro()+" edi��o com "+imprime.exemplaresArray.get(0).getNumeroDeExemplares()+" exemplares e possui o c�digo ISBN."
		+imprime.exemplaresArray.get(0).getCodigoISBN());
	}
	
	public static void alteraLivro(Livro altera) {
		System.out.println("sobre o livro que deseja alterar: ");
		imprimeLivro(altera);
		
	}
	
	public void excluirLivro() {
		
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


	public String getAnoDoLivro() {
		return anoDoLivro;
	}


	public void setAnoDoLivro(String anoDoLivro) {
		this.anoDoLivro = anoDoLivro;
	}



	public static int getTotalDeLivros() {
		return totalDeLivros;
	}


	public static void setTotalDeLivros(int totalDeLivros) {
		Livro.totalDeLivros = totalDeLivros;
	}
}

class NomeAutorComparator implements Comparator<AutoresDoLivro>{

	public int compare(AutoresDoLivro autor1, AutoresDoLivro autor2) {
		return autor1.getNomeDoAutor().compareTo(autor2.getNomeDoAutor());
	}
	
	
}