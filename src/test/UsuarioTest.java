package test;

import redesocial.ListaUsuario;
import redesocial.MensagemInvalidaException;
import redesocial.Usuario;
import redesocial.UsuarioExistenteException;
import redesocial.UsuarioInvalidoException;
import redesocial.UsuarioJaSeguidoException;
import redesocial.UsuarioSeguidoNaoEncontradoException;
import redesocial.UsuarioSeguidorExistenteException;
import redesocial.UsuarioSeguidoreSeguidoIguaisException;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCriarUsuario() {
		Usuario usuario = new Usuario("thiago");
		assertNotNull("Erro ao instanciar usuario", usuario);
	}
	
	@Test
	public void testUsuarioNome() {
		Usuario usuario = new Usuario("thiago");		
		assertEquals("Erro ao criar usuario thiago", "thiago", usuario.getNome());
	}
	
	@Test
	public void testAdicionarMensagem() {		
		Usuario usuario = new Usuario("thiago");
		try{			
			usuario.adicionarMensagem("mensagem de teste");
			assertTrue(true);		
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}	
	
	@Test
	public void testUsuarioNumeroMensagem() {
		Usuario usuario = new Usuario("thiago");	
		try{			
			usuario.adicionarMensagem("mensagem de teste");
			usuario.adicionarMensagem("mensagem de teste2");
			usuario.adicionarMensagem("mensagem de teste3");
			assertEquals("Erro: esperado 3 mensagens na lista.", 3, usuario.numeroMensagens());		
		}catch(MensagemInvalidaException e){
			fail("Erro ao adicionar mensagem: Mensagem invalida!");
		}
	}	
	
	@Test
	public void testUsuarioSeguir() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.seguir("usuarionovo");
			assertTrue("Erro ao seguir usuario", usuario.seguindo("usuarionovo"));
		}catch(UsuarioJaSeguidoException e){
			fail("Erro ao seguir usuário");
		}catch(UsuarioSeguidoreSeguidoIguaisException e){
			fail("Erro ao seguir usuário");
		}
	}
	
	@Test
	public void testUsuarioSeguirIgual() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.seguir("thiago");
			fail("Erro ao seguir usuário: não deve permitir usuario seguir a si mesmo.");			
		}catch(UsuarioSeguidoreSeguidoIguaisException e){
			assertTrue(true);
		}catch(UsuarioJaSeguidoException e){
			fail("Erro ao seguir usuário");
		}	
	}	

	@Test
	public void testUsuarioDeixarDeSeguir() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.seguir("usuarionovo");
		}catch(UsuarioJaSeguidoException e){		
		}catch(UsuarioSeguidoreSeguidoIguaisException e){			
		}
		
		try{
			usuario.deixarDeSeguir("usuarionovo");
			assertFalse("Erro ao deixar de seguir usuario", usuario.seguindo("usuarionovo"));
		}catch(UsuarioSeguidoNaoEncontradoException e){
			fail("Erro ao deixar de seguir usuário");
		}	
	}
	
	@Test
	public void testUsuarioAdicionarSeguidor() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.adicionarSeguidor("usuarionovo");
			assertTrue("Erro ao adicionar seguidor", usuario.seguidoPor("usuarionovo"));
		}catch(UsuarioSeguidorExistenteException e){
			fail("Erro ao adicionar seguidor");
		}	
	}	
	
	@Test
	public void testUsuarioListarSeguidos() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.seguir("usuarionovo");
			usuario.seguir("outrousuario");
		}catch(UsuarioJaSeguidoException e){
			fail("Erro ao adicionar seguidor");
		}catch(UsuarioSeguidoreSeguidoIguaisException e){			
		}
		
		assertEquals("Erro ao listar seguidos", "outrousuario", usuario.getListaSeguidos().get(0));
		assertEquals("Erro ao listar seguidos", "usuarionovo", usuario.getListaSeguidos().get(1));			
	}
	
	@Test
	public void testUsuarioListarSeguidores() {
		Usuario usuario = new Usuario("thiago");
		try{
			usuario.adicionarSeguidor("usuarionovo");
			usuario.adicionarSeguidor("outrousuario");
		}catch(UsuarioSeguidorExistenteException e){
			fail("Erro ao adicionar seguidor");
		}
		
		assertEquals("Erro ao listar seguidos", "outrousuario", usuario.getListaSeguidores().get(0));
		assertEquals("Erro ao listar seguidos", "usuarionovo", usuario.getListaSeguidores().get(1));			
	}
	
	@Test
	public void testUsuarioListarMensagensSeguidos() {
		Usuario thiago = null;
		Usuario usuarionovo = null;
		Usuario outrousuario = null;
		List<String> list = new ArrayList<String>();
		try {
			ListaUsuario.getListaUsuario().inserirUsuario("thiago");
			ListaUsuario.getListaUsuario().inserirUsuario("usuarionovo");
			ListaUsuario.getListaUsuario().inserirUsuario("outrousuario");
			thiago = ListaUsuario.getListaUsuario().getUsuario("thiago");
			usuarionovo = ListaUsuario.getListaUsuario().getUsuario("usuarionovo");
			outrousuario = ListaUsuario.getListaUsuario().getUsuario("outrousuario");
			
			thiago.seguir("usuarionovo");
			thiago.seguir("outrousuario");
			
			usuarionovo.adicionarMensagem("mensagem de teste 1!");
			usuarionovo.adicionarMensagem("mensagem de teste 2!");			
			outrousuario.adicionarMensagem("mensagem de teste 3!");
			outrousuario.adicionarMensagem("mensagem de teste 4!");	
			
			thiago.listarMensagensSeguidos(list);
			
			assertEquals("Erro ao listar mensagens dos seguidos.", "outrousuario mensagem de teste 4!", list.get(0));
			assertEquals("Erro ao listar mensagens dos seguidos.", "outrousuario mensagem de teste 3!", list.get(1));
			assertEquals("Erro ao listar mensagens dos seguidos.", "usuarionovo mensagem de teste 2!", list.get(2));
			assertEquals("Erro ao listar mensagens dos seguidos.", "usuarionovo mensagem de teste 1!", list.get(3));
		} catch (UsuarioExistenteException e) {			
		} catch (UsuarioInvalidoException e) {			
		} catch(MensagemInvalidaException e){			
		} catch (UsuarioJaSeguidoException e) {			
		} catch (UsuarioSeguidoreSeguidoIguaisException e) {			
		}		
	}	
	
	@Test
	public void testGetEstatisticas(){
		Usuario usuario1 = null;
		Usuario usuario2 = null;		
		List<String> list = new ArrayList<String>();
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("uuusuario");
			ListaUsuario.getListaUsuario().inserirUsuario("uuuusuario");
		} catch (UsuarioExistenteException e) {			
		} catch (UsuarioInvalidoException e) {		
		}
		
		try{
			usuario1 = ListaUsuario.getListaUsuario().getUsuario("uuusuario");
			usuario2 = ListaUsuario.getListaUsuario().getUsuario("uuuusuario");					
		} catch (UsuarioInvalidoException e) {		
		}
			
		try{
			usuario1.seguir("uuuusuario");
			usuario1.adicionarMensagem("teste de mensagem!");
		
			usuario1.listarEstatisticas(list);
			assertEquals("Erro ao retornar estatisticas.", "1", list.get(0));
			assertEquals("Erro ao retornar estatisticas.", "1", list.get(1));
			assertEquals("Erro ao retornar estatisticas.", "0", list.get(2));
		
			list.clear();			
			usuario2.listarEstatisticas(list);
			assertEquals("Erro ao retornar estatisticas.", "0", list.get(0));
			assertEquals("Erro ao retornar estatisticas.", "0", list.get(1));
			assertEquals("Erro ao retornar estatisticas.", "1", list.get(2));			

		} catch (MensagemInvalidaException e) {		
			fail("Erro ao retornar estatisticas: mensagem invalida.");
		} catch (UsuarioJaSeguidoException e) {
			fail("Erro ao retornar estatisticas: usuario ja seguido.");
		} catch (UsuarioSeguidoreSeguidoIguaisException e) {
			fail("Erro ao retornar estatisticas: erro!.");
		}		
	}
}
