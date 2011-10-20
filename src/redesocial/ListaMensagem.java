package redesocial;

import redesocial.ListaTendencia;
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
	
	public synchronized void inserirMensagem(String usuario, String texto) throws MensagemInvalidaException{
		Mensagem atual = null;
		
		if(!mensagemValida(texto)){
			throw new MensagemInvalidaException();
		}
		
		atual = getMensagem(usuario, texto);
		lista.add(0, atual);
		
		if(ListaTendencia.getListaTendencia().possuiTendencia(texto)){
			ListaTendencia.getListaTendencia().inserirTendencias(atual);
		}		
	}
	
	public void adicionar(Mensagem msg){
		lista.add(0, msg);
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
	
	public boolean existeMensagem(Mensagem msg){		
		int i = 0;
		boolean retorno = false;
		while((i<lista.size())&&(!retorno)){
			retorno = (lista.get(i).getUsuario()==msg.getUsuario())&&(lista.get(i).getTexto()==msg.getTexto());			
			i++;
		}
		return retorno;
	}
}
