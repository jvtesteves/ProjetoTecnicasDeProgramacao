import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao8 {
    public static void main(String[] args) {
        String arquivoCSV = "src/csv/campeonato-brasileiro-full.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            reader.readLine();

            Map<Integer, String> partidaComMaisGols = new HashMap<>();
            int maiorNumeroGols = 0;

            while (true) {
                String linha = reader.readLine();
                if (linha == null) {
                    break;
                }

                String[] partes = linha.split(",");
                int placarMandante = Integer.parseInt(partes[12].replaceAll("\"", "").trim());
                int placarVisitante = Integer.parseInt(partes[13].replaceAll("\"", "").trim());
                int numeroGols = placarMandante + placarVisitante;

                if (numeroGols > maiorNumeroGols) {
                    maiorNumeroGols = numeroGols;
                    String mandante = partes[4].replaceAll("\"", "").trim();
                    String visitante = partes[5].replaceAll("\"", "").trim();
                    partidaComMaisGols.put(maiorNumeroGols, mandante + " " + placarMandante + " - " + placarVisitante + " " + visitante);
                }
            }

            String partidaMaisGols = partidaComMaisGols.get(maiorNumeroGols);
            System.out.println("A partida com mais gols foi: " + partidaMaisGols);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
