package test;

import redesocial.TratadorMensagens;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TratadorMensagensTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTratadorMensagensExists() {
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotNull("Erro ao criar objeto TratadorMensagens", tratMensagens);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testTratadorMensagensExecute(){
		TratadorMensagens tratMensagens = new TratadorMensagens();		
		assertEquals("Erro ao executar tratador de mensagens", "mensagem-invalida", tratMensagens.Execute("teste"));				
	}
	
	@Test
	public void testCriarUsuario(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao criar usuario", "OK", tratMensagens.Execute("criar-usuario thiago"));		
	}
	
	@Test
	public void testCriarUsuarioExistente(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao criar usuario", "OK", tratMensagens.Execute("criar-usuario usuarioteste"));
		assertEquals("Erro ao criar usuario", "usuario-ja-existe", tratMensagens.Execute("criar-usuario usuarioteste"));
	}
	
	@Test
	public void testCriarUsuarioInvalido(){
		TratadorMensagens tratMensagens = new TratadorMensagens();		
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario ab"));
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario paulo23"));
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario mariaDoCarmoDaSilvaSantos"));
	}	
}
