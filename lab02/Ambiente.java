import java.util.ArrayList;

public class Ambiente {
    private int largura;
    private int altura;
    private int altitude;
    private ArrayList<Robo> robosAtivos;
    private ArrayList<Obstaculo> obstaculos;
    private String[][] mapa;

    public Ambiente(int altura, int largura, int altitude) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.robosAtivos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.mapa = new String[altura][largura];
        inicializarMapa();
    }

    private void inicializarMapa() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                mapa[i][j] = "_";
            }
        }
    }

    public void adicionarRobo(Robo robo) {
        if (dentroDosLimites(robo.getPosicaoX(), robo.getPosicaoY(), 0)) {
            robosAtivos.add(robo);
            atualizarMapa(robo.getPosicaoX(), robo.getPosicaoY(), "&");
        }
    }

    public void adicionarObstaculo(Obstaculo obstaculo) {
        if (dentroDosLimites(obstaculo.getX(), obstaculo.getY(), 0)) {
            obstaculos.add(obstaculo);
            atualizarMapa(obstaculo.getX(), obstaculo.getY(), "*");
        }
    }

    public boolean dentroDosLimites(int x, int y, int z) {
        return ((x >= 0 && x < largura) && 
               (y >= 0 && y < altura) && 
               (z >= 0 && z < altitude));
    }

    public boolean posicaoLivre(int x, int y) {
        if (!dentroDosLimites(x, y, 0)) return false;
        return mapa[y][x].equals("_");
    }

    public void atualizarMapa(int x, int y, String simbolo) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            mapa[y][x] = simbolo;
        }
    }

    public void exibirMapa() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getters and Setters
    public void setAmbiente(int newLargura, int newAltura, int newAltitude) {
        this.largura = newLargura;
        this.altura = newAltura;
        this.altitude = newAltitude;
        this.mapa = new String[newAltura][newLargura];
        inicializarMapa();
    }

    public int getAmbienteLargura() { return largura; }
    public int getAmbienteAltura() { return altura; }
    public int getAmbienteAltitude() { return altitude; }
    public String[][] getMapa() { return mapa; }
}