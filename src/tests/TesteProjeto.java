package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import empresa.Empresa;
import empresa.Prioridade;
import empresa.Tipo;

public class TesteProjeto {

	private Empresa exemplo;
	private int idAlice;
	private String nomeProjeto;
	
	@Before
	public void setUp() throws Exception {
		exemplo = new Empresa("Modelo");
		exemplo.adicionaFuncionario("Alice");
		idAlice = 0;
		nomeProjeto = "Novo Projeto";
		exemplo.criaProjeto(nomeProjeto);
	}

	@Test
	public void novaOcorrenciaFuncionarioOk() throws Exception {
		int oc = exemplo.criaOcorrencia(idAlice, Prioridade.ALTA, nomeProjeto, Tipo.BUG);
		
		assertEquals(0, oc);
		assertEquals(1, exemplo.numeroDeOcorrencias(idAlice, nomeProjeto));
	}
	
	@Test(expected = Exception.class)
	public void novaOcorrenciaFuncionarioNaoExiste() throws Exception {
		exemplo.criaOcorrencia(++idAlice, Prioridade.BAIXA, nomeProjeto, Tipo.MELHORIA);
		
	}
	
	@Test(expected = Exception.class)
	public void novaOcorrenciaFuncionarioSobrecarregado() throws Exception {
		for (int i = 0; i < 10; ++i) {
			exemplo.criaOcorrencia(idAlice, Prioridade.MEDIA, nomeProjeto, Tipo.TAREFA);
		}
		exemplo.criaOcorrencia(idAlice, Prioridade.BAIXA, nomeProjeto, Tipo.MELHORIA);
		
	}
	
	@Test
	public void finalizaOcorrencia() throws Exception {
		int idOcorrencia = exemplo.criaOcorrencia(idAlice, Prioridade.ALTA, nomeProjeto, Tipo.BUG);
		
		boolean finaliza = exemplo.finalizaOcorrencia(idOcorrencia);
		
		assertTrue(finaliza);		
		assertEquals(0, exemplo.numeroDeOcorrenciasAbertas(idAlice, nomeProjeto));
	}
	
	@Test
	public void finalizaOcorrenciaSegundoFuncionario() throws Exception {
		int idBob = 1;
		exemplo.adicionaFuncionario("Bob");
		exemplo.criaOcorrencia(idAlice, Prioridade.ALTA, nomeProjeto, Tipo.BUG);
		exemplo.criaOcorrencia(idBob, Prioridade.BAIXA, nomeProjeto, Tipo.TAREFA);
		
		boolean finaliza = exemplo.finalizaOcorrencia(0);
		
		assertTrue(finaliza);		
		assertEquals(1, exemplo.numeroDeOcorrenciasFechadas(idAlice, nomeProjeto));
		assertEquals(1, exemplo.numeroDeOcorrenciasAbertas(idBob, nomeProjeto));
	}
	
	@Test
	public void finalizaOcorrenciaNaoExiste() throws Exception {
		exemplo.criaOcorrencia(idAlice, Prioridade.MEDIA, nomeProjeto, Tipo.MELHORIA);
		
		boolean finaliza = exemplo.finalizaOcorrencia(10);
		
		assertFalse(finaliza);
		assertEquals(1, exemplo.numeroDeOcorrencias(idAlice, nomeProjeto));
	}
	
	@Test
	public void criaOcorrenciaAposFinalizarOcorrencias() throws Exception {
		for (int i = 0; i < 10; ++i) {
			exemplo.criaOcorrencia(idAlice, Prioridade.MEDIA, nomeProjeto, Tipo.TAREFA);
		}
		boolean final0 = exemplo.finalizaOcorrencia(0);
		boolean final1 = exemplo.finalizaOcorrencia(1);
		exemplo.criaOcorrencia(idAlice, Prioridade.MEDIA, nomeProjeto, Tipo.TAREFA);
		
		assertTrue(final0);
		assertTrue(final1);
		assertEquals(9, exemplo.numeroDeOcorrenciasAbertas(idAlice, nomeProjeto));
		assertEquals(2, exemplo.numeroDeOcorrenciasFechadas(idAlice, nomeProjeto));

	}

}
