/*
* Lixo.java
* 
* Última modificação: 01/06/2025
* 
* Classe componente do Lab04 da disciplina MC322 - Programação Orientada a Objetos
* 
* Autores: Anita Almeida e Daniela Naves
*/

/*
* Esta classe contém a estrutura de implementação de um lixo, que é um tipo de obstáculo.
*/

package mc322.ambiente;

public class Lixo extends Obstaculo {

    // Tipo de lixo
    private String tipoLixo;

    // Construtor
    public Lixo(int x1, int y1, int z1, int x2, int y2, int altura, String tipoLixo) {
        super(x1, y1, z1, x2, y2, altura, TipoObstaculo.LIXO);
        this.tipoLixo = tipoLixo;
    }

    // Getters e Setters
    public String getTipoLixo() {
        return tipoLixo;
    }

    public void setTipoLixo(String tipoLixo) {
        this.tipoLixo = tipoLixo;
    }
}


