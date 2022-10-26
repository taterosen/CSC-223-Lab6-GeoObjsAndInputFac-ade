package pointTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometry_objects.points.Point;

class PointTest {

	@Test
	void testGetX() {
		Point point = new Point(0,0);
		assertEquals(0, point.getX());
		
		Point point2 = new Point(12038,0);
		assertEquals(12038, point2.getX());
	}
	
	@Test
	void testGetY() {
		Point point = new Point(0,0);
		assertEquals(0, point.getY());
		
		Point point2 = new Point(0,-323982);
		assertEquals(-323982, point2.getY());
	}
	
	@Test
	void testGetName() {
		Point point = new Point(0,0);
		assertEquals("__UNNAMED", point.getName());
		
		Point point2 = new Point("PointA",0,0);
		assertEquals("PointA", point2.getName());
	}
	
	@Test
	void testIsUnnamed() {
		Point point = new Point(0,0);
		assertTrue(point.isUnnamed());
		
		Point namedPoint = new Point("Name",0,0);
		assertFalse(namedPoint.isUnnamed());
	}
	
	@Test
	void testCompareTo() {
		Point point = new Point(0,0);
		assertEquals(0, point.compareTo(point));
		
		Point largerPointX = new Point(1,0);
		assertEquals(1, largerPointX.compareTo(point));
		
		Point largerPointY = new Point(0,1);
		assertEquals(1, largerPointY.compareTo(point));
		
		Point smallerPointX = new Point(-1,0);
		assertEquals(-1, smallerPointX.compareTo(point));
		
		Point smallerPointY = new Point(0,-1);
		assertEquals(-1, smallerPointY.compareTo(point));
		
		Point equalPoint = new Point("Same Point",0,0);
		assertEquals(0, equalPoint.compareTo(point));
	}

}
