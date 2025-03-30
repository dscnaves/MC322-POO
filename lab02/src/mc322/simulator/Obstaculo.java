public class Obstaculo {
    // Atributos de posição do obstáculo dentro do mapa
    private int x;
    private int y;

    // Função Construtora para inicializar atributos do Objeto Obstáculo
    public Obstaculo(int x, int y){
       this.x = x;
       this.y = y; 
    }

    // Gettings e Settings
    public int getX() {return x;}
    public int getY() {return y;}

    public void setX(int newX) {
        this.x = newX; 
    }
    public void setY(int newY) {
        this.y = newY;
    }
}
