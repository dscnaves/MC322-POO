/*
* Plantinha.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de uma plantinha, que é um tipo de obstáculo.
*/

package mc322.ambiente;

import mc322.exceptions.CrescimentoMaximoAtingidoException;

public class Plantinha extends Obstaculo {

    // Atributos específicos de Plantinha
    private String especie;
    private double crescimento;
    private boolean saudavel;

    // Construtor
    public Plantinha(int x1, int y1, int z1, int x2, int y2, int altura, String tipo) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PLANTINHA);
        this.especie = tipo;
        this.crescimento = 0.0;
        this.saudavel = true;
    }

    // Métodos específicos de Plantinha
    public void regar() throws CrescimentoMaximoAtingidoException {
        if (crescimento >= 100) {
            throw new CrescimentoMaximoAtingidoException(especie);
        }

        crescimento = Math.min(100, crescimento + 10);
        System.out.println("Plantinha " + especie + " regada! Crescimento atual: " + crescimento + "%");
    }
    

    // Método para tratar a plantinha
    public void tratar() {
        saudavel = true;
        System.out.println("Plantinha" + especie + " foi tratada e está saudável!");
    }

    // Método para verificar se a plantinha pode ser colhida
    public boolean podeColher() {
        return saudavel && crescimento >= 80;
    }

    // Getters e Setters
    public String getEspecie() {
        return especie;
    }

    public double getCrescimento() {
        return crescimento;
    }

    // Método para verificar se a plantinha está saudável
    public boolean isSaudavel() {
        return saudavel;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public void setCrescimento(double crescimento) {
        this.crescimento = crescimento;
    }

    public void setSaudavel(boolean saudavel) {
        this.saudavel = saudavel;
    }
}


