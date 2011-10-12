package redesocial;

import redesocial.MensagemInvalidaException;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

public class ListaMensagem {
	private List<Mensagem> lista = new ArrayList<Mensagem>();	
	private int index = 0;
	
	private boolean mensagemValida(String texto){
		return (texto.length()>0)&&(texto.length()<=140);
	}	
	
	private Mensagem getMensagem(String usuario, String texto){
		return new Mensagem(Calendar.getInstance().getTime(), usuario, texto);
	}
	
	public void inserirMensagem(String usuario, String texto) throws MensagemInvalidaException{
		if(!mensagemValida(texto)){
			throw new MensagemInvalidaException();
		}
		lista.add(0, getMensagem(usuario, texto));
	}
	
	public int tamanhoLista(){
		return lista.size();
	}
	
	public boolean eof(){
		return index==lista.size();
	}
	
	public void next(){
		if(index<lista.size()){
			index++;
		}
	}
	
	public void prior(){
		if(index>0){
			index--;
		}
	}
	
	public void first(){
		index=0;
	}
	
	public void last(){
		index=lista.size()-1;
	}
	
	public Mensagem getMensagem(){		
		return lista.get(index);
	}
	
	public void fillListaMensagens(List<String> mensagens){
		first();
		while(!eof()){
			mensagens.add(getMensagem().getTexto());
			
			next();
		}
	}
}
