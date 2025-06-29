/*
* SensorSaude.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor de saúde, que é um tipo de sensor.
*/

package mc322.sensores;

import mc322.sensores.Sensor;
import mc322.robo.Robo;
import mc322.ambiente.Plantinha;
import mc322.ambiente.Ambiente;
import mc322.ambiente.Obstaculo;

public class SensorSaude extends Sensor {

    public SensorSaude(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de plantinhas doentes
    public void monitorar(Robo robo) {
        System.out.println("Robo " + robo.getId() + " ativou o Sensor de Saúde.");

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        boolean doenteEncontrado = false;

        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verifica se o obstáculo é uma plantinha e se está dentro do alcance do sensor
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;
                if (!plantinha.isSaudavel()) {
                    System.out.println("Plantinha " + plantinha.getTipo() + " doente detectada nas coordenadas (" + obst.x1 + ", " + obst.y1 + ", " + obst.z1 + ")!");
                    doenteEncontrado = true;
                }
            }
        }

        if (!doenteEncontrado) {
            System.out.println("Nenhuma plantinha doente detectada dentro do raio.");
        }
    }

    // Método para checar se há uma plantinha doente dentro do alcance do sensor
    public Plantinha checkup(Robo robo) {

        // Obtendo o ambiente e a posição do robo
        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verifica se o obstáculo é uma plantinha e se está dentro do alcance do sensor
            if (obst instanceof Plantinha && Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {
                Plantinha plantinha = (Plantinha) obst;

                // Verifica se a plantinha está doente
                if (!plantinha.isSaudavel()) {
                    // Retorna a plantinha doente
                    return plantinha;
                }
            }
        }        
        return null;
    }
}

