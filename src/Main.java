import komandos.Papildoma;

public class Main {

    public static void main(String[] args) {
	Papildoma objektas;
	objektas= new Papildoma();
	objektas.pasisveikinimas();
	while (true){
	    System.out.println();
	    objektas.paklausimas();
	    objektas.sprendimas();
    }


    }
}
