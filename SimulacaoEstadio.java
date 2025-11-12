import java.util.ArrayList;
import java.util.List;

/**
 * Classe principal para executar a simulação.
 * Ela cria e gerencia todas as threads (Roletas).
 */
public class SimulacaoEstadio {

    // --- PARÂMETROS DA SIMULAÇÃO ---
    // Você pode alterar estes valores
    
    // Define a quantidade de roletas que o estádio possui
    private static final int NUM_ROLETAS = 8; 
    
    // Define quantas pessoas passarão por CADA roleta
    private static final int PESSOAS_POR_ROLETA = 1000;
    // ---------------------------------

    public static void main(String[] args) {
        
        // 1. Cria o ÚNICO recurso compartilhado
        ContadorAcesso contadorGeral = new ContadorAcesso();

        // Lista para guardar as threads e poder esperar por elas
        List<Thread> threads = new ArrayList<>();

        System.out.println("Iniciando simulação com " + NUM_ROLETAS + " roletas.");
        System.out.println("Cada roleta processará " + PESSOAS_POR_ROLETA + " pessoas.");
        System.out.println("Total esperado: " + (NUM_ROLETAS * PESSOAS_POR_ROLETA));
        System.out.println("-------------------------------------------------");
        
        // 2. Cria e inicia todas as threads (Roletas)
        for (int i = 0; i < NUM_ROLETAS; i++) {
            // Cria o Runnable
            Roleta roleta = new Roleta(i, contadorGeral, PESSOAS_POR_ROLETA);
            
            // Cria a Thread a partir do Runnable
            Thread threadRoleta = new Thread(roleta);
            
            // Adiciona na lista e inicia
            threads.add(threadRoleta);
            threadRoleta.start();
        }

        // 3. Espera todas as threads terminarem
        // Isso é crucial para que a contagem final só seja
        // exibida após todas as roletas fecharem.
        for (Thread t : threads) {
            try {
                t.join(); // O método join() bloqueia a thread 'main'
                          // até que a thread 't' termine sua execução.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 4. Exibe o resultado final
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