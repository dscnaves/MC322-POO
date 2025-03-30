/*
 * RoboAgentePessaolSaude.java
 * 
 * Última modificação: 30/03/2025
 * 
 * Classe componente do Lab02 da disciplina MC322 - Programação Orientada a Objetos
 * 
 * Autores: Anita Almeida e Daniela Naves
 */

/*
 * Esta classe contém a estrutura de implementação de RoboAgentePessoalSaude
 * de um simulador de robôs, que representa uma classe para robôs que avaliam 
 * o nível de dor de um paciente com base em seu nível de empatia.
 */
public class RoboAgentePessoalSaude extends RoboAereo {

    // Atributo necessário para definir a classe RoboAgentePessoalSaude
    private int nivelEmpatia;

    // Método construtor para inicialização dos atributos da classe RoboAgentePessoalSaude
    public RoboAgentePessoalSaude(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, int nivelEmpatia, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.nivelEmpatia = nivelEmpatia;
    }

    // Função para simular atendimento médico do Robo Agente Pessoal de Saúde
    public void fazerCheckup(int nivelDor) {

        // Atendimento sofrerá variações a depender do nível de empatia doAgente Pessoal de Saúde
        if (nivelEmpatia >= 66) {
            System.out.println("Olá, com licenca, eu sou " + nome + ", seu agente pessoal de saude sempre pronto para ajudar! ;)");
            System.out.println("Em uma escala de 0 a 10, voce classificou sua dor como " + nivelDor + ".");
            
            // Tomada de decisão a depender do nível de dor do paciente
            if (nivelDor < 0 || nivelDor > 10) {
                System.out.println("Infelizmente, esse nivel de dor eh invalido. Tente novamente com um valor entre 1 e 10, por favor.");
            } else if (nivelDor <= 3) {
                System.out.println("Fico feliz que apenas uma dor leve foi detectada. Recomendo repouso e hidratacao. E lembre-se, estou aqui para voce! ;)");
            } else if (nivelDor <= 7) {
                System.out.println("Poxa, uma dor moderada foi detectada. Avaliacao mais detalhada necessaria. E lembre-se, estou aqui para voce! ;)");
            } else {
                System.out.println("Oh nao! Uma dor intensa foi detectada :o . Disque 192 ou se encaminhe ao suporte medico especializado mais proximo. E lembre-se, estou aqui para voce! ;)");
            }
        } else if (nivelEmpatia >= 33) {
            System.out.println("Olá, eu sou " + nome + ", seu agente pessoal de saude.");
            System.out.println("Em uma escala de 1 a 10, voce classificou sua dor como " + nivelDor + ".");
            
            // Tomada de decisão a depender do nível de dor do paciente
            if (nivelDor < 0 || nivelDor > 10) {
                System.out.println("Esse nivel de dor eh invalido. Tente novamente com um valor entre 1 e 10.");
            } else if (nivelDor <= 3) {
                System.out.println("Dor leve detectada. Recomendo repouso e hidratacao.");
            } else if (nivelDor <= 7) {
                System.out.println("Dor moderada detectada. Avaliacao mais detalhada necessaria.");
            } else {
                System.out.println("Dor intensa detectada. Disque 192 ou se encaminhe ao suporte medico especializado mais proximo.");
            }
        } else {
            System.out.println("Olá.");
            System.out.println("Em uma escala de 1 a 10, voce classificou sua dor como " + nivelDor + ".");
            
            // Tomada de decisão a depender do nível de dor do paciente
            if (nivelDor < 0 || nivelDor > 10) {
                System.out.println("Nivel de dor invalido. Valor validos somente entre 1 e 10.");
            } else if (nivelDor <= 3) {
                System.out.println("Dor leve detectada. Recomendacao: repouso e hidratacao.");
            } else if (nivelDor <= 7) {
                System.out.println("Dor moderada detectada. Recomendacao: avaliacao medica.");
            } else {
                System.out.println("Dor intensa detectada. Recomendacao: encaminhamento medico especializado.");
            }
        }
    }

    // Funções Getters e Setters
    public int getNivelEmpatia() {
        return nivelEmpatia;
    }

    public void setNivelEmpatia(int newNivelEmpatia) {
        if (newNivelEmpatia >= 0 && newNivelEmpatia <= 100) {
            this.nivelEmpatia = newNivelEmpatia;
        } else {
            System.out.println("Nivel de empatia invalido. Deve ser entre 0 e 100.");
        }
    }

}
