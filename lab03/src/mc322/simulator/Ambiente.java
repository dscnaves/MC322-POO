package mc322.simulator;

import java.util.ArrayList;
import mc322.simulator.robos.Robo;

public class Ambiente {

    // Dimensões do ambiente
    private int largura;
    private int altura;
    private int altitude;

    // Lista de robôs e obstáculos
    private ArrayList<Robo> robosAtivos;
    private ArrayList<Obstaculo> obstaculos;

    // Mapa tridimensional [y][x][z]
    private String[][][] mapa;

    // Construtor
    public Ambiente(int altura, int largura, int altitude) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.robosAtivos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.mapa = new String[altura][largura][altitude];
        inicializarMapa();
    }

    // Inicializa o mapa
    private void inicializarMapa() {
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                for (int z = 0; z < altitude; z++) {
                    mapa[y][x][z] = "_";
                }
            }
        }
    }

    // Adiciona um robô no ambiente
    public void adicionarRobo(Robo robo) {
        robosAtivos.add(robo);
        if (dentroDosLimites(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
            atualizarMapa(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude(), "🤖");
        } else {
            System.out.println("Robo fora dos limites. Posicionado em (0,0,0).");
            atualizarMapa(0, 0, 0, "🤖");
        }
    }

    // Remove um robô
    public void removerRobo(Robo robo) {
        if (robosAtivos.remove(robo)) {
            atualizarMapa(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude(), "_");
        }
    }

    // Adiciona um obstáculo
    public void adicionarObstaculo(Obstaculo obstaculo) {
        obstaculos.add(obstaculo);
        for (int y = obstaculo.getY1(); y <= obstaculo.getY2(); y++) {
            for (int x = obstaculo.getX1(); x <= obstaculo.getX2(); x++) {
                for (int z = obstaculo.getZ1(); z <= obstaculo.getZ1() + obstaculo.getAltura(); z++) {
                    if (dentroDosLimites(x, y, z)) {
                        atualizarMapa(x, y, z, "*");
                    }
                }
            }
        }
    }

    // Verifica se coordenadas estão dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return (x >= 0 && x < largura) && (y >= 0 && y < altura) && (z >= 0 && z < altitude);
    }

    // Verifica se a posição está livre
    public boolean posicaoLivre(int x, int y, int z) {
        return dentroDosLimites(x, y, z) && mapa[y][x][z].equals("_");
    }

    // Atualiza o mapa com um símbolo
    public void atualizarMapa(int x, int y, int z, String simbolo) {
        if (dentroDosLimites(x, y, z)) {
            mapa[y][x][z] = simbolo;
        }
    }

    // Exibe o mapa por andares
    public void exibirMapa() {
        for (int z = 0; z < altitude; z++) {
            System.out.println("\n[Mapa no andar Z=" + z + "]");
            for (int y = 0; y < altura; y++) {
                for (int x = 0; x < largura; x++) {
                    System.out.print(mapa[y][x][z] + " ");
                }
                System.out.println();
            }
        }
    }

    // Detecta colisões entre robôs e obstáculos
    public void detectarColisoes() {
        for (Robo robo : robosAtivos) {
            for (Obstaculo obstaculo : obstaculos) {
                if (obstaculo.contemPonto(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                    System.out.println("❌ COLISÃO: Robô " + robo.getNome() + " colidiu com um obstáculo.");
                }
            }
        }
    }

    // Getters
    public int getAmbienteLargura() { return largura; }
    public int getAmbienteAltura() { return altura; }
    public int getAmbienteAltitude() { return altitude; }
    public String[][][] getMapa() { return mapa; }
    public ArrayList<Obstaculo> getObstaculos() { return obstaculos; }
}