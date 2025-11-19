import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAcesso {

    // atomicInteger é usado para garantir que operações de incrementos não possam ser interrompidas no meio.
    private final AtomicInteger totalPessoas;

    public ContadorAcesso() {
        // inicializa o contador em 0
        this.totalPessoas = new AtomicInteger(0);
    }

    // registra a passagem de uma pessoa.
    public void registrarPassagem() {
        // incrementAndGet() atomicamente adiciona 1 ao valor atual e retorna o novo valor.
        int novoTotal = totalPessoas.incrementAndGet();
        if (novoTotal % 50 == 0) {
            System.out.println("Total agora: " + novoTotal);
        }
    }

    
     // retorna o número total de pessoas que passaram.
    
    public int getTotalPessoas() {
        return totalPessoas.get();
    }
}