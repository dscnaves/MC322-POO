/*
 * RoboLimpeza.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboLimpeza de um 
 * simulador de robôs, que representa uma classe para robôs especializados
 * em coleta e compactação de lixo.
 */

package mc322.simulator.robos;

public class RoboLimpeza extends RoboTerrestre{

    // Atributo necessário para definir a classe RoboLimpeza
    private int pesoMaximoLixo;

    // Método construtor para inicialização dos atributos da classe RoboLimpeza
    public RoboLimpeza(String nome, int posicaoX, int posicaoY, String direcao, int velocidadeMaxima, int pesoMaximoLixo, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, velocidadeMaxima, ambiente);
        this.pesoMaximoLixo = pesoMaximoLixo;
    }
    
    // Funnção para compactar lixo dentros dos limites de peso máximo
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
