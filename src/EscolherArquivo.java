import javax.swing.JFileChooser;

public class EscolherArquivo {

	public static String GetArquivo(){
		JFileChooser arquivoPos = new JFileChooser();
		int resultado = arquivoPos.showOpenDialog(null);
		
		if (resultado == JFileChooser.CANCEL_OPTION){
			return null;
		} else{
			return arquivoPos.getSelectedFile().getAbsolutePath();
		}	
	}
}
