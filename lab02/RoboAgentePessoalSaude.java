public class RoboAgentePessoalSaude extends RoboAereo {
    private int nivelEmpatia;

    public RoboAgentePessoalSaude(String nome, int posicaoX, int posicaoY, String direcao, int altitudeMaxima, int altitude, int nivelEmpatia, Ambiente ambiente) {
        super(nome, posicaoX, posicaoY, direcao, altitudeMaxima, altitude, ambiente);
        this.nivelEmpatia = nivelEmpatia;
    }

    public void fazerCheckup(int nivelDor) {
        if (nivelEmpatia >= 66) {
            System.out.println("Olá, com licenca, eu sou " + nome + ", seu agente pessoal de saude sempre pronto para ajudar! ;)");
            System.out.println("Em uma escala de 0 a 10, voce classificou sua dor como " + nivelDor + ".");
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
