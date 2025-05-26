/*
 * RoboLimpeza.java
 * 
 * Última modificação: 28/04/2025
 * 
 * Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboLimpeza de um 
 * simulador de robôs, que representa uma classe para robôs especializados
 * em coleta e compactação de lixo.
 */

 public class RoboLimpeza extends RoboTerrestre implements 
    Sensoreavel, Comunicavel, Diagnosticavel, Recarregavel, Autodesligavel {

    // Atributos específicos do RoboLimpeza
    private SensorReciclagem sensorReciclagem;

    // Construtor
    public RoboLimpeza(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, Ambiente ambiente) {
         super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
         this.sensorReciclagem = new SensorReciclagem(5.0);
         this.adicionarSensor(sensorReciclagem);
     }

     // Método para limpar lixo 
     public void limparLixo() {
         boolean lixoRemovido = false;

         // Verifica se há lixo na posição atual
         for (int i = 0; i < ambiente.getObstaculos().size(); i++) {
             Obstaculo obst = ambiente.getObstaculos().get(i);
             // Verifica se o obstáculo é um lixo e se está dentro do raio de alcance do sensor ()
             if (obst instanceof Lixo && Sensor.dentroDoAlcance(obst.getX1(), obst.getY1(), obst.getZ1(), posicaoX, posicaoY, altitude)) {
                 // Remove o lixo da lista de obstáculos
                 ambiente.removerEntidade(obst);
                 // Atualiza o mapa removendo o lixo
                 ambiente.atualizarMapa(posicaoX, posicaoY, altitude, TipoEntidade.VAZIO);
                 System.out.println("Lixo removido com sucesso na posição (" + posicaoX + ", " + posicaoY + ", " + altitude + ")!");
                 lixoRemovido = true;
                 consumirBateria(10);
                 desligarSeBateriaBaixa();
                 break;
             }
         }
 
         if (!lixoRemovido) {
             System.out.println("Nenhum lixo encontrado para limpar na posição atual.");
         }
     }

     // Método para classificar e limpar lixo
    public void classificarELimparLixo() {
        for (Obstaculo obst : ambiente.getObstaculos()) {
            
            // Verifica se o obstáculo é um lixo e se está dentro do raio de alcance do sensor ()
            if (obst instanceof Lixo) {
                if (Sensor.dentroDoAlcance(obst.x1, obst.y1, obst.z1, posicaoX, posicaoY, altitude)){
                    Lixo lixo = (Lixo) obst;
                    // Classifica o lixo
                    sensorReciclagem.classificarLixo(lixo.getTipoLixo());
                    // Remove o lixo da lista de obstáculos
                    ambiente.removerEntidade(obst);
                    // Atualiza o mapa removendo o lixo
                    ambiente.atualizarMapa(posicaoX, posicaoY, altitude, TipoEntidade.VAZIO);
                    System.out.println("Lixo classificado e removido da posição atual.");
                    consumirBateria(10);
                    desligarSeBateriaBaixa();
                    return;
                }                
            }
        }

        System.out.println("Nenhum lixo para classificar na posição atual.");
    }

    // Implementação de Sensoreavel
    @Override
    public void acionarSensores() {
        System.out.println(getId() + " acionando sensores de detecção de lixo...");
        usarSensores();
    }

    // Implementação de Comunicavel
    @Override
    public void enviarMensagem(Comunicavel destinatario, String mensagem) {
        System.out.println(getId() + " enviando mensagem: '" + mensagem + "' para robo " + ((Robo) destinatario).getId());
    }

    @Override
    public void receberMensagem(String mensagem) {
        System.out.println("Robo " + getId() + " recebeu mensagem: '" + mensagem + "'");
    }

    // Implementação de Diagnosticavel
    @Override
    public void realizarDiagnostico() {
        System.out.println("Robo " + getId() + " realizando diagnóstico:");
        System.out.println("- Bateria: " + bateria + "%");
        System.out.println("- Sensores: calibrados.");
        System.out.println("- Sistema: " + estado);
    }

    // Implementação de Recarregavel
    @Override
    public void recarregar() {
        this.bateria = 100;
        System.out.println("Robo " + getId() + " recarregou totalmente.");
    }

    // Implementação de AutoDesligavel
    @Override
    public void desligarSeBateriaBaixa() {
        if (bateria <= 10) {
            this.estado = EstadoRobo.DESLIGADO;
            System.out.println("Robo " + getId() + " esta com bateria muito baixa e foi desligado automaticamente.");
        }
    }
 }
 
 
 