package parseData;

import org.w3c.dom.NodeList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.List;
public class StaffMemberParser {
	private Document doc;
	private String courseNumber;
	String _type="";
	String _ID = "";
	ArrayList<StaffMember> _assistants = new ArrayList<StaffMember>();
	public StaffMemberParser(Document doc, String courseNumber){
		this.doc = doc;
		this.courseNumber = courseNumber;
	}
	public String info(Element node, String name){
		NodeList nList = node.getElementsByTagName(name);
		if (nList.getLength()!=0){
			Node desNode = nList.item(0);
			return desNode.getFirstChild().getNodeValue();
		}
		return "";
	}
	private void parseSingleStaff(Element node, String staff){
	}
	private void parseStaffMembers(Element node, String staff, String id){
		String fname = "";
		String lname = "";
		String email = "";
		String image= "";
		String office ="";
		String phone="";
		ArrayList<String> officeHours = new ArrayList<String>();
		
		//fname
		fname = info(node, "fname");
		//lname
		lname = info(node, "lname");

		//email
		email = info(node, "email");
		//image
		image = info(node, "image");
		
		//phone
		phone = info(node, "phone");
		//office
		office = info(node, "office");
		
		//officeHour
		NodeList officeHourList = node.getElementsByTagName("officeHour");
		for (int i=0; i<officeHourList.getLength();i++){
			String day = info((Element)officeHourList.item(i),"day");
			String start = info((Element)officeHourList.item(i),"start");
			String end = info((Element)officeHourList.item(i),"end");
			String officeHour="";
			if(start!=""){
				officeHour = day+" "+start+"-"+end;
			}
			else{
				officeHour = day;
			}
			officeHours.add(officeHour);
		}
		StaffMember instruct = null;
		if (staff.equals("instructor")){
			 instruct = new Instructor(staff, id,
				fname,lname,email,image, office,officeHours,phone);
			
		}
		else if(staff.equals("ta")){
			 instruct = new TA(staff, id,
					 fname,lname,email,image, office,officeHours);
		}
		else if (staff.equals("cp")){
			 instruct = new CP(staff, id,
					 fname,lname,email,image, office,officeHours);
		}
		else if(staff.equals("grader")){
			 instruct = new Grader(staff, id,
					 fname,lname,email,image, office,officeHours);
		}
		_assistants.add(instruct);

	}
	public List<StaffMember> parse(){
		NodeList nList = doc.getElementsByTagName("course");
		int length = nList.getLength();
		int findNode = -1;
		for (int i=0; i<length; i++){
			Element node = (Element)nList.item(i);
			String course = (String)node.getElementsByTagName("number").item(0).getFirstChild().getNodeValue();
			if (course == courseNumber) {
				findNode = i;				
				break;
			}
		}
		if (findNode != -1){
			Element node = (Element)nList.item(findNode);
			if (node.getElementsByTagName("staffMember").getLength()==0){
				if (node.getElementsByTagName("instructor").getLength()!=0){
					parseSingleStaff(node, "instructor");
				}
				if (node.getElementsByTagName("cp").getLength()!=0){
					parseSingleStaff(node, "cp");
				}
				if (node.getElementsByTagName("ta").getLength()!=0){
					parseSingleStaff(node, "ta");
				}
				if (node.getElementsByTagName("grader").getLength()!=0){
					parseSingleStaff(node, "grader");
				}
			}
			else{
				int numStaffs=node.getElementsByTagName("staffMember").getLength();
				for (int i = 0 ; i<numStaffs; i++){
					_type = (((Element) node.getElementsByTagName("staffMember").item(i)).getAttribute("type"));
					_ID= (((Element) node.getElementsByTagName("staffMember").item(i)).getAttribute("id"));
					parseStaffMembers(((Element) node.getElementsByTagName("staffMember").item(i)),_type,_ID);
				}
			}

		}
		return _assistants;
	}
}
