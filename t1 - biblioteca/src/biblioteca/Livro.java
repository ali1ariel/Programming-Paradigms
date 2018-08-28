package biblioteca;
import java.util.Scanner;

public class Livro {
	private int edicaoDoLivro;
	private EditoraDoLivro editora;
	private String nomeDoLivro;
	private int anoDoLivro;	
	private static int totalDeLivros = 0;
	private Scanner ler;
	
	public Livro cadastrarLivro() {
		Livro.setTotalDeLivros(Livro.getTotalDeLivros() + 1);
		Livro cadastro = new Livro();
		
		ler = new Scanner(System.in);
		System.out.println("Digite o nome do novo Livro");
		String nome = ler.nextLine();
		cadastro.setNomeDoLivro(nome);
		
		AutoresDoLivro autoria = new AutoresDoLivro();
		System.out.println("Digite o autor do livro");
		nome = ler.nextLine();
		autoria.setNomeDoAutor(nome);
		
		System.out.println("Digite a edição do novo livro");
		int edicao = ler.hashCode();
		cadastro.setEdicaoDoLivro(edicao);
		
		System.out.println("Digite o ano do novo livro");
		int ano = ler.hashCode();
		cadastro.setAnoDoLivro(ano);
		
		EditoraDoLivro editora = new EditoraDoLivro();
		System.out.println("Digite a editora do livro");
		nome = ler.nextLine();
		editora.setNomeDaEditora(nome);
		
		return cadastro;
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


	public int getAnoDoLivro() {
		return anoDoLivro;
	}


	public void setAnoDoLivro(int anoDoLivro) {
		this.anoDoLivro = anoDoLivro;
	}

	public int getEdicaoDoLivro() {
		return edicaoDoLivro;
	}

	public void setEdicaoDoLivro(int edicaoDoLivro) {
		this.edicaoDoLivro = edicaoDoLivro;
	}


	public static int getTotalDeLivros() {
		return totalDeLivros;
	}


	public static void setTotalDeLivros(int totalDeLivros) {
		Livro.totalDeLivros = totalDeLivros;
	}
}

