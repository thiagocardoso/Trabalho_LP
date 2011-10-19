package redesocial;

import redesocial.Mensagem;
import redesocial.ListaMensagem;

public class Tendencia {
	private String nome = "";
	private ListaMensagem list = new ListaMensagem();
	
	public Tendencia(String NomeTendencia){
		nome = NomeTendencia;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void inserirMensagem(Mensagem novaMensagem) throws MensagemInvalidaException{
		list.adicionar(novaMensagem);
	}
	
	public ListaMensagem getListaMensagens(){
		return list;
	}
}
