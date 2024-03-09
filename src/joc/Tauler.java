package joc;

import java.util.HashSet;

import peces.*;

public class Tauler {
    private Escac[][] escacs;

    public Tauler() {
        this.escacs = new Escac[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.escacs[i][j] = new SensePeca(((i + j) % 2 == 0 ? MyColor.NEGRE : MyColor.BLANC),
                        new Posicio(i, j));
            }
        }
    }
	
	public void crearPartida() {
		this.addPeca(new Torre(MyColor.BLANC, new Posicio(0,0)), new Posicio(0,0));
		this.addPeca(new Torre(MyColor.BLANC, new Posicio(7,0)), new Posicio(7,0));
		this.addPeca(new Cavall(MyColor.BLANC, new Posicio(1,0)), new Posicio(1,0));
		this.addPeca(new Cavall(MyColor.BLANC, new Posicio(6,0)), new Posicio(6,0));
		this.addPeca(new Alfil(MyColor.BLANC, new Posicio(2,0)), new Posicio(2,0));
		this.addPeca(new Alfil(MyColor.BLANC, new Posicio(5,0)), new Posicio(5,0));
		this.addPeca(new Reina(MyColor.BLANC, new Posicio(3,0)), new Posicio(3,0));
		this.addPeca(new Rei(MyColor.BLANC, new Posicio(4,0)), new Posicio(4,0));
		
		for (int i=0; i<8; i++) {
			this.addPeca(new Peo(MyColor.BLANC, new Posicio(i,1)), new Posicio(i,1));
		}
		
		this.addPeca(new Torre(MyColor.NEGRE, new Posicio(0,7)), new Posicio(0,7));
		this.addPeca(new Torre(MyColor.NEGRE, new Posicio(7,7)), new Posicio(7,7));
		this.addPeca(new Cavall(MyColor.NEGRE, new Posicio(1,7)), new Posicio(1,7));
		this.addPeca(new Cavall(MyColor.NEGRE, new Posicio(6,7)), new Posicio(6,7));
		this.addPeca(new Alfil(MyColor.NEGRE, new Posicio(2,7)), new Posicio(2,7));
		this.addPeca(new Alfil(MyColor.NEGRE, new Posicio(5,7)), new Posicio(5,7));
		this.addPeca(new Reina(MyColor.NEGRE, new Posicio(3,7)), new Posicio(3,7));
		this.addPeca(new Rei(MyColor.NEGRE, new Posicio(4,7)), new Posicio(4,7));
		
		for (int i=0; i<8; i++) {
			this.addPeca(new Peo(MyColor.NEGRE, new Posicio(i,6)), new Posicio(i,6));
		}
	}
	
	public void addPeca (Peca e, Posicio p) {
		this.escacs[p.getX()][p.getY()] = new AmbPeca(e, this.escacs[p.getX()][p.getY()].getColor() );
	}
	
	public Peca mouPeca(Posicio posInicial, Posicio posFinal) throws InvalidMovementException {
		if (posInicial.getX()<0 || posInicial.getY()>7 || posFinal.getX()<0 || posFinal.getY()>7) {
			throw new InvalidMovementException("moviment invàlid");
		}
		Peca mort = null;		
	
		if (this.escacs[posFinal.getX()][posFinal.getY()] instanceof AmbPeca) {
			mort = ((AmbPeca)this.escacs[posFinal.getX()][posFinal.getY()]).getPeca();
		} 
//		else {
//			this.escacs[posFinal.getX()][posFinal.getY()] = new AmbPeca(null,this.escacs[posFinal.getX()][posFinal.getY()].getColor());
//		}
		
		((AmbPeca)this.escacs[posInicial.getX()][posInicial.getY()]).getPeca().setPosicio(posFinal);
		this.escacs[posFinal.getX()][posFinal.getY()] = new AmbPeca(
				((AmbPeca)this.escacs[posInicial.getX()][posInicial.getY()]).getPeca(),
				this.escacs[posFinal.getX()][posFinal.getY()].getColor());
//		((AmbPeca)this.escacs[posFinal.getX()][posFinal.getY()]).setPeca(((AmbPeca)this.escacs[posInicial.getX()][posInicial.getY()]).getPeca());
//		((AmbPeca)this.escacs[posFinal.getX()][posFinal.getY()]).getPeca().setPosicio(posFinal);
		
		this.escacs[posInicial.getX()][posInicial.getY()] = new SensePeca(this.escacs[posInicial.getX()][posInicial.getY()].getColor(), posInicial);
		return mort;
	}
	
	 public HashSet<Posicio> pecesDelMateixEquip(MyColor col) {
	        HashSet<Posicio> pecesDelMateixEquip = new HashSet<>();
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if ((this.escacs[i][j] instanceof AmbPeca)
	                        && (((AmbPeca) this.escacs[i][j]).getPeca().isMateixEquip(col)))
	                    pecesDelMateixEquip.add(new Posicio(i, j));
	            }
	        }
	        return pecesDelMateixEquip;
	    }
	
	 public HashSet<Posicio> pecesEquipContrari(MyColor col) {
	        HashSet<Posicio> pecesEquipContrari = new HashSet<>();
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if ((this.escacs[i][j] instanceof AmbPeca)
	                        && !(((AmbPeca) this.escacs[i][j]).getPeca().isMateixEquip(col)))
	                    pecesEquipContrari.add(new Posicio(i, j));
	            }
	        }
	        return pecesEquipContrari;
	    }
	
	public Escac[][] getCaselles() {
		return this.escacs;
	}
	
	public Peca getPeca(Posicio pos) {
		return (this.escacs[pos.getX()][pos.getY()] instanceof AmbPeca) ? 
				((AmbPeca)this.escacs[pos.getX()][pos.getY()]).getPeca() :
				null;
	}
	
	public void imprimirTauler() {
		for (int j = 7; j >= 0; j--) {
			System.out.print(j + " - ");
			for (int i = 0; i < 8; i++) {
				if (this.escacs[i][j] != null) {
					System.out.print("|(" + this.escacs[i][j].toString() + ")|");
				}
				
			}
			System.out.println("");
		}
		System.out.print("    ");
		for (int i = 0; i < 8; i++) {
			System.out.print("|  " + i + "  |");
		}
	}
	
	public void imprimirMateix(MyColor col) {
	    HashSet<Posicio> peces = this.pecesDelMateixEquip(col);
	    for (int j = 7; j >= 0; j--) {
	        System.out.print(j + " - ");
	        for (int i = 0; i < 8; i++) {
	            Posicio pos = new Posicio(i, j);
	            if (this.escacs[i][j] != null) {
	                String aux = (peces.contains(pos)) ? " * " : " O ";
	                System.out.print("|(" + aux + ")|");
	            }
	        }
	        System.out.println("");
	    }
	    System.out.print("    ");
	    for (int i = 0; i < 8; i++) {
	        System.out.print("|  " + i + "  |");
	    }
	}

	public void imprimirContrari(MyColor col) {
	    HashSet<Posicio> peces = this.pecesEquipContrari(col);
	    for (int j = 7; j >= 0; j--) {
	        System.out.print(j + " - ");
	        for (int i = 0; i < 8; i++) {
	            Posicio pos = new Posicio(i, j);
	            if (this.escacs[i][j] != null) {
	                String aux = (peces.contains(pos)) ? " * " : " O ";
	                System.out.print("|(" + aux + ")|");
	            }
	        }
	        System.out.println("");
	    }
	    System.out.print("    ");
	    for (int i = 0; i < 8; i++) {
	        System.out.print("|  " + i + "  |");
	    }
	}

}
