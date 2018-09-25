package livros;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import usuarios.Usuario;

public class Exemplar extends Livro {
	private String edicaoDoLivro;
	private String anoDoLivro;
	private Integer numeroDeExemplares;
	private Integer codigoISBN;
	private Integer exemplaresDisponiveis;
	private ArrayList<Usuario> usuarioQueEmprestou = new ArrayList<Usuario>();
	static Scanner ler;
	
	
	public static void adicionarExemplar(Livro cadastro) {
		
		ler = new Scanner(System.in);
		
		Exemplar novoExemplar = new Exemplar();
		
		System.out.println("Digite a ediï¿½ï¿½o do novo livro");
		novoExemplar.setEdicaoDoLivro(ler.nextLine());
		
		System.out.println("Digite o ano do novo livro");
		novoExemplar.setAnoDoLivro(ler.nextLine());
		
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt(ler.nextLine()));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		System.out.println("Digite o nï¿½mero do cï¿½digo ISBN");
		novoExemplar.setCodigoISBN(Integer.parseInt(ler.nextLine()));
		
		cadastro.exemplaresArray.add(novoExemplar);
		
	}
		
	public static void imprimeExemplaresDoLivro(Livro imprime) {
		
		if(imprime.exemplaresArray.size()>1) {
			System.out.println(" apresenta os seguintes exemplares:");
		} else {
			System.out.println(" apresenta o exemplar:");
		}
		
		for (Integer a = 0;a<imprime.exemplaresArray.size();a++) {
			System.out.println(a+" - "+"edição: "+imprime.exemplaresArray.get(a).getEdicaoDoLivro()+", ano: "+imprime.exemplaresArray.get(a).getAnoDoLivro()+" e do código ISBN: "+imprime.exemplaresArray.get(a).getCodigoISBN()+", tem "+imprime.exemplaresArray.get(a).getExemplaresDisponiveis()+" de "+imprime.exemplaresArray.get(a).getNumeroDeExemplares()+" exemplares disponíveis.");
		}
	}
	
	public static void alteraExemplar(Livro altera) {
		
		ler = new Scanner (System.in);
		imprimeExemplaresDoLivro(altera);
		Integer exemp = Integer.valueOf(0);
		System.out.println("Você quer: \n (1) - Editar um exemplar (2) - Excluir um exemplar (3) - Adicionar um exemplar?");
		System.out.println("Se o livro tiver apenas um único exemplar, ao excluí-lo também estará excluindo o livro.");
		Integer option = Integer.parseInt(ler.nextLine());
		if(option.intValue()==1||option.intValue()==2) {
			System.out.println("Qual exemplar? digite o código que aparece no início da linha.");
			exemp = Integer.parseInt(ler.nextLine());
		}
		
		switch (option.intValue()) {
		case 1: 
			
			do {
				System.out.println("O que deseja alterar? \n (1) - numero da edicao. (2) - Ano do Livro. (3) - numero de exemplares ou (4) - codigo ISDB");
				switch (Integer.parseInt(ler.nextLine())) {
				case 1: 
					System.out.println("Digite a alteração");
					altera.exemplaresArray.get(exemp).setEdicaoDoLivro(ler.nextLine());
					break;
				case 2: 
					System.out.println("Digite a alteração");
					altera.exemplaresArray.get(exemp).setAnoDoLivro(ler.nextLine());
					break;
				case 3: 
					System.out.println("Digite a alteração");
					Integer verifica = Integer.parseInt(ler.nextLine());
					Integer emprestados = (altera.exemplaresArray.get(exemp).getNumeroDeExemplares().intValue() - altera.exemplaresArray.get(exemp).getExemplaresDisponiveis().intValue());
					if (verifica.intValue() >= emprestados) {
						altera.exemplaresArray.get(exemp).setNumeroDeExemplares(verifica);
						altera.exemplaresArray.get(exemp).setExemplaresDisponiveis(verifica - emprestados);
					} else {
						System.out.println("não foi possível alterar para um número de exemplare menor do que o de exemplares emprestados.");
					}
					break;
				case 4: 
					System.out.println("Digite a alteração");
					altera.exemplaresArray.get(exemp).setCodigoISBN(Integer.parseInt(ler.nextLine()));
					break;
				}
				
				System.out.println();
				imprimeExemplaresDoLivro(altera);
				System.out.println("\n Deseja alterar mais algo nesse exemplar? (1) - sim. (outro) - não");
			} while (Integer.parseInt(ler.nextLine())==1);	
			
			break;
		case 2:
			if(altera.exemplaresArray.size()==1) { 
				excluirLivro(altera);
			}
			else {
				altera.exemplaresArray.remove(exemp.intValue());
			}
			break;
		case 3:
			adicionarExemplar(altera);
			break;
		default:
			return;
				
		}
		
		
		
		
		
	}
		
	public static boolean verificaExemplares(ArrayList<Exemplar> exemplares) {
		for (Integer a = 0; a < exemplares.size();a++){
			if(exemplares.get(a).getNumeroDeExemplares()!=exemplares.get(a).getExemplaresDisponiveis()) return false;
		}
		return true;
	}
	

	
	//SETTERS AND GETTERS
	public String getEdicaoDoLivro() {
		return edicaoDoLivro;
	}
	public void setEdicaoDoLivro(String edicao) {
		String numeroDaEdicao = (edicao+"ª");
		this.edicaoDoLivro = numeroDaEdicao;
	}
	public String getAnoDoLivro() {
		return anoDoLivro;
	}
	public void setAnoDoLivro(String ano) {
		this.anoDoLivro = ano;
	}
	public Integer getNumeroDeExemplares() {
		return numeroDeExemplares;
	}
	public void setNumeroDeExemplares(Integer numeroDeExemplares) {
		this.numeroDeExemplares = numeroDeExemplares;
	}
	public Integer getCodigoISBN() {
		return codigoISBN;
	}
	public void setCodigoISBN(Integer codigoISBN) {
		this.codigoISBN = codigoISBN;
	}
	public Integer getExemplaresDisponiveis() {
		return exemplaresDisponiveis;
	}
	public void setExemplaresDisponiveis(Integer exemplaresDisponiveis) {
		this.exemplaresDisponiveis = exemplaresDisponiveis;
	}
	public ArrayList<Usuario> getUsuarioQueEmprestou() {
		return usuarioQueEmprestou;
	}
	public void setUsuarioQueEmprestou(ArrayList<Usuario> usuarioQueEmprestou) {
		this.usuarioQueEmprestou = usuarioQueEmprestou;
	}
}


class ISBNLivroComparator implements Comparator<Exemplar>{

	public int compare(Exemplar exemplar1, Exemplar exemplar2) {
		return exemplar1.getCodigoISBN().compareTo(exemplar2.getCodigoISBN());
	}
	
	
}

