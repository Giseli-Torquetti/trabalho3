package socketservidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MissionariosCanibaisServidorSocket {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6789);
            System.out.println("Servidor aguardando conexões...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão recebida de " + clientSocket.getInetAddress());

                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                String received = input.readLine();
                String[] params = received.split(",");

                int missionarios = Integer.parseInt(params[0].trim());
                int canibais = missionarios;
                int vagasBarca = 2;

                Barca barca = new Barca(vagasBarca);

                List<String> resultados = new ArrayList<>();

                Thread[] missionariosThreads = new Thread[missionarios];
                Thread[] canibaisThreads = new Thread[canibais];

                // Criação e inicialização das threads representando missionários e canibais
                for (int i = 0; i < missionarios; i++) {
                    missionariosThreads[i] = new Pessoa("Missionário " + (i + 1), barca, 0, 1, resultados);
                    missionariosThreads[i].start();
                }

                for (int i = 0; i < canibais; i++) {
                    canibaisThreads[i] = new Pessoa("Canibal " + (i + 1), barca, 0, 1, resultados);
                    canibaisThreads[i].start();
                }

                // Aguarda o término das threads
                for (Thread missionario : missionariosThreads) {
                    try {
                        missionario.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Erro ao aguardar a thread: " + e.getMessage());
                    }
                }
                for (Thread canibal : canibaisThreads) {
                    try {
                        canibal.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.out.println("Erro ao aguardar a thread: " + e.getMessage());
                    }
                }

                // Envia resultados para o cliente
                for (String resultado : resultados) {
                    output.println(resultado);
                }

                // Fecha conexão com o cliente
                output.println("FIM");
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
