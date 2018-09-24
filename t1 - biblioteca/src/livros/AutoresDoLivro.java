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
		if(imprime.autorArray.size()>1) {
			for(Integer a=0; a < imprime.autorArray.size(); a++)
			{
				System.out.print(imprime.autorArray.get(a).getNomeDoAutor());
				if ((a+1) == imprime.autorArray.size()) System.out.println(". ");
				else System.out.print(", ");
			}
		} else {
			System.out.print(imprime.autorArray.get(0).getNomeDoAutor()+". ");
		}
		
	}
		
	public static void excluiAutor (Livro altera) {
		
		ler = new Scanner(System.in);
		
		System.out.println("qual autor você deseja excluir?");
		System.out.println("ATENÇÃO: não é possível remover autores quando há apenas um no livro.");
		
		for (Integer a = 0; a < altera.autorArray.size(); a++) {
			System.out.println(a+" - "+altera.autorArray.get(a).getNomeDoAutor());
		}
		
		Integer option = Integer.parseInt(ler.nextLine());
		
		if (altera.autorArray.size()>1) {
			System.out.println("Você quer: (1) - excluir ou (outro) - cancelar? \n"+option.intValue()+" - "+altera.autorArray.get(option.intValue()).getNomeDoAutor());
			
			switch (Integer.parseInt(ler.nextLine())) {
			case 1:
				altera.autorArray.get(option).getLivrosDoAutor().remove(altera);
				altera.autorArray.remove(option.intValue());
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
	
			
			if(!Livro.todosOsAutores.contains(autoria)) Livro.todosOsAutores.add(autoria);
				
				cadastro.autorArray.add(Livro.todosOsAutores.get(Livro.todosOsAutores.indexOf(autoria)));
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
