public class RoboCientista extends RoboAereo {
    private char letraCodificacao;

    public RoboCientista(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, char letraCodificacao, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.letraCodificacao = letraCodificacao;
    }

    public void decodificarMensagem(String mensagemCodificada) {
        System.out.println("*bip-bop-wu-wu* " + nome + " decodificando mensagem...");
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
