import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Questao1 {
    public static void main(String[] args) {
        String arquivoCSV = "src/csv/campeonato-brasileiro-full.csv";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            String linha;
            reader.readLine();

            Map<String, Integer> contagemVitoriasPorTime = new HashMap<>();

            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                String dataString = partes[2].replaceAll("\"", "").trim();
                LocalDate data = LocalDate.parse(dataString, dateFormatter);

                if (data.getYear() == 2008) {
                    String vencedor = partes[10].replaceAll("\"", "").trim();

                    // Ignora o "-"
                    if (!vencedor.equals("-")) {
                        contagemVitoriasPorTime.put(vencedor, contagemVitoriasPorTime.getOrDefault(vencedor, 0) + 1);
                    }
                }
            }

            int maxVitorias = contagemVitoriasPorTime.values().stream()
                    .max(Integer::compareTo)
                    .orElse(0);

            // Encontra os times
            System.out.println("Times com mais vitórias em 2008:");
            for (Map.Entry<String, Integer> entry : contagemVitoriasPorTime.entrySet()) {
                if (entry.getValue() == maxVitorias) {
                    System.out.println(entry.getKey() + " - " + entry.getValue() + " vitórias.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
