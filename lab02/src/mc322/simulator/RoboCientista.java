public class RoboCientista extends RoboAereo {
    private char letraCodificacao;

    // Método construtor
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
