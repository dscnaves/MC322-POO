public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente){
        super(nome, posicaoX, posicaoY, direcao, ambiente);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY){
        // (Pega o menor valor entre o valor absoluto de deltaX e a velocidadeMaxima) multiplica pelo (sinal de movimentação de forma a sabermos o sentido da movimentação)
        deltaX = Math.min(Math.abs(deltaX), velocidadeMaxima) * (deltaX > 0 ? 1 : -1);
        deltaY = Math.min(Math.abs(deltaY), velocidadeMaxima) * (deltaY > 0 ? 1 : -1);
        
        super.mover(deltaX, deltaY);
    }

    // Função Getters 
    public int getVelocidadeMaxima(){
        return this.velocidadeMaxima;
    }
}

