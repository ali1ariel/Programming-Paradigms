package gerenciamentoBiblioteca;

import usuarios.*;

import java.util.ArrayList;
import java.util.Scanner;

import livros.*;

public class Reserva {
	private Usuario usuarioQueReserva;
	private Exemplar exemplarReservando;
	private static ArrayList<Reserva> todasAsReservas = new ArrayList<Reserva>();
	static Scanner ler = new Scanner(System.in);
	
	public static boolean solicitarReserva() {
		
		Livro buscar = Livro.buscarLivro(Livro.getTodosOsLivros());
		Exemplar reservar = Exemplar.selecionaExemplar(buscar);
		
		if(reservar.getExemplaresDisponiveis()>0) {
			System.out.println("O livro ainda contém unidades disponíveis.");
			return false;
		}
		
		else {
			Reserva reservando = new Reserva();
			reservando.setUsuarioQueReserva(Usuario.selecionaUsuario());
			reservando.setExemplarReservando(reservar);
			reservar.modificaExemplaresDisponiveis('-');
			Reserva.getTodasAsReservas().add(reservando);
			return true;
		}
	}
	
	public static boolean cancelarReserva() {
		imprimirReservas();
		System.out.println("selecione a reserva que quer cancelar:");
		Integer seleciona = Integer.parseInt(ler.nextLine());
		if((seleciona > Reserva.getTodasAsReservas().size())||(seleciona<1)) return false;	
		else {
			Reserva auxiliar = Reserva.getTodasAsReservas().get(seleciona);
			auxiliar.getExemplarReservando().modificaExemplaresDisponiveis('+');
			Reserva.getTodasAsReservas().remove(auxiliar);
			return true;
		}
	}
	
	public static void imprimirReservas() {
		for(Integer a = 0; a.intValue() < Reserva.getTodasAsReservas().size();a++) {
			Reserva auxiliar = Reserva.getTodasAsReservas().get(a);
			System.out.print(a+" - o usuário ");
			Usuario.imprimeUsuario(auxiliar.getUsuarioQueReserva());
			System.out.println("reservou ");
			Livro.imprimeInformaçõesLivro(auxiliar.getExemplarReservando().getLivroDesseExemplar());
			System.out.println("no exemplar ");
			Exemplar.imprimeInformacoesDoExemplar(auxiliar.getExemplarReservando());
			System.out.println("________________");
		}
		
		
	}
	
	public Usuario getUsuarioQueReserva() {
		return usuarioQueReserva;
	}
	public void setUsuarioQueReserva(Usuario usuarioQueReserva) {
		this.usuarioQueReserva = usuarioQueReserva;
	}
	public Exemplar getExemplarReservando() {
		return exemplarReservando;
	}
	public void setExemplarReservando(Exemplar exemplarReservando) {
		this.exemplarReservando = exemplarReservando;
	}

	public static ArrayList<Reserva> getTodasAsReservas() {
		return todasAsReservas;
	}

	public static void setTodasAsReservas(ArrayList<Reserva> todasAsReservas) {
		Reserva.todasAsReservas = todasAsReservas;
	}

	
}
