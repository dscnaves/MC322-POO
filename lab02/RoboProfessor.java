public class RoboProfessor extends RoboTerrestre{
    private int aulasMaximasPorDia;

    public RoboProfessor(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, int aulasMaximasPorDia, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.aulasMaximasPorDia = aulasMaximasPorDia;
    }

    public void darAula(int quantidadeAulas) {
        int AulasDadas = Math.max(quantidadeAulas, 0);
        if (quantidadeAulas <= aulasMaximasPorDia) {
            System.out.println("Robo " + nome + " deu " + AulasDadas + " aula(s) hoje!");
        } else {
            System.out.println("Robo professor(a) sobrecarregado(a)! Robo " + nome + " so pode dar ate " + aulasMaximasPorDia + " aulas por dia.");
        }
    }

    // Funções Getters e Setters
    public int getAulasMaximasPorDia(){ return aulasMaximasPorDia; }

    public void setAulasMaximasPorDia(int newAulasMaximasPorDia){
        this.aulasMaximasPorDia = newAulasMaximasPorDia;
    }
}
