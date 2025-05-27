/*
* Ambiente.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

import java.util.ArrayList;

// Esta classe define a Central de Comunicação, que registra e exibe mensagens trocadas entre objetos
public class CentralComunicacao {

    // Atributo que armazena as mensagens trocadas
    private ArrayList<String> mensagens = new ArrayList<>();

    // Método para registrar uma mensagem enviada por um remetente
    public void registrarMensagem(String remetente, String msg) {
        mensagens.add(remetente + ": " + msg);
    }

    // Método para registrar uma mensagem recebida por um destinatário
    public void exibirMensagens() {
        System.out.println("Historico de mensagens:");
        for (String m : mensagens) {
            System.out.println(m);
        }
    }
}
