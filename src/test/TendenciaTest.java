package test;

import java.util.Calendar;

import redesocial.Mensagem;
import redesocial.MensagemInvalidaException;
import redesocial.Tendencia;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TendenciaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTendenciaCriacao() {
		Tendencia tendencia = new Tendencia("#tv");
		assertNotNull("Erro ao criar tendencia", tendencia);
	}

	@Test
	public void testTendenciaInserirMensagem() {
		Tendencia tendencia = new Tendencia("#tv");
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "usuarioteste", "textoteste #tv");
		
		try {
			tendencia.inserirMensagem(msg);
			tendencia.getListaMensagens().first();
			assertEquals("Erro ao inserir mensagem na tendencia #tv.", "textoteste #tv", tendencia.getListaMensagens().getMensagem().getTexto());
		} catch (MensagemInvalidaException e) {
			fail("Erro ao inserir mensgaem na tendencia #tv.");
		}				
	}
}
