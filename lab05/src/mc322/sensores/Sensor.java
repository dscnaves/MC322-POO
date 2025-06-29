/*
* Sensor.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor, que é um componente dos robôs.
*/

package mc322.sensores;

import mc322.robo.Robo;

public abstract class Sensor {
    
    // Atributo de alcance do sensor
    protected static double raio;

    public Sensor(double raio) {
        this.raio = raio;
    }

    // Método abstrato para monitorar o ambiente
    public abstract void monitorar(Robo robo);

    // Método para verificar alcance de utilização do sensor
    public static boolean dentroDoAlcance(int xLixo, int yLixo, int zLixo, int xRobo, int yRobo, int zRobo){

        // Convertendo a variável raio para o número de quadrados em volta do robo
        int alcance = (int) Math.ceil(raio);

        // Verifica se o ponto (xLixo, yLixo, zLixo) está dentro do alcance do sensor
        if(xLixo >= xRobo - alcance && xLixo <= xRobo + alcance &&
           yLixo >= yRobo - alcance && yLixo <= yRobo + alcance &&
           zLixo >= zRobo - alcance && zLixo <= zRobo + alcance){
            return true;
        } else {
            return false;
        }
    }

    // Funções Getters e Setters
    public double getRaio() {
        return raio;
    }

    public void setRaio(double raio) {
        this.raio = raio;
    }
}
