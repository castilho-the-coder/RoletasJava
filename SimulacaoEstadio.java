import java.util.ArrayList;
import java.util.List;


public class SimulacaoEstadio {
    private static final int NUM_ROLETAS = 5; 
    private static final int PESSOAS_POR_ROLETA = 200;
    
    public static void main(String[] args) {
        
        // cria o ÚNICO recurso compartilhado
        ContadorAcesso contadorGeral = new ContadorAcesso();

        // lista para guardar as threads e poder esperar por elas
        List<Thread> threads = new ArrayList<>();

        System.out.println("Iniciando simulação com " + NUM_ROLETAS + " roletas.");
        System.out.println("Cada roleta processará " + PESSOAS_POR_ROLETA + " pessoas.");
        System.out.println("Total esperado: " + (NUM_ROLETAS * PESSOAS_POR_ROLETA));
        System.out.println("-------------------------------------------------");
        
        for (int i = 0; i < NUM_ROLETAS; i++) {
            // runnable
            Roleta roleta = new Roleta(i, contadorGeral, PESSOAS_POR_ROLETA);
            
            // thread a partir do runnable
            Thread threadRoleta = new Thread(roleta);
            
            // adiciona na lista e inicia
            threads.add(threadRoleta);
            threadRoleta.start();
        }

        // Espera todas as threads terminarem
        for (Thread t : threads) {
            try {
                t.join(); //  join() bloqueia a thread 'main' até que a thread 't' termine sua execução.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // resultado final
        int totalRegistrado = contadorGeral.getTotalPessoas();
        int totalEsperado = NUM_ROLETAS * PESSOAS_POR_ROLETA;

        System.out.println("-------------------------------------------------");
        System.out.println("Todas as roletas terminaram.");
        System.out.println("Total Esperado: " + totalEsperado);
        System.out.println("Total Registrado: " + totalRegistrado);

        if (totalRegistrado == totalEsperado) {
            System.out.println("SUCESSO! Nenhum acesso foi perdido.");
        } else {
            System.err.println("FALHA! " + (totalEsperado - totalRegistrado) + " acessos foram perdidos.");
        }
    }
}