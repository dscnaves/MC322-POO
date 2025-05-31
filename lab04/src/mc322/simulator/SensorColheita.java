/*
* SensorColheita.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor de colheita.
*/

public class SensorColheita extends Sensor {

    // Construtor
    public SensorColheita(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de plantinhas prontas para colheita
    public void monitorar(Robo robo) {
        System.out.println("Robo " + robo.getId() + " ativou o Sensor de Colheita.");

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean plantinhaPronta = false;        

        // Verifica se há uma plantinha pronta para colheita dentro do alcance do sensor
        for (Obstaculo obst : ambiente.getObstaculos()) {
            // Verifica se o obstáculo é uma plantinha e se está dentro do alcance do sensor
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                // Verifica se a plantinha está pronta para colheita
                Plantinha plantinha = (Plantinha) obst;
                // Verifica se a plantinha está pronta para colheita
                if (plantinha.podeColher()) {
                    System.out.println("🌿 Plantinha " + plantinha.getTipo() + " pronta para colheita nas coordenadas (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")!");
                    plantinhaPronta = true;
                }
            }
        }        

        if (!plantinhaPronta) {
            System.out.println("Nenhuma plantinha pronta para colheita dentro do raio.");
        }
    }

    // Método para checar se há uma plantinha pronta para colheita dentro do alcance do sensor
    public Plantinha checarColheita(Robo robo) {

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        for (Obstaculo obst : ambiente.getObstaculos()) {
            // Verifica se o obstáculo é uma plantinha e se está dentro do alcance do sensor
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;

                // Verifica se a plantinha está pronta para colheita
                if (plantinha.podeColher()) {

                    // Retorna a plantinha pronta para colheita
                    return plantinha;
                }
            }
        }
        // Caso não encontre uma plantinha pronta para colheita, retorna null
        System.out.println("Nenhuma plantinha pronta para colheita encontrada.");
        return null;
    }
}


