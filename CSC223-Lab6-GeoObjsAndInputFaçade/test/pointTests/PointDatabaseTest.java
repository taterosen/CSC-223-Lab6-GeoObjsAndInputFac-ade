package pointTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;
import geometry_objects.points.PointDatabase;

class PointDatabaseTest {

	private PointDatabase pointDataBaseCreation() {

		PointDatabase pointDatabase = new PointDatabase();

		for(int i = 0; i < 20; i++) {

			pointDatabase.put("Point" + i, (double) i, (double) i);
		}
		return pointDatabase;

	}

	@Test
	void testPointDatabase() {
		PointDatabase pointDatabase = new PointDatabase();
		assertTrue(pointDatabase.size()==0);
	}
	@Test
	void testPointDatabaseList() {
		ArrayList<Point> points = new ArrayList<Point>();
		
		
		for (int i=0;i<10;i++) {
			points.add(new Point("Point" + i, (double) i, (double) i));
			
		}
		
		PointDatabase pdb = new PointDatabase(points);
		assertTrue(pdb.size()==10);
	}
	
	@Test
	void testPut() {
		PointDatabase pdb = pointDataBaseCreation();
		assertTrue(pdb.size()==20);


	}

	@Test
	void testgetName() {
		PointDatabase pdb = pointDataBaseCreation();
		Point point = new Point("Point0", 0.0, 0.0);
		assertTrue(pdb.getName(point).equals("Point0"));

	}
	@Test
	void testgetNameFloat() {
		
		PointDatabase pdb = pointDataBaseCreation();
		assertTrue(pdb.getName(0.0,0.0).equals("Point0"));
	}

	@Test
	void testgetPointFromName() {
		PointDatabase pdb = pointDataBaseCreation();
		Point point = new Point("Point1",1.0,1.0);

		assertTrue(pdb.getPoint("Point1").equals(point));
	}
	@Test
	void testgetPointFromPoint() {
		PointDatabase pdb = pointDataBaseCreation();
		Point point = new Point("Point1",1.0,1.0);

		Point point2 = new Point("Point2",1,1);

		assertTrue(pdb.getPoint(point).equals(point));

		assertTrue(pdb.getPoint(point).equals(point2));

	}
	@Test
	void testgetPointFromDouble() {

		PointDatabase pdb = pointDataBaseCreation();

		Point point = new Point("Point1",1.0,1.0);

		assertTrue(pdb.getPoint(1, 1).equals(point));

		assertFalse(pdb.getPoint(0,0).equals(point));

		assertTrue(pdb.getPoint(0.9, 0.8)==null);

	}


}
