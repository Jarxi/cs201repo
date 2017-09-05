package parseData;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class CourseParser {
	private ArrayList<Course> courseList = new ArrayList<Course>();
	private NodeList nList;
	private Document doc;
	public CourseParser(Document doc,Element departmentNode){
		//number
		nList = departmentNode.getElementsByTagName("course");
		this.doc = doc;
	}
	
	public ArrayList<Course> parse(){
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			Element node =(Element) nList.item(i);
			//number
			Node numberNode = node.getElementsByTagName("number").item(0);
			String number = numberNode.getFirstChild().getNodeValue();
			
			//term
			Node termNode = node.getElementsByTagName("term").item(0);
			String term = termNode.getFirstChild().getNodeValue();
			
			//year
			Node yearNode = node.getElementsByTagName("year").item(0);
			String year = yearNode.getFirstChild().getNodeValue();
			
			//staffMember
			StaffMemberParser smp = new StaffMemberParser(doc, number);
			ArrayList<StaffMember> staffMembers =  (ArrayList<StaffMember>) smp.parse();
			
			//meetings
			meetingParser mp = new meetingParser(doc,number, staffMembers);
			ArrayList<Meeting> meetings = (ArrayList<Meeting>) mp.parse();
			
			Course createCourse = new Course(number, term, year, staffMembers, meetings);
			courseList.add(createCourse);
		}
		return courseList;
	}
}
