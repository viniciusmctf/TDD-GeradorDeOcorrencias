package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import empresa.Empresa;

public class TesteEmpresa {

	private Empresa exemplo;
	
	@Before
	public void setup() {
		exemplo = new Empresa("Minha Empresa");
	}
	
	@Test
	public void criaEmpresa() {
		Empresa e = new Empresa("nome");
		
		assertEquals("nome", e.getNome());
	}
	
	@Test
	public void adicionaFuncionarioNaoExiste() {
		exemplo.adicionaFuncionario("Alice");
		
		assertTrue(exemplo.existeFuncionarioId(0));
		assertTrue(exemplo.existeFuncionarioNome("Alice"));
		assertEquals("Alice", exemplo.nomeFuncionario(0));
	}
	
	@Test
	public void verificaFuncionarioNaoExiste() {
		boolean existe = exemplo.existeFuncionarioNome("Alice");
		
		assertFalse(existe);
	}
	
	@Test
	public void removeFuncionarioNaoExiste() throws Exception{
		boolean remocao = exemplo.removeFuncionario(3);
		
		assertFalse(remocao);
	}
	
	@Test
	public void removeFuncionarioExisteNome() {
		exemplo.adicionaFuncionario("Alice");
		boolean remocao = exemplo.removeFuncionario("Alice");
		
		assertTrue(remocao);
	}
	
	@Test 
	public void removeFuncionarioExisteId() {
		exemplo.adicionaFuncionario("Alice");
		boolean remocao = exemplo.removeFuncionario(0);
		
		assertTrue(remocao);
	}

	
	@Test
	public void adicionaProjetoResponsavelExiste() throws Exception {
		exemplo.criaProjeto("Projeto Inovador");
		
		assertTrue(exemplo.existeProjeto("Projeto Inovador"));
		assertEquals("Projeto Inovador", exemplo.getProjeto("Projeto Inovador").getNome());
	}
	
	@Test
	public void removeProjetoNaoExiste() {
		boolean remocao = exemplo.removeProjeto("Projeto Antigo");
		
		assertFalse(remocao);
	}
	
	@Test
	public void removeProjetoExiste() throws Exception {
		exemplo.adicionaFuncionario("Alice");
		exemplo.criaProjeto("Projeto Inovador");
		boolean remocao = exemplo.removeProjeto("Projeto Inovador");
		
		assertTrue(remocao);
		assertFalse(exemplo.existeProjeto("ProjetoInovador"));
	}
	
}
