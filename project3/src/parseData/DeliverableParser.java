package parseData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
public class DeliverableParser {
	private Document doc;
	private Element node;
	public String info(Element node, String name){
		NodeList nList = node.getElementsByTagName(name);
		if (nList.getLength()!=0){
			Node desNode = nList.item(0);
			return desNode.getFirstChild().getNodeValue();
		}
		return "";
	}
	public DeliverableParser(Document doc, Element node){
		this.doc = doc;
		this.node = node;
	}
	public ArrayList<Deliverable> parse(){
		
		ArrayList<Deliverable> deliverableList = new ArrayList<Deliverable>();
		
		NodeList nList = node.getElementsByTagName("deliverable");
		int length = nList.getLength();
		for(int i=0; i<length; i++){
			Element dNode =(Element) nList.item(i);
			String number=  "";
			String dueDate = "";
			String title = "";
			String gradePercentage = "";
			ArrayList<File> fileList = new ArrayList<File>();
			//number
			number = dNode.getAttribute("number");
			
			//dueDate
			dueDate = info(dNode,"dueDate");
			
			//title
			title = info(dNode,"title");
			
			//gradePercentage
			gradePercentage = info(dNode,"gradePercentage");
			
			//fileList
			NodeList fileNodeList = dNode.getElementsByTagName("files");
			if(fileNodeList.getLength()!=0){
				Element fileNode =(Element) fileNodeList.item(0);
				FileParser fp = new FileParser(doc,fileNode);
				fileList = fp.parse();
			}
			
			Deliverable createDel = new Deliverable(number, dueDate, title, gradePercentage, fileList);
			deliverableList.add(createDel);
		}
		return deliverableList;
	}
}
