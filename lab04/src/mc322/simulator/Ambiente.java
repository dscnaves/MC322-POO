/*
* Ambiente.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
*/

import java.util.ArrayList;

public class Ambiente {

    // Dimensões do ambiente
    private int largura;
    private int profundidade;
    private int altitude;

    // Lista de robôs e obstáculos
    private ArrayList<Entidade> entidades;

    // Mapa tridimensional [y][x][z]
    private TipoEntidade[][][] mapa;

    // Construtor
    public Ambiente(int profundidade, int largura, int altitude) {
        this.largura = largura;
        this.profundidade = profundidade;
        this.altitude = altitude;
        this.entidades = new ArrayList<>();
        this.mapa = new TipoEntidade[profundidade][largura][altitude];
        inicializarMapa();
    }

    // Inicializa o mapa
    private void inicializarMapa() {
        for (int y = 0; y < profundidade; y++) {
            for (int x = 0; x < largura; x++) {
                for (int z = 0; z < altitude; z++) {
                    mapa[y][x][z] = TipoEntidade.VAZIO;
                }
            }
        }
    }

    // Adiciona uma entidade
    public void adicionarEntidade(Entidade e){
        entidades.add(e);

        if (e instanceof Obstaculo) {
            Obstaculo o = (Obstaculo) e;
            for (int y = o.getY1(); y <= o.getY2(); y++) {
                for (int x = o.getX1(); x <= o.getX2(); x++) {
                    mapa[y][x][o.getZ1()] = o.getTipo();
                }
            }
        } else {
            mapa[e.getX()][e.getY()][e.getZ()] = e.getTipo();
        }
    }

    // Remove uma entidade
    public void removerEntidade(Entidade e) {
        entidades.remove(e);

        if (e instanceof Obstaculo) {
            Obstaculo o = (Obstaculo) e;
            for (int y = o.getY1(); y <= o.getY2(); y++) {
                for (int x = o.getX1(); x <= o.getX2(); x++) {
                    mapa[y][x][o.getZ1()] = TipoEntidade.VAZIO;
                }
            }
        } else {
            mapa[e.getX()][e.getY()][e.getZ()] = TipoEntidade.VAZIO;
        }
    }

    // Verifica se coordenadas estão dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return (x >= 0 && x < largura) && (y >= 0 && y < profundidade) && (z >= 0 && z < altitude);
    }

    // Verifica se a posição está livre
    public boolean posicaoLivre(int x, int y, int z) {
        return dentroDosLimites(x, y, z) && mapa[y][x][z] == TipoEntidade.VAZIO;
    }

    // Exibe o mapa por andares
    public void exibirMapa() {

        // Exibe o mapa por andares
        for (int z = 0; z < altitude; z++) {
            System.out.println("\n[Mapa no andar Z=" + z + "]");
            for (int y = 0; y < profundidade; y++) {
                for (int x = 0; x < largura; x++) {
                    char c;
                    switch (mapa[y][x][z]) {
                        case VAZIO: 
                            c = '.';
                            break;
                        case ROBO:
                            c = 'R';
                            break;
                        case OBSTACULO:
                            c = 'O';
                            break;
                        default:
                            c = '?';
                    }
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }
    }

    // Exibe o mapa por andares
    public void exibirPlanoXMapa(int z) {
        System.out.println("\n[Mapa no andar Z=" + z + "]");
        for (int y = 0; y < profundidade; y++) {
            for (int x = 0; x < largura; x++) {
                System.out.print(mapa[y][x][z] + " ");
            }
            System.out.println();
        }
    }

    // Atualiza o mapa 
    public void atualizarMapa(int x, int y, int z, TipoEntidade tipo) {
        if (dentroDosLimites(x, y, z)) {
            mapa[y][x][z] = tipo;
        }
    }

    // Detecta colisões entre robôs e obstáculos
    public void detectarColisoes() {
        for (Entidade e : entidades) {
            if (e instanceof Robo) {
                Robo robo = (Robo) e;
                for (Entidade ent : entidades) {
                    if (ent instanceof Obstaculo) {
                        Obstaculo o = (Obstaculo) ent;
                        if (o.contemPonto(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                            System.out.println("COLISÃO: Robô " + robo.getNome() + " colidiu com um obstáculo.");
                        }
                    }
                }
            }
        }
    }

    public boolean estaOcupado(int x, int y, int z) {
        return mapa[x][y][z] != TipoEntidade.VAZIO;
    }

    // Getters
    public int getAmbienteLargura() { return largura; }
    public int getAmbienteProfundidade() { return profundidade; }
    public int getAmbienteAltitude() { return altitude; }
    public TipoEntidade[][][] getMapa() { return mapa; }
    public ArrayList<Entidade> getEntidades() { return entidades; }

    public ArrayList<Obstaculo> getObstaculos() {
        ArrayList<Obstaculo> obstaculos = new ArrayList<>();
        for (Entidade e : entidades) {
            if (e instanceof Obstaculo) {
                obstaculos.add((Obstaculo) e);
            }
        }
        return obstaculos;
    }
}