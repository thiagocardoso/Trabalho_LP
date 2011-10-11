package test;

import redesocial.ListaUsuario;
import redesocial.UsuarioExistenteException;
import redesocial.UsuarioInvalidoException;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListaUsuarioTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListaUsuarioExists() {
		ListaUsuario lista = ListaUsuario.getListaUsuario();
		assertNotNull("Erro ao criar lista de usuarios", lista);
	}
	
	@Test
	public void testInserirUsuario(){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("usuarioteste");
			assertTrue("Erro ao inserir usuario", ListaUsuario.getListaUsuario().existeUsuario("usuarioteste"));
		}catch(UsuarioExistenteException e){
			fail("Erro ao inserir usuario: usuario ja existente.");
		}catch(UsuarioInvalidoException e){
			fail("usuario invalido");
		}
	}

	@Test
	public void testInserirUsuarioDuplicado(){		
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("usuarioteste");
			ListaUsuario.getListaUsuario().inserirUsuario("usuarioteste");
			fail("Erro ao inserir usuario");
		}catch(UsuarioExistenteException e){
			assertTrue(true);
		}catch(UsuarioInvalidoException e){
			fail("usuario invalido");
		}
	}	
	
	@Test
	public void testInserirNomeUsuarioInvalidoLimiteInferior(){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("ab");			
			fail("Erro: não deve aceitar nomes com menos de 3 caracteres.");
		}catch(UsuarioInvalidoException e){
			assertTrue(true);
		}catch(UsuarioExistenteException e){
			fail("Erro: usuario existente");
		}
	}
	
	@Test
	public void testInserirNomeUsuarioInvalidoLimiteSuperior(){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("mariaDoCarmoDaSilvaSantos");			
			fail("Erro: não deve aceitar nomes com mais de 20 caracteres.");
		}catch(UsuarioInvalidoException e){
			assertTrue(true);
		}catch(UsuarioExistenteException e){
			fail("Erro: usuario existente");
		}
	}
	
	@Test
	public void testInserirNomeUsuarioInvalidoNumeros(){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario("paulo23");			
			fail("Erro: deve aceitar nomes compostos apenas por letras.");
		}catch(UsuarioInvalidoException e){
			assertTrue(true);
		}catch(UsuarioExistenteException e){
			fail("Erro: usuario existente");
		}
	}	
}
