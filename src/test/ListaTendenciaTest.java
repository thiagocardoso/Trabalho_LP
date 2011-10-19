package test;

import redesocial.ListaTendencia;
import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redesocial.Mensagem;

public class ListaTendenciaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetListaTendencias() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();
		assertNotNull("Erro ao acessar lista de tendencias", lista);
	}

	@Test
	public void testPossuiTendencia() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #tv");
		
		assertTrue("Erro ao verificar tendencia.", lista.possuiTendencia(msg.getTexto()));		
	}

	@Test
	public void testInserirTendencia() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #tv");
		
		assertTrue("Erro ao criar tendencias.", lista.possuiTendencia(msg.getTexto()));	
		lista.inserirTendencias(msg);
	}
			
	@Test
	public void testListarTendencias() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #tv #bola #futebol");
		
		assertTrue("Erro ao criar tendencias.", lista.possuiTendencia(msg.getTexto()));	
		lista.inserirTendencias(msg);
		assertEquals("Erro ao listar tendencias.", "#tv", lista.listaTendencias().get(0));
		assertEquals("Erro ao listar tendencias.", "#futebol", lista.listaTendencias().get(1));
		assertEquals("Erro ao listar tendencias.", "#bola", lista.listaTendencias().get(2));
	}	
	
	@Test
	public void testNumeroMensagemTendencia() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #vermelho #preto");
		
		assertTrue("Erro ao criar tendencias.", lista.possuiTendencia(msg.getTexto()));	
		lista.inserirTendencias(msg);
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #verde #preto");
		lista.inserirTendencias(msg);
		assertEquals("Erro ao contar tendencias.", 2, lista.getTendencia("#azul").getListaMensagens().tamanhoLista());
		assertEquals("Erro ao contar tendencias.", 1, lista.getTendencia("#vermelho").getListaMensagens().tamanhoLista());
		assertEquals("Erro ao contar tendencias.", 1, lista.getTendencia("#verde").getListaMensagens().tamanhoLista());
		assertEquals("Erro ao contar tendencias.", 2, lista.getTendencia("#preto").getListaMensagens().tamanhoLista());		
	}		
}
