public class Robo{
    //Atributos necessários para definir a classe Robo
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
        this.direcao = direcao;
        this.ambiente = ambiente;
    }

    // Função responsável pela movimentação do Robo
    public void mover(int deltaX, int deltaY){
        // Movimentação no eixo X primeiro
        int finalX = posicaoX + deltaX;
        int passoX = (deltaX > 0 ? 1 : -1);
        while (posicaoX != finalX) {
            int proximoX = posicaoX + passoX;
            // Verificar se a próxima posição está livre para seguir
            if (ambiente.posicaoLivre(proximoX, posicaoY)) {
                // Atualizar posição que o Robo deixou como livre
                ambiente.atualizarMapa(posicaoX, posicaoY, "_");
                // Atualizar posição do Robo e atualizar do mapa
                posicaoX = proximoX;
                ambiente.atualizarMapa(posicaoX, posicaoY, "&");
            }
            // Se a posição não estiver livre, Robo irá parar execução do caminhar em X
            else {
                System.out.println(nome + " encontrou obstaculo ao andar no eixo X e parou em (" + posicaoX + ", " + posicaoY + ")");
                break;
            }
        }
        
        // Movimentação no eixo Y 
        int finalY = posicaoY + deltaY;
        int passoY = (deltaY > 0 ? 1 : -1);
        while (posicaoY != finalY) {
            int proximoY = posicaoY + passoY;
            
            // Verificar se a próxima posição está livre para seguir
            if (ambiente.posicaoLivre(posicaoX, proximoY)) {
                
                // Atualizar posição que o Robo deixou como livre
                ambiente.atualizarMapa(posicaoX, posicaoY, "_");
                
                // Atualizar posição do Robo e atualizar do mapa
                posicaoY = proximoY;
                ambiente.atualizarMapa(posicaoX, posicaoY, "&");
            }
            
            // Se a posição não estiver livre, Robo irá parar execução do caminhar em Y
            else {
                System.out.println(nome + " encontrou obstaculo ao andar no eixo Y e parou em (" + posicaoX + ", " + posicaoY + ")");
                break;
            }
        }
    }

    // Função para identificar obstáculos ao longo do mapa nas 4 direções cartesinas
    public void identificarObstaculo(){
        System.out.println("Verificando obstaculos ao redor do robo " + nome + ":");

        // Checar cada uma das 4 direções
        checarPosicao(posicaoX + 1, posicaoY, "Leste");
        checarPosicao(posicaoX - 1, posicaoY, "Oeste");
        checarPosicao(posicaoX, posicaoY + 1, "Norte");
        checarPosicao(posicaoX, posicaoY - 1, "Sul");
    }

    // Função para checar os obstáculos em 1 direção cartesiana por vez
    private void checarPosicao(int x, int y, String direcao) {

        // Se a posição checada está fora do mapa
        if (!ambiente.dentroDosLimites(x, y, 0)) {
            System.err.println("- Fora do mapa ao " + direcao);
        } 
        
        // Se a posição checada contém um obstáculo
        else if (!ambiente.posicaoLivre(x, y)) {
            System.out.println("- Obstaculo ao " + direcao + " em (" + x + ", " + y + ")");
        }
        
        // Se a posição checada está livre
        else {
            System.out.println("- Sem obstaculo ao " + direcao);
        }
    }
    
    // Função responsável pela exibição da posição atual do Robo
    public void exibirPosicao(){
        System.out.println("Posicao atual do robo " + nome + ": (" + posicaoX + " , " + posicaoY + ")");
    }

    // Funções Getters e Setting
    public int getPosicaoX(){ return posicaoX; }
    public int getPosicaoY(){ return posicaoY; }
    public void setPosicaoX(int newx){ this.posicaoX = newx; }
    public void setPosicaoY(int newy){ this.posicaoY = newy; }

}
