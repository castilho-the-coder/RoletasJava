import java.util.Random;

/**
 * Representa uma única roleta (catraca) como uma thread.
 * Ela implementa a interface Runnable.
 */
public class Roleta implements Runnable {

    private final int id;
    private final ContadorAcesso contador;
    private final int numPessoasParaPassar;
    private final Random rand = new Random();

    /**
     * Construtor da Roleta.
     *
     * @param id Identificador desta roleta.
     * @param contador O contador GERAL (compartilhado) que será incrementado.
     * @param numPessoasParaPassar Quantas pessoas passarão por ESTA roleta.
     */
    public Roleta(int id, ContadorAcesso contador, int numPessoasParaPassar) {
        this.id = id;
        this.contador = contador;
        this.numPessoasParaPassar = numPessoasParaPassar;
    }

    @Override
    public void run() {
        System.out.println("Roleta " + id + " foi ativada.");
        
        long inicio = System.currentTimeMillis();
        
        for (int i = 0; i < numPessoasParaPassar; i++) {
            // Chama o método seguro do contador
            contador.registrarPassagem();
            
            // Simula o tempo que leva para uma pessoa passar
            try {
                // Dorme por um tempo aleatório (0 a 10ms) para
                // simular o acesso concorrente de forma mais realista.
                Thread.sleep(rand.nextInt(10)); 
            } catch (InterruptedException e) {
                System.err.println("Roleta " + id + " foi interrompida.");
                Thread.currentThread().interrupt();
            }
        }
        
        long duracao = System.currentTimeMillis() - inicio;
        System.out.println(">>> Roleta " + id + " terminou de processar " + numPessoasParaPassar + " pessoas em " + duracao + "ms");
    }
}