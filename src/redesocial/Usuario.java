package redesocial;

import java.util.List;
import java.util.ArrayList;
import redesocial.UsuarioJaSeguidoException;

public class Usuario {
	private String nome = "";
	private ListaMensagem listaMensagem = new ListaMensagem();	
	private List<String> listaSeguindo = new ArrayList<String>();
	private List<String> listaSeguidores = new ArrayList<String>();
	
	public Usuario(String nomeusuario){
		nome = nomeusuario;
	}
	
	public String getNome(){
		return nome;
	}
	
	public boolean seguindo(String usuario){
		return listaSeguindo.contains(usuario);
	}
	
	public void seguir(String usuario) throws UsuarioJaSeguidoException, UsuarioSeguidoreSeguidoIguaisException{
		if(seguindo(usuario)){
			throw new UsuarioJaSeguidoException();
		}
		
		if(usuario.equals(getNome())){
			throw new UsuarioSeguidoreSeguidoIguaisException();
		}
		
		listaSeguindo.add(0, usuario);
		
		try {
			ListaUsuario.getListaUsuario().getUsuario(usuario).adicionarSeguidor(getNome());
		} catch (UsuarioSeguidorExistenteException e) {			
		} catch (UsuarioInvalidoException e) {			
		}
	}
	
	public void deixarDeSeguir(String usuario) throws UsuarioSeguidoNaoEncontradoException{
		if(!seguindo(usuario)){
			throw new UsuarioSeguidoNaoEncontradoException();
		}
		
		listaSeguindo.remove(usuario);
		
		try {
			ListaUsuario.getListaUsuario().getUsuario(usuario).removerSeguidor(getNome());
		} catch (UsuarioInvalidoException e) {			
		}
	}
	
	public boolean seguidoPor(String usuario){
		return listaSeguidores.contains(usuario);
	}
	
	public void adicionarSeguidor(String usuario) throws UsuarioSeguidorExistenteException{
		if(seguidoPor(usuario)){
			throw new UsuarioSeguidorExistenteException();
		}
		
		listaSeguidores.add(0, usuario);
	}
	
	public void removerSeguidor(String usuario){
		listaSeguidores.remove(usuario);
	}
	
	public List<String> getListaSeguidos(){
		return listaSeguindo;
	}
	
	public List<String> getListaSeguidores(){
		return listaSeguidores;
	}
	
	public void adicionarMensagem(String texto) throws MensagemInvalidaException{
		listaMensagem.inserirMensagem(getNome(), texto);					
	}
	
	public int numeroMensagens(){
		return listaMensagem.tamanhoLista();
	}
	
	public ListaMensagem getListaMensagens(){
		return listaMensagem;
	}
	
	private Mensagem getMaisNova(List<Mensagem> list){
		Mensagem retorno = null;
		int i=1;
		retorno = list.get(0);
		while(i<list.size()){			
			if(retorno.getDataCriacao().before(list.get(i).getDataCriacao())){
				retorno = list.get(i);
			}
			i++;
		}
		return retorno;
	}
	
	public void listarMensagensSeguidos(List<String> list){
		ListaMensagem actual = null;
		List<Mensagem> localList = new ArrayList<Mensagem>();
		Mensagem mensagem = null;
		int i=0;
		while(i<listaSeguindo.size()){
			try {
				actual = ListaUsuario.getListaUsuario().getUsuario(listaSeguindo.get(i)).getListaMensagens();
				actual.first();
				while(!actual.eof()){
					localList.add(actual.getMensagem());					
					actual.next();
				}								
			} catch (UsuarioInvalidoException e) {				
			}
			i++;
		}
		
		while(!localList.isEmpty()){
			mensagem = getMaisNova(localList);			
			list.add(mensagem.getUsuario()+" "+mensagem.getTexto());
			localList.remove(mensagem);
		}		
	}
} 
