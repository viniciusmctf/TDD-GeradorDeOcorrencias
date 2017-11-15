package empresa;

import java.util.HashMap;
import java.util.Map;


public class Empresa {

	private String nome;
	private Map<Integer, Funcionario> funcionarios;
	private Map<String, Projeto> projetos;
	private int ultimoId;
	private int ultimaOc;

	public Empresa(String nome) {
		this.nome = nome;
		funcionarios = new HashMap<Integer, Funcionario>();
		projetos = new HashMap<String, Projeto>();
		ultimoId = 0;
		ultimaOc = 0;
	}

	public String getNome() {
		return nome;
	}

	public int adicionaFuncionario(String nomeFuncionario) {
		funcionarios.put(ultimoId, new Funcionario(ultimoId, nomeFuncionario));
		ultimoId++;
		return (ultimoId-1);
	}

	public boolean existeFuncionarioId(int idFuncionario) {
		return funcionarios.containsKey(idFuncionario);
	}

	public boolean existeFuncionarioNome(String nomeFuncionario) {
		for (Map.Entry<Integer, Funcionario> entry : funcionarios.entrySet()) {
			if (entry.getValue().getNome() == nomeFuncionario) {
				return true;
			}
		}
		return false;
	}

	public String nomeFuncionario(int idFuncionario) {
		return funcionarios.get(idFuncionario).getNome();
	}

	public boolean removeFuncionario(int idRemocao) {
		if (existeFuncionarioId(idRemocao)) {
			funcionarios.remove(idRemocao);
			return true;
		} 
		return false;
	}

	public boolean removeFuncionario(String nomeRemocao) {
		if (existeFuncionarioNome(nomeRemocao)) {
			for (Map.Entry<Integer, Funcionario> entry : funcionarios.entrySet()) {
				if (entry.getValue().getNome() == nomeRemocao) {
					funcionarios.remove(entry.getKey());
					return true;
				}
			}
		}
		return false;
	}

	public void criaProjeto(String nomeProjeto) throws Exception {
		if (projetos.containsKey(nomeProjeto)) {
			throw new Exception("Projeto já existe");		
		} else {
			Projeto proj = new Projeto(nomeProjeto);
			projetos.put(nomeProjeto, proj);
		}
	}

	public boolean existeProjeto(String nomeRemocao) {
		return projetos.containsKey(nomeRemocao);
	}

	public Projeto getProjeto(String nomeProjeto) {
		return projetos.get(nomeProjeto);
	}

	public boolean removeProjeto(String nomeRemocao) {
		if (projetos.containsKey(nomeRemocao)) {
			projetos.remove(nomeRemocao);
			return true;
		}
		return false;
	}

	public int criaOcorrencia(int idFuncionario, Prioridade prior, String projeto, Tipo t) throws Exception {
		if (!funcionarios.containsKey(idFuncionario)) {
			throw new Exception("Funcionário não existe");
		}
		if (projetos.get(projeto).getNroOcorrencias(idFuncionario, EstadoOcorrencia.ABERTA) >= 10) {			
			throw new Exception("Funcionário já possui 10 ocorrências");
		}
		int id = projetos.get(projeto).criaOcorrencia(prior, t, ultimaOc++, idFuncionario);
		return id;
	}

	public int numeroDeOcorrencias(int idFuncionario, String projeto) throws Exception {
		if (!funcionarios.containsKey(idFuncionario)) {
			throw new Exception("Funcionario não existe");
		}				
		return projetos.get(projeto).getNroOcorrencias(idFuncionario);
	}

	public boolean finalizaOcorrencia(int idOcorrencia) {
		boolean finaliza = false;
		for (Map.Entry<String, Projeto> entry : projetos.entrySet()) {
			int funcionario = entry.getValue().finalizaOcorrencia(idOcorrencia);
			if (funcionario >= 0) {
				finaliza = true;
				break;
			}
		}
		return finaliza;
	}

	public int numeroDeOcorrenciasAbertas(int idAlice, String nomeProjeto) throws Exception {
		if (!funcionarios.containsKey(idAlice)) {
			throw new Exception("Funcionario não existe");
		}
		return projetos.get(nomeProjeto).getNroOcorrencias(idAlice, EstadoOcorrencia.ABERTA);
	}

	public int numeroDeOcorrenciasFechadas(int idAlice, String nomeProjeto) throws Exception {
		if (!funcionarios.containsKey(idAlice)) {
			throw new Exception("Funcionario não existe");
		}
		return projetos.get(nomeProjeto).getNroOcorrencias(idAlice, EstadoOcorrencia.FINALIZADA);
	}

	public boolean passarOcorrencia(int idAlice, int idOcorrencia, String nomeProjeto) throws Exception {
		boolean ret = false;
		if (!funcionarios.containsKey(idAlice)) {
			throw new Exception("Funcionario não existe");
		} else if (!projetos.containsKey(nomeProjeto)) {
			throw new Exception("Projeto não existe");
		} else if (projetos.get(nomeProjeto).getNroOcorrencias(idAlice, EstadoOcorrencia.ABERTA) >= 10) {
			ret = false;
		} else {
			projetos.get(nomeProjeto).funcionarioOcorrencia(idAlice, idOcorrencia);
			ret = true;
		}
		return ret;
	}

	public int numeroDeOcorrencias(int idBob) throws Exception {
		if (!funcionarios.containsKey(idBob)) {
			throw new Exception("Funcionario não existe");
		}
		int count = 0;
		for (Map.Entry<String, Projeto> entry : projetos.entrySet()) {
			count += numeroDeOcorrencias(idBob, entry.getKey());
		}
		return count;
	}

	public void trocaPrioridadeOcorrencia(int idOcorrencia, Prioridade prior, String nomeProjeto) throws Exception {
		if (!projetos.containsKey(nomeProjeto)) {
			throw new Exception("Projeto não existe");
		}
		projetos.get(nomeProjeto).getOcorrencia(idOcorrencia).setPrior(prior);
	}
}
