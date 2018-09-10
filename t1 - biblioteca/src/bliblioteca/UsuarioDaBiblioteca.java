package bliblioteca;
import java.util.Scanner;

public class UsuarioDaBiblioteca {
	private String nomeDoUsuario;
	private String loginDoUsuario;
	private String senhaDoUsuario;
	private boolean professor;
	private int livrosEmprestados;
	private static int totalDeUsuarios = 0;
	private static int totalDeAlunos = 0;
	private static int totalDeProfessores = 0;
	private Scanner ler;
	

	

	public UsuarioDaBiblioteca incluirUsuario() {
		UsuarioDaBiblioteca.setTotalDeUsuarios(UsuarioDaBiblioteca.getTotalDeUsuarios()+1);
		UsuarioDaBiblioteca user = new UsuarioDaBiblioteca();
		ler = new Scanner(System.in);
		System.out.println("Digite o nome do novo Usuário");
		String nome = ler.nextLine();
		user.setNomeDoUsuario(nome);
		System.out.println("Digite o login do novo Usuário");
		String login = ler.nextLine();
		user.setLoginDoUsuario(login);
		System.out.println("Digite a senha do novo Usuário");
		String senha = ler.nextLine();
		user.setLoginDoUsuario(senha);
		System.out.println("É professor? \n 1 - Sim \n outro - Não");
		int prof = ler.nextInt();
		if (prof == 1) {
			user.setProfessor(true);
			UsuarioDaBiblioteca.setTotalDeProfessores(UsuarioDaBiblioteca.getTotalDeProfessores()+1);
		}
		else {
			user.setProfessor(false);
			UsuarioDaBiblioteca.setTotalDeProfessores(UsuarioDaBiblioteca.getTotalDeProfessores()+1);
		}
		user.setLivrosEmprestados(0);
			
		return user;		
	}
	
	public void excluirUsuario(UsuarioDaBiblioteca excluindo) {
		if(excluindo.isProfessor()) {
			UsuarioDaBiblioteca.totalDeProfessores--;
		}
		else UsuarioDaBiblioteca.totalDeAlunos--;
		UsuarioDaBiblioteca.totalDeUsuarios--;
	}

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getSenhaDoUsuario() {
		return senhaDoUsuario;
	}

	public void setSenhaDoUsuario(String senhaDoUsuario) {
		this.senhaDoUsuario = senhaDoUsuario;
	}

	public boolean isProfessor() {
		return professor;
	}

	public void setProfessor(boolean professor) {
		this.professor = professor;
	}

	public int getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(int livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}

	public static int getTotalDeUsuarios() {
		return totalDeUsuarios;
	}

	public static void setTotalDeUsuarios(int totalDeUsuarios) {
		UsuarioDaBiblioteca.totalDeUsuarios = totalDeUsuarios;
	}

	public static int getTotalDeProfessores() {
		return totalDeProfessores;
	}

	public static void setTotalDeProfessores(int totalDeProfessores) {
		UsuarioDaBiblioteca.totalDeProfessores = totalDeProfessores;
	}

	public static int getTotalDeAlunos() {
		return totalDeAlunos;
	}

	public static void setTotalDeAlunos(int totalDeAlunos) {
		UsuarioDaBiblioteca.totalDeAlunos = totalDeAlunos;
	}

	public String getLoginDoUsuario() {
		return loginDoUsuario;
	}

	public void setLoginDoUsuario(String loginDoUsuario) {
		this.loginDoUsuario = loginDoUsuario;
	}
}