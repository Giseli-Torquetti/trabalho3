public class MissionariosCanibaisConcorrente {
    public static void main(String[] args) {
        Barca barca = new Barca(2);

        Thread[] missionarios = new Thread[]{
                new Pessoa("Missionário 1", barca, 0, 1),
                new Pessoa("Missionário 2", barca, 0, 1),
                new Pessoa("Missionário 3", barca, 0, 1)
        };

        Thread[] canibais = new Thread[]{
                new Pessoa("Canibal 1", barca, 0, 1),
                new Pessoa("Canibal 2", barca, 0, 1),
                new Pessoa("Canibal 3", barca, 0, 1)
        };

        // Inicializa as threads dos missionários
        for (Thread missionario : missionarios) {
            missionario.start();
        }

        // Inicializa as threads dos canibais
        for (Thread canibal : canibais) {
            canibal.start();
        }
    }
}