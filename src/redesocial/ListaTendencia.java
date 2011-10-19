package redesocial;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.*;

public class ListaTendencia {
	private static ListaTendencia listaTendencia = null;	
	private String padraoTendencia = ".*\\s#[a-zA-Z0-9]+.*";
	private HashMap<String, Tendencia> list = new HashMap<String, Tendencia>();
	
	private ListaTendencia(){		
	}
	
	public static synchronized ListaTendencia getListaTendencia(){
		if (listaTendencia == null){
			listaTendencia = new ListaTendencia();
		}
		return listaTendencia;
	}
	
	public boolean possuiTendencia(String texto){
		//Pattern padrao = Pattern.compile(".*\\s#[a-zA-Z0-9]+.*");
		//Matcher pesquisa = padrao.matcher(texto);
		//return pesquisa.matches();
		return Pattern.matches(padraoTendencia, texto); 
	}
	
	public void inserirTendencias(Mensagem msg){
		Tendencia actual = null;
		Pattern padrao = Pattern.compile("#[a-zA-Z]+");
		Matcher pesquisa = padrao.matcher(msg.getTexto());		
			
		while (pesquisa.find()){				
			if(!list.containsKey(pesquisa.group())){
				actual = new Tendencia(pesquisa.group());				
				
				try {
					actual.inserirMensagem(msg);
				} catch (MensagemInvalidaException e) {				
				}
				
				list.put(pesquisa.group(), actual);
			}else{				
				if(!list.get(pesquisa.group()).getListaMensagens().existeMensagem(msg)){					
					list.get(pesquisa.group()).getListaMensagens().adicionar(msg);
				}
			}
		}		
	}
	
	public List<String> listaTendencias(){
		List<String> retorno = new ArrayList<String>();		
		retorno.addAll(list.keySet());
		
		return retorno;
	}
	
	public Tendencia getTendencia(String nome){
		return list.get(nome);
	}
}
