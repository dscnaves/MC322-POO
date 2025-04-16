/*
 * RoboAereo.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboAereo de um
 * simulador de robôs, que representa uma classe para robôs que se movem
 * em X, Y e altitude (Z).
 */

package mc322.simulator.robos;

public class RoboAereo extends Robo {

    // Atributos necessários para definir a classe RoboAereo
    private int altitude;
    private int altitudeMaxima;

    // Método construtor para inicialização dos atributos da classe RoboAereo
    public RoboAereo(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, Ambiente ambiente){
        super(nome, posicaoX, posicaoY, direcao, ambiente);
        this.altitudeMaxima = altitudeMaxima;
        
        // Define altitude inicial do robo aereo
        if (altitude >= 0 && altitude <= altitudeMaxima 
        && altitude <= ambiente.getAmbienteAltitude()) {
            this.altitude = altitude;
        } else {
            this.altitude = 0;
            System.out.println("Robo " + nome + " com altitude inicial invalida. Altitude inicial definida como 0.");
        }
    }

    // Função que definirá o movimento de subida do robo
    public void subir(int metros){
        int newAltitude = altitude + metros;
        int limite = Math.min(altitudeMaxima, ambiente.getAmbienteAltitude());
        if (newAltitude <= limite){
            altitude = newAltitude;
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            metros = limite - altitude;
            altitude = limite;
            System.out.println("Altura maxima do robo ou do ambiente excedida!");
            System.out.println(nome + " subiu " + metros + " metros. Altitude atual: " + altitude);
        }
    }

    // Função que definirá o movimento de descida do robo
    public void descer(int metros) {
        int newAltitude = altitude - metros;
        if (newAltitude >= 0) {
            altitude = newAltitude;
            System.out.println(nome + " desceu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            altitude = 0;
            System.out.println("Altura minima do ambiente excedida!");
            System.out.println(nome + " atingiu o solo");
        }
    }

    // Funções Getters e Setters
    public int getAltitude() { return altitude; }

    public int getAltitudeMaxima() { return altitudeMaxima; }

    public void setAltitude(int newAltitude){
        this.altitude = newAltitude;
    }

    public void setAltitudeMaxima(int newAltitudeMaxima){
        this.altitudeMaxima = newAltitudeMaxima;
    }

}
