package usuarios;
import livros.Livro;

public class LivrosClassificacao {
	private boolean emprestimo;
	private Livro livro;
	
	public boolean isEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(boolean emprestimo) {
		this.emprestimo = emprestimo;
	}
	public Livro getLivro() {
		return livro;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}
