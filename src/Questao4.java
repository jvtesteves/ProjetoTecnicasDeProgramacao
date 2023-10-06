import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao4 {
    public static void main(String[] args) {
        String arquivoGolsCSV = "C:/Users/joaov/Documents/Santander/TP/Aula/campeonato-brasileiro-gols.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoGolsCSV))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho do nosso arquivo csv

            Map<String, Integer> contagemGolsPenaltisPorJogador = new HashMap<>();

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String jogador = partes[3].replaceAll("\"", "").trim(); // Nome do jogador
                String tipoGol = partes[5].replaceAll("\"", "").trim(); // Tipo de gol

                // Verificação para saber se o gol é de Penalty
                if (tipoGol.equalsIgnoreCase("Penalty")) {
                    if (!jogador.isEmpty()) {
                        contagemGolsPenaltisPorJogador.put(jogador, contagemGolsPenaltisPorJogador.getOrDefault(jogador, 0) + 1);
                    }
                }
            }

            // Encontra o jogador que mais fez gols de penalti
            Map.Entry<String, Integer> jogadorMaisGolsPenaltis = contagemGolsPenaltisPorJogador.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElse(null);

            if (jogadorMaisGolsPenaltis != null) {
                System.out.println("Jogador que mais fez gols de pênaltis: " +
                        jogadorMaisGolsPenaltis.getKey() + " com " + jogadorMaisGolsPenaltis.getValue() + " gols de pênaltis.");
            } else {
                System.out.println("Não foi possível determinar o jogador que mais fez gols de pênaltis.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

