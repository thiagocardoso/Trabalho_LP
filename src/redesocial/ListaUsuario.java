package redesocial;

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
			
		//list.add(usuario);
		list.put(usuario, new Usuario(usuario));
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
}
