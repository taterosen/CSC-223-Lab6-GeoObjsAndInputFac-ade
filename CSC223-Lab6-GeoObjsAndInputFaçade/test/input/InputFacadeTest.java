package input;

import static org.junit.jupiter.api.Assertions.*;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

import geometry_objects.Segment;
import geometry_objects.points.PointDatabase;
import input.components.ComponentNode;
import input.components.FigureNode;
import input.exception.ParseException;
import input.visitor.UnparseVisitor;
import input.parser.JSONParser;

class InputFacadeTest {
	
	public static ComponentNode runExtractFigureTest(String filename)
	{ 
		InputFacade parser = new InputFacade();

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		System.out.print(figureStr);
		return parser.extractFigure(figureStr); 
	}
	
	public static Entry<PointDatabase, Set<Segment>> runToGeoRepTest(String filename)
	{ 
		InputFacade geoParser = new InputFacade();

		String figureStr = utilities.io.FileUtilities.readFileFilterComments(filename);
		System.out.print(figureStr);
		return geoParser.toGeometryRepresentation(figureStr); 
	}
	
	
	//all tests below test extractFigure() by calling runExtractFigureTest or empty filename
	
	@Test
	void empty_json_string_test()
	{
		InputFacade parser = new InputFacade();

		assertThrows(ParseException.class, () -> { parser.extractFigure("{}"); });
	}

	@Test void single_triangle_test()
	{
		ComponentNode node = runExtractFigureTest("jsonfiles/single_triangle.json");

		assertTrue(node instanceof FigureNode);
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"Right Triangle in the first quadrant.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(1.0, 1.0)\n"
				+ "        Point(C)(1.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  C  \n"
				+ "        B : C  \n"
				+ "    }\n"
				+ "}\n";
		
		StringBuilder sb = new StringBuilder(); 
		UnparseVisitor unparser = new UnparseVisitor();
		unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	
		
		assertEquals(expected, sb.toString());
	}

	@Test 
	void point_test() { 
		ComponentNode node = runExtractFigureTest("jsonfiles/point.json");

		assertTrue(node instanceof FigureNode);
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"A single point\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "    }\n"
				+ "}\n";

		StringBuilder sb = new StringBuilder(); 
		UnparseVisitor unparser = new UnparseVisitor();
		unparser.visitFigureNode((FigureNode)node,
			new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

		assertEquals(expected, sb.toString());
	}

	@Test 
	void line_seg_test() { 
		ComponentNode node = runExtractFigureTest("jsonfiles/lineseg.json");
	  
		assertTrue(node instanceof FigureNode);
		
		String expected = "Figure \n"
				+ "{\n"
				+ "    Description : \"One line segment consisting of two points on y-axis.\"\n"
				+ "    Points:\n"
				+ "    {\n"
				+ "        Point(A)(0.0, 0.0)\n"
				+ "        Point(B)(0.0, 1.0)\n"
				+ "    }\n"
				+ "    Segments:\n"
				+ "    {\n"
				+ "        A : B  \n"
				+ "    }\n"
				+ "}\n";
	  
		StringBuilder sb = new StringBuilder(); 
		UnparseVisitor unparser = new UnparseVisitor();
		unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

		assertEquals(expected, sb.toString());
	  }
	
	  @Test 
	  void snake_test() { 
		  ComponentNode node = runExtractFigureTest("jsonfiles/snake.json"); 
		  
		  assertTrue(node instanceof FigureNode);
		  
		  String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"Three triangles glued by vertex in a row\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "        Point(B)(0.0, 1.0)\n"
					+ "        Point(C)(1.0, 0.0)\n"
					+ "        Point(D)(2.0, 0.0)\n"
					+ "        Point(E)(2.0, 1.0)\n"
					+ "        Point(F)(3.0, 1.0)\n"
					+ "        Point(G)(3.0, 0.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  C  \n"
					+ "        B : C  \n"
					+ "        C : D  E  \n"
					+ "        D : E  \n"
					+ "        E : F  G  \n"
					+ "        F : G  \n"
					+ "    }\n"
					+ "}\n";
	  
		  StringBuilder sb = new StringBuilder(); 
		  UnparseVisitor unparser = new UnparseVisitor();
		  unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

		  assertEquals(expected, sb.toString());
	  }
	 

	
	  @Test 
	  void collinear_line_segments() { 
		  ComponentNode node = runExtractFigureTest("jsonfiles/collinear_line_segments.json");
	  
		  assertTrue(node instanceof FigureNode);
		  
		  String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"A seqeunce of collinear line segments mimicking one line with 6 points.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "        Point(B)(4.0, 0.0)\n"
					+ "        Point(C)(9.0, 0.0)\n"
					+ "        Point(D)(11.0, 0.0)\n"
					+ "        Point(E)(16.0, 0.0)\n"
					+ "        Point(F)(26.0, 0.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  \n"
					+ "        B : C  \n"
					+ "        C : D  \n"
					+ "        D : E  \n"
					+ "        E : F  \n"
					+ "    }\n"
					+ "}\n";
	  
		  StringBuilder sb = new StringBuilder(); 
		  UnparseVisitor unparser = new UnparseVisitor();
		  unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	 

		  assertEquals(expected, sb.toString());
	  }
	 

	
	  @Test 
	  void crossing_symmetric_triangle() { 
		  ComponentNode node = runExtractFigureTest(
				  				"jsonfiles/crossing_symmetric_triangle.json");
	  
		  assertTrue(node instanceof FigureNode);
		  
		  String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"Crossing symmetric triangle construction.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(3.0, 6.0)\n"
					+ "        Point(B)(2.0, 4.0)\n"
					+ "        Point(C)(4.0, 4.0)\n"
					+ "        Point(D)(0.0, 0.0)\n"
					+ "        Point(E)(6.0, 0.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  C  \n"
					+ "        B : C  D  E  \n"
					+ "        C : D  E  \n"
					+ "        D : E  \n"
					+ "    }\n"
					+ "}\n";
	  
		  StringBuilder sb = new StringBuilder(); 
		  UnparseVisitor unparser = new UnparseVisitor();
		  unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

		  assertEquals(expected, sb.toString());
	  }
	 

	
	  @Test 
	  void fully_connected_irregular_polygon() { 
		  ComponentNode node = runExtractFigureTest(
				  				"jsonfiles/fully_connected_irregular_polygon.json");
	  
		  assertTrue(node instanceof FigureNode);
		  
		  String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"Irregular pentagon in which each vertex is connected to each other.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "        Point(B)(4.0, 0.0)\n"
					+ "        Point(C)(6.0, 3.0)\n"
					+ "        Point(D)(3.0, 7.0)\n"
					+ "        Point(E)(-2.0, 4.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  C  D  E  \n"
					+ "        B : C  D  E  \n"
					+ "        C : D  E  \n"
					+ "        D : E  \n"
					+ "    }\n"
					+ "}\n";
	  
		  StringBuilder sb = new StringBuilder(); 
		  UnparseVisitor unparser = new UnparseVisitor();
		  unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

		  assertEquals(expected, sb.toString());
	  }
	 

	
	  @Test 
	  void Tri_Quad() { 
		  ComponentNode node = runExtractFigureTest("jsonfiles/Tri_Quad.json");
	  
		  assertTrue(node instanceof FigureNode);
		  
		  String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"Tri Quad Shape.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(4.0, 0.0)\n"
					+ "        Point(B)(8.0, 0.0)\n"
					+ "        Point(C)(4.0, 5.0)\n"
					+ "        Point(D)(8.0, 5.0)\n"
					+ "        Point(E)(0.0, 10.0)\n"
					+ "        Point(F)(12.0, 10.0)\n"
					+ "        Point(G)(4.0, 12.0)\n"
					+ "        Point(H)(8.0, 12.0)\n"
					+ "        Point(I)(6.0, 10.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  C  \n"
					+ "        B : D  \n"
					+ "        C : D  E  I  \n"
					+ "        D : F  I  \n"
					+ "        E : G  \n"
					+ "        F : H  \n"
					+ "        G : I  \n"
					+ "        H : I  \n"
					+ "    }\n"
					+ "}\n";
	  
		  StringBuilder sb = new StringBuilder(); 
		  UnparseVisitor unparser = new UnparseVisitor();
		  unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

		  assertEquals(expected, sb.toString());
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	//all tests below test toGeometryRepresentation() by calling runToGeoRepTest or empty filename
		
		@Test
		void empty_json_string_GeoTest()
		{
			InputFacade parser = new InputFacade();

			assertThrows(ParseException.class, () -> { parser.toGeometryRepresentation("{}"); });
		}

		@Test void single_triangle_GeoTest()
		{
			Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/single_triangle.json");

			assertTrue(node instanceof Entry);
			
			PointDatabase expected = new PointDatabase();
			expected.put("A", 0.0, 0.0);
			expected.put("B", 1.0, 1.0);
			expected.put("C", 1.0, 0.0);
			
			String expectedString = "Figure \n"
					+ "{\n"
					+ "    Description : \"Right Triangle in the first quadrant.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "        Point(B)(1.0, 1.0)\n"
					+ "        Point(C)(1.0, 0.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  C  \n"
					+ "        B : C  \n"
					+ "    }\n"
					+ "}\n";
			
			StringBuilder sb = new StringBuilder(); 
			UnparseVisitor unparser = new UnparseVisitor();
			unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	
			
			assertEquals(expected, sb.toString());
		}

		@Test 
		void point_GeoTest() { 
			Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/point.json");

			assertTrue(node instanceof FigureNode);
			
			String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"A single point\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "    }\n"
					+ "}\n";

			StringBuilder sb = new StringBuilder(); 
			UnparseVisitor unparser = new UnparseVisitor();
			unparser.visitFigureNode((FigureNode)node,
				new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

			assertEquals(expected, sb.toString());
		}

		@Test 
		void line_seg_test_GeoTest() { 
			Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/lineseg.json");
		  
			assertTrue(node instanceof FigureNode);
			
			String expected = "Figure \n"
					+ "{\n"
					+ "    Description : \"One line segment consisting of two points on y-axis.\"\n"
					+ "    Points:\n"
					+ "    {\n"
					+ "        Point(A)(0.0, 0.0)\n"
					+ "        Point(B)(0.0, 1.0)\n"
					+ "    }\n"
					+ "    Segments:\n"
					+ "    {\n"
					+ "        A : B  \n"
					+ "    }\n"
					+ "}\n";
		  
			StringBuilder sb = new StringBuilder(); 
			UnparseVisitor unparser = new UnparseVisitor();
			unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

			assertEquals(expected, sb.toString());
		  }
		
		  @Test 
		  void snake_test_GeoTest() { 
			  Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/snake.json"); 
			  
			  assertTrue(node instanceof FigureNode);
			  
			  String expected = "Figure \n"
						+ "{\n"
						+ "    Description : \"Three triangles glued by vertex in a row\"\n"
						+ "    Points:\n"
						+ "    {\n"
						+ "        Point(A)(0.0, 0.0)\n"
						+ "        Point(B)(0.0, 1.0)\n"
						+ "        Point(C)(1.0, 0.0)\n"
						+ "        Point(D)(2.0, 0.0)\n"
						+ "        Point(E)(2.0, 1.0)\n"
						+ "        Point(F)(3.0, 1.0)\n"
						+ "        Point(G)(3.0, 0.0)\n"
						+ "    }\n"
						+ "    Segments:\n"
						+ "    {\n"
						+ "        A : B  C  \n"
						+ "        B : C  \n"
						+ "        C : D  E  \n"
						+ "        D : E  \n"
						+ "        E : F  G  \n"
						+ "        F : G  \n"
						+ "    }\n"
						+ "}\n";
		  
			  StringBuilder sb = new StringBuilder(); 
			  UnparseVisitor unparser = new UnparseVisitor();
			  unparser.visitFigureNode((FigureNode)node,
						new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));

			  assertEquals(expected, sb.toString());
		  }
		 

		
		  @Test 
		  void collinear_line_segments_GeoTest() { 
			  Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/collinear_line_segments.json");
		  
			  assertTrue(node instanceof FigureNode);
			  
			  String expected = "Figure \n"
						+ "{\n"
						+ "    Description : \"A seqeunce of collinear line segments mimicking one line with 6 points.\"\n"
						+ "    Points:\n"
						+ "    {\n"
						+ "        Point(A)(0.0, 0.0)\n"
						+ "        Point(B)(4.0, 0.0)\n"
						+ "        Point(C)(9.0, 0.0)\n"
						+ "        Point(D)(11.0, 0.0)\n"
						+ "        Point(E)(16.0, 0.0)\n"
						+ "        Point(F)(26.0, 0.0)\n"
						+ "    }\n"
						+ "    Segments:\n"
						+ "    {\n"
						+ "        A : B  \n"
						+ "        B : C  \n"
						+ "        C : D  \n"
						+ "        D : E  \n"
						+ "        E : F  \n"
						+ "    }\n"
						+ "}\n";
		  
			  StringBuilder sb = new StringBuilder(); 
			  UnparseVisitor unparser = new UnparseVisitor();
			  unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	 

			  assertEquals(expected, sb.toString());
		  }
		 

		
		  @Test 
		  void crossing_symmetric_triangle_GeoTest() { 
			  Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest(
					  				"jsonfiles/crossing_symmetric_triangle.json");
		  
			  assertTrue(node instanceof FigureNode);
			  
			  String expected = "Figure \n"
						+ "{\n"
						+ "    Description : \"Crossing symmetric triangle construction.\"\n"
						+ "    Points:\n"
						+ "    {\n"
						+ "        Point(A)(3.0, 6.0)\n"
						+ "        Point(B)(2.0, 4.0)\n"
						+ "        Point(C)(4.0, 4.0)\n"
						+ "        Point(D)(0.0, 0.0)\n"
						+ "        Point(E)(6.0, 0.0)\n"
						+ "    }\n"
						+ "    Segments:\n"
						+ "    {\n"
						+ "        A : B  C  \n"
						+ "        B : C  D  E  \n"
						+ "        C : D  E  \n"
						+ "        D : E  \n"
						+ "    }\n"
						+ "}\n";
		  
			  StringBuilder sb = new StringBuilder(); 
			  UnparseVisitor unparser = new UnparseVisitor();
			  unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

			  assertEquals(expected, sb.toString());
		  }
		 

		
		  @Test 
		  void fully_connected_irregular_polygon_GeoTest() { 
			  Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest(
					  				"jsonfiles/fully_connected_irregular_polygon.json");
		  
			  assertTrue(node instanceof FigureNode);
			  
			  String expected = "Figure \n"
						+ "{\n"
						+ "    Description : \"Irregular pentagon in which each vertex is connected to each other.\"\n"
						+ "    Points:\n"
						+ "    {\n"
						+ "        Point(A)(0.0, 0.0)\n"
						+ "        Point(B)(4.0, 0.0)\n"
						+ "        Point(C)(6.0, 3.0)\n"
						+ "        Point(D)(3.0, 7.0)\n"
						+ "        Point(E)(-2.0, 4.0)\n"
						+ "    }\n"
						+ "    Segments:\n"
						+ "    {\n"
						+ "        A : B  C  D  E  \n"
						+ "        B : C  D  E  \n"
						+ "        C : D  E  \n"
						+ "        D : E  \n"
						+ "    }\n"
						+ "}\n";
		  
			  StringBuilder sb = new StringBuilder(); 
			  UnparseVisitor unparser = new UnparseVisitor();
			  unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

			  assertEquals(expected, sb.toString());
		  }
		 

		
		  @Test 
		  void Tri_Quad_GeoTest() { 
			  Entry<PointDatabase, Set<Segment>> node = runToGeoRepTest("jsonfiles/Tri_Quad.json");
		  
			  assertTrue(node instanceof FigureNode);
			  
			  String expected = "Figure \n"
						+ "{\n"
						+ "    Description : \"Tri Quad Shape.\"\n"
						+ "    Points:\n"
						+ "    {\n"
						+ "        Point(A)(4.0, 0.0)\n"
						+ "        Point(B)(8.0, 0.0)\n"
						+ "        Point(C)(4.0, 5.0)\n"
						+ "        Point(D)(8.0, 5.0)\n"
						+ "        Point(E)(0.0, 10.0)\n"
						+ "        Point(F)(12.0, 10.0)\n"
						+ "        Point(G)(4.0, 12.0)\n"
						+ "        Point(H)(8.0, 12.0)\n"
						+ "        Point(I)(6.0, 10.0)\n"
						+ "    }\n"
						+ "    Segments:\n"
						+ "    {\n"
						+ "        A : B  C  \n"
						+ "        B : D  \n"
						+ "        C : D  E  I  \n"
						+ "        D : F  I  \n"
						+ "        E : G  \n"
						+ "        F : H  \n"
						+ "        G : I  \n"
						+ "        H : I  \n"
						+ "    }\n"
						+ "}\n";
		  
			  StringBuilder sb = new StringBuilder(); 
			  UnparseVisitor unparser = new UnparseVisitor();
			  unparser.visitFigureNode((FigureNode)node,
					new AbstractMap.SimpleEntry<StringBuilder, Integer>(sb, 0));	  

			  assertEquals(expected, sb.toString());
		  }
}
