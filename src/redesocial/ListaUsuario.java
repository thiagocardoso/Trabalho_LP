package redesocial;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import redesocial.UsuarioExistenteException;
import redesocial.Usuario;
import java.lang.Character;

public class ListaUsuario {
	private static ListaUsuario listaUsuario = null;	
	//private List<String> list = new ArrayList<String>();
	private HashMap<String, Usuario> list = new HashMap<String, Usuario>();
	
	private ListaUsuario(){		
	}
	
	public void resetar(){
		//listaUsuario = null;
		list.clear();
	}
	
	private boolean tamanhoValido(String usuario){
		return (usuario.length() >= 3)&&(usuario.length() <= 20);
	}
	
	private boolean contemApenasLetras(String usuario){
		int i = 0;
		boolean result = true;
		
		while((i < usuario.length())&&(result)){			
			result = Character.isLetter(usuario.charAt(i));
			i++;
		}
		
		return result;
	}
	
	public static synchronized ListaUsuario getListaUsuario(){
		if (listaUsuario == null){
			listaUsuario = new ListaUsuario();
		}
		return listaUsuario;
	}
	
	private synchronized void DoInserirUsuario(Usuario usuario){
		list.put(usuario.getNome(), usuario);
	}
	
	public void inserirUsuario(String usuario) throws UsuarioExistenteException, UsuarioInvalidoException{	
		if(existeUsuario(usuario)){
			throw new UsuarioExistenteException();
		}
	
		if(!tamanhoValido(usuario)){			
			throw new UsuarioInvalidoException();
		}
		
		if(!contemApenasLetras(usuario)){
			throw new UsuarioInvalidoException();
		}
			
		//list.add(usuario);
		//list.put(usuario, new Usuario(usuario));
		DoInserirUsuario(new Usuario(usuario));
	}
	
	public boolean existeUsuario(String usuario){		
		return list.containsKey(usuario.trim());
	}
	
	public Usuario getUsuario(String usuario) throws UsuarioInvalidoException{
		if(!existeUsuario(usuario)){
			throw new UsuarioInvalidoException();
		}
		return list.get(usuario);
	}
	
	public List<String> getLista(){
		List<String> retorno = new ArrayList<String>();
		retorno.addAll(list.keySet());
		//System.out.print(list.size());
		return retorno;
	}
}
