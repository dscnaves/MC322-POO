package mc322.sistemas;

import java.util.ArrayList;

public class ModuloComunicacao {

    private ArrayList<String> mensagens;

    public ModuloComunicacao() {
        mensagens = new ArrayList<>();
    }

    public void enviarMensagem(String mensagem) {
        System.out.println("Enviando mensagem: " + mensagem);
        mensagens.add("Enviada: " + mensagem);
    }

    public void receberMensagem(String mensagem) {
        System.out.println("Mensagem recebida: " + mensagem);
        mensagens.add("Recebida: " + mensagem);
    }

    public ArrayList<String> getHistoricoMensagens() {
        return mensagens;
    }
}
