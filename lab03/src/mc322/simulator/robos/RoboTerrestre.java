/*
 * RoboTerrestre.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboTerrestre de um
 * simulador de robôs, que representa uma classe para robôs que se movem
 * no plano XY com velocidade máxima limitada.
 */

 package mc322.simulator.robos;
 import mc322.simulator.Ambiente;
 
 public class RoboTerrestre extends Robo {
 
     // Atributos necessários para definir a classe RoboTerrestre
     private int velocidadeMaxima;
 
     // Método construtor para inicialização dos atributos da classe RoboTerrestre
     public RoboTerrestre(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente){
         super(nome, posicaoX, posicaoY, direcao, ambiente,0);
         this.velocidadeMaxima = velocidadeMaxima;
     }
 
     @Override
     public void mover(int deltaX, int deltaY, int deltaZ){
         // Movimentação no eixo Z não é permitida para robôs terrestres
         deltaZ = 0;
 
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
 
         super.mover(newdeltaX, newdeltaY, deltaZ);
     }
 
     // Funções Getters e Setters
     public int getVelocidadeMaxima(){
         return this.velocidadeMaxima;
     }
 
     public void setVelocidadeMaxima(int velocidadeMaxima){
         this.velocidadeMaxima = velocidadeMaxima;
     }
     
 }
 
 