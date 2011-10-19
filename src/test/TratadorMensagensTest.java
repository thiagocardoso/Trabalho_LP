package test;

import redesocial.ListaUsuario;
import redesocial.TratadorMensagens;
import redesocial.UsuarioExistenteException;
import redesocial.UsuarioInvalidoException;
import redesocial.UsuarioJaSeguidoException;
import redesocial.UsuarioSeguidoreSeguidoIguaisException;
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
	
	@Test
	public void testSeguir(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		try {
			ListaUsuario.getListaUsuario().inserirUsuario("joao");		
			ListaUsuario.getListaUsuario().inserirUsuario("maria");
		} catch (UsuarioExistenteException e) {			
		} catch (UsuarioInvalidoException e) {			
		}		
		assertEquals("Erro ao executar comando seguir", "ok", tratMensagens.Execute("seguir joao maria").get(0));
	}
	
	@Test
	public void testListarSeguidores(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		
		try {
			ListaUsuario.getListaUsuario().getUsuario("joao").seguir("maria");
		} catch (UsuarioJaSeguidoException e) {			
		} catch (UsuarioSeguidoreSeguidoIguaisException e) {			
		} catch (UsuarioInvalidoException e) {		
		}
		
		assertEquals("Erro ao executar comando listar seguidores", "joao", tratMensagens.Execute("listar-seguidores maria").get(0));
	}
	
	@Test
	public void testListarSeguidos(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao executar comando listar seguidos", "maria", tratMensagens.Execute("listar-seguidos joao").get(0));
	}	
		
	@Test
	public void testListarMensagensSeguidos(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotNull("Erro ao listar mensagem dos seguidos", tratMensagens.Execute("listar-mensagens-seguidos joao"));
	}		
	
	@Test
	public void testDeixardeSeguir(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertEquals("Erro ao executar comando deixar de seguir", "ok", tratMensagens.Execute("deixar-de-seguir joao maria").get(0));
	}			
	
	@Test
	public void testListarEstatisticasUsuario(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotSame("Erro ao executar comando listar estatisticas do usuario", "", tratMensagens.Execute("listar-estatisticas-usuario joao maria").get(0));
		assertNotSame("Erro ao executar comando listar estatisticas do usuario", "comando-invalido", tratMensagens.Execute("listar-estatisticas-usuario joao maria").get(0));
	}				

	@Test
	public void testListarTendencia(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotNull("Erro ao executar comando listar tendencia", tratMensagens.Execute("listar-tendencia"));		
	}	
	
	@Test
	public void testListarMensagensComPalavraMarcada(){
		TratadorMensagens tratMensagens = new TratadorMensagens();
		assertNotNull("Erro ao executar comando listar mensagens com palavra marcada", tratMensagens.Execute("listar-mensagens-com-palavra-marcada #tv"));		
	}		
}
