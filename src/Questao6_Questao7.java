import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Questao6_Questao7 {
    public static void main(String[] args) {
        String arquivoCSV = "src/csv/campeonato-brasileiro-cartoes.csv";

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivoCSV))) {
            reader.readLine();

            Map<String, Integer> contagemCartoesAmarelosPorJogador = new HashMap<>();
            Map<String, Integer> contagemCartoesVermelhosPorJogador = new HashMap<>();

            while (true) {
                String linha = reader.readLine();
                if (linha == null) {
                    break;
                }

                String[] partes = linha.split(",");
                String cartao = partes[3].replaceAll("\"", "").trim();
                String jogador = partes[4].replaceAll("\"", "").trim();

                if (cartao.equalsIgnoreCase("Amarelo")) {
                    contagemCartoesAmarelosPorJogador.put(jogador, contagemCartoesAmarelosPorJogador.getOrDefault(jogador, 0) + 1);
                } else if (cartao.equalsIgnoreCase("Vermelho")) {
                    contagemCartoesVermelhosPorJogador.put(jogador, contagemCartoesVermelhosPorJogador.getOrDefault(jogador, 0) + 1);
                }
            }

            String jogadorMaisRecebedorCartoesAmarelos = encontrarJogadorMaisRecebedor(contagemCartoesAmarelosPorJogador);

            String jogadorMaisRecebedorCartoesVermelhos = encontrarJogadorMaisRecebedor(contagemCartoesVermelhosPorJogador);

            // Exibe os resultados
            System.out.println("O jogador que mais recebeu cartões amarelos é: " + jogadorMaisRecebedorCartoesAmarelos);
            System.out.println("Número de cartões amarelos recebidos: " + contagemCartoesAmarelosPorJogador.get(jogadorMaisRecebedorCartoesAmarelos));

            System.out.println("O jogador que mais recebeu cartões vermelhos é: " + jogadorMaisRecebedorCartoesVermelhos);
            System.out.println("Número de cartões vermelhos recebidos: " + contagemCartoesVermelhosPorJogador.get(jogadorMaisRecebedorCartoesVermelhos));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encontrarJogadorMaisRecebedor(Map<String, Integer> contagemCartoesPorJogador) {
        String jogadorMaisRecebedor = null;
        int maxCartoes = 0;

        for (Map.Entry<String, Integer> entry : contagemCartoesPorJogador.entrySet()) {
            if (entry.getValue() > maxCartoes) {
                jogadorMaisRecebedor = entry.getKey();
                maxCartoes = entry.getValue();
            }
        }

        return jogadorMaisRecebedor;
    }
}
