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
	}
	
	@Test
	public void testTratadorMensagensExecute(){
		TratadorMensagens tratMensagens = new TratadorMensagens();		
		assertEquals("Erro ao executar tratador de mensagens", "comando-invalido", tratMensagens.Execute("teste").get(0));				
	}
	
	@Test
	public void testCriarUsuario(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao criar usuario", "ok", tratMensagens.Execute("criar-usuario thiago").get(0));		
	}
	
	@Test
	public void testCriarUsuarioExistente(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao criar usuario", "ok", tratMensagens.Execute("criar-usuario usuarioteste").get(0));
		assertEquals("Erro ao criar usuario", "usuario-ja-existe", tratMensagens.Execute("criar-usuario usuarioteste").get(0));
	}
	
	@Test
	public void testCriarUsuarioInvalido(){
		TratadorMensagens tratMensagens = new TratadorMensagens();		
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario ab").get(0));
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario paulo23").get(0));
		assertEquals("Erro ao criar usuario", "nome-invalido", tratMensagens.Execute("criar-usuario mariaDoCarmoDaSilvaSantos").get(0));
	}	
	
	@Test
	public void testPostarMensagem(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao postar mensagem", "ok", tratMensagens.Execute("postar-mensagem thiago Eu moro em Maringá").get(0));
	}
	
	@Test
	public void testPostarMensagemVazia(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao postar mensagem", "mensagem-invalida", tratMensagens.Execute("postar-mensagem thiago ").get(0));
	}
	
	@Test
	public void testPostarMensagemUsuarioInexistente(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao postar mensagem", "usuario-nao-encontrado", tratMensagens.Execute("postar-mensagem joao Eu moro em Maringá").get(0));
	}
	
	@Test
	public void testPostarMensagemMaiorQueLimite(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao postar mensagem", "mensagem-invalida", tratMensagens.Execute("postar-mensagem thiago Vejam este nome: Pedro de Alcântara Francisco António João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim").get(0));
	}
	
	@Test
	public void testListarMensagensUsuario(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotSame("Erro ao listar mensagens", "", tratMensagens.Execute("listar-mensagens-usuario thiago").get(0));
		assertNotSame("Erro ao listar mensagens", "comando-invalido", tratMensagens.Execute("listar-mensagens-usuario thiago").get(0));		
	}	
}
