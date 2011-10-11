package redesocial;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Iniciando servidor");
        Servidor servidor = new Servidor();
        servidor.iniciar();        
    }
}