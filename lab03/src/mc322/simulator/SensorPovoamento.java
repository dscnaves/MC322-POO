public class SensorPovoamento extends Sensor {

    public SensorPovoamento(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {
        System.out.println("🛰️ " + robo.getNome() + " ativou o Sensor de Povoamento.");
        Planeta planeta = checkPovo(robo);
        
        if (planeta != null) {
            if (planetaJaPovoado(robo.getAmbiente(), planeta)) {
                System.out.println("👽 Planeta povoado detectado!");
            } else {
                System.out.println("🪐 Planeta desabitado detectado! Pode ser nomeado.");
            }
        } else {
            System.out.println("Nenhum planeta encontrado na posição atual.");
        }
    }

    public Planeta checkPovo(Robo robo) {
        for (Obstaculo obst : robo.getAmbiente().getObstaculos()) {
            if (obst.getTipo() == TipoObstaculo.PLANETA && 
                obst.contemPonto(robo.getPosicaoX(), robo.getPosicaoY(), robo.getAltitude())) {
                return (Planeta) obst;
            }
        }
        return null;
    }

    private boolean planetaJaPovoado(Ambiente ambiente, Planeta planeta) {
        for (Obstaculo obst : ambiente.getObstaculos()) {
            if (obst.getTipo() == TipoObstaculo.ALIENIGENA &&
                obst.getX1() >= planeta.getX1() && obst.getX2() <= planeta.getX2() &&
                obst.getY1() >= planeta.getY1() && obst.getY2() <= planeta.getY2()) {
                return true;
            }
        }
        return false;
    }
}


