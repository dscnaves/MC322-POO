public class Robo{
    private String nome;
    private int posicaoX;
    private int posicaoY;

    public Robo(String name, int x, int y){
        this.nome = name;
        this.posicaoX = x;
        this.posicaoY = y;
    }

    public void mover(int deltaX, int deltaY){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
    }
    
    public void exibirPosicao(){
        System.out.println("Posicao atual do robo" + nome + ": (" + posicaoX + " , " + posicaoY + ")");
    }

    public int getPosicaoX(){
        return posicaoX;
    }

    public int getPosicaoY(){
        return posicaoY;
    }

    public void setPosicaoX(int newx){
        this.posicaoX = newx;
    }
    
    public void setPosicaoY(int newy){
        this.posicaoY = newy; 
    }
}