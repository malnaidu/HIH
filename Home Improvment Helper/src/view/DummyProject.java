package view;
import java.io.Serializable;

public class DummyProject implements Serializable {

	private String projectName;

	private String cost;

	private String material;

	private String myProjectDescription;

	private String myMeasurement1;

	private String myMeasurement2;

	private String myPrice1;

	private String myPrice2;

	public DummyProject(final String theName)  {
		projectName = theName;
	}

	public DummyProject(String theName, String theCost, String theMaterial) {
		projectName = theName;
		cost = theCost;
		material = theMaterial;
	}

	public DummyProject(String projectName, String projectDescription,
						String measurement1, String measurement2,
						String price1, String price2, String totalCost) {
		this.projectName = projectName;
		this.myProjectDescription = projectDescription;
		this.myMeasurement1 = measurement1;
		this.myMeasurement2 = measurement2;
		this.myPrice1 = price1;
		this.myPrice2 = price2;
		this.cost = totalCost;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getCost() {
		return cost;
	}

	public String getMaterial() {
		return material;
	}

	public String getMyPrice1() {
		return myPrice1;
	}

	public String getMyMeasurement2() {
		return myMeasurement2;
	}

	public String getMyPrice2 () {
		return myPrice2;
	}

	public String getMyMeasurement1() {
		return myMeasurement1;
	}

	public String getMyProjectDescription() {
		return myProjectDescription;
	}

}