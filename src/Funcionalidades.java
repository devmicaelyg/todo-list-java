import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Funcionalidades {
	
	static String pathArquivo = EscolherArquivo.GetArquivo();
	
	public static boolean escreverLista() {
		Lista item = new Lista();
		int tamanhoLista;
		int id = 1;
		String input;
		String entrada = "";

		try {
			FileOutputStream lista = new FileOutputStream(pathArquivo);
			PrintWriter escreveLista = new PrintWriter(lista);

			escreveLista.println("Identificador;Descrição;Categoria;Urgente");

			do {
				input = JOptionPane.showInputDialog("Insira a quantidade de tarefas que vai ter sua lista:");
			} while (!(ValidacaoInput.isNumero(input)));

			tamanhoLista = Integer.parseInt(input);

			for (int i = 0; i < tamanhoLista; ++i) {
				item.Descricao = JOptionPane.showInputDialog("Insira a descrição da tarefa:");
				item.Categoria = JOptionPane.showInputDialog("Insira a categoria da tarefa:");

				entrada = JOptionPane.showInputDialog("Digite S se for urgente e N se não for. \nCaso não seja digitado nada, será considerado não urgente ");

				if (entrada.equals("S") || entrada.equals("s")) {
					item.isUrgente = true;
				} else if (entrada.equals("N") || entrada.equals("n")) {
					item.isUrgente = false;
				} else {
					item.isUrgente = false;
				}

				id = item.Identificador++;

				escreveLista.println(id + ";" + item.Descricao + ";" + item.Categoria + ";" + item.isUrgente);
			}

			escreveLista.close();
			lista.close();

			return true;

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possível escrever a lista por conta do seguinte erro: " + e);
			return false;
		}

	}
	
	public static void lerLista() {	
		BufferedReader br = null;

		try {
			FileInputStream lista = new FileInputStream(pathArquivo);
			InputStreamReader input = new InputStreamReader(lista);
			br = new BufferedReader(input);

			String linha;
			String saida = ""; 

			while ((linha = br.readLine()) != null) {
				saida += linha + "\n";
			}

			JOptionPane.showMessageDialog(null, saida);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não deu para ler o arquivo por conta do seguinte erro: " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Não deu para parar a leitura o arquivo por conta do seguinte erro: " + e);
				}
			}
		}
	}


	public static List<String> lerListaComArrayList() {
		List<String> text = new ArrayList<>();
		BufferedReader br = null;
		
		try {
			FileInputStream lista = new FileInputStream(pathArquivo);
			InputStreamReader input = new InputStreamReader(lista);
			br = new BufferedReader(input);

			String linha;

			while ((linha = br.readLine()) != null) {
				text.add(linha);
			}

		} catch (Exception e) {} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {}
			}
		}
		return text;
	}

	static String saida = " ";

	public static void verificarUrgenciaTarefa() {
		List<String> read = new ArrayList<>();
		String entrada = "";
		int contador=0;
		boolean repetir = true;

		read = lerListaComArrayList();

		do {		
			do {
				entrada = JOptionPane.showInputDialog("Se você deseja saber quais tarefas NÃO são urgentes digite N "
						+ "\nSe você deseja saber quais tarefas SÃO urgentes digite U"
						+ "\nCaso não tenha tarefas do nível de urgência escolhido, você poderá escolher voltar ao menu ou encerrar o programa.");
			} while (!(ValidacaoInput.isSomenteLetra(entrada)));
			
			switch (entrada) {
			case "N":
			case "n":
				read.stream().filter(p -> p.contains("false")).forEach(res -> {
					
					String[] teste = res.split(";");

					for (String a : teste) {
						if (a.equals("false")) {
							a = "Não \n";
						}
						saida += " | " + a;
					}
					JOptionPane.showMessageDialog(null, "Contador | Descrição | Categoria | Urgente" + "\n" + saida);
				});				
				
				saida = ""; 
				repetir = false;
				break;

			case "U":
			case "u":
				read.stream().filter(p -> p.contains("true")).forEach(res -> {
					String[] teste = res.split(";");

					for (String a : teste) {
						if (a.equals("true")) {
							a = "Sim \n";
						}
						saida += " | " + a;
					}

					JOptionPane.showMessageDialog(null, "Contador | Descrição | Categoria | Urgente" + "\n" + saida);
				});
				
				saida = "";
				repetir = false;
				break;

			default:
				JOptionPane.showMessageDialog(null, "Insira uma letra válida");
				repetir = true;
			}

		} while (repetir);
	}

	public static void verificarPorCategoria() {
		BufferedReader br = null;
		String categoria;
		try {
			FileInputStream lista = new FileInputStream(pathArquivo);
			InputStreamReader input = new InputStreamReader(lista);
			br = new BufferedReader(input);

			String linha;

			do {
				categoria = JOptionPane.showInputDialog("Insira a categoria que deseja buscar:"
						+ "\nCaso não tenha a categoria digitada, você poderá escolher voltar ao menu ou encerrar o programa.\"");
			} while (!(ValidacaoInput.isSomenteLetra(categoria)));

			while ((linha = br.readLine()) != null) {
				String[] res = linha.split(";");
		
				for (int i = 0; i < res.length; ++i) {
					if (res[2].equals(categoria)) {
						JOptionPane.showMessageDialog(null, res[0] + " | " + res[1] + " | " + res[2] + " | " + res[3]); 
						i = res.length;
					}
			
				}
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não deu para abrir o arquivo por conta do seguinte erro: " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Não deu para parar a leitura o arquivo por conta do seguinte erro: " + e);
				}
			}
		}
	}
	
	public static void alterarTarefa() {
		BufferedReader br = null;
		String identificador, linha, entrada;
		int contador = 0;
		
		String[] novoVet;

		try {
			FileInputStream lista = new FileInputStream(pathArquivo);
			InputStreamReader input = new InputStreamReader(lista);
			br = new BufferedReader(input);

			br.readLine();
			br.mark(100000);

			do {
				identificador = JOptionPane.showInputDialog("Insira o contador da tarefa que deseja alterar:");
			} while (!(ValidacaoInput.isNumero(identificador)));
			
			while ((linha = br.readLine()) != null) {
				contador++; 
			}
			br.reset();
			
			if(contador > 0) {
				novoVet = new String[contador];
				
				for(int i = 0; i < contador; i++) {
					String registro = br.readLine();
					String[] res = registro.split(";");
					
					if (res[0].equals(identificador)) {
						
						JOptionPane.showMessageDialog(null, res[0] + " | " + res[1] + " | " + res[2] + " | " + res[3]);
						
						res[1] = JOptionPane.showInputDialog("Insira a nova descrição:");
						res[2] = JOptionPane.showInputDialog("Insira a nova categoria:");
						
						entrada = JOptionPane.showInputDialog("Digite S se for urgente e N se não for: ");

						if (entrada.equals("S") || entrada.equals("s")) {
							res[3] = "true";
						} else if (entrada.equals("N") || entrada.equals("n")) {
							res[3] = "false";
						} else {
							res[3] = "false";
						}
						
						JOptionPane.showMessageDialog(null, res[0] + " | " + res[1] + " | " + res[2] + " | " + res[3]);		
					}
					
					novoVet[i] = res[0] + ";" + res[1] + ";" + res[2] + ";" + res[3];
					System.out.print(novoVet[i] + "\n");
				}
				
				persistirInformacoes(novoVet);
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não deu para abrir o arquivo por conta do seguinte erro: " + e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"Não deu para parar a leitura o arquivo por conta do seguinte erro: " + e);
				}
			}
		}
	}

	public static void persistirInformacoes(String[] input) {
		try {
			FileOutputStream lista = new FileOutputStream(pathArquivo);
			PrintWriter escreveLista = new PrintWriter(lista);
		
			escreveLista.println("Identificador;Descrição;Categoria;Urgente");
			
			for(String i: input) {
				escreveLista.print(i + "\n");
			}
			
			escreveLista.close();
			
			lista.close();
		} catch(Exception e) {}
	}
}
