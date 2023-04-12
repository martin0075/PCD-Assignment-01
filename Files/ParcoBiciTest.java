public class ParcoBiciTest {

	public static void main(String[] args) {
		Bici bici1 = new Bici(6, 14);
		Bici bici2 = new Bici(4, 14);
		Bici bici3 = new Bici(7, 18);
		Bici bici4 = new Bici(6, 14);
		
		Bici mountainBike1 = new MountainBike(6, 6, 14);
		Bici mountainBike2 = new MountainBike(2, 6, 14);
		Bici mountainBike3 = new MountainBike(2, 5, 14);
		Bici mountainBike4 = new MountainBike(1, 6, 14);
		
		Bici biciStrada1 = new BiciStrada(20, 6, 12);
		Bici biciStrada2 = new BiciStrada(20, 14, 15);
		Bici biciStrada3 = new BiciStrada(50, 14, 14);
		Bici biciStrada4 = new BiciStrada(60, 14, 15);
		
		ParcoBici parcoBici = new ParcoBici();
		
		parcoBici.aggiungiBici(bici1);
		parcoBici.aggiungiBici(bici2);
		parcoBici.aggiungiBici(bici3);
		parcoBici.aggiungiBici(bici4);
		
		parcoBici.aggiungiBici(mountainBike1);
		parcoBici.aggiungiBici(mountainBike2);
		parcoBici.aggiungiBici(mountainBike3);
		parcoBici.aggiungiBici(mountainBike4);
		
		parcoBici.aggiungiBici(biciStrada1);
		parcoBici.aggiungiBici(biciStrada2);
		parcoBici.aggiungiBici(biciStrada3);
		parcoBici.aggiungiBici(biciStrada4);
		
		parcoBici.stampa();
	}
	
}
