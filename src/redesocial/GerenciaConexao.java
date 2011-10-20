package redesocial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import redesocial.TratadorMensagens;

public class GerenciaConexao implements Runnable{
	private Socket cliente;
	
	private static String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }
	
    private void enviarRetorno(OutputStream out, List<String> retorno) throws IOException{
    	int i = 0;
    	while(i<retorno.size()){
    		writeLine(out, retorno.get(i));
    		i++;
    	}
    }	
	
    private static void writeLine(OutputStream out, String linhas) throws IOException {
        out.write(linhas.getBytes());
        out.write('\n');
    }    
    
	public GerenciaConexao(Socket clienteNovo){
		cliente = clienteNovo;
	}
    
	
	public void run(){	
		TratadorMensagens tratMsg = new TratadorMensagens();
		String comando="";
		try{		
			comando = readLine(cliente.getInputStream());
			enviarRetorno(cliente.getOutputStream(), tratMsg.Execute(comando.trim()));
			
		}catch(IOException e){			
		}finally{
			try{
				cliente.close();
			}catch(IOException e){				
			}
		}			
	}
}
