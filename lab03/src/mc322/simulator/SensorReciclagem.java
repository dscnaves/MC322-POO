package mc322.simulator;

import mc322.simulator.robos.RoboLimpeza;
import mc322.simulator.robos.Robo;

public class SensorReciclagem extends Sensor {
    public SensorReciclagem(double raio) {
        super(raio);
    }

    @Override
    public void monitorar(Robo robo) {

        // Verificar se robo é da subclasse RoboLimpeza (Outros tipos de ronôs não podem utilizar tal sensor)
        if (robo instanceof RoboLimpeza) {
            System.out.println("♻️ " + robo.getNome() + " ativou o sensor de reciclagem.");
        } else {
            System.out.println("SensorReciclagem não aplicável para este tipo de robô.");
        }
    }

    public void classificarLixo(String tipo) {
        switch (tipo) {
            case "papel":
                System.out.println("- Papel: Direcionando para compactação.");
                break;
            case "plastico":
                System.out.println("- Plastico: Direcionando para reciclagem de plastico.");
                break;
            case "metal":
                System.out.println("- Metal: Direcionando para fundição.");
                break;
            case "organico":
                System.out.println("- Orgânico: Direcionando para compostagem.");
                break;
            case "vidro":
                System.out.println("- Vidro: Direcionando para reciclagem de vidro.");
                break;
            default:
                System.out.println("- Tipo de lixo desconhecido. Armazenar para análise manual.");
        }
    }
}