/*
* SensorMetal.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um sensor de metal.
*/

public class SensorMetal extends Sensor {

    public SensorMetal(double raio) {
        super(raio);
    }

    @Override

    // Método para monitorar o ambiente e detectar metais
    public void monitorar(Robo robo) {
        if (!(robo instanceof RoboRastreador)) {
            System.out.println("SensorMetal somente aplicável a RoboRastreador.");
            return;
        }

        System.out.println(robo.getId() + " ativou o Sensor de Metal.");

        Ambiente ambiente = robo.getAmbiente();
        int xRobo = robo.getPosicaoX();
        int yRobo = robo.getPosicaoY();
        int zRobo = robo.getAltitude();

        int alcance = (int) Math.ceil(raio);
        boolean encontrado = false;

        // Verifica cada obstáculo no ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {

            // Verifica se o obstáculo é um metal e se está dentro do alcance do sensor
            if (Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, xRobo, yRobo, zRobo)) {

                // Verifica se o obstáculo é um tesouro ou lixo
                if (obst.getTipoObstaculo() == TipoObstaculo.TESOURO || obst.getTipoObstaculo() == TipoObstaculo.LIXO) {

                    // Atualiza a localização do tesouro e classifica o metal
                    ((RoboRastreador) robo).atualizarLocalizacaoTesouro(obst.x1, obst.y1, obst.z1);
                    ((RoboRastreador) robo).classificarMetal(obst);
                    encontrado = true;
                }
            }
        }

        // Se nenhum metal foi encontrado, informar ao usuário
        if (!encontrado) {
            System.out.println("Nenhum metal detectado dentro do raio.");
        }
    }

    // Criar função para detectar tesouros
    public Obstaculo detectorTesouros(Robo robo){
        if (!(robo instanceof RoboRastreador)) {
            System.out.println("Este sensor só funciona com RoboRastreador");
            return null;
        }
        
        
        Ambiente ambiente = robo.getAmbiente();
        boolean tesouroEncontrado = false;

        // Verifica cada obstáculo no ambiente
        for (Obstaculo obst : ambiente.getObstaculos()) {
            // Verifica se o obstáculo é um tesouro e se está dentro do alcance do sensor
            if (obst.getTipoObstaculo() == TipoObstaculo.TESOURO) {
                // Verifica se o obstáculo é um tesouro e se está dentro do alcance do sensor
                if (Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())){
                    tesouroEncontrado = true;
                    return obst;
                }
            }
        } 
        if (!tesouroEncontrado){
            System.out.println("Não há tesouro para extrair.");
            return null;
        }
        return null;
    }

}