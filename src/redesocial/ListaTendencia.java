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
	
	//private synchronized void insereRanking(Tendencia tendencia){
//		int i = 0;
		//if(tendencia.getListaMensagens().tamanhoLista()>ranking.get(0).getListaMensagens().tamanhoLista()){
			
		//}
	//}
	
	public synchronized void inserirTendencias(Mensagem msg){
		Tendencia actual = null;
		Pattern padrao = Pattern.compile("#[a-zA-Z]+");
		Matcher pesquisa = padrao.matcher(msg.getTexto());		
			
		while (pesquisa.find()){		
			//synchronized(list){
				if(!list.containsKey(pesquisa.group())){
					actual = new Tendencia(pesquisa.group());				
				
					try {
						actual.inserirMensagem(msg);
					} catch (MensagemInvalidaException e) {				
					}				
								
					list.put(pesquisa.group(), actual);
				}else{				
					//if(!list.get(pesquisa.group()).getListaMensagens().existeMensagem(msg)){								
						list.get(pesquisa.group()).getListaMensagens().adicionar(msg);				
					//}				
				}
			//}
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
	
	private String getMelhorTendencia(List<String> lista){		
		int i = 0;
		String melhor = "";
		
		melhor = list.get(lista.get(i)).getNome();
		i++;
		while(i<lista.size()){
			if(list.get(melhor).getListaMensagens().tamanhoLista()<list.get(lista.get(i)).getListaMensagens().tamanhoLista()){
				melhor = lista.get(i);
			}else{
				if(list.get(melhor).getListaMensagens().tamanhoLista()==list.get(lista.get(i)).getListaMensagens().tamanhoLista()){
					if(melhor.compareTo(lista.get(i))>0){
						melhor = lista.get(i);
					}
				}
			}
			i++;
		}
		
		return melhor;		
	}
	
	public synchronized void reCriarListaTendencias(){
		List<String> listaUsuarios = ListaUsuario.getListaUsuario().getLista();
		ListaMensagem actual = null;
		int iUsuario = 0;
		list.clear();		
		//System.out.print(listaUsuarios.size());
		while(iUsuario<listaUsuarios.size()){
			try {
				actual = ListaUsuario.getListaUsuario().getUsuario(listaUsuarios.get(iUsuario)).getListaMensagens();
				//System.out.print(listaUsuarios.get(iUsuario));
				actual.first();
				while(!actual.eof()){
					if(possuiTendencia(actual.getMensagem().getTexto())){
						inserirTendencias(actual.getMensagem());
					}
					actual.next();
				}
			} catch (UsuarioInvalidoException e) {				
			}	
			
			iUsuario++;
		}
	}
	
	public List<String> listaRankingTendencias(){
		HashMap<String, Tendencia> backup = new HashMap<String, Tendencia>();
		backup.putAll(list);
		reCriarListaTendencias();
		
		List<String> temporario = listaTendencias();
		List<String> retorno = new ArrayList<String>();
		String atual = "";
		int i = 0;
				
		while((!temporario.isEmpty())&&(i<5)){
			atual = getMelhorTendencia(temporario);
			temporario.remove(atual);
			retorno.add(atual);
			
			System.out.print("tendencia: "+ atual +" tamanho: "+ list.get(atual).getListaMensagens().tamanhoLista()+ "\n");
			
			i++;
		}		
		
		list.clear();
		list.putAll(backup);
		backup.clear();
		/*
		i = 0;
		temporario = listaTendencias();
		
		while(i<temporario.size()){
			System.out.print(temporario.get(i) +" "+ list.get(temporario.get(i)).getListaMensagens().tamanhoLista() +"\n");
			i++;
		}
		*/
		return retorno;		
	}
	
	public void limparTendencias(){
		//list = new HashMap<String, Tendencia>();
		list.clear();
	}	

	public List<String> listaMensagensTendencia(String tendencia){
		List<String> retorno = new ArrayList<String>();
		ListaMensagem listaMsg = null;
		if(list.containsKey(tendencia)){
			listaMsg = list.get(tendencia).getListaMensagens();
			listaMsg.first();
			while(!listaMsg.eof()){
				retorno.add(listaMsg.getMensagem().getUsuario() + " "+listaMsg.getMensagem().getTexto());				
				listaMsg.next();
			}
		}
		return retorno;
	}
}
