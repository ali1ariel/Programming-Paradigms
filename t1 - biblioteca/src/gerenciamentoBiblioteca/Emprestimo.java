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
	private static ArrayList<Emprestimo> todosOsEmprestimos = new ArrayList<Emprestimo>();
	static Scanner ler = new Scanner(System.in);
	
	public static void menuEmprestimo() {
		do {
			System.out.println("O que gostaria de fazer? \n (1) - Fazer empréstimo. (2) - devolver um livro (3) - renovar. (4) - Imprimir Empréstimo.");
			switch (Integer.parseInt(ler.nextLine())) {
			case 1:
				Livro paraEmprestar = Livro.buscarLivro(Livro.getTodosOsLivros());
				novoEmprestimo(paraEmprestar);
				break;
			case 2:
				 Emprestimo devolver = buscaEmprestimo();
				if(devolver == null) {
					System.out.println("Emprestimo nao encontrado");
					break;
				}
				devolverEmprestimo(devolver);
				break;
			case 3: 
				Emprestimo renovar = buscaEmprestimo();
				if(renovar == null) {
					System.out.println("Emprestimo nao encontrado");
					break;
				}
				renovarEmprestimo(renovar);
				break;				
			case 4:
				imprimeTodosOsEmprestimos(getTodosOsEmprestimos());
				break;
				default:
					System.out.println("Opção invalida");
					break;
			}
			System.out.println("deseja algo a mais com os emprestimos?\n (1) - SIM. (2) - Nao.");
		}while(Integer.parseInt(ler.nextLine())==1);
		
	}

	public static boolean novoEmprestimo(Livro paraEmprestar) {
		
		Exemplar emprestar = null;
		Emprestimo emprestando = new Emprestimo();
		
		emprestar = Exemplar.selecionaExemplar(paraEmprestar);
		
		Usuario emprestante = Usuario.selecionaUsuario();
		
		if(emprestante==null) {
			return false;
		}
		
		if(emprestar.getExemplaresDisponiveis()==0) {
			System.out.println("Nao e possivel emprestar o exemplar, pois nao ha mais exemplares disponiveis.");
			System.out.println("Deseja reservar uma unidade? (1) - sim");
			if(Integer.parseInt(ler.nextLine())==1) {
				return Reserva.solicitarReserva(emprestar);
			}
			return false;
		}
		
		emprestando.setEmprestante(emprestante);
		emprestante.getLivrosEmprestados().add(emprestar);
		emprestar.getEmprestimosEfetuados().add(emprestando);
		emprestar.modificaExemplaresDisponiveis('-');
		emprestando.setEmprestado(emprestar);
		
		if (emprestante.isProfessor()) emprestando.setDiasRestantes(15);
		else emprestando.setDiasRestantes(7);
		
		Emprestimo.getTodosOsEmprestimos().add(emprestando);
		
		return true;
		
	}
	
	
	
	public static boolean renovarEmprestimo (Emprestimo renovar) { ///FUNÇÃO RETORNA VDD OU FALSO DEPENDENDO SE CONSEGUIR EMPRESTAR
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
		
		getTodosOsEmprestimos().remove(devolver);
		
		return true;
	}
	

	public static void imprimeEmprestimosDeUsuarios(Usuario imprime) {
		for(Integer a = Integer.valueOf(0); a.intValue() < getTodosOsEmprestimos().size(); a++ ) {
			if(getTodosOsEmprestimos().get(a.intValue()).getEmprestante()==imprime) {
				System.out.println(" emprestou o livro:");
				Exemplar.imprimeLivroEExemplar(getTodosOsEmprestimos().get(a).emprestado);
				System.out.println(" e faltam "+getTodosOsEmprestimos().get(a.intValue()).getDiasRestantes()+" dias pra devolver.");
				System.out.println("__________________");
			}
		}
	}

	public static void imprimeTodosOsEmprestimos(ArrayList<Emprestimo> baseDeEmprestimos) {
		ArrayList<Usuario> impressao = new ArrayList<Usuario>();
		for(Integer a = 0; a.intValue() < baseDeEmprestimos.size(); a++ ) {
			Usuario base = baseDeEmprestimos.get(a).getEmprestante();
			if(!impressao.contains(base)) {
				System.out.print("O usuario: "+base.getNomeDoUsuario());
				imprimeEmprestimosDeUsuarios(base);
				impressao.add(base);
			}
		}
	}
	
	public static Emprestimo buscaEmprestimo() {
		System.out.println("Deseja buscar os emprestimos: \n (1) - Título do Livro, (2) - ISBN do Livro, (3) - por usuario");
		Integer option = Integer.parseInt(ler.nextLine());
		ArrayList<Emprestimo> buscas = new ArrayList<Emprestimo>();
		
		
				
		if(option < 1||option >3) {
			System.out.println("Nenhuma opção do tipo");
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
				System.out.println("digite o nome do usuario:");
				break;
			}
		}
		buscador = ler.nextLine();
		for (Integer a = 0; a.intValue() < getTodosOsEmprestimos().size();a++) {	
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
			System.out.println("selecione o indice do emprestimo buscado");
			Integer selecao = Integer.parseInt(ler.nextLine());
			if(!(selecao<buscas.size())) {
				System.out.println("Livro Invalido");
				return null;
			}
			return buscas.get(selecao);
		}
		
	}
	

	
	public static Emprestimo buscaPorTitulo(Integer a, String buscador) {
		
		Livro procurando = getTodosOsEmprestimos().get(a.intValue()).getEmprestado().getLivroDesseExemplar();
		if(procurando.getNomeDoLivro().contains(buscador)) {
			return getTodosOsEmprestimos().get(a.intValue());
		}
		else return null;
	}
		
	
	public static Emprestimo buscaPorISBN(Integer a, Integer buscador) {
		
		Exemplar procurando = getTodosOsEmprestimos().get(a.intValue()).getEmprestado();
		if(procurando.getCodigoISBN().equals(buscador)) {
			return getTodosOsEmprestimos().get(a.intValue());
		}
		else return null;
	}	
	
	public static Emprestimo buscaPorUsuario(Integer a, String buscador) {
		
		Usuario procurando = getTodosOsEmprestimos().get(a.intValue()).getEmprestante();
		if(procurando.getNomeDoUsuario().contains(buscador)) {
			return getTodosOsEmprestimos().get(a.intValue());
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

	public static ArrayList<Emprestimo> getTodosOsEmprestimos() {
		return todosOsEmprestimos;
	}

	public static void setTodosOsEmprestimos(ArrayList<Emprestimo> todosOsEmprestimos) {
		Emprestimo.todosOsEmprestimos = todosOsEmprestimos;
	}
	
}

class UsuarioComparator implements Comparator<Emprestimo> {
	public int compare(Emprestimo emp1, Emprestimo emp2) {
		return emp1.getEmprestante().getNomeDoUsuario().compareTo(emp2.getEmprestante().getNomeDoUsuario());
	}
}
