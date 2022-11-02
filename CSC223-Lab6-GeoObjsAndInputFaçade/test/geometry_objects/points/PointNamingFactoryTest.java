package geometry_objects.points;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;


import org.junit.jupiter.api.Test;

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
		
		Point point2 = pnf.put(new Point("name",1, 0));
		assertEquals(new Point("name",1,0), point2);
		
		//overwrite point *_A
		point = pnf.put(new Point("update",0,0));
		assertEquals(new Point("update",0,0), point);

		//does not overwrite
		point2 = pnf.put(new Point(1,0));
		assertEquals(new Point("name",1,0), point2);
		
	}

	@Test
	void testPutCoordinates() {

		PointNamingFactory pnf = new PointNamingFactory();
		
		Point point = pnf.put(0,0);
		assertEquals(new Point("*_A",0,0), point);

		Point point2 = pnf.put(1,1);
		assertEquals(new Point("*_B",1,1), point2);
	}

	@Test
	void testPutNameAndCoordinates() {

		PointNamingFactory pnf = new PointNamingFactory();

		Point point = pnf.put("name",0,0);
		assertEquals(new Point("name",0,0), point);

		Point nullPoint = pnf.put(null,1,1);
		assertEquals(new Point("*_A",1,1), nullPoint);

		Point emptyStringPoint = pnf.put(null,2,2);
		assertEquals(new Point("*_B",2,2), emptyStringPoint);
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
		
		//regular point existing
		Point point = new Point("Point1",1,1);
		assertEquals(pnf.lookupExisting("Point1", 1,1),point);
		
		//create unnamed point
		Point unnamedPoint= new Point(30,30);
		
		//creating point that has no name
		Point nonAutopoint = pnf.lookupExisting(unnamedPoint.getName(),unnamedPoint.getX(),unnamedPoint.getY());
		assertEquals(nonAutopoint.getName(),"*_A");
		
		//Testing override of name
		nonAutopoint = pnf.lookupExisting("Point30", 30, 30);
		assertEquals(nonAutopoint.getName(),"Point30");
		
		
		//creating point that doesn't exist
		Point nullPoint = new Point("Point50",50,50);
		
		//point doesn't exist already
		assertEquals(pnf.lookupExisting("Point50", 50,50),nullPoint);
		
		


	}

	@Test
	void testCreateNewPoint() {

		PointNamingFactory pnf = addToDataBase();
		
		Point point = pnf.createNewPoint("Point5", 1, 1);
		
		assertTrue(point.equals(pnf.get(point)));
		
		assertTrue(point.getName().equals("Point5"));
		
		
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

		PointNamingFactory pnf = new PointNamingFactory();
		
		for (int i=0; i<25;i++) {
			pnf.getCurrentName();	
			}
		
		//check wrap around to double letters
		assertTrue(pnf.getCurrentName().equals("*_Z"));
		
		assertTrue(pnf.getCurrentName().equals("*_AA"));
		

	}


	@Test
	void testUpdateName() 
	{

		PointNamingFactory pnf = new PointNamingFactory();
		
		assertTrue(pnf.getCurrentName().equals("*_A"));
		
		pnf.updateName();
		
		assertTrue(pnf.getCurrentName().equals("*_C"));
		
		assertTrue(pnf.getCurrentName().equals("*_D"));
		//updating name and making sure changed
		pnf.updateName();
		
		assertTrue(pnf.getCurrentName().equals("*_F"));

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
