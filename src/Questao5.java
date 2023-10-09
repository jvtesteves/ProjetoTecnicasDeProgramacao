import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao5 {
    public static void main(String[] args) {
        String arquivoCSV = "src/csv/campeonato-brasileiro-gols.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            reader.readLine();

            Map<String, Integer> contagemGolsContraPorJogador = new HashMap<>();

            while (true) {
                String linha = reader.readLine();
                if (linha == null) {
                    break;
                }

                String[] partes = linha.split(",");
                String tipoGol = partes[5].replaceAll("\"", "").trim();

                if (tipoGol.equalsIgnoreCase("Gol Contra")) {
                    String jogador = partes[3].replaceAll("\"", "").trim();

                    contagemGolsContraPorJogador.put(jogador, contagemGolsContraPorJogador.getOrDefault(jogador, 0) + 1);
                }
            }

            String jogadorMaisGoleadorContra = null;
            int maxGolsContra = 0;

            for (Map.Entry<String, Integer> entry : contagemGolsContraPorJogador.entrySet()) {
                if (entry.getValue() > maxGolsContra) {
                    jogadorMaisGoleadorContra = entry.getKey();
                    maxGolsContra = entry.getValue();
                }
            }

            if (jogadorMaisGoleadorContra != null) {
                System.out.println("O jogador que mais fez gols contra é: " + jogadorMaisGoleadorContra);
                System.out.println("Número de gols contra: " + maxGolsContra);
            } else {
                System.out.println("Nenhum gol contra foi encontrado no arquivo.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
