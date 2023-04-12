

import java.util.Random;

public class ShapeFactory {

	public Shape getRandomShape() {
		Random random = new Random();

		
		
		int shape = random.nextInt(7);
		switch(shape) {
			case 0:
				return new Block();
			case 1:
				return new S();
			case 2:
				return new Z();
			case 3:
				return new L();
			case 4:
				return new J();
			case 5:
				return new T();
			default:
				return new Line();
		}		
		
	}
	
}
