package parseData;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DepartmentParser {
	private NodeList departmentList;
	private Document doc;
	private ArrayList<Department> departments = new ArrayList<Department>();
	public DepartmentParser(Document doc, Element schoolNode){
		  departmentList =schoolNode.getElementsByTagName("department");
		  this.doc = doc;
	}
	
	public ArrayList<Department> parse(){
		int length = departmentList.getLength();
		for (int i=0; i<length; i++){
			  //longname
			Element departmentNode = (Element)departmentList.item(i);
			
			Node longnameNode = departmentNode.getElementsByTagName("longName").item(0);
			String longname =longnameNode.getFirstChild().getNodeValue();
			  
			//prefix
			Node prefixNode = departmentNode.getElementsByTagName("prefix").item(0);
			String prefix = prefixNode.getFirstChild().getNodeValue();

			//course
			CourseParser cp = new CourseParser(doc, departmentNode);
			ArrayList<Course> courses = cp.parse();
			Department dp = new Department(longname, prefix, courses);
			departments.add(dp);
		}
		return departments;
	}
}
