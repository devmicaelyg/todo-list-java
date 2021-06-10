import javax.swing.JOptionPane;

public class ValidacaoInput {

	static boolean isInputValido = true;
	
	public static boolean isNumero(String input) {
		if (!input.matches("[0-9]*") || input.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Valor inválido, somente números devem ser inseridos.");
			return isInputValido = false;
		} else {
			return isInputValido = true;
		}
	}

	
	public static boolean isSomenteLetra(String input) {
		if (!input.matches("[A-z ]*") || input.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Valor inválido, somente letras devem ser inseridas.");
			return isInputValido = false;
		} else {
			return isInputValido = true;
		}
	}

}
