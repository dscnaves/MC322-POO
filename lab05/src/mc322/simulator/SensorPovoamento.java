/*
* SensorPovoamento.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor de povoamento, que é um tipo de sensor.
*/

public class SensorPovoamento extends Sensor {

    public SensorPovoamento(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente
    public void monitorar(Robo robo) {
        System.out.println("Robo " + robo.getId() + " ativou o Sensor de Povoamento.");
        Planeta planeta = roboDentroPlaneta(robo);

        // Verifica se o planeta está povoado ou não
        if (planeta != null) {
            
            // Verifica se o planeta está povoado ou não
            if (planetaJaPovoado(robo.getAmbiente(), planeta)) {
                System.out.println("Planeta povoado detectado!");
            } else {
                System.out.println("Planeta desabitado detectado! Pode ser nomeado.");
            }
        } else {
            System.out.println("Nenhum planeta encontrado na posição atual.");
        }
    }

    // Método para verificar se o robo está dentro de um planeta
    public Planeta roboDentroPlaneta(Robo robo) {
        for (Obstaculo obst : robo.getAmbiente().getObstaculos()) {
            
            // Verifica se o obstáculo é um planeta e se contém a posição do robô
            if (obst.getTipoObstaculo() == TipoObstaculo.PLANETA && 
                obst.contemPonto(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                return (Planeta) obst;
            }
        }
        return null;
    }

    // Método para verificar se o planeta já está povoado
    public boolean planetaJaPovoado(Ambiente ambiente, Planeta planeta) {
        for (Obstaculo obst : ambiente.getObstaculos()) {
            
            // Verifica se o obstáculo é um alienígena e se está dentro do planeta
            if (obst.getTipoObstaculo() == TipoObstaculo.ALIENIGENA &&
                obst.getX1() >= planeta.getX1() && obst.getX2() <= planeta.getX2() &&
                obst.getY1() >= planeta.getY1() && obst.getY2() <= planeta.getY2()) {
                return true;
            }
        }
        return false;
    }
}


