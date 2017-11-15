package empresa;

public class Ocorrencia {
	private Prioridade prior;
	private int id;
	private int idFuncionario;
	private EstadoOcorrencia estado;
	
	public Ocorrencia(Prioridade p, int id, int idFuncionario) {
		this.id = id;
		this.idFuncionario = idFuncionario;
		prior = p;
		estado = EstadoOcorrencia.ABERTA;
	}

	public int getId() {
		return id;
	}

	public void finaliza() {
		estado = EstadoOcorrencia.FINALIZADA;
	}

	public EstadoOcorrencia getEstado() {
		return estado;
	}

	public Prioridade getPrior() {
		return prior;
	}

	public void setPrior(Prioridade prior) {
		this.prior = prior;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public void setEstado(EstadoOcorrencia estado) {
		this.estado = estado;
	}
}
