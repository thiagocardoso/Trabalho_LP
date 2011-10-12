package test;

import java.util.Calendar;
import redesocial.Mensagem;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MensagemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMensagemCriacao() {
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "thiago", "teste de mensagem");
		assertNotNull("Erro ao instanciar mensagem", msg);
	}
	
	@Test
	public void testMensagemLerUsuario() {
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "thiago", "teste de mensagem");
		assertEquals("Erro ao retornar usuario da mensagem", "thiago", msg.getUsuario());
	}
	
	@Test
	public void testMensagemLerMensagem() {
		Mensagem msg = new Mensagem(Calendar.getInstance().getTime(), "thiago", "teste de mensagem");
		assertEquals("Erro ao retornar texto da mensagem", "teste de mensagem", msg.getTexto());
	}	
}
