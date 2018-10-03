package livros;
import java.util.ArrayList;
import java.util.Scanner;
import gerenciamentoBiblioteca.Emprestimo;

public class Exemplar {
	private String edicaoDoLivro;
	private String anoDoLivro;
	private Integer numeroDeExemplares;
	private Integer codigoISBN;
	private Integer exemplaresDisponiveis;
	private Livro livroDesseExemplar;
	private ArrayList<Emprestimo> emprestimosEfetuados = new ArrayList<Emprestimo>();
	static Scanner ler;
	
	
	public static void adicionarExemplar(Livro cadastro) {
		
		ler = new Scanner(System.in);
		
		Exemplar novoExemplar = new Exemplar();
		
		System.out.println("Digite a edi��o do novo livro");
		novoExemplar.setEdicaoDoLivro(ler.nextLine());
		
		System.out.println("Digite o ano do novo livro");
		novoExemplar.setAnoDoLivro(ler.nextLine());
		
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt(ler.nextLine()));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		System.out.println("Digite o n�mero do c�digo ISBN");
		novoExemplar.setCodigoISBN(Integer.parseInt(ler.nextLine()));
		
		novoExemplar.setLivroDesseExemplar(cadastro);
		cadastro.getExemplaresArray().add(novoExemplar);
		
		
	}
		
	public static void imprimeExemplaresDoLivro(Livro imprime) {
		
		if(imprime.getExemplaresArray().size()>1) {
			System.out.println(" apresenta os seguintes exemplares:");
		} else {
			System.out.println(" apresenta o exemplar:");
		}
		
		for (Integer a = 0;a<imprime.getExemplaresArray().size();a++) {
			System.out.println(a+" - "+"edi��o: "+imprime.getExemplaresArray().get(a).getEdicaoDoLivro()+", ano: "+imprime.getExemplaresArray().get(a).getAnoDoLivro()+" e do c�digo ISBN: "+imprime.getExemplaresArray().get(a).getCodigoISBN()+", tem "+imprime.getExemplaresArray().get(a).getExemplaresDisponiveis()+" de "+imprime.getExemplaresArray().get(a).getNumeroDeExemplares()+" exemplares dispon�veis.");
		}
	}
	
	public static void alteraExemplar(Livro altera) {
		
		ler = new Scanner (System.in);
		imprimeExemplaresDoLivro(altera);
		Integer exemp = Integer.valueOf(0);
		System.out.println("Voc� quer: \n (1) - Editar um exemplar (2) - Excluir um exemplar (3) - Adicionar um exemplar?");
		System.out.println("Se o livro tiver apenas um �nico exemplar, ao exclu�-lo tamb�m estar� excluindo o livro.");
		Integer option = Integer.parseInt(ler.nextLine());
		if(option.intValue()==1||option.intValue()==2) {
			System.out.println("Qual exemplar? digite o c�digo que aparece no in�cio da linha.");
			exemp = Integer.parseInt(ler.nextLine());
		}
		
		switch (option.intValue()) {
		case 1: 
			
			do {
				System.out.println("O que deseja alterar? \n (1) - numero da edicao. (2) - Ano do Livro. (3) - numero de exemplares ou (4) - codigo ISDB");
				switch (Integer.parseInt(ler.nextLine())) {
				case 1: 
					System.out.println("Digite a altera��o");
					altera.getExemplaresArray().get(exemp).setEdicaoDoLivro(ler.nextLine());
					break;
				case 2: 
					System.out.println("Digite a altera��o");
					altera.getExemplaresArray().get(exemp).setAnoDoLivro(ler.nextLine());
					break;
				case 3: 
					System.out.println("Digite a altera��o");
					Integer verifica = Integer.parseInt(ler.nextLine());
					Integer emprestados = (altera.getExemplaresArray().get(exemp).getNumeroDeExemplares().intValue() - altera.getExemplaresArray().get(exemp).getExemplaresDisponiveis().intValue());
					if (verifica.intValue() >= emprestados) {
						altera.getExemplaresArray().get(exemp).setNumeroDeExemplares(verifica);
						altera.getExemplaresArray().get(exemp).setExemplaresDisponiveis(verifica - emprestados);
					} else {
						System.out.println("n�o foi poss�vel alterar para um n�mero de exemplare menor do que o de exemplares emprestados.");
					}
					break;
				case 4: 
					System.out.println("Digite a altera��o");
					altera.getExemplaresArray().get(exemp).setCodigoISBN(Integer.parseInt(ler.nextLine()));
					break;
				}
				
				System.out.println();
				imprimeExemplaresDoLivro(altera);
				System.out.println("\n Deseja alterar mais algo nesse exemplar? (1) - sim. (outro) - n�o");
			} while (Integer.parseInt(ler.nextLine())==1);	
			
			break;
		case 2:
			if(altera.getExemplaresArray().size()==1) { 
				Livro.excluirLivro(altera);
			}
			else {
				altera.getExemplaresArray().remove(exemp.intValue());
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
	
	
	
	public boolean modificaExemplaresDisponiveis(char verifica) {
		if(verifica == '+') {
			this.exemplaresDisponiveis = exemplaresDisponiveis+1;
			return true;
		}
		else if(verifica == '-') {
			this.exemplaresDisponiveis = exemplaresDisponiveis-1;
			return true;
		}
		else return false;
		
	}
	
	

	
	//SETTERS AND GETTERS
	public String getEdicaoDoLivro() {
		return edicaoDoLivro;
	}
	public void setEdicaoDoLivro(String edicao) {
		String numeroDaEdicao = (edicao+"�");
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
	public Livro getLivroDesseExemplar() {
		return livroDesseExemplar;
	}

	public void setLivroDesseExemplar(Livro livroDesseExemplar) {
		this.livroDesseExemplar = livroDesseExemplar;
	}

	public ArrayList<Emprestimo> getEmprestimosEfetuados() {
		return emprestimosEfetuados;
	}

	public void setEmprestimosEfetuados(ArrayList<Emprestimo> emprestimosEfetuados) {
		this.emprestimosEfetuados = emprestimosEfetuados;
	}
}


