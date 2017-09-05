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
	private String _type = "";
	private String _ID = "";
	private String _fname = "";
	private String _lname = "";
	private String _email = "";
	private String _image = "";
	private String _phone = "";
	private String _office = "";
	private String _officeHour = "";
	private List<StaffMember> _assistants = new ArrayList<StaffMember>();
	
	public StaffMemberParser(Document doc, String courseNumber){
		this.doc = doc;
		this.courseNumber = courseNumber;
	}
	private void parseSingleStaff(Element node, String staff){
		Node fnameNode = node.getElementsByTagName(staff).item(0).getFirstChild();
		Node siblingNode = fnameNode.getNextSibling();
		while (siblingNode.getNodeName() != null){
			if (siblingNode instanceof Element){
				if (siblingNode.getNodeName() == "fname"){
					_fname = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="lname"){
					_lname = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName() == "email"){
					_email = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="image"){
					_image = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="office"){
					_office = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="phone"){
					_phone = ( siblingNode).getFirstChild().getNodeValue();
				}
				else{
					NodeList dayNode = ((Element) siblingNode).getElementsByTagName("day");
					NodeList startNode = ((Element) siblingNode).getElementsByTagName("start");
					NodeList endNode = ((Element) siblingNode).getElementsByTagName("end");
					String officeHour="";
					if (startNode.getLength() != 0){
						for (int i=0; i<startNode.getLength()-1; i++){
							String time = startNode.item(i).getFirstChild().getNodeValue();
							time = time +"-"+ endNode.item(i).getFirstChild().getNodeValue();
							officeHour = officeHour + time+", "; 
						}
						String time=startNode.item(startNode.getLength()-1).getFirstChild().getNodeValue();
						time = time +"-"+ endNode.item(startNode.getLength()-1).getFirstChild().getNodeValue();
						officeHour = officeHour + time;
						_officeHour = officeHour;
					}
					
				}
			}
			siblingNode = siblingNode.getNextSibling();
			if (siblingNode == null){
				break;
			}
		}
		StaffMember instruct = null;
		if (staff.equals("instructor")){
			 instruct = new Instructor(staff, "0",
				_fname,_lname,_email,_image, _office,_officeHour,_phone);
			
		}
		else if(staff.equals("ta")){
			 instruct = new TA(staff, "0",
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		else if (staff.equals("cp")){
			 instruct = new CP(staff, "0",
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		else if(staff.equals("grader")){
			 instruct = new Grader(staff, "0",
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		_assistants.add(instruct);
//		System.out.print(instruct.toString());
	}
	private void parseStaffMembers(Element node, String staff, String id){
		Node fnameNode = node.getFirstChild();
		Node siblingNode = fnameNode.getNextSibling();
		while (siblingNode.getNodeName() != null){
			if (siblingNode instanceof Element){
				if (siblingNode.getNodeName() == "fname"){
					_fname = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="lname"){
					_lname = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if(siblingNode.getNodeName()=="name"){
					_fname = ((Element) siblingNode).getElementsByTagName("fname").item(0).getFirstChild().getNodeValue();
					_lname = ((Element) siblingNode).getElementsByTagName("lname").item(0).getFirstChild().getNodeValue();
					
				}
				else if (siblingNode.getNodeName() == "email"){
					_email = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="image"){
					_image = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="office"){
					_office = ( siblingNode).getFirstChild().getNodeValue();
				}
				else if (siblingNode.getNodeName()=="phone"){
					_phone = ( siblingNode).getFirstChild().getNodeValue();
				}
				else{
					NodeList dayNode = ((Element) siblingNode).getElementsByTagName("day");
					NodeList startNode = ((Element) siblingNode).getElementsByTagName("start");
					NodeList endNode = ((Element) siblingNode).getElementsByTagName("end");
					String officeHour="";
					if (startNode.getLength()!=0){
						for (int i=0; i<startNode.getLength()-1; i++){
							String time = startNode.item(i).getFirstChild().getNodeValue();
							time = time +"-"+ endNode.item(i).getFirstChild().getNodeValue();
							officeHour = officeHour + time+", "; 
						}
						String time=startNode.item(startNode.getLength()-1).getFirstChild().getNodeValue();
						time = time +"-"+ endNode.item(startNode.getLength()-1).getFirstChild().getNodeValue();
						officeHour = officeHour + time;
						_officeHour = officeHour;
					}
					
				}
			}
			siblingNode = siblingNode.getNextSibling();
			if (siblingNode == null){
				break;
			}
		}
		StaffMember instruct = null;
		if (staff.equals("instructor")){
			 instruct = new Instructor(staff, id,
				_fname,_lname,_email,_image, _office,_officeHour,_phone);
			
		}
		else if(staff.equals("ta")){
			 instruct = new TA(staff, id,
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		else if (staff.equals("cp")){
			 instruct = new CP(staff, id,
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		else if(staff.equals("grader")){
			 instruct = new Grader(staff, id,
					_fname,_lname,_email,_image, _office,_officeHour);
		}
		_assistants.add(instruct);
//		 System.out.print(instruct.toString());
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
