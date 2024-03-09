package peces;

import joc.MyColor;
import joc.Posicio;
import joc.Tauler;

import java.util.HashSet;

public class Peo extends Peca {

    public Peo(MyColor pCol, Posicio pos) {
        super(pCol, 1, pos);
    }

    public HashSet<Posicio> posicionsPerMenjar() {
        HashSet<Posicio> posicionsPerMenjar = new HashSet<>();
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        MyColor equip = this.getEquip();

        if (equip == MyColor.NEGRE) {
            if (0 < y && y <= 7) {
                if (0 <= x && x < 7) {
                    posicionsPerMenjar.add(new Posicio(x + 1, y - 1));
                }
                if (0 < x && x <= 7) {
                    posicionsPerMenjar.add(new Posicio(x - 1, y - 1));
                }
            }
        } else {
            if (0 <= y && y < 7) {
                if (0 <= x && x < 7) {
                    posicionsPerMenjar.add(new Posicio(x + 1, y + 1));
                }
                if (0 < x && x <= 7) {
                    posicionsPerMenjar.add(new Posicio(x - 1, y + 1));
                }
            }
        }

        return posicionsPerMenjar;
    }

    @Override
    public HashSet<Posicio> posicionsPosibles() {
        HashSet<Posicio> posicionsPosibles = new HashSet<>();
        int x = this.getPosicio().getX();
        int y = this.getPosicio().getY();
        MyColor equip = this.getEquip();

        if (equip == MyColor.BLANC) {
            if (0 < y && y <= 7) {
                posicionsPosibles.add(new Posicio(x, y + 1));
            }
        } else {
            if (0 <= y && y < 7) {
                posicionsPosibles.add(new Posicio(x, y - 1));
            }
        }

        return posicionsPosibles;
    }

    @Override
    public boolean movimentPosible(Tauler tauler, Posicio posicioNova) {
        int x1 = getPosicio().getX();
        int y1 = getPosicio().getY();
        int x2 = posicioNova.getX();
        int y2 = posicioNova.getY();

        if (this.posicionsPosibles().contains(posicioNova) && (tauler.getCaselles()[x2][y2] instanceof SensePeca)) {
            return true;
        }
        if (this.posicionsPerMenjar().contains(posicioNova) && tauler.pecesEquipContrari(this.equip).contains(posicioNova)) {
            return true;
        }
        if (this.equip == MyColor.BLANC && y1 == 1 && y2 == 3 && x2 == x1) {
            return true;
        }
        if (this.equip == MyColor.NEGRE && y1 == 6 && y2 == 4 && x2 == x1) {
            return true;
        }

        return false;
    }


    @Override
    public String toString() {
        return "P" + super.toString();
    }
}
