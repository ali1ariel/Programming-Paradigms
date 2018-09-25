package livros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class EditoraDoLivro {
	private String nomeDaEditora;
	private ArrayList<Livro> livrosDaEditora = new ArrayList<Livro>();
	static Scanner ler = new Scanner(System.in);	
	
	public static void adicionarEditora(Livro cadastro, String nomeEditora) {
		
		EditoraDoLivro editora = new EditoraDoLivro();
		
		editora.setNomeDaEditora(nomeEditora);
		editora.getLivrosDaEditora().add(cadastro);
		cadastro.setEditora(editora);
		Livro.todasAsEditoras.add(editora);
		
	}
	
	public static void alteraEditora(Livro altera) {
		System.out.println("Digite o nome da Editora");
		String editora = ler.nextLine();
		if(altera.getEditora().getNomeDaEditora().equals(editora)) return;
		
		for(Integer a = 0; a.intValue() < Livro.todasAsEditoras.size();a++) {
			System.out.println(a.intValue() + " - "+Livro.todasAsEditoras.get(a).getNomeDaEditora());
		}
		
		
		Integer aux = Livro.todasAsEditoras.indexOf(altera.getEditora());
		Livro.todasAsEditoras.get(aux).getLivrosDaEditora().remove(altera);
		altera.setEditora(null);
		
		for(Integer a = 0; a.intValue() < Livro.todasAsEditoras.size(); a++) {
			if (Livro.todasAsEditoras.get(a).getNomeDaEditora().equals(editora)) {
				Livro.todasAsEditoras.get(a).getLivrosDaEditora().add(altera);
				altera.setEditora(Livro.todasAsEditoras.get(a));
				return;
			}
		}
		
		adicionarEditora(altera, editora);

		return;		
	}
	
	public static Livro listarLivrosEditora() {
		
		ArrayList<Livro> editoraLivrosArray = new ArrayList<Livro>();
		
		
		System.out.print("digite o nome da editora: ");
		String editora = ler.nextLine();
		for (Integer a = 0; a.intValue() < Livro.todosOsLivros.size();a++) {
			if(Livro.todosOsLivros.get(a).getEditora().getNomeDaEditora().equals(editora)) {
				editoraLivrosArray.add(Livro.todosOsLivros.get(a));
			}
		}
		if(editoraLivrosArray.size()==0) {
			System.out.println("editora não encontrada.");
			return null;
		}
		System.out.println("Os livros da editora selecionada são:");
		
		return Livro.buscaPorOrdem(editoraLivrosArray);
		
		
	}
	
	//SETTERS AND GETTERS
	public String getNomeDaEditora() {
		return nomeDaEditora;
	}
	public void setNomeDaEditora(String nomeDaEditora) {
		this.nomeDaEditora = nomeDaEditora;
	}
	public ArrayList<Livro> getLivrosDaEditora() {
		return livrosDaEditora;
	}
	public void setLivrosDaEditora(ArrayList<Livro> livrosDaEditora) {
		this.livrosDaEditora = livrosDaEditora;
	}
	
	
	@Override
	public boolean equals(Object aux) {
		EditoraDoLivro editora = (EditoraDoLivro) aux;
		return this.getNomeDaEditora().equals(editora.getNomeDaEditora());
	}
}

class NomeEditoraComparator implements Comparator<EditoraDoLivro>{

	public int compare(EditoraDoLivro editora1, EditoraDoLivro editora2) {
		return editora1.getNomeDaEditora().compareTo(editora2.getNomeDaEditora());
	}
	
	
}

