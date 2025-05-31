/*
 * RoboAereo.java
 * 
 * Última modificação: 28/04/2025
 * 
 * Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboAereo de um
 * simulador de robôs, que representa uma classe para robôs que se movem
 * em X, Y e altitude (Z).
 */
 
 public class RoboAereo extends Robo {
 
    // Atributos necessários para definir a classe RoboAereo
    protected int altitude;
    private int altitudeMaxima;

    // Implementação do método abstrato executarTarefa()
    @Override
    public void executarTarefa() {
        System.out.println("Inicializando execução de tarefa padrão do Robo Aereo...\n");
    }

    // Método construtor para inicialização dos atributos da classe RoboAereo
    public RoboAereo(String id, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, Ambiente ambiente){
        super(id, posicaoX, posicaoY, ambiente, 0);
        this.altitudeMaxima = altitudeMaxima;
        
        // Define altitude inicial do robo aereo
        if (altitude >= 0 && altitude <= altitudeMaxima 
        && altitude <= ambiente.getAmbienteAltitude()) {
            this.altitude = altitude;
            setAltitude(altitude);
        } else {
            this.altitude = 0;
            setAltitude(0);
            System.out.println("Robo " + id + " com altitude inicial invalida. Altitude inicial definida como 0.");
        }
    }

    // Função que definirá o movimento de subida do robo
    public void subir(int metros){
        int newAltitude = altitude + metros;
        int limite = Math.min(altitudeMaxima, ambiente.getAmbienteAltitude());
        if (newAltitude <= limite){
            altitude = newAltitude;
            setAltitude(altitude);
            System.out.println(id + " subiu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            metros = limite - altitude;
            altitude = limite;
            setAltitude(altitude);
            System.out.println("Altura maxima do robo ou do ambiente excedida!");
            System.out.println(id + " subiu " + metros + " metros. Altitude atual: " + altitude);
        }
    }

    // Função que definirá o movimento de descida do robo
    public void descer(int metros) {
        int newAltitude = altitude - metros;
        if (newAltitude >= 0) {
            altitude = newAltitude;
            setAltitude(altitude);
            System.out.println(id + " desceu " + metros + " metros. Altitude atual: " + altitude);
        } else {
            altitude = 0;
            setAltitude(0);
            System.out.println("Altura minima do ambiente excedida!");
            System.out.println(id + " atingiu o solo");
        }
    }

    // Funções Getters e Setters
    @Override
    public int getAltitude() { return altitude; }

    public int getAltitudeMaxima() { return altitudeMaxima; }

    @Override
    public void setAltitude(int newAltitude){
        this.altitude = newAltitude;
        super.setAltitude(newAltitude);
    }

    public void setAltitudeMaxima(int newAltitudeMaxima){
        this.altitudeMaxima = newAltitudeMaxima;
    }

}
