package usuarios;
import java.util.ArrayList;

public class Usuario {
	private String nomeDoUsuario;
	private Login user;
	private ArrayList<LivrosClassificacao> livrosEmprestados = new ArrayList<LivrosClassificacao>();
	private boolean professor;
	private Integer multa;
	

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getLoginDoUsuario() {
		return this.user.getUser();
	}

	public void setLoginDoUsuario(String loginDoUsuario) {
		this.user.setUser(loginDoUsuario);
	}

	public String getSenhaDoUsuario() {
		return this.user.getPassword();
	}

	public void setSenhaDoUsuario(String senhaDoUsuario) {
		this.user.setPassword(senhaDoUsuario);
	}

	public Login getUser() {
		return user;
	}

	public void setUser(Login user) {
		this.user = user;
	}

	public ArrayList<LivrosClassificacao> getLivrosEmprestados() {
		return livrosEmprestados;
	}

	public void setLivrosEmprestados(ArrayList<LivrosClassificacao> livrosEmprestados) {
		this.livrosEmprestados = livrosEmprestados;
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
	
}
