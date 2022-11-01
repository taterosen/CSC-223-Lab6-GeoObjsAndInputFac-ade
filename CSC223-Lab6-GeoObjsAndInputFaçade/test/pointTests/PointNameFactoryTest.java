package pointTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;

class PointNameFactoryTest {

	private PointNamingFactory addToDataBase() {
		PointNamingFactory pointNamingFactory = new PointNamingFactory();
		
		for(int i = 0; i < 20; i++) {
			
			Point point = new Point("Point" + i, (double) i, (double) i);
			pointNamingFactory.put(point);
		}
		return pointNamingFactory;
	}
	
	
	@Test
	void testPutPoint() {
		
		PointNamingFactory pnf = new PointNamingFactory();
		
		Point point = new Point(0, 0);
		pnf.put(point);
		System.out.println(pnf);
		assertTrue(pnf.contains(point));
		assertTrue(pnf.contains(0, 0));
		assertTrue(pnf.contains(new Point("*_A",0.00,0.00)));
		
		

		
	}
	
	@Test
	void testPutCoordinates() {
		
		PointNamingFactory pnf = new PointNamingFactory();
		
		pnf.put(0,0);
		assertTrue(pnf.contains(0, 0));
		assertTrue(pnf.contains(new Point("*_A",0.00,0.00)));
		

		
	}
	
	@Test
	void testPutNameAndCoordinates() {
		
		PointNamingFactory pnf = new PointNamingFactory();
				
		pnf.put("name",0,0);
		assertTrue(pnf.contains(new Point("name", 0, 0)));
		
		
	}
	
	@Test
	void testGetPoint() {
		
		PointNamingFactory pnf = addToDataBase();
		
		Point point1 = new Point ("Point1", 1.00, 1.00);
		assertEquals(point1, pnf.get(point1));
		
		Point otherPoint = new Point("name", 50.00, 50.00);
		assertNull(pnf.get(otherPoint));
	}
	
	
	@Test
	void testGetCoordinates() {
		
		PointNamingFactory pnf = addToDataBase();
		
		assertEquals(new Point("Point1", 1.0, 1.0), pnf.get(1.0, 1.0));
		
		assertNull(pnf.get(50.00, 50.00));

	}
	
	
	
	
	@Test
	void testUpdateName() {
		
		PointNamingFactory pnf = addToDataBase();
		
		

		
	}
		

}
