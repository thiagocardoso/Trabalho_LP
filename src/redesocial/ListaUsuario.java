package redesocial;

import java.util.*;
import redesocial.UsuarioExistenteException;
import java.lang.Character;

public class ListaUsuario {
	private static ListaUsuario listaUsuario = null;	
	private List<String> list = new ArrayList<String>();
	
	private ListaUsuario(){		
	}
	
	public void resetar(){
		listaUsuario = null;
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
			
		list.add(usuario);		
	}
	
	public boolean existeUsuario(String usuario){		
		return list.indexOf(usuario.trim())>=0;
	}
}
