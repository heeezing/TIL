package kr.s24.interfacetest;

interface I {
	public abstract void play();
}

class Pianist implements I {
	@Override
	public void play() {
		System.out.println("피아노를 연주하다");
	}
}

class Cellist implements I {
	@Override
	public void play() {
		System.out.println("첼로를 연주하다");
	}
}

class Stage {
	public void autoPlay(I i) {
		i.play();
	}
}

public class InterMain05 {
	public static void main(String[] args) {
		Stage st = new Stage();
		st.autoPlay(new Pianist());  //Pianist타입 -> I타입
		st.autoPlay(new Cellist());  //Cellist타입 -> I타입
	}
}
