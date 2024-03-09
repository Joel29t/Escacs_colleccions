package peces;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

import java.util.HashSet;

public class Rei extends Peca {

    public Rei(MyColor pCol, Posicio pos) {
        super(pCol, 100, pos);
    }

    @Override
    public HashSet<Posicio> posicionsPosibles() {
        HashSet<Posicio> posicionsPosibles = new HashSet<>();
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == x || i == x + 1 || i == x - 1) {
                    if (j == y || j == y + 1 || j == y - 1)
                        posicionsPosibles.add(new Posicio(i, j));
                }
            }
        }
        posicionsPosibles.remove(new Posicio(x, y));
        return posicionsPosibles;
    }

    @Override
    public boolean movimentPosible(Tauler tauler, Posicio posicioNova) {
        int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();

        if (!posicionsPosibles().contains(posicioNova)) {
            return false;
        }
        if (tauler.pecesDelMateixEquip(this.equip).contains(posicioNova)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "R" + super.toString();
    }
}
