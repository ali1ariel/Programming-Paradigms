package usuarios;

import java.util.Scanner;

public class Login {
	private String userName;
	private String password;
	static Scanner ler = new Scanner(System.in);
	
	
	public static Login defineLogin () {
		Login cadastro = new Login();
		boolean tentativa = false;
		do {
			System.out.println("Digite o nome de usuario que deseja:");
			String userName = ler.nextLine();
			if(Usuario.getTodosOsUsuarios().size()<1) {
				cadastro.setUserName(userName);
				break;
			}
			for(Integer a = 0; a.intValue() < Usuario.getTodosOsUsuarios().size();a++) {
				if(Usuario.getTodosOsUsuarios().get(a.intValue()).getUser().getUserName().equals(userName)) {
					System.out.println("Nao e possível adicionar um usuário com esse Login, tente novamente");
					break;
				}
				if(a.intValue() == (Usuario.getTodosOsUsuarios().size()-1)) {
					tentativa = true;
					cadastro.setUserName(userName);
					break;
				}
			}
		} while (tentativa==false);
		
		
		
		tentativa = false;
		do {
		System.out.println("Digite a senha que deseja:");
		String senha1 = ler.nextLine();
		System.out.println("Confirme a senha digitada");
		String senha2 = ler.nextLine();
		if(senha1.equals(senha2)) {
			tentativa = true;
			cadastro.setPassword(senha1);
		}
		else System.out.println("As senhas nao conferem, tente novamente.");
		
		} while (tentativa==false);
		
		
		
		return cadastro;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
