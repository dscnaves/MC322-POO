import java.util.ArrayList;

public class Ambiente{
    // Atributos necessários para definir a classe Ambiente
    private int largura;
    private int altura;
    private int altitude;
    private ArrayList<Robo> robosAtivos;

    // Método construtor para inicialização dos atributos da classe Ambiente
    public Ambiente(int altura, int largura){
        this.largura = largura;
        this.altura = altura;
        this.altitude = altitude;
        this.robosAtivos = new ArrayList<>();
    }

    // Método para adicionar robôs ao ambiente
    public void adicionarRobo(Robo robo) {
        robosAtivos.add(robo);
    }

    // Função responsável por verificar se a posição de destino do robo está dentro dos limites do ambiente
    public boolean dentroDosLimites(int x, int y, int z){
        return ((x>=0 && x<=largura) && (y>=0 && y<=altura) && (z>=0 && z<altitude));
    }
    
    // Funções Getters e Setting
    public void setAmbiente(int newLargura, int newAltura, int newAltitude){
        this.largura = newLargura;
        this.altura = newAltura;
        this.altitude = newAltitude;
    }

    public int getAmbienteLargura(){
        return largura;
    }

    public int getAmbienteAltura(){
        return altura;
    }

    public int getAmbienteAltitude(){
        return altitude;
    }

}