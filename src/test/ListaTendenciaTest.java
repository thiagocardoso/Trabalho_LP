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
		
		lista.limparTendencias();
		
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #preto");		
		lista.inserirTendencias(msg);
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #verde");
		lista.inserirTendencias(msg);
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #amarelo");
		lista.inserirTendencias(msg);		
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #vermelho");
		lista.inserirTendencias(msg);		
		assertEquals("Erro ao contar tendencias.", "#azul", lista.listaRankingTendencias().get(0));
		assertEquals("Erro ao contar tendencias.", "#amarelo", lista.listaRankingTendencias().get(1));
		assertEquals("Erro ao contar tendencias.", "#preto", lista.listaRankingTendencias().get(2));	
		assertEquals("Erro ao contar tendencias.", "#verde", lista.listaRankingTendencias().get(3));	
		assertEquals("Erro ao contar tendencias.", "#vermelho", lista.listaRankingTendencias().get(4));	
	}	
	
	public void testListarMensagemTendencia() {
		ListaTendencia lista = ListaTendencia.getListaTendencia();	
		
		lista.limparTendencias();
		
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #preto");		
		lista.inserirTendencias(msg);
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #verde");
		lista.inserirTendencias(msg);
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #amarelo");
		lista.inserirTendencias(msg);		
		
		msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #azul #vermelho");
		lista.inserirTendencias(msg);
		
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #verde", lista.listaMensagensTendencia("#verde").get(0));
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #preto", lista.listaMensagensTendencia("#preto").get(0));
		
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #vermelho", lista.listaMensagensTendencia("#azul").get(0));
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #amarelo", lista.listaMensagensTendencia("#azul").get(1));
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #verde", lista.listaMensagensTendencia("#azul").get(2));
		assertEquals("Erro ao listar mensagens das tendencias.", "usuarioteste textoteste #azul #preto", lista.listaMensagensTendencia("#azul").get(3));		
	}	
}
