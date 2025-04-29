/*
* Planeta.java
* 
* Última modificação: 28/04/2025
* 
* Classe componente do Lab03 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um planeta, que é um tipo de obstáculo.
*/

public class Planeta extends Obstaculo {

    // Nome do planeta
    private String nome;

    // Construtor
    public Planeta(int x1, int y1, int z1, int x2, int y2, int altura) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.PLANETA);
        this.nome = ""; // Começa sem nome
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}


