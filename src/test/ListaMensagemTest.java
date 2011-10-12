package test;

import redesocial.UsuarioExistenteException;
import redesocial.ListaUsuario;
import redesocial.UsuarioInvalidoException;
import redesocial.MensagemInvalidaException;
import redesocial.ListaMensagem;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListaMensagemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListaMensagemCriacao() {
		ListaMensagem lista = new ListaMensagem();
		assertNotNull("Erro ao criar lista de mensagens", lista);
	}

	@Test
	public void testListaMensagemAdicionar() {
		ListaMensagem lista = new ListaMensagem();
				
		try{			
			lista.inserirMensagem("thiago", "mensagem de teste");
			assertTrue(true);				
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}	
	
	@Test
	public void testListaMensagemAdicionarDuas() {
		ListaMensagem lista = new ListaMensagem();
		
		try{			
			lista.inserirMensagem("thiago", "mensagem de teste");
			lista.inserirMensagem("thiago", "mensagem de teste2");
			assertEquals("Erro: esperado 2 mensagens.", 2, lista.tamanhoLista());		
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}	
	
	@Test
	public void testListaMensagemAdicionarLimiteSuperior() {
		ListaMensagem lista = new ListaMensagem();
		
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("thiago");
		}catch(UsuarioExistenteException e){				
		}catch(UsuarioInvalidoException e){			
		}
		
		try{			
			lista.inserirMensagem("thiago", "Vejam este nome: Pedro de Alcântara Francisco António João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim");			
			fail("Erro ao adicionar mensagem invalida: nao deve adicionar msg com mais de 140 caracteres!");
		}catch(MensagemInvalidaException e){
			assertTrue(true);
		}
	}	
	
	@Test
	public void testListaMensagemConteudos() {
		ListaMensagem lista = new ListaMensagem();
		
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("thiago");
		}catch(UsuarioExistenteException e){				
		}catch(UsuarioInvalidoException e){			
		}
		
		try{			
			lista.inserirMensagem("thiago", "teste");
			lista.inserirMensagem("thiago", "teste1");
			lista.inserirMensagem("thiago", "teste2");
			lista.inserirMensagem("thiago", "teste3");
			lista.inserirMensagem("thiago", "teste4");
			
			lista.first();			
			assertEquals("Erro: mensagem diferente.", "teste4", lista.getMensagem().getTexto());
			lista.next();
			assertEquals("Erro: mensagem diferente.", "teste3", lista.getMensagem().getTexto());
			lista.next();
			assertEquals("Erro: mensagem diferente.", "teste2", lista.getMensagem().getTexto());
			lista.next();
			assertEquals("Erro: mensagem diferente.", "teste1", lista.getMensagem().getTexto());
			lista.next();
			assertEquals("Erro: mensagem diferente.", "teste", lista.getMensagem().getTexto());
			lista.next();			
			assertTrue("Erro ao checar fim da lista", lista.eof());
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}		
}
