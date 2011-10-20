package redesocial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    private static final int PORTA = 1234;
    
    //private TratadorMensagens tratMensagem = new TratadorMensagens();
    
    public void iniciar() throws IOException {
        ServerSocket socket = new ServerSocket(PORTA);

        try {
            while (true) {
                atenderCliente(socket.accept());
            }
        } finally {
            socket.close();
        }
    }   
    
    private void enviarRetorno(OutputStream out, List<String> retorno) throws IOException{
    	int i = 0;
    	while(i<retorno.size()){
    		writeLine(out, retorno.get(i));
    		i++;
    	}
    }
    
    private void atenderCliente(final Socket cliente) throws IOException {
        GerenciaConexao gerente = new GerenciaConexao(cliente);
        new Thread(gerente).start();        
    }
    
    private static String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }
    
    private static void writeLine(OutputStream out, String linhas) throws IOException {
        out.write(linhas.getBytes());
        out.write('\n');
    }
}
