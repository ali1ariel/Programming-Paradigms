package biblioteca;
import java.util.Scanner;

public class Livro {
	private EditoraDoLivro editora;
	private String nomeDoLivro;
	private String anoDoLivro;	
	private AutoresDoLivro autor;
	private Exemplar exemplares;
	private static int totalDeLivros = 0;
	private static Scanner ler;
	
	public static Livro cadastrarLivro() {
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();
		
		ler = new Scanner(System.in);
		System.out.println("Digite o nome do novo Livro");
		String nome = ler.nextLine();
		cadastro.setNomeDoLivro(nome);
		
		AutoresDoLivro autoria = new AutoresDoLivro();
		
		boolean verificadorAutor = true;
		while(verificadorAutor) {
		System.out.println("Digite o autor do livro");
		nome = ler.nextLine();
		autoria.setNomeDoAutor(nome);
		cadastro.setAutor(autoria);
		System.out.println("Quer adicionar um novo autor?\n 1-Sim outro-n�o");
		int resposta = Integer.parseInt(ler.nextLine());
		System.out.println(resposta);
		if (resposta!=1) 
			verificadorAutor=false;
		}
		if (verificadorAutor) {
			AutoresDoLivro novaAutoria = new AutoresDoLivro();
			autoria.setProximo(novaAutoria);
			autoria = novaAutoria;
		}
		System.out.println("Digite a edi��o do novo livro");
		Exemplar novoExemplar = new Exemplar();
		cadastro.setExemplares(novoExemplar);
		String edicao = ler.nextLine();
		cadastro.getExemplares().setEdicaoDoLivro(edicao);
		
		
		System.out.println("Digite o ano do novo livro");
		String ano = ler.nextLine();
		cadastro.setAnoDoLivro(ano);
		
		EditoraDoLivro editora = new EditoraDoLivro();
		
		System.out.println("Digite a editora do livro");
		String nomeEditora = ler.nextLine();
		editora.setNomeDaEditora(nomeEditora);
		cadastro.setEditora(editora);
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		int exemplar = Integer.parseInt(ler.nextLine());
		cadastro.getExemplares().setNumeroDeExemplares(exemplar);
		
		System.out.println("Digite o n�mero do c�digo ISBN");
		int codigoISBN = Integer.parseInt(ler.nextLine());
		
		cadastro.getExemplares().setCodigoISBN(codigoISBN);
		
		return cadastro;
	}
	
	public static void imprimeLivro(Livro imprime) {
		
		System.out.println("O Livro "+imprime.getNomeDoLivro()+" do autor "+imprime.getAutor().getNomeDoAutor()+" e do ano "+imprime.getAnoDoLivro()+" � da editora "
		+imprime.getEditora().getNomeDaEditora()+" e � a "+imprime.getExemplares().getEdicaoDoLivro()+" edi��o com "+imprime.getExemplares().getNumeroDeExemplares()+" exemplares e possui o c�digo ISBN."
		+imprime.getExemplares().getCodigoISBN());
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


	public AutoresDoLivro getAutor() {
		return autor;
	}


	public void setAutor(AutoresDoLivro autor) {
		this.autor = autor;
	}

	public Exemplar getExemplares() {
		return exemplares;
	}

	public void setExemplares(Exemplar exemplares) {
		this.exemplares = exemplares;
	}
}

