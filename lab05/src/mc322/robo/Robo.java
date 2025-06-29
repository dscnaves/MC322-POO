/*
 * Robo.java
 * 
 * Última modificação: 01/06/2025
 * 
 * Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
*/
/*
 * Esta classe contém a estrutura de implementação de Robo de um
 * simulador de robôs, que representa uma classe base para todos
 * os robôs, com movimentação.
 */

 package mc322.robo; 

 import java.util.ArrayList;
 import mc322.ambiente.Ambiente;
 import mc322.ambiente.Entidade;
 import mc322.ambiente.Obstaculo;
 import mc322.ambiente.TipoEntidade;
 import mc322.exceptions.ColisaoException;
 import mc322.exceptions.RoboDesligadoException;
 import mc322.sensores.Sensor;
 
 public abstract class Robo implements Entidade {

    // Enumeração para representar os estados do Robo
    public enum EstadoRobo {
        LIGADO,
        DESLIGADO
    }

     //Atributos necessários para definir a classe Robo
     protected String id;
     protected int posicaoX;
     protected int posicaoY;
     protected int altitude;
     protected Ambiente ambiente;

     protected EstadoRobo estado;

     protected int bateria;
     
     private TipoEntidade tipoEntidade = TipoEntidade.ROBO;
     
     // Inclusão de sensores ao robo
     protected ArrayList<Sensor> sensores = new ArrayList<>();
     
     // Método construtor para inicialização dos atributos da classe Robo
     public Robo(String id, int x, int y, Ambiente ambiente, int altitude){
         this.id = id;
         this.posicaoX = x;
         this.posicaoY = y;
         this.ambiente = ambiente;
         this.altitude = altitude;
         this.bateria = 100;
         this.estado = EstadoRobo.DESLIGADO;
     }

     // Função para adicionar sensores ao robo
     public void adicionarSensor(Sensor s) {
         sensores.add(s);
     }

    // Função percorre o array de sensores do robo que chama o método executa a função proncipal de cada sensor
    public void usarSensores() {
        if (estado != EstadoRobo.LIGADO) {
            throw new RoboDesligadoException("Robo " + id + " está desligado e não pode usar sensores.");
        }
        for (Sensor s : sensores) {
            s.monitorar(this);
        }
     }

     public void consumirBateria(int consumo) {
        bateria -= consumo;
        if (bateria < 0) bateria = 0;
     }
 
     // Função responsável pela movimentação do Robo
     public void moverPara(int finalX, int finalY, int finalZ){
        if (estado != EstadoRobo.LIGADO) {
            throw new RoboDesligadoException("Robo " + id + " está desligado e não pode se mover.");
        }

         // Movimentação no eixo X
         int deltaX = finalX - posicaoX;
         int passoX = (deltaX > 0 ? 1 : -1);
         while (posicaoX != finalX) {
             int proximoX = posicaoX + passoX;
             if (ambiente.posicaoLivre(proximoX, posicaoY, altitude)) {
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.VAZIO;
                 posicaoX = proximoX;
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.ROBO;
             } else {
                 throw new ColisaoException("Robo " + id + " encontrou posição ocupada ao mover no eixo X: (" + proximoX + "," + posicaoY + "," + altitude + ")");
             }
         }
 
         // Movimentação no eixo Y
         int deltaY = finalY - posicaoY;
         int passoY = (deltaY > 0 ? 1 : -1);
         while (posicaoY != finalY) {
             int proximoY = posicaoY + passoY;
             if (ambiente.posicaoLivre(posicaoX, proximoY, altitude)) {
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.VAZIO;
                 posicaoY = proximoY;
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.ROBO;
             } else {
                 throw new ColisaoException("Robo " + id + " encontrou posição ocupada ao mover no eixo Y: (" + posicaoX + "," + proximoY + "," + altitude + ")");
             }
         }
 
         // Movimentação no eixo Z (altitude)
         int deltaZ = finalZ - altitude;
         int passoZ = (deltaZ > 0 ? 1 : -1);
         while (altitude != finalZ) {
             int proximoZ = altitude + passoZ;
             if (ambiente.dentroDosLimites(posicaoX, posicaoY, proximoZ) && ambiente.posicaoLivre(posicaoX, posicaoY, proximoZ)) {
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.VAZIO;
                 altitude = proximoZ;
                 ambiente.getMapa()[posicaoY][posicaoX][altitude] = TipoEntidade.ROBO;
             } else {
                throw new ColisaoException("Robo " + id + " encontrou posição ocupada ao mover no eixo Z: (" + posicaoX + "," + posicaoY + "," + proximoZ + ")");
             }
         }
     }
 
     // Função para identificar obstáculos ao longo do mapa nas 3 dimensões (cubo 3x3x3)
     public void identificarObstaculo(){
         System.out.println("Verificando obstaculos ao redor do robo " + id + ":");
         int raio = 1;
         boolean encontrou = false;
 
         for (int dx = -raio; dx <= raio; dx++) {
             for (int dy = -raio; dy <= raio; dy++) {
                 for (int dz = -raio; dz <= raio; dz++) {
                     if (dx == 0 && dy == 0 && dz == 0) continue;
                     int nx = posicaoX + dx;
                     int ny = posicaoY + dy;
                     int nz = altitude + dz;
                     if (ambiente.dentroDosLimites(nx, ny, nz)) {
                        for (Entidade ent : ambiente.getEntidades()) {
                            if (ent instanceof Obstaculo) {
                                Obstaculo obst = (Obstaculo) ent;
                                if (obst.contemPonto(nx, ny, nz)) {
                                    System.out.println("- Obstaculo detectado em (" + nx + ", " + ny + ", " + nz + ") - Tipo: " + obst.getTipo());
                                    encontrou = true;
                                }
                            }
                        }
                    }
                 }
             }
         }
 
         if (!encontrou) {
             System.out.println("- Nenhum obstaculo detectado nas proximidades.");
         }
     }

     public void ligar() {
        estado = EstadoRobo.LIGADO;
     }

     public void desligar() {
        estado = EstadoRobo.DESLIGADO;
     }

     // Método abstrato que deve ser implementado por subclasses para realizar tarefas específicas
     public abstract void executarTarefa();
 
     // Função responsável pela exibição da posição atual do Robo
     public void exibirPosicao(){
         System.out.println("Posicao atual do robo " + id + ": (" + posicaoX + " , " + posicaoY + " , " + altitude + ")");
     }
     
     @Override
     public TipoEntidade getTipo() { return tipoEntidade; }

     @Override
     public String getDescricao(){
        return "Robo " + this.id + " na posicao (" + posicaoX + ", " + posicaoY + ", " + altitude + ")";
     }

     @Override
     public char getRepresentacao(){
        return 'R';
     }

     // Funções Getters e Setting
     @Override
     public int getX(){ return posicaoX; }

     @Override
     public int getY(){ return posicaoY; }

     @Override
     public int getZ(){ return altitude; }

     public int getPosicaoX(){ return posicaoX; }
 
     public int getPosicaoY(){ return posicaoY; }
 
     public int getAltitude(){ return altitude; }
 
     public void setPosicaoX(int newx){ this.posicaoX = newx; }
 
     public void setPosicaoY(int newy){ this.posicaoY = newy; }
 
     public void setAltitude(int newz){ this.altitude = newz; }
 
     public Ambiente getAmbiente() {
        return ambiente;
     }
 
     public String getId() {
        return id;
     }

     public int getBateria() {
        return bateria;
     }

     public void setEstado(EstadoRobo estado) {
        this.estado = estado;
     }

     public EstadoRobo getEstado() {
        return estado;
     }     
 }
 