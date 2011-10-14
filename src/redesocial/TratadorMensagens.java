package redesocial;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import redesocial.ListaUsuario;

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
			//UsuarioMensagem.getUsuarioMensagem().adicionar(usuario, texto);
			ListaUsuario.getListaUsuario().getUsuario(usuario).adicionarMensagem(texto);
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
		ListaMensagem lista = null;
		try{
			lista = ListaUsuario.getListaUsuario().getUsuario(usuario).getListaMensagens();
			lista.fillListaMensagens(retorno);
		}catch(UsuarioInvalidoException e){
			retorno.add("usuario-nao-encontrado");
		}
		return retorno;
	}
	
	private String DoSeguir(String usuarios){
		String usuario1 = retornaUsuarioDaMensagem(usuarios);
		String usuario2 = removeUsuarioDaMensagem(usuario1, usuarios);		
		try {
			if(!ListaUsuario.getListaUsuario().existeUsuario(usuario2)){
				return "seguido-nao-encontrado";
			}			
			ListaUsuario.getListaUsuario().getUsuario(usuario1.trim()).seguir(usuario2.trim());
			return "ok";
		} catch (UsuarioJaSeguidoException e) {
			return "ja-seguindo";
		} catch (UsuarioSeguidoreSeguidoIguaisException e) {
			return "seguidor-e-seguido-sao-iguais";
		} catch (UsuarioInvalidoException e) {
			return "seguidor-nao-encontrado";
		}		
	}

	private String DoDeixarDeSeguir(String usuarios){
		String usuario1 = retornaUsuarioDaMensagem(usuarios);
		String usuario2 = removeUsuarioDaMensagem(usuario1, usuarios);		
		try {			
			if(!ListaUsuario.getListaUsuario().existeUsuario(usuario2.trim())){
				return "seguido-nao-encontrado";
			}
			
			ListaUsuario.getListaUsuario().getUsuario(usuario1.trim()).deixarDeSeguir(usuario2.trim());
			return "ok";		
		} catch (UsuarioInvalidoException e) {
			return "seguidor-nao-encontrado";
		} catch (UsuarioSeguidoNaoEncontradoException e) {
			return "nao-seguindo";
		}		
	}		
	
	private List<String> DoListarSeguidos(String usuario){
		List<String> list = null;
		try {
			list = ListaUsuario.getListaUsuario().getUsuario(usuario).getListaSeguidos();
			return list;
		} catch (UsuarioInvalidoException e) {
			return getRetorno("usuario-invalido");
		}
	}
	
	private List<String> DoListarSeguidores(String usuario){
		List<String> list = null;
		try {
			list = ListaUsuario.getListaUsuario().getUsuario(usuario).getListaSeguidores();
			return list;
		} catch (UsuarioInvalidoException e) {
			return getRetorno("usuario-invalido");
		}
	}
	
	private List<String> DoListarMensagensSeguidos(String usuario){
		List<String> list = new ArrayList<String>();		
		try {
			ListaUsuario.getListaUsuario().getUsuario(usuario).listarMensagensSeguidos(list);
			return list;
		} catch (UsuarioInvalidoException e) {
			return getRetorno("usuario-invalido");
		}
		
	}
	
	private List<String> DoListarEstatistica(String usuario){
		List<String> list = new ArrayList<String>();		
		try {
			ListaUsuario.getListaUsuario().getUsuario(usuario).listarEstatisticas(list);
			return list;
		} catch (UsuarioInvalidoException e) {
			return getRetorno("usuario-invalido");
		}		
	}	
	
	private void DoResetar(){
		ListaUsuario.getListaUsuario().resetar();
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
		
		if (mensagem.startsWith("seguir")){			
			String usuarios = mensagem.replace("seguir", "");			
			return getRetorno(DoSeguir(usuarios.trim()));	
		}
		
		if (mensagem.startsWith("listar-seguidos")){
			String usuario = mensagem.replace("listar-seguidos", "");
			return DoListarSeguidos(usuario.trim());	
		}
		
		if (mensagem.startsWith("listar-seguidores")){
			String usuario = mensagem.replace("listar-seguidores", "");
			return DoListarSeguidores(usuario.trim());	
		}
		
		if (mensagem.startsWith("deixar-de-seguir")){
			String usuarios = mensagem.replace("deixar-de-seguir", "");
			return getRetorno(DoDeixarDeSeguir(usuarios.trim()));
		}

		if (mensagem.startsWith("listar-mensagens-seguidos")){
			String usuario = mensagem.replace("listar-mensagens-seguidos", "");
			return DoListarMensagensSeguidos(usuario.trim());
		}		

		if (mensagem.startsWith("listar-estatisticas-usuario")){
			String usuario = mensagem.replace("listar-estatisticas-usuario", "");
			return DoListarEstatistica(usuario.trim());
		}				
		
		if (mensagem.startsWith("resetar")){
			DoResetar();
		}
		return getRetorno("comando-invalido");
	}
}
