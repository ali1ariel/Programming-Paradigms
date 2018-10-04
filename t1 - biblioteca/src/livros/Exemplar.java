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
		
		System.out.println("Digite a ediï¿½ï¿½o do novo livro");
		novoExemplar.setEdicaoDoLivro(ler.nextLine());
		
		System.out.println("Digite o ano do novo livro");
		novoExemplar.setAnoDoLivro(ler.nextLine());
		
		
		System.out.println("Digite a quantidade de exemplares do novo livro");
		novoExemplar.setNumeroDeExemplares(Integer.parseInt(ler.nextLine()));
		novoExemplar.setExemplaresDisponiveis(novoExemplar.getNumeroDeExemplares());
		
		System.out.println("Digite o nï¿½mero do cï¿½digo ISBN");
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
			System.out.print(a+" - ");
			imprimeInformacoesDoExemplar(imprime.getExemplaresArray().get(a));
		}
	}
	
	public static void imprimeInformacoesDoExemplar (Exemplar imprime) {
		System.out.println("ediï¿½ï¿½o: "+imprime.getEdicaoDoLivro()+", ano: "+imprime.getAnoDoLivro()+" e do cï¿½digo ISBN: "+imprime.getCodigoISBN()+", tem "+imprime.getExemplaresDisponiveis()+" de "+imprime.getNumeroDeExemplares()+" exemplares disponï¿½veis.");
	}
	
	public static void imprimeLivroEExemplar(Exemplar imprime) {
		Livro paraImprimir = imprime.getLivroDesseExemplar();
		Livro.imprimeInformaçõesLivro(paraImprimir);
		System.out.println("E o exemplar emprestado desse livro contém as informações:");
		imprimeInformacoesDoExemplar(imprime);
		System.out.println("__________________");
		
	}
	
	public static void alteraExemplar(Livro altera) {
		
		ler = new Scanner (System.in);
		imprimeExemplaresDoLivro(altera);
		Integer exemp = Integer.valueOf(0);
		System.out.println("Vocï¿½ quer: \n (1) - Editar um exemplar (2) - Excluir um exemplar (3) - Adicionar um exemplar?");
		System.out.println("Se o livro tiver apenas um ï¿½nico exemplar, ao excluï¿½-lo tambï¿½m estarï¿½ excluindo o livro.");
		Integer option = Integer.parseInt(ler.nextLine());
		if(option.intValue()==1||option.intValue()==2) {
			System.out.println("Qual exemplar? digite o cï¿½digo que aparece no inï¿½cio da linha.");
			exemp = Integer.parseInt(ler.nextLine());
		}
		
		switch (option.intValue()) {
		case 1: 
			
			do {
				System.out.println("O que deseja alterar? \n (1) - numero da edicao. (2) - Ano do Livro. (3) - numero de exemplares ou (4) - codigo ISDB");
				switch (Integer.parseInt(ler.nextLine())) {
				case 1: 
					System.out.println("Digite a alteraï¿½ï¿½o");
					altera.getExemplaresArray().get(exemp).setEdicaoDoLivro(ler.nextLine());
					break;
				case 2: 
					System.out.println("Digite a alteraï¿½ï¿½o");
					altera.getExemplaresArray().get(exemp).setAnoDoLivro(ler.nextLine());
					break;
				case 3: 
					System.out.println("Digite a alteraï¿½ï¿½o");
					Integer verifica = Integer.parseInt(ler.nextLine());
					Integer emprestados = (altera.getExemplaresArray().get(exemp).getNumeroDeExemplares().intValue() - altera.getExemplaresArray().get(exemp).getExemplaresDisponiveis().intValue());
					if (verifica.intValue() >= emprestados) {
						altera.getExemplaresArray().get(exemp).setNumeroDeExemplares(verifica);
						altera.getExemplaresArray().get(exemp).setExemplaresDisponiveis(verifica - emprestados);
					} else {
						System.out.println("nï¿½o foi possï¿½vel alterar para um nï¿½mero de exemplare menor do que o de exemplares emprestados.");
					}
					break;
				case 4: 
					System.out.println("Digite a alteraï¿½ï¿½o");
					altera.getExemplaresArray().get(exemp).setCodigoISBN(Integer.parseInt(ler.nextLine()));
					break;
				}
				
				System.out.println();
				imprimeExemplaresDoLivro(altera);
				System.out.println("\n Deseja alterar mais algo nesse exemplar? (1) - sim. (outro) - nï¿½o");
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
	
	public static Exemplar selecionaExemplar(Livro verifica) {	
		
		if(verifica.getExemplaresArray().size()==1) return verifica.getExemplaresArray().get(0);
		
		else {
			
			Exemplar.imprimeExemplaresDoLivro(verifica);
			System.out.println("selecione o índice");
			Integer exemp = Integer.parseInt(ler.nextLine());
			if((exemp > verifica.getExemplaresArray().size())||exemp<1) {
				System.out.println("Exemplar invÃ¡lido.");
				return null;
			}
			return verifica.getExemplaresArray().get(exemp);
		}
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
		String numeroDaEdicao = (edicao+"ï¿½");
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


