package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class AssignmentParser {
	private Element assignmentNode;
	private Document doc;
	public AssignmentParser(Document doc, Element assignmentNode){
		this.assignmentNode = assignmentNode;
		this.doc = doc;
	}
	public String info(Element node, String name){
		NodeList nList = node.getElementsByTagName(name);
		if (nList.getLength()!=0){
			Node desNode = nList.item(0);
			if (desNode.getFirstChild()!=null){
				return desNode.getFirstChild().getNodeValue();
			}
			else{
				return "";
			}
		}
		return "";
	}
	public ArrayList<Assignment> parse(){
		
		//parse
		ArrayList<Assignment> assignmentList = new ArrayList<Assignment>();
		NodeList nList = assignmentNode.getElementsByTagName("assignment");
		int length =nList.getLength();
		for (int i=0; i<length; i++){
			String number = "";
			String assignedDate = "";
			String dueDate = "";
			String title = "";
			String url = "";
			ArrayList<File> fileList = new ArrayList<File>();
			String gradePercentage = "";
			String gradingCriteria = "";
			String solution = "";
			ArrayList<Deliverable> deliverableList = new ArrayList<Deliverable>();
			Element node = (Element) nList.item(i);
			//number
			number = node.getAttribute("number");
			
			//assignedDate
			assignedDate = info(node,"assignedDate");
			
			//dueDate
			dueDate = info(node,"dueDate");
			
			//title
			title = info(node,"title");
			System.out.println(title);
			//url
			NodeList nodeList =  node.getElementsByTagName("url");
			if (nodeList.getLength()!=0){
				Node urlNode = node.getElementsByTagName("url").item(0);
				if (urlNode.getFirstChild()!=null){
					url = urlNode.getFirstChild().getNodeValue();
				}
			}

			//files
			NodeList fileNodeList = node.getElementsByTagName("files");
			if (fileNodeList.getLength()!=0){
				Element filesNode = (Element)node.getElementsByTagName("files").item(0);
				FileParser fp = new FileParser(doc,filesNode);
				fileList = fp.parse();
			}
			
			//gradePercentage
			gradePercentage = info(node,"gradePercentage");
			
			//gradingCriteria
			gradingCriteria = info(node,"gradingCriteria");
			
			//solution
			solution = info(node,"solution");
			
			//deliverable
			NodeList delNodeList = node.getElementsByTagName("deliverables");
			if(delNodeList.getLength()!=0){
				Element delNode =(Element) delNodeList.item(0);
				DeliverableParser dp = new DeliverableParser(doc,delNode);
				deliverableList = dp.parse();
			}
			
			Assignment createAssignment = new Assignment(number, assignedDate,
					dueDate,title,url,fileList,gradePercentage,gradingCriteria,solution,deliverableList);
			assignmentList.add(createAssignment);
		}
		return assignmentList;
	}
	
}
