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
	
	public static void menuReservas() {
		do {
			System.out.println("O que deseja fazer? (1) - solicitar uma reserva. (2) - cancelar uma reserva. (3) - Listar todas as reservas.");
			Integer option = Integer.parseInt(ler.nextLine());
			switch(option) {
			case 1:{
				if(solicitarReservaComBusca()) System.out.println("Reservado com sucesso");	
				else System.out.println("nao foi possivel reservar");
				break;
			}
			case 2:{
				if(cancelarReserva()) System.out.println("Cancelado com sucesso!");
				else System.out.println("nao foi possivel cancelar");
				break;
			}
			case 3:{
				imprimirReservas(getTodasAsReservas());
				break;
			}
			default:{
				System.out.println("Opcao invalida");
				break;
			}
			}
			System.out.println("deseja algo a mais com as reservas?\\n (1) - SIM. (outro) - Nao.");
		}while(Integer.parseInt(ler.nextLine())==1);
		return;
	}
	
	public static boolean solicitarReservaComBusca() {
		
		Livro buscar = Livro.buscarLivro(Livro.getTodosOsLivros());
		Exemplar reservar = Exemplar.selecionaExemplar(buscar);
		return solicitarReserva(reservar);
		
	}
	
	public static boolean solicitarReserva(Exemplar reservar) {
				
		if(reservar.getExemplaresDisponiveis()>0) {
			System.out.println("O livro ainda contém unidades disponiveis.");
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
		imprimirReservas(getTodasAsReservas());
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
	
	public static void imprimirReservas(ArrayList<Reserva> imprime) {
		for( int a = 0; a < imprime.size(); a++) {
			Reserva auxiliar = imprime.get(a);
			System.out.print(a+" - o usuario ");
			Usuario.imprimeUsuario(auxiliar.getUsuarioQueReserva());
			System.out.println("reservou ");
			Livro.imprimeInformacoesLivro(auxiliar.getExemplarReservando().getLivroDesseExemplar());
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
