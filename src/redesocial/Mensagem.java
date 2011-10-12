package redesocial;

import java.util.Date;

public class Mensagem {
	private Date dataCriacao;
	private String usuario;
	private String texto;
	
	public Mensagem(Date aDataCriacao, String aUsuario, String aTexto){
		dataCriacao = aDataCriacao;
		usuario = aUsuario;
		texto = aTexto;
	}
	
	public void setUsuario(String valor){
		usuario = valor;
	}
	
	public void setTexto(String valor){
		texto = valor;
	}
	
	public void setDataCriacao(Date valor){
		dataCriacao = valor;
	}
	
	public String getUsuario(){
		return usuario;
	}
	
	public String getTexto(){
		return texto;
	}
	
	public Date getDataCriacao(){
		return dataCriacao;
	}
}
