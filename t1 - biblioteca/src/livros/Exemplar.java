package livros;
import java.util.ArrayList;

import usuarios.Usuario;

public class Exemplar extends Livro {
	private String edicaoDoLivro;
	private String anoDoLivro;
	private Integer numeroDeExemplares;
	private Integer codigoISBN;
	private Integer exemplaresDisponiveis;
	private ArrayList<Usuario> usuarioQueEmprestou;
	
	public String getEdicaoDoLivro() {
		return edicaoDoLivro;
	}
	public void setEdicaoDoLivro(String edicao) {
		String numeroDaEdicao = (edicao+"ª");
		System.out.println(numeroDaEdicao);
		this.edicaoDoLivro = numeroDaEdicao;
	}
	
	public String getAnoDoLivro() {
		return anoDoLivro;
	}
	public void setAnoDoLivro(String ano) {
		this.anoDoLivro = ano;
	}
	
	public Exemplar getProximo() {
		return proximo;
	}
	public void setProximo(Exemplar proximo) {
		this.proximo = proximo;
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
	public ArrayList<Usuario> getUsuarioQueEmprestou() {
		return usuarioQueEmprestou;
	}
	public void setUsuarioQueEmprestou(ArrayList<Usuario> usuarioQueEmprestou) {
		this.usuarioQueEmprestou = usuarioQueEmprestou;
	}
}
