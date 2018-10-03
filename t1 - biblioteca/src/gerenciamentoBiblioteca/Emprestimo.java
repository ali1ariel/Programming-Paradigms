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
		
		if(paraEmprestar.getExemplaresArray().size()==1) emprestar = paraEmprestar.getExemplaresArray().get(0);
		else {
			Exemplar.imprimeExemplaresDoLivro(paraEmprestar);
			System.out.println("Qual edição deseja emprestar?");
			Integer exemp = Integer.parseInt(ler.nextLine());
			if(exemp > paraEmprestar.getExemplaresArray().size()) {
				System.out.println("Exemplar inválido.");
				return false;
			}
			emprestar = paraEmprestar.getExemplaresArray().get(exemp);
		}
		
		System.out.println("Qual o usuário que irá emprestar?");
		Usuario emprestante = Usuario.buscaUsuario();
		if(!(podeEmprestarUsuario(emprestante))) {
			System.out.println("Não foi permitido novo emprestimo");
			return false;
		}
		
		if(emprestar.getExemplaresDisponiveis()==0) {
			System.out.println("Não é possivel emprestar o exemplar, pois não há mais exemplares disponíveis.");
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
	
	public static boolean podeEmprestarUsuario (Usuario conferir) {
		if(conferir.getMulta().intValue()>0) {
			if(conferir.getLivrosEmprestados().isEmpty()) {
				System.out.println("H� uma multa, por�m nenhum livro emprestado.");
				return true; // tem multa mas não há livros emprestados
			}
			System.out.println("Há multa pendente e um livro emprestado.");
			return false;
			}
		if(conferir.isProfessor()) {
			if(conferir.getLivrosEmprestados().size()<=5) return true;
		}
		else {
			if(conferir.getLivrosEmprestados().size()<=3) return true;
		}
		System.out.println("Máximo de livros emprestados");
		return false;
	}
	
	public static boolean renovarEmprestimo (Emprestimo renovar) { ///FUN��O RETORNA VDD OU FALSO DEPENDENDO SE CONSEGUIR EMPRESTAR
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
	

	public static void imprimeEmprestimos() {
		
	}

	public static void imprimeTodosOsEmprestimos() {
		
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
