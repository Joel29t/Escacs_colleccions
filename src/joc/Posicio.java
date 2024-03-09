package joc;

import java.util.Objects;

public class Posicio {
	private int x;
	private int y;
	
	public Posicio () {
		this(0,0);
	}
	
	public Posicio (int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public Posicio clone() {
		return new Posicio(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return "["+this.x+", "+this.y+"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicio other = (Posicio) obj;
		return x == other.x && y == other.y;
	}
	
}
