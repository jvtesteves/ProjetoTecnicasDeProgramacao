import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao3 {
    public static void main(String[] args) {
        String arquivoGolsCSV = "C:/Users/joaov/Documents/Santander/TP/Aula/campeonato-brasileiro-gols.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoGolsCSV))) {
            String linha;
            reader.readLine(); // Ignora o cabeçalho

            Map<String, Integer> contagemGolsPorJogador = new HashMap<>();

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String jogador = partes[3].replaceAll("\"", "").trim(); // Nome do jogador

                // Verifica se o nome do jogador está presente e atualiza a contagem
                if (!jogador.isEmpty()) {
                    contagemGolsPorJogador.put(jogador, contagemGolsPorJogador.getOrDefault(jogador, 0) + 1);
                }
            }

            // Encontrando o jogador que fez mais gols
            Map.Entry<String, Integer> jogadorMaisGols = contagemGolsPorJogador.entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .orElse(null);

            if (jogadorMaisGols != null) {
                System.out.println("Jogador que mais fez gols: " +
                        jogadorMaisGols.getKey() + " com " + jogadorMaisGols.getValue() + " gols.");
            } else {
                System.out.println("Não foi possível determinar o jogador que mais fez gols.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

