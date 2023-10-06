import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao2 {
    public static void main(String[] args) {
        String arquivoCSV = "C:/Users/joaov/Documents/Santander/TP/Aula/campeonato-brasileiro-full.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho do nosso arquivo csv

            Map<String, Integer> contagemJogosPorEstado = new HashMap<>();

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String estadoMandante = partes[14].replaceAll("\"", "").trim(); // Estado do time mandante

                // Verifica se o estado do mandante está presente e atualiza a contagem
                if (!estadoMandante.isEmpty()) {
                    contagemJogosPorEstado.put(estadoMandante, contagemJogosPorEstado.getOrDefault(estadoMandante, 0) + 1);
                }
            }

            // Encontra o estado com menos jogos
            Map.Entry<String, Integer> estadoMenosJogos = contagemJogosPorEstado.entrySet().stream()
                    .min(Map.Entry.comparingByValue())
                    .orElse(null);

            if (estadoMenosJogos != null) {
                System.out.println("Estado com menos jogos (mandante) entre 2003 e 2022: " +
                        estadoMenosJogos.getKey() + " com " + estadoMenosJogos.getValue() + " jogos.");
            } else {
                System.out.println("Não foi possível determinar o estado com menos jogos (mandante) entre 2003 e 2022.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

