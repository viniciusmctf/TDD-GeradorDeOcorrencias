package empresa;



public class Funcionario {

	private int id;
	private String nomeFuncionario;

	public Funcionario(int ultimoId, String nomeFuncionario) {
		this.id = ultimoId;
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getNome() {
		return nomeFuncionario;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}
