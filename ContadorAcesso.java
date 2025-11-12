import java.util.concurrent.atomic.AtomicInteger;

/**
 * Representa o recurso compartilhado: o contador total de pessoas.
 * Esta classe é "thread-safe" graças ao uso de AtomicInteger.
 */
public class ContadorAcesso {

    // AtomicInteger é usado para garantir que operações de incremento
    // sejam atômicas, ou seja, não podem ser interrompidas no meio.
    private final AtomicInteger totalPessoas;

    public ContadorAcesso() {
        // Inicializa o contador em 0
        this.totalPessoas = new AtomicInteger(0);
    }

    /**
     * Registra a passagem de uma pessoa.
     * Esta operação é garantidamente segura contra concorrência.
     */
    public void registrarPassagem() {
        // incrementAndGet() atomicamente adiciona 1 ao valor atual
        // e retorna o novo valor.
        int novoTotal = totalPessoas.incrementAndGet();
        if (novoTotal % 100 == 0) {
            System.out.println("Total agora: " + novoTotal);
        }
    }

    /**
     * Retorna o número total de pessoas que passaram.
     */
    public int getTotalPessoas() {
        return totalPessoas.get();
    }
}