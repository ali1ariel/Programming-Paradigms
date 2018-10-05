package livros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class AutoresDoLivro {
	private String nomeDoAutor;
	private ArrayList<Livro> livrosDoAutor = new ArrayList<Livro>();
	static Scanner ler;

	
	public static void imprimeAutorDoLivro(Livro imprime) {
		System.out.print(" de:");
		if(imprime.getAutorArray().size()>1) {
			for(Integer a=0; a < imprime.getAutorArray().size(); a++)
			{
				System.out.print(imprime.getAutorArray().get(a).getNomeDoAutor());
				if ((a+1) == imprime.getAutorArray().size()) System.out.println(". ");
				else System.out.print(", ");
			}
		} else {
			System.out.print(imprime.getAutorArray().get(0).getNomeDoAutor()+". ");
		}
		
	}
		
	public static void excluiAutor (Livro altera) {
		
		ler = new Scanner(System.in);
		
		System.out.println("qual autor você deseja excluir?");
		System.out.println("ATENCAO: nao e possivel remover autores quando ha apenas um no livro.");
		
		for (Integer a = 0; a < altera.getAutorArray().size(); a++) {
			System.out.println(a+" - "+altera.getAutorArray().get(a).getNomeDoAutor());
		}
		
		Integer option = Integer.parseInt(ler.nextLine());
		
		if (altera.getAutorArray().size()>1) {
			System.out.println("Voce quer: (1) - excluir ou (outro) - cancelar? \n"+option.intValue()+" - "+altera.getAutorArray().get(option.intValue()).getNomeDoAutor());
			
			switch (Integer.parseInt(ler.nextLine())) {
			case 1:
				altera.getAutorArray().get(option).getLivrosDoAutor().remove(altera);
				altera.getAutorArray().remove(option.intValue());
			default:
				return;
					
			} 
		}
		
		
	}
	
	public static void adicionarAutor (Livro cadastro) {
		
	
		NomeAutorComparator organiza = new NomeAutorComparator();
		ler = new Scanner(System.in);
		
		AutoresDoLivro autoria = new AutoresDoLivro();
		boolean verificadorAutor = true;
		while(verificadorAutor) {
			System.out.println("Digite o autor do livro");
			autoria.setNomeDoAutor(ler.nextLine());
	
			
			if(!Livro.getTodosOsAutores().contains(autoria)) Livro.getTodosOsAutores().add(autoria);
				
				cadastro.getAutorArray().add(Livro.getTodosOsAutores().get(Livro.getTodosOsAutores().indexOf(autoria)));
				cadastro.getAutorArray().get(cadastro.getAutorArray().indexOf(autoria)).getLivrosDoAutor().add(cadastro);
				cadastro.getAutorArray().sort(organiza);
			
			System.out.println("Quer adicionar um novo autor?\n 1-Sim outro-nao");
			Integer resposta = Integer.valueOf(ler.nextLine());
			System.out.println(resposta);
			if (!resposta.equals(1)) {
				verificadorAutor=false;
			}else {
				autoria = new AutoresDoLivro();
			}
			Livro.getTodosOsAutores().sort(organiza);
		}

	}
	
	//SETTERS AND GETTERS
	public String getNomeDoAutor() {
		return nomeDoAutor;
	}
	public void setNomeDoAutor(String nomeDoAutor) {
		this.nomeDoAutor = nomeDoAutor;
	}
	public ArrayList<Livro> getLivrosDoAutor() {
		return livrosDoAutor;
	}
	
	
	@Override
	public boolean equals(Object aux) {
		AutoresDoLivro autor = (AutoresDoLivro) aux;
		return this.nomeDoAutor.equals(autor.nomeDoAutor);
	}
}

class NomeAutorComparator implements Comparator<AutoresDoLivro>{

	public int compare(AutoresDoLivro autor1, AutoresDoLivro autor2) {
		return autor1.getNomeDoAutor().compareTo(autor2.getNomeDoAutor());
	}
	
	
}
