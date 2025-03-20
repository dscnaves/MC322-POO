public class RoboTerrestre extends Robo {
    private int velocidadeMaxima;

    public RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima){
        super(nome, posicaoX, posicaoY, direcao);
        this.velocidadeMaxima = velocidadeMaxima;
    }

    @Override
    public void mover(int deltaX, int deltaY, String direcao){
        if(Math.abs(deltaX) <= velocidadeMaxima && Math.abs(deltaY) <= velocidadeMaxima){
            super.mover(deltaX, deltaY, direcao);
            System.out.println("Movimento realizado com sucesso!");
        } else{
            System.out.println("Movimento excede a velocidade máxima!");
        }
    }

    public int getVelocidadeMaxima(){
        return this.velocidadeMaxima;
    }
}

