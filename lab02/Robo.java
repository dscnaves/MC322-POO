public class Robo{
    //Atributoos necessários para definir a classe Robo
    private String nome;
    private int posicaoX;
    private int posicaoY;
    private String direcao;
    
    // Método construtor para inicialização dos atributos da classe Robo
    public Robo(String name, int x, int y, String direcao){
        this.nome = name;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = "Norte";
    }

    // Função responsável pela movimentação do Robo
    public void mover(int deltaX, int deltaY, String direcao){
        this.posicaoX += deltaX;
        this.posicaoY += deltaY;
        this.direcao = direcao;
    }

    public void identificarObstaculo(){

    }
    
    // Função responsável pela exibição da posição atual do Robo
    public void exibirPosicao(){
        System.out.println("Posicao atual do robo" + nome + ": (" + posicaoX + " , " + posicaoY + ")");
    }

    // Funções Getters e Setting
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
