/*
 * Ambiente.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

import java.util.ArrayList;

/*
 * Esta classe contém a estrutura de implementação do Ambiente 
 * de um simulador de robôs, responsavel por definir o tamanho
 * do mesmo, possíveis obstáculos e robôs presentes.
 */
public class Ambiente {

    // Dimensões do ambiente
    private int largura;
    private int altura;
    private int altitude;

    // Lista que armazenará os robos existentes dentro desse ambiente
    private ArrayList<Robo> robosAtivos;

    // Lista que armazenará os obstáculos existentes dentro desse ambiente
    private ArrayList<Obstaculo> obstaculos;

    // Matriz que representará o plano terrestre do ambiente
    private String[][] mapa;

    // Método construtor
    public Ambiente(int altura, int largura, int altitude) {
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.robosAtivos = new ArrayList<>();
        this.obstaculos = new ArrayList<>();
        this.mapa = new String[altura][largura];
        inicializarMapa();
    }

    // Inicialização do mapa com todas as posições livres
    private void inicializarMapa() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                mapa[i][j] = "_";
            }
        }
    }

    // Método para adicionar o robo dentro do ambiente
    public void adicionarRobo(Robo robo) {
        if (dentroDosLimites(robo.getPosicaoX(), robo.getPosicaoY(), 0)) {
            robosAtivos.add(robo);
            atualizarMapa(robo.getPosicaoX(), robo.getPosicaoY(), "&");
        }
    }

    // Método para adicionar o obstáculo dentro do ambiente 
    public void adicionarObstaculo(Obstaculo obstaculo) {
        if (dentroDosLimites(obstaculo.getX(), obstaculo.getY(), 0)) {
            obstaculos.add(obstaculo);
            atualizarMapa(obstaculo.getX(), obstaculo.getY(), "*");
        }
    }

    // Função para verificar se determinada coordenada está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z) {
        return ((x >= 0 && x < largura) && 
               (y >= 0 && y < altura) && 
               (z >= 0 && z < altitude));
    }

    // Função para verificar se determinada coordenada está livre dentro dos limites do ambiente
    public boolean posicaoLivre(int x, int y) {
        if (!dentroDosLimites(x, y, 0)) return false;
        return mapa[y][x].equals("_");
    }

    // Função para atualizar mapa
    public void atualizarMapa(int x, int y, String simbolo) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            mapa[y][x] = simbolo;
        }
    }

    // Função para imprimir mapa
    public void exibirMapa() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < largura; j++) {
                System.out.print(mapa[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Getters e Setters
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