package mc322.simulator.robos;

import mc322.simulator.Ambiente;
import mc322.simulator.Obstaculo;
import mc322.simulator.SensorMetal;
import mc322.simulator.TipoObstaculo;

public class RoboRastreador extends RoboTerrestre {

    private int tesouroX;
    private int tesouroY;
    private int tesouroZ;
    private int qtdeTesouro;

    public RoboRastreador(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.tesouroX = -1;
        this.tesouroY = -1;
        this.tesouroZ = -1;
        this.qtdeTesouro = 0;

        // Adiciona o Sensor de Metal
        this.adicionarSensor(new SensorMetal(3.0));
    }

    public void atualizarLocalizacaoTesouro(int x, int y, int z) {
        this.tesouroX = x;
        this.tesouroY = y;
        this.tesouroZ = z;
        System.out.println("📌 Tesouro detectado nas coordenadas: (" + x + ", " + y + ", " + z + ")");
    }

    public void classificarMetal(Obstaculo obstaculo) {
        if (obstaculo.getTipo() == TipoObstaculo.TESOURO) {
            System.out.println("💰 Tesouro identificado!");
            qtdeTesouro++;
        } else if (obstaculo.getTipo() == TipoObstaculo.LIXO) {
            System.out.println("🗑️ Metal lixo encontrado. Mensagem enviada ao RoboLimpeza.");
        } else {
            System.out.println("❌ Objeto encontrado não é metal conhecido.");
        }
    }

    public void checkCriptomoeda() {
        System.out.println("💎 Tesouros encontrados até agora: " + qtdeTesouro);
    }

    public int getQtdeTesouro() {
        return qtdeTesouro;
    }
}


