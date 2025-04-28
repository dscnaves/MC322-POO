public class Obstaculo {

    // Atributos de posição do obstáculo dentro do mapa
    protected int x1;
    protected int y1;
    protected int z1;
    protected int x2;
    protected int y2;
    protected int altura; // Altura relativa a z1

    protected TipoObstaculo tipo;

    // Construtor
    public Obstaculo(int x1, int y1, int z1, int x2, int y2, int altura, TipoObstaculo tipo) {
        this.x1 = Math.min(x1, x2);
        this.y1 = Math.min(y1, y2);
        this.x2 = Math.max(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.z1 = Math.max(z1, 0);
        this.altura = Math.max(altura, 0);
        this.tipo = tipo;
    }

    // Método para verificar se o ponto (x,y,z) está dentro do obstáculo
    public boolean contemPonto(int x, int y, int z) {
        return (x >= x1 && x <= x2) &&
               (y >= y1 && y <= y2) &&
               (z >= z1 && z <= z1 + altura);
    }

    // Getters
    public int getX1() { return x1; }
    public int getY1() { return y1; }
    public int getX2() { return x2; }
    public int getY2() { return y2; }
    public int getZ1() { return z1; }
    public int getAltura() { return altura; }
    public TipoObstaculo getTipo() { return tipo; }

    // Setters
    public void setX1(int newX) { this.x1 = newX; }
    public void setY1(int newY) { this.y1 = newY; }
    public void setX2(int newX) { this.x2 = newX; }
    public void setY2(int newY) { this.y2 = newY; }
    public void setZ1(int newZ) { this.z1 = newZ; }
    public void setAltura(int newAltura) { this.altura = Math.max(newAltura, 0); }
}


