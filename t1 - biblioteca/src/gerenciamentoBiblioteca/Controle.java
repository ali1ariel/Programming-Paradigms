package gerenciamentoBiblioteca;

public class Controle {
	private static Integer dia=0;
	
	public static void novoDia() {
		Controle.setDia(Controle.getDia().intValue()+1);
		for (Integer a = 0; a.intValue() < Emprestimo.getTodosOsEmprestimos().size();a++) {
			Emprestimo modifica = Emprestimo.getTodosOsEmprestimos().get(a.intValue());
			if(modifica.getDiasRestantes().intValue() > 0) {
				modifica.setDiasRestantes(modifica.getDiasRestantes()-1);
			}
			else {
				modifica.getEmprestante().setMulta(modifica.getEmprestante().getMulta()+1);
			}
		}
		
	}
	
	
	
	
	
	
	public static Integer getDia() {
		return dia;
	}

	public static void setDia(Integer dia) {
		Controle.dia = dia;
	}
}
