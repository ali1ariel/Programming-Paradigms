package usuarios;
import java.util.ArrayList;
import livros.Livro;

public class Usuario {
	private String nomeDoUsuario;
	private String loginDoUsuario;
	private String senhaDoUsuario;
	private ArrayList<Livro> livrosEmprestados = new ArrayList<Livro>();
	

	public String getNomeDoUsuario() {
		return nomeDoUsuario;
	}

	public void setNomeDoUsuario(String nomeDoUsuario) {
		this.nomeDoUsuario = nomeDoUsuario;
	}

	public String getLoginDoUsuario() {
		return loginDoUsuario;
	}

	public void setLoginDoUsuario(String loginDoUsuario) {
		this.loginDoUsuario = loginDoUsuario;
	}

	public String getSenhaDoUsuario() {
		return senhaDoUsuario;
	}

	public void setSenhaDoUsuario(String senhaDoUsuario) {
		this.senhaDoUsuario = senhaDoUsuario;
	}
	
}
