package view;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *Project class stores the project object.
 *Coded by Alisher.
 *Modified by Travis on 6/7/17 (modified fields, constructors, getters)
 */
@SuppressWarnings("serial")
public class Project implements Serializable {

	private String projectName;

	private Double cost;

	private String myProjectDescription;

	private ArrayList<String> myMeasurements;
	
	private ArrayList<Item> myItems;

	/**
	 * Creates a Project with only a name of a project.
	 * @param theName	project name.
	 */
	public Project(final String theName)  {
		projectName = theName;
	}

	/**
	 * Creates a Project with name, total cost and material of a project.
	 * @param theName	project name.
	 * @param theCost	total cost of project.
	 * @param theMaterial	material of project.
	 */
	public Project(String theName, String theCost) {
		projectName = theName;
		double temp = Double.parseDouble(theCost);
		cost = temp;
	}

	/**
	 *Creates a Dummy project with the fields listed below.
	 * @param projectName project name.
	 * @param projectDescription project description.
	 * @param measurements	the list of measurements
	 * @param items	first	the list of items
	 * @param totalCost	total cost of project
	 */
	public Project(String projectName, String projectDescription,
						ArrayList<String> measurements,
						ArrayList<Item> items, String totalCost) {
		this.projectName = projectName;
		this.myProjectDescription = projectDescription;
		this.myMeasurements = measurements;
		myItems = items;
		double temp = Double.parseDouble(totalCost);
		this.cost = temp;
	}

	/**
	 *Returns project name.
	 * @return	projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 *Returns total cost of project.
	 * @return	cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 *Returns measurements.
	 * @return myMeasurements
	 */
	public ArrayList<String> getMyMeasurements() {
		return myMeasurements;
	}

	/**
	 *Returns project description.
	 * @return	myProjectDescription
	 */
	public String getMyProjectDescription() {
		return myProjectDescription;
	}
	
	/**
	 *Returns items.
	 * @return	myItems
	 */
	public ArrayList<Item> getItems()
	{
		return myItems;
	}
}