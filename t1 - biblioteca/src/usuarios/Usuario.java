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
	private static ArrayList<Usuario> todosOsUsuarios = new ArrayList<Usuario>();

	
	public static void menuUsuario() {
		do {
			System.out.println("O que deseja fazer? (1) - cadastrar usuario. (2) - excluir usuário. (3) - listar usuarios. (4) - pagar multa de usuario.");
			Integer option = Integer.parseInt(ler.nextLine());
			switch(option) {
			case 1:{
				cadastrarUsuario();
				break;
			}
			case 2:{
				Usuario excluir = buscaUsuario();
				excluirUsuario(excluir);
				break;
			}
			case 3:{
				listarUsuarios(getTodosOsUsuarios());
				break;
			}
			case 4:{
				Usuario devedor = buscaUsuario();
				devedor.pagaMulta();
				break;
			}
			default:{
				System.out.println("Opção inválida");
				break;
			}
			}
			System.out.println("Deseja fazer algo a mais com os usuários?\n (1) - Sim. (outro) - não.");
			
		}while(Integer.parseInt(ler.nextLine())==1);
		return;
	}
	
	public static void cadastrarUsuario() {
		
		Usuario.setTotalDeUsuarios(Usuario.getTotalDeUsuarios() + 1);
		Usuario cadastro = new Usuario();
		
		System.out.println("Digite o nome do novo usuario");
		cadastro.setNomeDoUsuario(ler.nextLine());
				
		cadastro.setUser(Login.defineLogin());
		
		System.out.println(cadastro.getUser().getUserName());
		
		System.out.println("O usuario e um aluno ou professor? (1) - Aluno, (outro) - professor");
		if(Integer.parseInt(ler.nextLine())==1) {
			cadastro.setProfessor(false);
		} else {
			cadastro.setProfessor(true);
		}
			
		getTodosOsUsuarios().add(cadastro);
		
		return;
	}
	
	public static void excluirUsuario(Usuario excluir) {
			
		if (excluir.getLivrosEmprestados().size()>0) {
			System.out.println("Nï¿½o ï¿½ possï¿½vel excluir esse usuï¿½rio, pois apresenta livros ainda emprestados.");
			return;
		}
		
		if (excluir.getMulta().intValue()>0) {
			System.out.println("Nï¿½o ï¿½ possï¿½vel excluir esse usuï¿½rio, pois apresenta multas vencidas.");
			return;
		}
		
		setTotalDeUsuarios(Usuario.getTotalDeUsuarios() - 1);
		getTodosOsUsuarios().remove(excluir);
		return;
	}

	public static void listarUsuarios (ArrayList<Usuario> imprime) {
	for(Integer a = 0; a.intValue() < imprime.size();a++) {
		System.out.print("O usuï¿½rio "+a.intValue());
		imprimeUsuario(imprime.get(a));
	}
}
	
	public static void imprimeUsuario (Usuario imprime) {
		System.out.print(" se chama: "+imprime.getNomeDoUsuario()+" e seu login ï¿½: "+imprime.getUser().getUserName());
		if(imprime.isProfessor()) System.out.println(" e ï¿½ professor.");
		else System.out.println(" e ï¿½ aluno.");
	}
	
	
	
	public static Usuario buscaUsuario () {
		
		Usuario usuarioBuscado = null;
		
		System.out.println("Vocï¿½ gostaria de buscar por nome ou pelo login? (1) - Nome, (outro) - Login");
		Integer option = Integer.parseInt(ler.nextLine());
		
		System.out.println("Digite o Nome/Login buscado");
		String busca = ler.nextLine();
		
		switch (option.intValue()) {
		case 1:
			usuarioBuscado = buscaUsuarioNome(busca);
			break;
			default:
				for (Integer a = 0; a.intValue() < getTodosOsUsuarios().size();a++) {
					if(getTodosOsUsuarios().get(a).getUser().getUserName().contains(busca)) {
						usuarioBuscado = getTodosOsUsuarios().get(a);
					}
				}
		}
		
		
		
		return usuarioBuscado;
	}
	
	public static Usuario buscaUsuarioNome(String busca) {
		
		ArrayList<Usuario> buscas = new ArrayList<Usuario>();
		
		
		boolean mark = false;
		for (Integer a = 0; a.intValue() < getTodosOsUsuarios().size();a++) {
			if(getTodosOsUsuarios().get(a).getNomeDoUsuario().contains(busca)) {
				buscas.add(getTodosOsUsuarios().get(a));
				mark = true;
			}
		}
		
		if (mark == false) return null;
		if(buscas.size()==1) return buscas.get(0);
		else {
			listarUsuarios(buscas);
			System.out.println("selecione o indice do usuario buscado");
			Integer selecionado = Integer.parseInt(ler.nextLine());
			if(!(selecionado<buscas.size())) {
				System.out.println("usuario Invalido");
				return null;
			}
			return buscas.get(selecionado);
		}
		
	}
	
	
	public static Usuario selecionaUsuario() {
		System.out.println("Qual o usuario que ira emprestar?");
		Usuario emprestante = Usuario.buscaUsuario();
		if(emprestante == null) return null;
		if(!(podeEfetuarUsuario(emprestante))) {
			System.out.println("Nao foi permitido novo emprestimo");
			return null;
		}
		
		return emprestante;
		
	}
	
	public static boolean podeEfetuarUsuario (Usuario conferir) {
		if(conferir.getMulta().intValue()>0) {
			if(conferir.getLivrosEmprestados().isEmpty()) {
				System.out.println("Ha uma multa, porem nenhum livro emprestado.");
				return true; // tem multa mas nÃ£o hÃ¡ livros emprestados
			}
			System.out.println("Ha multa pendente e um livro emprestado.");
			return false;
			}
		if(conferir.isProfessor()) {
			if(conferir.getLivrosEmprestados().size()<=5) return true;
		}
		else {
			if(conferir.getLivrosEmprestados().size()<=3) return true;
		}
		System.out.println("Maximo de livros emprestados");
		return false;
	}
	
	public void pagaMulta() {
		this.setMulta(0);
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

	public static ArrayList<Usuario> getTodosOsUsuarios() {
		return todosOsUsuarios;
	}

	public static void setTodosOsUsuarios(ArrayList<Usuario> todosOsUsuarios) {
		Usuario.todosOsUsuarios = todosOsUsuarios;
	}
}
