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

package mc322.simulator;

public class Obstaculo {

    // Atributos de posição do obstáculo dentro do mapa
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    /**
     * @param x1 Coordenada X do canto superior esquerdo
     * @param y1 Coordenada Y do canto superior esquerdo
     * @param x2 Coordenada X do canto inferior direito
     * @param y2 Coordenada Y do canto inferior direito
     * @param tipo Tipo de obstáculo (não pode ser null)
     */

    // Composição
    private TipoObstaculo tipo;
    private int alturaPadrao;

    // Função Construtora para inicializar atributos do Objeto Obstáculo
    public Obstaculo(int x1, int y1, int x2, int y2, TipoObstaculo tipo){
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.tipo = tipo;
        this.alturaPadrao = tipo.getAlturaPadrao();
    }

    // Métodos de verificação se um determinada coordenada (x,y) está dentro da região obstáculo
    public boolean contemPonto(int x, int y) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    // Getters e Setters
    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public TipoObstaculo getTipo() { return tipo; }
    

    public void setX1(int newX) {this.x1 = newX;}
    public void setX2(int newX) {this.x2 = newX;}
    public void setY1(int newY) {this.y1 = newY;}
    public void setY2(int newY) {this.y2 = newY;}
    public void setAltura(int altura) { 
        this.alturaPadrao = Math.max(altura, 0); 
    }

    //Acho que não precisa pq n queremos mudar o tipo do obstáculo
    //public void setTipo(TipoObstaculo tipo) {this.tipo = tipo;}  

    //Depois me fala qual dos dois ficou melhor
    // public int getX(String qualX) {
    //     switch(qualX){
    //         case "x1":
    //             return x1;
    //         case "x2":
    //             return x2;
    //         default:
    //         System.out.println("Valor inválido de acesso ao X de obstáculo");
    //             return -1;
    //     }
    // }

    // public int gety(String qualY) {
    //     switch(qualY){
    //         case "y1":
    //             return y1;
    //         case "y2":
    //             return y2;
    //         default:
    //         System.out.println("Valor inválido de acesso ao Y de obstáculo");
    //             return -1;
    //     }
    // }
}
