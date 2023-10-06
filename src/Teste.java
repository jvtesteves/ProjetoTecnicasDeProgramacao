import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        String arquivoCSV = "C:/Users/joaov/Documents/Santander/TP/Aula/campeonato-brasileiro-full.csv";

        try (Scanner scanner = new Scanner(new File(arquivoCSV))) {
            scanner.nextLine(); // Ignora o cabeçalho

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(",");

                String dataString = dados[2].trim();
                String vencedor = dados[10].trim();

                // Verifica se o jogo ocorreu em 2008
                if (dataString.endsWith("/2008") && !vencedor.equals("null")) {
                    System.out.println("Jogo em 2008:");
                    System.out.println(linha);
                    break; // Para após encontrar o primeiro jogo em 2008
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
