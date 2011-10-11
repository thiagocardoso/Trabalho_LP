package redesocial;

import java.lang.String;
import redesocial.ListaUsuario;

public class TratadorMensagens {
	private String DoCriarUsuario(String usuario){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario(usuario);
			return "OK";
		}catch(UsuarioExistenteException e){
			return "usuario-ja-existe";
		}catch(UsuarioInvalidoException e){
			return "nome-invalido";
		}
	}
	
	public String Execute(String mensagem){
		if (mensagem.startsWith("criar-usuario")){
			String usuario = mensagem.replace("criar-usuario", "");			
			return DoCriarUsuario(usuario.trim());
		}				
		return "mensagem-invalida";
	}
}
