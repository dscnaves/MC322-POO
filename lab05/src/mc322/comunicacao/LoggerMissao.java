package mc322.comunicacao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerMissao {
    private PrintWriter writer;

    public LoggerMissao(String nomeArquivo) throws IOException {
        writer = new PrintWriter(new FileWriter(nomeArquivo, true)); // true = append
    }

    public void log(String mensagem) {
        writer.println(mensagem);
        writer.flush();
    }

    public void fechar() {
        writer.close();
    }
}
