package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import view.Item;
import view.Project;
import view.UserProfile;

/**
 * Tests for UserProfile and Project classes.
 * Tests constructor, import and export methods.
 * Coded by Everyone.
 */

public class Tests {

	private static final double TOLERANCE = .000001;
	
	private UserProfile myProfile;
	
	private Project myProject;
	
	/**
	 * Initializes a test fixtures before each test.
	 */
	@Before
	public void setUp() {
		List<String> measures = new ArrayList<String>();
		measures.add("measure1");
		measures.add("measure2");
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("nameOfItem1", "7.11"));
		items.add(new Item("nameOfItem2", "12.3"));
		myProfile = new UserProfile("name", "easyPassword");
		myProject = new Project("name", "description", (ArrayList<String>) measures, (ArrayList<Item>) items, "10.0");
	}
	
	/**
	 * Tests UserProfile Constructor fields for correctness.
	 */
	@Test
	public void testProfileConstructor() {
		assertEquals("name", myProfile.getUsername());
		assertEquals("easyPassword", myProfile.getPassword());
	}
	
	/**
	 * Tests UserProfile's toString method.
	 */
	@Test
	public void testProfileToString() {
		assertEquals("name easyPassword", myProfile.toString());
	}
	

	/**
	 * Tests that UserProfile has been exported
	 * and imported properly.
	 */
	@Test
	public void testImport() {
		List<UserProfile> list = new ArrayList<UserProfile>();
		list.add(myProfile);
		UserProfile.export((ArrayList<UserProfile>) list);
		list = UserProfile.importData();
		System.out.println(list.size());
		assertEquals("name", list.get(0).getUsername());
	}
	
	/**
	 * Tests that profile.ser file was created successfully.
	 */
	@Test
	public void testExport() {
		List<UserProfile> list = new ArrayList<UserProfile>();
		UserProfile.export((ArrayList<UserProfile>) list);
		
		String path =  System.getProperty("user.dir") + "\\profile.ser";
		System.out.println(path);
		File dir = new File(path);
		assertTrue(dir.exists());
	}
	
	/**
	 * Tests string and double values of project constructor.
	 */
	@Test
	public void testProjectConstructorStrings() {
		assertEquals("name", myProject.getProjectName());
		assertEquals("description", myProject.getMyProjectDescription());
		assertEquals(10.0, myProject.getCost(), TOLERANCE);
	}
	
	/**
	 *Tests lists of project constructor.
	 */
	@Test
	public void testProjectConstructorList() {
		List<String> checkMeasures = myProject.getMyMeasurements();
		assertEquals("measure1", checkMeasures.get(0));
		assertEquals("measure2", checkMeasures.get(1));
	}
	
	/**
	 * Tests lists of project constructor as well as Item class getters.
	 */
	@Test
	public void testProjectConstructorItem() {
		List<Item> checkItems = myProject.getItems();
		assertEquals("nameOfItem1", checkItems.get(0).getName());
		assertEquals(12.3, checkItems.get(1).getPrice(), TOLERANCE);
	}
	
	
	
	
	
}
