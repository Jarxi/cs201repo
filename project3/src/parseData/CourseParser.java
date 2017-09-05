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
			
			//title
			Node titleNode = node.getElementsByTagName("title").item(0);
			String title = titleNode.getFirstChild().getNodeValue();
			
			//unit
			Node unitNode = node.getElementsByTagName("units").item(0);
			String unit = unitNode.getFirstChild().getNodeValue();
			
			//staffMember
			StaffMemberParser smp = new StaffMemberParser(doc, number);
			ArrayList<StaffMember> staffMembers =  (ArrayList<StaffMember>) smp.parse();
			
			//meetings
			meetingParser mp = new meetingParser(doc,number, staffMembers);
			ArrayList<Meeting> meetings = (ArrayList<Meeting>) mp.parse();
			//Syllabus
			Element syllabusNode = (Element) node.getElementsByTagName("syllabus").item(0);
			Node urlNode = syllabusNode.getElementsByTagName("url").item(0);
			String url = urlNode.getFirstChild().getNodeValue();
			//lectures
			Element lectureNode = (Element) node.getElementsByTagName("lectures").item(0);
			MainLectureParser mlp = new MainLectureParser(doc, lectureNode);
			ArrayList<MainLecture> mainLectureList = mlp.parse();
		
			//assignments
			Element assignmentNode = (Element) node.getElementsByTagName("assignments").item(0);
			AssignmentParser ap = new AssignmentParser(doc, assignmentNode);
			ArrayList<Assignment> assignmentList = ap.parse();
			//exams
			Element examNode = (Element) node.getElementsByTagName("exams").item(0);
			ExamParser ep = new ExamParser(doc, examNode);
			Exam theExam = ep.parse();
			
			
			Course createCourse = new Course(number, term, year, staffMembers, meetings, title, unit,
					url,mainLectureList, assignmentList, theExam);
			courseList.add(createCourse);
		}
		return courseList;
	}
}
