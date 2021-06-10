import javax.swing.JOptionPane;

public class Principal {
	
	public static void main(String args[]) {
		String opcao; 
		boolean repetir, response; 
		
		JOptionPane.showMessageDialog(null, "Bem-vindo(a) a sua lista virtual");
		
		do {
			opcao = JOptionPane.showInputDialog(
					"Insira o número referente ao que deseja fazer? "
					+ "\nA- Criar uma lista de tarefas \nB- Ler sua lista de tarefas "
					+ "\nC- Verificar urgencia de tarefas lista \nD- Verificar tarefas por categoria"
					+ "\nE- Alterar tarefa da lista \nF- Encerrar sistema");

			if(opcao == null) {
				repetir = mensagemFinal();
			}
			
			switch (opcao){
			case "A":
			case "a":
				response = Funcionalidades.escreverLista();
				if(response) JOptionPane.showMessageDialog(null, "Lista salva com sucesso!");
				repetir = mensagemFinal();
				break; 
			case "B":
			case "b":
				Funcionalidades.lerLista();
				repetir =  mensagemFinal();
				break; 
			case "C":
			case "c":
				Funcionalidades.verificarUrgenciaTarefa();
				repetir =  mensagemFinal();
				break; 
			
			case "D":
			case "d":
				Funcionalidades.verificarPorCategoria();
				repetir =  mensagemFinal();
				break;
				
			case "E":
			case "e":
				Funcionalidades.alterarTarefa();
				repetir =  mensagemFinal();
				break;
				
			case "F":
			case "f":
				repetir =  mensagemFinal();
				break;
			
			default: 
				JOptionPane.showMessageDialog(null, "Escolha uma opção válida!");
				repetir = true;
			}
		} while(repetir);
		
	}
	
	public static Boolean mensagemFinal() {
		String op = "";
		Boolean repetir;

		op = JOptionPane.showInputDialog("O que deseja fazer?\r\n"
				+ "Digite [M] para voltar ao menu ou qualquer outra tecla para encerrar...");
		switch (op) {
		case "M":
		case "m":
			repetir = true;
			break;
		default:
			repetir = false;
			JOptionPane.showMessageDialog(null, "Obrigado por usar nosso sistema, até a próxima :) ");
		}
		return repetir;
	}
	
}
