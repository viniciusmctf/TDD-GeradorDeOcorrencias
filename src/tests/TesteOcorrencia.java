package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import empresa.Empresa;
import empresa.Ocorrencia;
import empresa.Prioridade;
import empresa.Tipo;

public class TesteOcorrencia {

	private Empresa exemplo;
	private int idAlice;
	private String nomeProjeto;
	private int idOcorrencia;
	
	@Before
	public void setup() throws Exception {
		exemplo = new Empresa("Empresa exemplo");
		nomeProjeto = "Projeto 0";
		exemplo.criaProjeto(nomeProjeto);
		idAlice = exemplo.adicionaFuncionario("Alice");
		idOcorrencia = exemplo.criaOcorrencia(idAlice, Prioridade.ALTA, nomeProjeto, Tipo.MELHORIA);
	}
	
	@Test
	public void trocaResponsavelOk() throws Exception {
		int idBob;
		String nomeBob = "Bob";
		idBob = exemplo.adicionaFuncionario(nomeBob);
		
		exemplo.passarOcorrencia(idBob, idOcorrencia, nomeProjeto);
		int ocAlice = exemplo.numeroDeOcorrencias(idAlice, nomeProjeto);
		int ocBob = exemplo.numeroDeOcorrencias(idBob, nomeProjeto);
		
		assertEquals(0, ocAlice);
		assertEquals(1, ocBob);
	}
	
	@Test(expected = Exception.class)
	public void trocaResponsavelNaoExiste() throws Exception {
		exemplo.passarOcorrencia(128, idOcorrencia, nomeProjeto);
	}
	
	@Test
	public void trocaResponsavelSobrecarregado() throws Exception {
		int idBob;
		String nomeBob = "Bob";
		idBob = exemplo.adicionaFuncionario(nomeBob);
				
		for (int i = 0; i < 10; i++) {
			exemplo.criaOcorrencia(idBob, Prioridade.BAIXA, nomeProjeto, Tipo.BUG);
		}
		
		boolean passar = exemplo.passarOcorrencia(idBob, idOcorrencia, nomeProjeto);
		
		assertFalse(passar);
		assertEquals(10, exemplo.numeroDeOcorrencias(idBob));
		assertEquals(1, exemplo.numeroDeOcorrencias(idAlice));		
	}
	
	@Test
	public void trocaPrioridade() throws Exception {
		exemplo.trocaPrioridadeOcorrencia(idOcorrencia, Prioridade.BAIXA, nomeProjeto);
		
		assertEquals(Prioridade.BAIXA, exemplo.getProjeto(nomeProjeto).getOcorrencia(idOcorrencia).getPrior());
	}

}
