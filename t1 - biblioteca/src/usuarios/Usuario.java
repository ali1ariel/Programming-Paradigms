package usuarios;
import java.util.ArrayList;
import java.util.Scanner;

import livros.*;

public class Usuario {
	private String nomeDoUsuario;
	private Login user;
	private ArrayList<Exemplar> livrosEmprestados = new ArrayList<Exemplar>();
	private boolean professor;
	private Integer multa = 0;
	private static Integer totalDeUsuarios = 0;
	static Scanner ler = new Scanner(System.in);
	static ArrayList<Usuario> todosOsUsuarios = new ArrayList<Usuario>();

	
	public static void cadastrarUsuario() {
		
		Usuario.setTotalDeUsuarios(Usuario.getTotalDeUsuarios() + 1);
		Usuario cadastro = new Usuario();
		
		System.out.println("Digite o nome do novo usuario");
		cadastro.setNomeDoUsuario(ler.nextLine());
				
		cadastro.setUser(Login.defineLogin());
		
		System.out.println("O usu�rio � um aluno ou professor? (1) - Aluno, (outro) - professor");
		if(Integer.parseInt(ler.nextLine())==1) {
			cadastro.setProfessor(false);
		} else {
			cadastro.setProfessor(true);
		}
			
		todosOsUsuarios.add(cadastro);
		
		return;
	}
	
	public static void excluirUsuario(Usuario excluir) {
			
		if (excluir.getLivrosEmprestados().size()>0) {
			System.out.println("N�o � poss�vel excluir esse usu�rio, pois apresenta livros ainda emprestados.");
			return;
		}
		
		if (excluir.getMulta().intValue()>0) {
			System.out.println("N�o � poss�vel excluir esse usu�rio, pois apresenta multas vencidas.");
			return;
		}
		
		setTotalDeUsuarios(Usuario.getTotalDeUsuarios() - 1);
		todosOsUsuarios.remove(excluir);
		return;
	}

	public static void listarUsuarios (ArrayList<Usuario> imprime) {
	for(Integer a = 0; a.intValue() < imprime.size();a++) {
		System.out.print("O usu�rio "+a.intValue());
		imprimeUsuario(imprime.get(a));
	}
}
	
	public static void imprimeUsuario (Usuario imprime) {
		System.out.print(" se chama: "+imprime.getNomeDoUsuario()+" e seu login �: "+imprime.getUser().getUserName());
		if(imprime.isProfessor()) System.out.println(" e � professor.");
		else System.out.println(" e � aluno.");
	}
	
	public static Usuario buscaUsuario () {
		
		Usuario usuarioBuscado = null;
		
		System.out.println("Voc� gostaria de buscar por nome ou pelo login? (1) - Nome, (outro) - Login");
		Integer option = Integer.parseInt(ler.nextLine());
		
		System.out.println("Digite o Nome/Login buscado");
		String busca = ler.nextLine();
		
		switch (option.intValue()) {
		case 1:
			usuarioBuscado = buscaUsuarioNome(busca);
			break;
			default:
				for (Integer a = 0; a.intValue() < todosOsUsuarios.size();a++) {
					if(todosOsUsuarios.get(a).getUser().getUserName().equals(busca)) {
						usuarioBuscado = todosOsUsuarios.get(a);
					}
				}
		}
		
		
		
		return usuarioBuscado;
	}
	
	public static Usuario buscaUsuarioNome(String busca) {
		
		ArrayList<Usuario> buscas = new ArrayList<Usuario>();
		
		
		
		for (Integer a = 0; a.intValue() < todosOsUsuarios.size();a++) {
			if(todosOsUsuarios.get(a).getNomeDoUsuario().contains(busca)) {
				buscas.add(todosOsUsuarios.get(a));
			}
		}
		
		if (buscas.size()<1) return null;
		if(buscas.size()==1) return buscas.get(0);
		else {
			listarUsuarios(buscas);
			System.out.println("selecione o �ndice do usu�rio buscado");
			Integer selecionado = Integer.parseInt(ler.nextLine());
			if(!(selecionado<buscas.size())) {
				System.out.println("usu�rio Inv�lido");
				return null;
			}
			return buscas.get(selecionado);
		}
		
	}
	
	
	public static Usuario selecionaUsuario() {
		System.out.println("Qual o usuário que irá emprestar?");
		Usuario emprestante = Usuario.buscaUsuario();
		if(!(podeEfetuarUsuario(emprestante))) {
			System.out.println("Não foi permitido novo emprestimo");
			return null;
		}
		
		return emprestante;
		
	}
	
	public static boolean podeEfetuarUsuario (Usuario conferir) {
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
	
	
	
	//GETTERS AND SETTERS
	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}
	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}
	public boolean isProfessor() {
		return professor;
	}
	public void setProfessor(boolean professor) {
		this.professor = professor;
	}
	public Integer getMulta() {
		return multa;
	}
	public void setMulta(Integer multa) {
		this.multa = multa;
	}
	public static Integer getTotalDeUsuarios() {
		return totalDeUsuarios;
	}
	public static void setTotalDeUsuarios(Integer totalDeUsuarios) {
		Usuario.totalDeUsuarios = totalDeUsuarios;
	}
	public Login getUser() {
		return user;
	}
	public void setUser(Login user) {
		this.user = user;
	}
	public ArrayList<Exemplar> getLivrosEmprestados() {
		return livrosEmprestados;
	}
	public void setLivrosEmprestados(ArrayList<Exemplar> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
	}
}
