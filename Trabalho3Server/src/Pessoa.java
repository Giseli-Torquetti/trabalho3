
class Pessoa extends Thread {
    private final String tipo;
    private final Barca barca;
    private final int margemInicial;
    private final int margemFinal;

    Pessoa(String tipo, Barca barca, int margemInicial, int margemFinal) {
        this.tipo = tipo;
        this.barca = barca;
        this.margemInicial = margemInicial;
        this.margemFinal = margemFinal;
    }

    public void run() {
        atravessarRio();
    }

    private void atravessarRio() {
        System.out.println(tipo + " na margem " + margemInicial + " está esperando para atravessar.");

        try {
            barca.atravessar();
            System.out.println(tipo + " atravessando da margem " + margemInicial + " para a margem " + margemFinal + ".");
            sleep(1000); // Simula o tempo de travessia

            System.out.println(tipo + " chegou na margem " + margemFinal + ".");
            barca.desembarcar();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro ao atravessar o rio: " + e.getMessage());
        }
    }
}
