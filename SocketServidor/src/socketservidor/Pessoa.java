package socketservidor;


import java.util.List;

class Pessoa extends Thread {
    private final String tipo;
    private final Barca barca;
    private final int margemInicial;
    private final int margemFinal;
    private final List<String> resultados;

    Pessoa(String tipo, Barca barca, int margemInicial, int margemFinal, List<String> resultados) {
        this.tipo = tipo;
        this.barca = barca;
        this.margemInicial = margemInicial;
        this.margemFinal = margemFinal;
        this.resultados = resultados;
    }

    public void run() {
        atravessarRio();
    }

    private void atravessarRio() {
        resultados.add(tipo + " na margem " + margemInicial + " está esperando para atravessar.");

        try {
            barca.atravessar();
            resultados.add(tipo + " atravessando da margem " + margemInicial + " para a margem " + margemFinal + ".");
            sleep(100); // Simula o tempo de travessia

            resultados.add(tipo + " chegou na margem " + margemFinal + ".");
            barca.desembarcar();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Erro ao atravessar o rio: " + e.getMessage());
        }
    }
}
