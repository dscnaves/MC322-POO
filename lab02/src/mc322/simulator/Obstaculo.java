/*
 * Obstaculo.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação dos Obstaculos 
 * de um simulador de robôs.
 */
public class Obstaculo {

    // Atributos de posição do obstáculo dentro do mapa
    private int x;
    private int y;

    // Função Construtora para inicializar atributos do Objeto Obstáculo
    public Obstaculo(int x, int y){
       this.x = x;
       this.y = y; 
    }

    // Getters e Setters
    public int getX() {return x;}

    public int getY() {return y;}

    public void setX(int newX) {
        this.x = newX; 
    }
    public void setY(int newY) {
        this.y = newY;
    }
    
}
