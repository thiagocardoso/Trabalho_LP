package test;

import redesocial.UsuarioMensagem;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redesocial.ListaUsuario;
import redesocial.MensagemInvalidaException;
import redesocial.UsuarioExistenteException;
import redesocial.UsuarioInvalidoException;

public class UsuarioMensagemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdicionarMensagem() {
		UsuarioMensagem lista = UsuarioMensagem.getUsuarioMensagem();
		
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("thiago");
		}catch(UsuarioExistenteException e){				
		}catch(UsuarioInvalidoException e){			
		}
		
		try{			
			lista.adicionar("thiago", "mensagem de teste");
			assertTrue(true);
		}catch(UsuarioInvalidoException e){
			fail("Erro ao adicionar mensagem: Usuario invalido!");
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}
	
	@Test
	public void testNumeroMensagemPorUsuario() {
		UsuarioMensagem lista = UsuarioMensagem.getUsuarioMensagem();	
		try{			
			try{
				ListaUsuario.getListaUsuario().inserirUsuario("thiago");
			}catch(UsuarioExistenteException e){				
			}catch(UsuarioInvalidoException e){			
			}			
			
			lista.adicionar("thiago", "mensagem de teste");
			lista.adicionar("thiago", "mensagem de teste2");
			lista.adicionar("thiago", "mensagem de teste3");
			assertEquals("Erro: esperado 3 mensagens na lista.", 3, lista.numeroMensagemPorUsuario("thiago"));
		}catch(UsuarioInvalidoException e){
			fail("Erro ao adicionar mensagem: Usuario invalido!");
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}
	
	@Test
	public void testNumeroMensagemUsuariosDiferentes() {
		UsuarioMensagem lista = UsuarioMensagem.getUsuarioMensagem();		
		try{
			try{
				ListaUsuario.getListaUsuario().inserirUsuario("thiago");
			}catch(UsuarioExistenteException e){				
			}catch(UsuarioInvalidoException e){			
			}
			
			try{
				ListaUsuario.getListaUsuario().inserirUsuario("thiagocardoso");
			}catch(UsuarioExistenteException e){				
			}catch(UsuarioInvalidoException e){			
			}			
			
			lista.adicionar("thiago", "mensagem de teste");
			lista.adicionar("thiago", "mensagem de teste2");
			lista.adicionar("thiago", "mensagem de teste3");

			lista.adicionar("thiagocardoso", "mensagem2 de teste");
			lista.adicionar("thiagocardoso", "mensagem2 de teste2");						
			assertEquals("Erro: esperado 3 mensagens na lista.", 3, lista.numeroMensagemPorUsuario("thiago"));
			assertEquals("Erro: esperado 2 mensagens na lista.", 2, lista.numeroMensagemPorUsuario("thiagocardoso"));
		}catch(UsuarioInvalidoException e){
			fail("Erro ao adicionar mensagem: Usuario invalido!");
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}		
}
