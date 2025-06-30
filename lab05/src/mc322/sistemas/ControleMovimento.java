package mc322.sistemas;

import mc322.robo.Robo;
import mc322.ambiente.Ambiente;
import mc322.ambiente.TipoEntidade;
import mc322.exceptions.ColisaoException;

public class ControleMovimento {

    public void moverPara(Robo robo, int finalX, int finalY, int finalZ) {
        Ambiente ambiente = robo.getAmbiente();

        // Eixo X
        int deltaX = finalX - robo.getX();
        int passoX = (deltaX > 0 ? 1 : -1);
        while (robo.getX() != finalX) {
            int proximoX = robo.getX() + passoX;
            if (ambiente.posicaoLivre(proximoX, robo.getY(), robo.getZ())) {
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.VAZIO;
                robo.setPosicaoX(proximoX);
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.ROBO;
            } else {
                throw new ColisaoException("Colisão no eixo X: (" + proximoX + "," + robo.getY() + "," + robo.getZ() + ")");
            }
        }

        // Eixo Y
        int deltaY = finalY - robo.getY();
        int passoY = (deltaY > 0 ? 1 : -1);
        while (robo.getY() != finalY) {
            int proximoY = robo.getY() + passoY;
            if (ambiente.posicaoLivre(robo.getX(), proximoY, robo.getZ())) {
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.VAZIO;
                robo.setPosicaoY(proximoY);
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.ROBO;
            } else {
                throw new ColisaoException("Colisão no eixo Y: (" + robo.getX() + "," + proximoY + "," + robo.getZ() + ")");
            }
        }

        // Eixo Z
        int deltaZ = finalZ - robo.getZ();
        int passoZ = (deltaZ > 0 ? 1 : -1);
        while (robo.getZ() != finalZ) {
            int proximoZ = robo.getZ() + passoZ;
            if (ambiente.dentroDosLimites(robo.getX(), robo.getY(), proximoZ)
                && ambiente.posicaoLivre(robo.getX(), robo.getY(), proximoZ)) {
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.VAZIO;
                robo.setAltitude(proximoZ);
                ambiente.getMapa()[robo.getY()][robo.getX()][robo.getZ()] = TipoEntidade.ROBO;
            } else {
                throw new ColisaoException("Colisão no eixo Z: (" + robo.getX() + "," + robo.getY() + "," + proximoZ + ")");
            }
        }
    }
}
