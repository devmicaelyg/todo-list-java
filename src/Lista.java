
public class Lista {
	int Identificador; 
	String Descricao; 
	String Categoria; 
	boolean isUrgente; 
	
	public Lista() {}
	
	public Lista(int Identificador, String Descricao, String Categoria, boolean isUrgente) {
		this.Identificador = Identificador; 
		this.Descricao = Descricao; 
		this.Categoria = Categoria; 
		this.isUrgente = isUrgente;
	}
}
