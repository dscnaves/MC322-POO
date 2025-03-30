/*
 * RoboProfessor.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboProfessor de um 
 * simulador de robôs, que representa uma classe para robôs capazes de dar
 * aulas com um determinado limite diário.
 */
public class RoboProfessor extends RoboTerrestre{

    // Atributo necessário para definir a classe RoboProfessor
    private int aulasMaximasPorDia;
    
    // Método construtor para inicialização dos atributos da classe RoboProfessor
    public RoboProfessor(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, int aulasMaximasPorDia, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.aulasMaximasPorDia = aulasMaximasPorDia;
    }

    // Função para gerenciamento de quantidade de aulas em um dia
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
