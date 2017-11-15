package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import empresa.Empresa;
import empresa.Funcionario;

public class TesteFuncionario {

	private int idAlice;
	private String nomeAlice;
	private Empresa exemplo;
	
	@Before
	public void setup() {
		idAlice = 0;
		nomeAlice = "Alice";
		exemplo = new Empresa("Empresa Exemplo");
	}
	
	
	@Test
	public void criaFuncionario() {
		Funcionario alice = new Funcionario(idAlice, nomeAlice);
		
		assertEquals("Alice", alice.getNome());
		assertEquals(0, alice.getId());
	}

}
