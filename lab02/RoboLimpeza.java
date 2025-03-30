public class RoboLimpeza extends RoboTerrestre{
    private int pesoMaximoLixo;

    public RoboLimpeza(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, int pesoMaximoLixo, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.pesoMaximoLixo = pesoMaximoLixo;
    }
    
    public void compactarLixo(int pesoLixo) {
        if (pesoLixo <= pesoMaximoLixo) {
            int pesoLixoCompactado = Math.max(pesoLixo, 0);
            System.out.println("Robo " + nome + " compactou " + pesoLixoCompactado + "kg de lixo!");
        } else {
            System.out.println("Compactacao nao realizada por excesso de lixo!"); 
            System.out.println("Robo " + nome + " so pode compactar ate " + pesoMaximoLixo + " kg de lixo.");
        }
    }

    // Funções Getters e Setters
    public int getPesoMaximoLixo(){ return pesoMaximoLixo; }

    public void setPesoMaximoLixo(int newPesoMaximoLixo) {
        this.pesoMaximoLixo = newPesoMaximoLixo;
    }
}
