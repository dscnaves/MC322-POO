/*
 * Robo.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de Robo de um
 * simulador de robôs, que representa uma classe base para todos
 * os robôs, com movimentação.
 */

 import java.util.ArrayList;
 
 public class Robo{

     //Atributos necessários para definir a classe Robo
     protected String nome;
     protected int posicaoX;
     protected int posicaoY;
     protected int altitude;
     protected String direcao;
     protected Ambiente ambiente;
     
     // Inclusão de sensores ao robo
     protected ArrayList<Sensor> sensores = new ArrayList<>();
     
     // Método construtor para inicialização dos atributos da classe Robo
     public Robo(String name, int x, int y, String direcao, Ambiente ambiente, int altitude){
         this.nome = name;
         this.posicaoX = x;
         this.posicaoY = y;
         this.direcao = direcao;
         this.ambiente = ambiente;
         this.altitude = altitude;
     }
 
     public void adicionarSensor(Sensor s) {
         sensores.add(s);
     }
     
     public void usarSensores() {
         for (Sensor s : sensores) {
             s.monitorar(this);
         }
     }
 
     // Função responsável pela movimentação do Robo
     public void mover(int deltaX, int deltaY, int deltaZ){
         // Movimentação no eixo X
         int finalX = posicaoX + deltaX;
         int passoX = (deltaX > 0 ? 1 : -1);
         while (posicaoX != finalX) {
             int proximoX = posicaoX + passoX;
             if (ambiente.posicaoLivre(proximoX, posicaoY, altitude)) {
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                 posicaoX = proximoX;
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "🤖");
             } else {
                 System.out.println(nome + " encontrou obstaculo ou fim do ambiente ao andar no eixo X e parou em (" + posicaoX + ", " + posicaoY + ", " + altitude + ")");
                 break;
             }
         }
 
         // Movimentação no eixo Y
         int finalY = posicaoY + deltaY;
         int passoY = (deltaY > 0 ? 1 : -1);
         while (posicaoY != finalY) {
             int proximoY = posicaoY + passoY;
             if (ambiente.posicaoLivre(posicaoX, proximoY, altitude)) {
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                 posicaoY = proximoY;
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "🤖");
             } else {
                 System.out.println(nome + " encontrou obstaculo ou fim do ambiente ao andar no eixo Y e parou em (" + posicaoX + ", " + posicaoY + ", " + altitude + ")");
                 break;
             }
         }
 
         // Movimentação no eixo Z (altitude)
         int finalZ = altitude + deltaZ;
         int passoZ = (deltaZ > 0 ? 1 : -1);
         while (altitude != finalZ) {
             int proximoZ = altitude + passoZ;
             if (ambiente.dentroDosLimites(posicaoX, posicaoY, proximoZ) && ambiente.posicaoLivre(posicaoX, posicaoY, proximoZ)) {
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "_");
                 altitude = proximoZ;
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, "🤖");
             } else {
                 System.out.println(nome + " encontrou obstaculo ou fim do ambiente ao andar no eixo Z e parou em (" + posicaoX + ", " + posicaoY + ", " + altitude + ")");
                 break;
             }
         }
     }
 
     // Função para identificar obstáculos ao longo do mapa nas 3 dimensões (cubo 3x3x3)
     public void identificarObstaculo(){
         System.out.println("Verificando obstaculos ao redor do robo " + nome + ":");
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
                         for (Obstaculo obst : ambiente.getObstaculos()) {
                             if (obst.contemPonto(nx, ny, nz)) {
                                 System.out.println("- Obstaculo detectado em (" + nx + ", " + ny + ", " + nz + ") - Tipo: " + obst.getTipo());
                                 encontrou = true;
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
 
     // Função responsável pela exibição da posição atual do Robo
     public void exibirPosicao(){
         System.out.println("Posicao atual do robo " + nome + ": (" + posicaoX + " , " + posicaoY + " , " + altitude + ")");
     }
 
     // Funções Getters e Setting
     public int getPosicaoX(){ return posicaoX; }
 
     public int getPosicaoY(){ return posicaoY; }
 
     public int getAltitude(){ return altitude; }
 
     public void setPosicaoX(int newx){ this.posicaoX = newx; }
 
     public void setPosicaoY(int newy){ this.posicaoY = newy; }
 
     public void setAltitude(int newz){ this.altitude = newz; }
 
     public Ambiente getAmbiente() {
         return ambiente;
     }
 
     public String getNome() {
         return nome;
     }
 }
 