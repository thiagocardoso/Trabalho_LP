package redesocial;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import redesocial.ListaUsuario;
import redesocial.UsuarioMensagem;

public class TratadorMensagens {
	private String DoCriarUsuario(String usuario){
		try{
			ListaUsuario.getListaUsuario().inserirUsuario(usuario);
			return "ok";
		}catch(UsuarioExistenteException e){
			return "usuario-ja-existe";
		}catch(UsuarioInvalidoException e){
			return "nome-invalido";
		}
	}
	
	private String retornaUsuarioDaMensagem(String mensagem){
		int index = mensagem.indexOf(" ");
		
		if(index >= 0){
			return mensagem.substring(0, index);
		}else{
			return mensagem;
		}
	}
	
	private String removeUsuarioDaMensagem(String usuario, String post){
		int index = post.indexOf(usuario);
		
		if(index >= 0){
			return post.substring(index+usuario.length(), post.length()).trim();
		}else{
			return post;
		}
	}	
	
	private String DoPostarMensagem(String post){
		String usuario = retornaUsuarioDaMensagem(post);
		String texto = removeUsuarioDaMensagem(usuario, post);
		try{			
			UsuarioMensagem.getUsuarioMensagem().adicionar(usuario, texto);
			return "ok";
		}catch(UsuarioInvalidoException e){
			return "usuario-nao-encontrado";
		}catch(MensagemInvalidaException e){
			return "mensagem-invalida";
		}
	}
	
	private List<String> getRetorno(String texto){
		List<String> retorno = new ArrayList<String>();
		retorno.add(texto);
		return retorno;
	}
	
	private List<String> DoListarMensagensUsuario(String usuario){
		List<String> retorno = new ArrayList<String>();
		//System.out.println(usuario);
		ListaMensagem lista = UsuarioMensagem.getUsuarioMensagem().getListaMensagem(usuario); 
		
		if(lista!=null){
			lista.fillListaMensagens(retorno);
		}else{
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}
	
	private void DoResetar(){
		ListaUsuario.getListaUsuario().resetar();
		UsuarioMensagem.getUsuarioMensagem().resetar();
	}
	
	public List<String> Execute(String mensagem){
		if (mensagem.startsWith("criar-usuario")){
			String usuario = mensagem.replace("criar-usuario", "");			
			return getRetorno(DoCriarUsuario(usuario.trim()));
		}				
		
		if (mensagem.startsWith("postar-mensagem")){
			String post = mensagem.replace("postar-mensagem", "");
			return getRetorno(DoPostarMensagem(post.trim()));
		}	
		
		if (mensagem.startsWith("listar-mensagens-usuario")){
			String usuario = mensagem.replace("listar-mensagens-usuario", "");
			return DoListarMensagensUsuario(usuario.trim());
		}		
		
		if (mensagem.startsWith("resetar")){
			DoResetar();
		}
		return getRetorno("comando-invalido");
	}
}
