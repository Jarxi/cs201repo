package parseData;

import java.util.ArrayList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class meetingParser {
	private Document doc;
	private String courseNumber;
	private ArrayList<StaffMember> _assistantsList;
	public meetingParser(Document doc, String courseNumber, ArrayList<StaffMember> staffMembers){
		this.doc = doc;
		this.courseNumber = courseNumber;
		_assistantsList = staffMembers;
	}
	
	
	public ArrayList<Meeting> parse(){
	
		ArrayList<Meeting> meetingList = new ArrayList<Meeting>();
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
			if (node.getElementsByTagName("meeting").getLength()!=0){
				for (int i =0; i<node.getElementsByTagName("meeting").getLength(); i++){
					String _type = "";
					String _section = "";
					String _room = "";
					String _meetingDay = "";
					String _meetingTime = "";
					ArrayList<StaffMember> _assistants = new ArrayList<StaffMember>();
					//type
					Element mNode =(Element) node.getElementsByTagName("meeting").item(i);
					_type = mNode.getAttribute("type");
					//section
					Element sectionNode = (Element)mNode.getElementsByTagName("section").item(0);
					if (sectionNode!=null){
						_section = sectionNode.getFirstChild().getNodeValue();
					}
					//room
					Element roomNode = (Element)mNode.getElementsByTagName("room").item(0);
					if (roomNode!=null){
						_room = roomNode.getFirstChild().getNodeValue();
					}
					
					//meetingDay
					Element meetingDayNode = (Element)mNode.getElementsByTagName("meetingPeriods").item(0);
					if (meetingDayNode!=null){ 
						String meetingDay="";
						String day= "";
						for (int j = 0; j<meetingDayNode.getElementsByTagName("day").getLength()-1;j++){
							Element dayNode = (Element)meetingDayNode.getElementsByTagName("day").item(j);
							day = dayNode.getFirstChild().getNodeValue();
							meetingDay = meetingDay + day + "/";
						}
						int index = meetingDayNode.getElementsByTagName("day").getLength()-1;
						Element dayNode = (Element)meetingDayNode.getElementsByTagName("day").item(index);
						day = dayNode.getFirstChild().getNodeValue();
						meetingDay = meetingDay +day;
						_meetingDay = meetingDay;
//						System.out.println(_meetingDay);
					}
					
					//meetingTime
					Element meetingTimeNode = (Element)mNode.getElementsByTagName("meetingTime").item(0);
					Element timeNode = (Element)mNode.getElementsByTagName("time").item(0);
					if(meetingTimeNode == null && timeNode!=null){
						meetingTimeNode = timeNode;
					}
					if (meetingTimeNode!=null){ 
						String meetingTime="";

						for (int j = 0; j<meetingTimeNode.getElementsByTagName("start").getLength()-1;j++){
							Element startNode = (Element)meetingDayNode.getElementsByTagName("start").item(j);
							Element endNode = (Element)meetingDayNode.getElementsByTagName("end").item(j);
							String start = startNode.getFirstChild().getNodeValue();
							String end = endNode.getFirstChild().getNodeValue();
							meetingTime = meetingTime + start + "-" +end + "\n";
						}
						int index = meetingTimeNode.getElementsByTagName("start").getLength()-1;
						Element startNode = (Element)meetingTimeNode.getElementsByTagName("start").item(index);
						Element endNode = (Element)meetingTimeNode.getElementsByTagName("end").item(index);
						String start = startNode.getFirstChild().getNodeValue();
						String end = endNode.getFirstChild().getNodeValue();
						meetingTime = meetingTime + start+ "-" + end;
						_meetingTime = meetingTime;
//						System.out.println(_meetingTime);
					}
					
					//assistants
					Element assistantsNode = (Element)mNode.getElementsByTagName("assistants").item(0);
					if (assistantsNode!=null){
						for (int j = 0; j<assistantsNode.getElementsByTagName("assistant").getLength();j++){
							Element theAssistantNode = (Element)assistantsNode.getElementsByTagName("assistant").item(j);
							for (int k=0; k<_assistantsList.size();k++){
								if (theAssistantNode.getAttribute("staffMemberID").equals(_assistantsList.get(k).getId()) ){
									_assistants.add(_assistantsList.get(k));
									//System.out.println(_assistantsList.get(k).toString());
								}
							}
						}
					}
					if (_type.equals("lecture")){
						Meeting meeting = new Lecture("lecture",_section,_room,_meetingDay,_meetingTime,_assistants);
						meetingList.add(meeting);
					}
					else if(_type.equals("lab")){
						Meeting meeting = new Lab("lab",_section,_room,_meetingDay,_meetingTime,_assistants);
						meetingList.add(meeting);
					}
					else if(_type.equals("quiz")){
						Meeting meeting = new Quiz("quiz",_section,_room,_meetingDay,_meetingTime,_assistants);
						meetingList.add(meeting);
					}
					
				}
			}
			
		}
		return meetingList;
	}
}
