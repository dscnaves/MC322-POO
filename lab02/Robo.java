public class Robo{
    //Atributoos necessários para definir a classe Robo
    protected String nome;
    protected int posicaoX;
    protected int posicaoY;
    protected String direcao;
    protected Ambiente ambiente;
    
    // Método construtor para inicialização dos atributos da classe Robo
    public Robo(String name, int x, int y, String direcao, Ambiente ambiente){
        this.nome = name;
        this.posicaoX = x;
        this.posicaoY = y;
        this.direcao = "Norte";
        this.ambiente = ambiente;
    }

    // Função responsável pela movimentação do Robo
    public void mover(int deltaX, int deltaY, String direcao){
        // Ajustar a direção a cada movimentação do robo
        if (deltaX > 0) this.direcao = "Leste";
        else if (deltaX < 0) this.direcao = "Oeste";
        else if (deltaY > 0) this.direcao = "Norte";
        else if (deltaY < 0) this.direcao = "Sul";

        // Calculando posições finais
        int finalX = posicaoX + deltaX;
        int finalY = posicaoY + deltaY;
        
        // Mover passo por passo
        while (posicaoX != finalX || posicaoY != finalY) {
            int stepX = Integer.compare(finalX, posicaoX);
            int stepY = Integer.compare(finalY, posicaoY);

            if (posicaoX != finalX) {
                moverPasso(stepX, 0);
            } else if (posicaoY != finalY) {
                moverPasso(0, stepY);
            }
        }
    }

    protected void moverPasso(int stepX, int stepY) {
        int newX = posicaoX + stepX;
        int newY = posicaoY + stepY;

        // Verificando se o Robo pode dar o próximo passo
        if (ambiente.posicaoLivre(newX, newY)) {
            // Marcar posição antiga como desocupada
            ambiente.atualizarMapa(posicaoX, posicaoY, "_");
            
            // Robo dá um passo
            posicaoX = newX;
            posicaoY = newY;
            
            // Atualizar posição do Robo no mapa
            ambiente.atualizarMapa(posicaoX, posicaoY, "&");

            System.out.println(nome + " moveu para (" + posicaoX + ", " + posicaoY + ")");
        } else {
            identificarObstaculo(newX, newY);
            contornarObstaculo(stepX, stepY);
        }
    }

    public void identificarObstaculo(){
        System.out.println(nome + " identificou um obstáculo em (" + x + ", " + y + ")");
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
