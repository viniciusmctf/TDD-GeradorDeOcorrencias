package empresa;

import java.util.HashMap;
import java.util.Map;

public class Projeto {

	private String nomeProjeto;
	private Map<Integer, Ocorrencia> ocorrencias;

	public Projeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
		ocorrencias = new HashMap<Integer, Ocorrencia>();
	}

	public String getNome() {
		// TODO Auto-generated method stub
		return nomeProjeto;
	}

	public int getNroOcorrencias(int idFuncionario) {
		int count = 0;
		for (Map.Entry<Integer, Ocorrencia> entry : ocorrencias.entrySet()) {
			if (entry.getValue().getIdFuncionario() == idFuncionario) {
				++count;
			}
		}
		return count;
	}

	public int criaOcorrencia(Prioridade prior, Tipo t, int id, int idFuncionario) throws Exception {
		if (ocorrencias.containsKey(id)) {
			throw new Exception("Ocorrencia já existe");
		}
		Ocorrencia oc;
		switch(t) {
			case BUG:
				oc = new Bug(prior, id, idFuncionario);
				break;
			case TAREFA:
				oc = new Tarefa(prior, id, idFuncionario);
				break;
			case MELHORIA:
				oc = new Melhoria(prior, id, idFuncionario);
				break;
			default:
				throw new Exception("Ocorrência de tipo desconhecido");
		}
		ocorrencias.put(id, oc);
		return id;
	}

	public int finalizaOcorrencia(int idOcorrencia) {
		int funcionario = -1;
		for (Map.Entry<Integer, Ocorrencia> entry : ocorrencias.entrySet()) {
			if (entry.getValue().getId() == idOcorrencia) {
				funcionario = entry.getValue().getIdFuncionario();
				entry.getValue().finaliza();
				break;
			}
		}		
		return funcionario;
	}

	public int getNroOcorrencias(int idAlice, EstadoOcorrencia estado) {
		int count = 0;
		for (Map.Entry<Integer, Ocorrencia> entry : ocorrencias.entrySet()) {
			if (entry.getValue().getEstado() == estado && entry.getValue().getIdFuncionario() == idAlice) {
				++count;
			}
		}
		return count;
	}

	public void funcionarioOcorrencia(int idAlice, int idOcorrencia) {
		ocorrencias.get(idOcorrencia).setIdFuncionario(idAlice);
	}

	public Ocorrencia getOcorrencia(int idOcorrencia) {
		return ocorrencias.get(idOcorrencia);
	}
}
