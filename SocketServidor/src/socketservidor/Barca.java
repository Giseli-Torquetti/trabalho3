package socketservidor;

import java.util.concurrent.Semaphore;

class Barca {
    private final Semaphore semaphore;

    Barca(int permits) {
        semaphore = new Semaphore(permits);
    }

    void atravessar() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro ao atravessar o rio: " + e.getMessage());
        }
    }

    void desembarcar() {
        semaphore.release();
    }
}