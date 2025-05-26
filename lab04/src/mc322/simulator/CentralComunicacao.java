
import java.util.ArrayList;

public class CentralComunicacao {
    private ArrayList<String> mensagens = new ArrayList<>();

    public void registrarMensagem(String remetente, String msg) {
        mensagens.add(remetente + ": " + msg);
    }

    public void exibirMensagens() {
        System.out.println("Historico de mensagens:");
        for (String m : mensagens) {
            System.out.println(m);
        }
    }
}
