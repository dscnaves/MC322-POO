/*
 * RoboCientista.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboCientista de um 
 * simulador de robôs, que representa uma classe para robôs capazes de 
 * decodificar mensagens com base em letra-chave removível.
 */
public class RoboCientista extends RoboAereo {

    // Atributo necessário para definir a classe RoboCientista
    private char letraCodificacao;

    // Método construtor para inicialização dos atributos da classe RoboCientista
    public RoboCientista(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, char letraCodificacao, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.letraCodificacao = letraCodificacao;
    }

    // Função para decodificar uma mensagem na língua da letra codificada
    public void decodificarMensagem(String mensagemCodificada) {
        System.out.println("*bip-bop-wu-wu* " + nome + " decodificando mensagem...");

        // Retirar letra codificada da palavra recebida
        System.out.println("Mensagem decodificada: " + mensagemCodificada.replaceAll(Character.toString(letraCodificacao), ""));
        System.out.println("Mensagem decodificada com sucesso! *wu-wu-bip-bop*");
    }

    // Funções Getters e Setters
    public char getLetraCodificacao() {
        return letraCodificacao;
    }

    public void setLetraCodificacao(char newLetraCodificacao) {
        this.letraCodificacao = newLetraCodificacao;
    }
    
}
