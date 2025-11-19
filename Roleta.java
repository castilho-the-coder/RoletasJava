import java.util.Random;

public class Roleta implements Runnable {

    private final int id;
    private final ContadorAcesso contador;
    private final int numPessoasParaPassar;
    private final Random rand = new Random();

    /**
     * roleta:
     * @param id identificador desta roleta
     * @param contador contador GERAL que será incrementado
     * @param numPessoasParaPassar quantas pessoas passarão por ESTA roleta
     */
    public Roleta(int id, ContadorAcesso contador, int numPessoasParaPassar) {
        this.id = id;
        this.contador = contador;
        this.numPessoasParaPassar = numPessoasParaPassar;
    }

    @Override
    public void run() {
        System.out.println("Roleta " + id + " foi ativada.");

        for (int i = 0; i < numPessoasParaPassar; i++) {
            contador.registrarPassagem();
            
            // simula o tempo que leva para uma pessoa passar
            try {
                // dorme por um tempo aleatório (0 a 10ms) para simular o acesso concorrente
                Thread.sleep(rand.nextInt(10)); 
            } catch (InterruptedException e) {
                System.err.println("Roleta " + id + " foi interrompida.");
                Thread.currentThread().interrupt();
            }
        }
        
        System.out.println(">>> Roleta " + id + " terminou de processar " + numPessoasParaPassar + " pessoas");
    }
}