/*
* SensorPortal.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor de portal.
*/

package mc322.sensores;

import mc322.ambiente.Obstaculo;
import mc322.ambiente.Portal;
import mc322.ambiente.TipoObstaculo;
import mc322.robo.Robo;

public class SensorPortal extends Sensor {

    // Construtor
    public SensorPortal(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente em busca de portais
    public void monitorar(Robo robo) {
        System.out.println("Robo " + robo.getId() + " ativou o Sensor de Portal.");
        Portal portal = checkPortal(robo);

        // Verifica se encontrou um portal
        if (portal != null) {
            System.out.println("Portal detectado em (" + 
                portal.getX1() + "," + portal.getY1() + "," + portal.getZ1() + ")");
            System.out.println("Destino: (" + 
                portal.getDestinoX() + "," + portal.getDestinoY() + "," + portal.getDestinoZ() + ")");
        } else {
            System.out.println("Nenhum portal encontrado no alcance.");
        }
    }

    // Método para verificar se há um portal no alcance do sensor
    public Portal checkPortal(Robo robo) {
        
        // Percorre a lista de obstáculos do ambiente
        for (Obstaculo obst : robo.getAmbiente().getObstaculos()) {
            // Verifica se o obstáculo é um portal e se está dentro do alcance do sensor
            if (obst.getTipoObstaculo() == TipoObstaculo.PORTAL && 
                Sensor.dentroDoAlcance(obst.getX1(), obst.getY1(), obst.getZ1(), 
                                      robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                return (Portal) obst;
            }
        }
        return null;
    }
}


