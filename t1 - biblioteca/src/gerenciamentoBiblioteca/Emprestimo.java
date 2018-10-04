package gerenciamentoBiblioteca;

import livros.*;
import usuarios.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class Emprestimo {
	
	private Exemplar emprestado;
	private Usuario emprestante;
	private Integer diasRestantes;
	static ArrayList<Emprestimo> todosOsEmprestimos;
	static Scanner ler = new Scanner(System.in);

	public static boolean novoEmprestimo(Livro paraEmprestar) {
		
		Exemplar emprestar = null;
		Emprestimo emprestando = new Emprestimo();
		
		emprestar = Exemplar.selecionaExemplar(paraEmprestar);
		
		Usuario emprestante = Usuario.selecionaUsuario();
		
		if(emprestante==null) {
			return false;
		}
		
		if(emprestar.getExemplaresDisponiveis()==0) {
			System.out.println("N√£o √© possivel emprestar o exemplar, pois n√£o h√° mais exemplares dispon√≠veis.");
			return false;
		}
		
		emprestando.setEmprestante(emprestante);
		emprestante.getLivrosEmprestados().add(emprestar);
		emprestar.getEmprestimosEfetuados().add(emprestando);
		emprestar.modificaExemplaresDisponiveis('-');
		emprestando.setEmprestado(emprestar);
		
		if (emprestante.isProfessor()) emprestando.setDiasRestantes(15);
		else emprestando.setDiasRestantes(7);
		
		
		return true;
		
	}
	
	
	
	public static boolean renovarEmprestimo (Emprestimo renovar) { ///FUN«√O RETORNA VDD OU FALSO DEPENDENDO SE CONSEGUIR EMPRESTAR
		if (renovar.getEmprestado().getExemplaresDisponiveis()<0) {
			return false;
		}
		if(renovar.getEmprestante().isProfessor()) {
			renovar.setDiasRestantes(renovar.getDiasRestantes()+15);
		}
		else renovar.setDiasRestantes(renovar.getDiasRestantes()+7);
		
		return true;

	}
	
	public static boolean devolverEmprestimo (Emprestimo devolver) {
		
		devolver.setDiasRestantes(null);
		
		Exemplar emprestado = devolver.getEmprestado();
		
		emprestado.modificaExemplaresDisponiveis('+');
		emprestado.getEmprestimosEfetuados().remove(devolver);
		
		devolver.getEmprestante().getLivrosEmprestados().remove(emprestado);
		
		todosOsEmprestimos.remove(devolver);
		
		return true;
	}
	

	public static void imprimeEmprestimosDeUsuarios(Usuario imprime) {
		for(Integer a = Integer.valueOf(0); a.intValue() < todosOsEmprestimos.size(); a++ ) {
			if(todosOsEmprestimos.get(a.intValue()).getEmprestante()==imprime) {
				System.out.println(" emprestou o(s) livro(s):");
				Exemplar.imprimeLivroEExemplar(todosOsEmprestimos.get(a).emprestado);
			}
		}
	}

	public static void imprimeTodosOsEmprestimos(ArrayList<Emprestimo> baseDeEmprestimos) {
		ArrayList<Usuario> impressao = new ArrayList<Usuario>();
		for(Integer a = Integer.valueOf(0); a.intValue() < baseDeEmprestimos.size(); a++ ) {
			Usuario base = baseDeEmprestimos.get(a).getEmprestante();
			if(!impressao.contains(base)) {
				System.out.print("O usu·rio: "+base.getNomeDoUsuario());
				imprimeEmprestimosDeUsuarios(base);
				impressao.add(base);
			}
		}
	}
	public static Emprestimo buscaEmprestimo() {
		System.out.println("Deseja buscar os emprÈstimos: \n (1) - TÌtulo do Livro, (2) - ISBN do Livro, (3) - por usu·rio");
		Integer option = Integer.parseInt(ler.nextLine());
		ArrayList<Emprestimo> buscas = new ArrayList<Emprestimo>();
		
		
				
		if(option < 1||option >3) {
			System.out.println("Nenhuma opÁ„o do tipo");
		return null;
		}
		
		Emprestimo addic = null;
		String buscador = new String();
		switch(option) {
			case 1: {
				System.out.println("digite o nome do livro:");
				break;
			}
			case 2: {
				System.out.println("digite o ISDB do livro:");
				break;
			}
			case 3: {
				System.out.println("digite o nome do usu·rio:");
				break;
			}
		}
		buscador = ler.nextLine();
		for (Integer a = 0; a.intValue() < todosOsEmprestimos.size();a++) {	
			switch(option) {
				case 1:{
					addic = buscaPorTitulo(a, buscador);
					if(addic != null) {
						buscas.add(addic);
					}
					break;
				}
				case 2:{
					addic = buscaPorISBN(a, Integer.parseInt(buscador));
					if(addic != null) {
						buscas.add(addic);
					}
					break;
				}
				case 3:{
					addic = buscaPorUsuario(a, buscador);
					if(addic != null) {
						buscas.add(addic);
					}
					break;
				}
			}
			
		}
		
		if (buscas.size()<1) {
			System.out.println("Nada encontrado");
			return null;
		}
		
		if(buscas.size()==1) {
			return buscas.get(0);
		}
		else {
			imprimeTodosOsEmprestimos(buscas);
			System.out.println("selecione o Ìndice do emprestimo buscado");
			Integer selecao = Integer.parseInt(ler.nextLine());
			if(!(selecao<buscas.size())) {
				System.out.println("Livro Inv·lido");
				return null;
			}
			return buscas.get(selecao);
		}
		
	}
	

	
	public static Emprestimo buscaPorTitulo(Integer a, String buscador) {
		
		Livro procurando = todosOsEmprestimos.get(a.intValue()).getEmprestado().getLivroDesseExemplar();
		if(procurando.getNomeDoLivro().contains(buscador)) {
			return todosOsEmprestimos.get(a.intValue());
		}
		else return null;
	}
		
	
	public static Emprestimo buscaPorISBN(Integer a, Integer buscador) {
		
		Exemplar procurando = todosOsEmprestimos.get(a.intValue()).getEmprestado();
		if(procurando.getCodigoISBN().equals(buscador)) {
			return todosOsEmprestimos.get(a.intValue());
		}
		else return null;
	}	
	
	public static Emprestimo buscaPorUsuario(Integer a, String buscador) {
		
		Usuario procurando = todosOsEmprestimos.get(a.intValue()).getEmprestante();
		if(procurando.getNomeDoUsuario().contains(buscador)) {
			return todosOsEmprestimos.get(a.intValue());
		}
		else return null;
	}
	
	public Exemplar getEmprestado() {
		return emprestado;
	}

	public void setEmprestado(Exemplar emprestado) {
		this.emprestado = emprestado;
	}

	public Usuario getEmprestante() {
		return emprestante;
	}
	

	public void setEmprestante(Usuario emprestante) {
		this.emprestante = emprestante;
	}

	public Integer getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(Integer diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
	
}

class UsuarioComparator implements Comparator<Emprestimo> {
	public int compare(Emprestimo emp1, Emprestimo emp2) {
		return emp1.getEmprestante().getNomeDoUsuario().compareTo(emp2.getEmprestante().getNomeDoUsuario());
	}
}
