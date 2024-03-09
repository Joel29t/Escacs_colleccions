package peces;

import java.util.HashSet;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

public class Cavall extends Peca {

	public Cavall(MyColor pCol, Posicio pos) {
		super(pCol, 8, pos);		
	}

	@Override
	public HashSet<Posicio> posicionsPosibles() {
		HashSet<Posicio> posicionsPosibles = new HashSet<>();
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if (i == x+2){
                    if(j == y+1 || j == y-1)
                        posicionsPosibles.add(new Posicio(i,j));
                }   
                if (i == x+1){
                    if(j == y+2 || j == y-2)
                    	posicionsPosibles.add(new Posicio(i,j));
                }
                if (i == x-1){
                    if(j == y+2 || j == y-2)
                    	posicionsPosibles.add(new Posicio(i,j));
                }
                if (i == x-2){
                    if(j == y+1 || j == y-1)
                    	posicionsPosibles.add(new Posicio(i,j));
                }
            }
        }
        posicionsPosibles.remove(new Posicio(x,y));
        return posicionsPosibles;
	}


    @Override
    public boolean movimentPosible(Tauler tauler, Posicio posicioNova) {
        int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();

        if (!posicionsPosibles().contains(posicioNova))
            return false;
        else if (tauler.getPeca(posicioNova) != null && tauler.getPeca(posicioNova).getEquip() == this.getEquip())
            return false;

        return true;
    }


	@Override
	public String toString() {
		return "C"+super.toString();
	}
}
