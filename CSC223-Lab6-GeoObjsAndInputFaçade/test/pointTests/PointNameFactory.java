package pointTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;

class PointNameFactory {

	
	private PointNamingFactory addToDataBase() {
		PointNamingFactory pointNamingFactory = new PointNamingFactory();
		
		for(int i = 0; i < 20; i++) {
			
			Point point = new Point("Point" + i, (double) i, (double) i);
			pointNamingFactory.put(point);
		}
		return pointNamingFactory;
	}
	@Test
	void testUpdateName() {
		
		PointNamingFactory pointNamingFactory = addToDataBase();

		
	}
		

}
