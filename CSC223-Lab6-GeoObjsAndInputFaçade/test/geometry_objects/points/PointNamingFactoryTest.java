package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointNamingFactory;

class PointNamingFactoryTest {

	private PointNamingFactory addToDataBase() {
		PointNamingFactory pointNamingFactory = new PointNamingFactory();

		for(int i = 0; i < 20; i++) {

			Point point = new Point("Point" + i, (double) i, (double) i);
			pointNamingFactory.put(point);
		}

		return pointNamingFactory;
	}

	@Test
	void testPointNamingFactory() {

		PointNamingFactory pnf = new PointNamingFactory();
		assertEquals(0, pnf.size());

	}

	@Test
	void testPointNamingFactoryList() {
		List<Point> list = new ArrayList<Point>();
		list.add(new Point(0,0));
		list.add(new Point(1,1));
		PointNamingFactory pnf = new PointNamingFactory(list);

		assertEquals(2, pnf.size());

	}


	@Test
	void testPutPoint() {

		PointNamingFactory pnf = new PointNamingFactory();

		Point point = pnf.put(new Point(0,0));
		assertEquals(new Point("*-A",0,0), point);
		
		
		assertTrue(pnf.contains(0, 0));
		assertTrue(pnf.contains(new Point("*_A",0.00,0.00)));

		Point point2 = new Point("name",1, 0);
		pnf.put(point2);
		assertTrue(pnf.contains(point2));
		assertTrue(pnf.contains(1, 0));
		assertTrue(pnf.contains(new Point("name",1.00,0.00)));
	}

	@Test
	void testPutCoordinates() {

		PointNamingFactory pnf = new PointNamingFactory();

		pnf.put(0,0);
		assertTrue(pnf.contains(0, 0));
		assertTrue(pnf.contains(new Point("*_A",0.00,0.00)));

		pnf.put(50,50);
		assertTrue(pnf.contains(50, 50));
		assertTrue(pnf.contains(new Point("*_B",50.00,50.00)));

		//duplicate
		System.out.print(pnf.put(0,0));
		assertTrue(pnf.contains(0, 0));
		

	}

	@Test
	void testPutNameAndCoordinates() {

		PointNamingFactory pnf = new PointNamingFactory();

		pnf.put("name",0,0);
		assertTrue(pnf.contains(new Point("name", 0, 0)));

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
	void testLookupExisting() {

		PointNamingFactory pnf = addToDataBase();
		


	}

	@Test
	void testCreateNewPoint() {

		PointNamingFactory pnf = addToDataBase();


	}

	@Test
	void testContainsPoint() {

		PointNamingFactory pnf = addToDataBase();

		assertTrue(pnf.contains(new Point(1.0,1.0)));
		assertFalse(pnf.contains(new Point(100, 100)));

	}

	@Test
	void testContainsCoordinates() {

		PointNamingFactory pnf = addToDataBase();

		assertTrue(pnf.contains(1.0,1.0));
		assertFalse(pnf.contains(100.0,100.0));


	}

	@Test
	void testGetCurrentName() {

		PointNamingFactory pnf = addToDataBase();


	}


	@Test
	void testUpdateName() {

		PointNamingFactory pnf = addToDataBase();


	}

	@Test
	void testGetAllPoints() {
		Set<Point> set = new HashSet<Point>();
		PointNamingFactory pnf = addToDataBase();

		set = pnf.getAllPoints();

		assertTrue(set.size()==20);

	}

	@Test
	void testClear() {

		PointNamingFactory pnf = addToDataBase();
		assertEquals(20, pnf.size());

		pnf.clear();
		assertEquals(0, pnf.size());
	}

	@Test
	void testSize() {
		PointNamingFactory pnf = addToDataBase();

		assertTrue(pnf.size()==20);

		pnf.clear();

		assertTrue(pnf.size()==0);

	}



}
