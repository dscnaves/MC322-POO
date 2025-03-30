public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente){
        super(nome, posicaoX, posicaoY, direcao, ambiente);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY){
        // (Pega o menor valor entre o valor absoluto de deltaX e a velocidadeMaxima) multiplica pelo (sinal de movimentação de forma a sabermos o sentido da movimentação)
        int newdeltaX = Math.min(Math.abs(deltaX), velocidadeMaxima) * (deltaX > 0 ? 1 : -1);
        int newdeltaY = Math.min(Math.abs(deltaY), velocidadeMaxima) * (deltaY > 0 ? 1 : -1);
        
        // Verifica limite de velocidade máxima
        if (Math.abs(deltaX) > velocidadeMaxima) {
            System.out.println(nome + " tentou andar " + deltaX + " posicoes no eixo X, mas foi limitado a " + newdeltaX + " posicoes por sua veocidade máxima.");
        }

        if (Math.abs(deltaY) > velocidadeMaxima) {
            System.out.println(nome + " tentou andar " + deltaY + " posicoes no eixo Y, mas foi limitado a " + newdeltaY + " posicoes por sua veocidade máxima.");
        }

        super.mover(newdeltaX, newdeltaY);
    }

    // Funções Getters e Setters
    public int getVelocidadeMaxima(){
        return this.velocidadeMaxima;
    }

    public void setVelocidadeMaxima(int velocidadeMaxima){
        this.velocidadeMaxima = velocidadeMaxima;
    }
}

