public class Ambiente{
    private int largura;
    private int altura;

    public Ambiente(int altura, int largura){
        this.largura = largura;
        this.altura = altura;
    }

    public boolean dentroDosLimites(int x, int y){
        return ((x>=0 && x<=largura) && (y>=0 && y<=altura));
    }
    
    public void setAmbiente(int newLargura, int newAltura){
        this.largura = newLargura;
        this.altura = newAltura;
    }

    public int getAmbienteLargura(){
        return largura;
    }

    public int getAmbienteAltura(){
        return altura;
    }

}