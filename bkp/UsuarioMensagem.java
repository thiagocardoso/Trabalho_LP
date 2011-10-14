package redesocial;

import java.util.HashMap;
import redesocial.ListaMensagem;

public class UsuarioMensagem {
	private static UsuarioMensagem usuarioMensagem = null;	
	private HashMap<String, ListaMensagem> lista = new HashMap<String, ListaMensagem>();
	
	private UsuarioMensagem(){		
	}
	
	public void resetar(){
		usuarioMensagem = null;
	}	
	
	public static synchronized UsuarioMensagem getUsuarioMensagem(){
		if (usuarioMensagem == null){
			usuarioMensagem = new UsuarioMensagem();
		}
		return usuarioMensagem;
	}
	
	private boolean usuarioValido(String usuario){
		return ListaUsuario.getListaUsuario().existeUsuario(usuario);
	}
	
	private void persistirListaMensagem(String usuario){
		if(!lista.containsKey(usuario)){
			lista.put(usuario, new ListaMensagem());
		}
	}
	
	public void adicionar(String usuario, String texto) throws UsuarioInvalidoException, MensagemInvalidaException{
		try{
			if(!usuarioValido(usuario)){
				throw new UsuarioInvalidoException();
			}
			
			persistirListaMensagem(usuario);			
			lista.get(usuario).inserirMensagem(usuario, texto);
		}catch(UsuarioInvalidoException e){
			throw e;
		}catch(MensagemInvalidaException e){
			throw e;
		}				
	}	
	
	public int numeroMensagemPorUsuario(String usuario){
		if (!lista.containsKey(usuario)){
			return 0;			
		}
		return lista.get(usuario).tamanhoLista();
	}
	
	public ListaMensagem getListaMensagem(String usuario){
		return lista.get(usuario);
	}
}
