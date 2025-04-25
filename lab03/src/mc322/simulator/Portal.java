package mc322.simulator;

public class Portal extends Obstaculo {

    private int destinoX;
    private int destinoY;
    private int destinoZ;

    public Portal(int x1, int y1, int z1, int x2, int y2, int altura, int destinoX, int destinoY, int destinoZ) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PORTAL);
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.destinoZ = destinoZ;
    }

    public int getDestinoX() {
        return destinoX;
    }

    public int getDestinoY() {
        return destinoY;
    }

    public int getDestinoZ() {
        return destinoZ;
    }

    public void setDestinoX(int destinoX) {
        this.destinoX = destinoX;
    }

    public void setDestinoY(int destinoY) {
        this.destinoY = destinoY;
    }

    public void setDestinoZ(int destinoZ) {
        this.destinoZ = destinoZ;
    }
}

