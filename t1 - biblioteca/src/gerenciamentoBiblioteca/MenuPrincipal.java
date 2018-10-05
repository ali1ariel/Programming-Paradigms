package gerenciamentoBiblioteca;

import java.util.Scanner;

import livros.Livro;
import usuarios.Usuario;

public class MenuPrincipal {
	static Scanner ler = new Scanner(System.in);
	
	public static void main(String[] args) {
		do {
			System.out.println("Com o que gostaria de lidar? (1) - Livros. (2) - Usuarios. (3) - Emprestimos. (4) - Reservas. (5) - adicionar um dia");
			Integer option = Integer.parseInt(ler.nextLine());
			switch(option) {
			case 1:{
				Livro.menuLivros(Livro.getTodosOsLivros());
				break;
			}
			case 2:{
				Usuario.menuUsuario();
				break;
			}
			case 3:{
				Emprestimo.menuEmprestimo();
				break;
			}
			case 4:{
				Reserva.menuReservas();
				break;
			}
			case 5:{
				Controle.novoDia();
				break;
			}
			default:{
				System.out.println("Opção invalida");
				break;
			}
			}
			System.out.println("deseja algo a mais nesse menu principal?\n (1) - SIM. (outro) - ENCERRAR.");
		}while(Integer.parseInt(ler.nextLine())==1);
		return;
	}
}
