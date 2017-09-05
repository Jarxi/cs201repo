package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class MainLectureParser {
	Element mainLectureNode;
	Document doc;
	public MainLectureParser(Document doc, Element mainLectureNode){
		this.mainLectureNode = mainLectureNode;
		this.doc = doc;
	}
	
	public ArrayList<MainLecture> parse(){
		ArrayList<MainLecture> lectureList = new ArrayList<MainLecture>();
		//textBook
		Element textbookNode = (Element) mainLectureNode.getElementsByTagName("textbooks").item(0);
		TextbookParser tbp = new TextbookParser(doc,textbookNode);
		ArrayList<Textbook> textbookList = tbp.parse();
		
		//lecture
		NodeList nList = mainLectureNode.getElementsByTagName("lecture");
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			String week="";
			String number = "";
			String date = "";
			String day ="";
			ArrayList<MainLab> labList = new ArrayList<MainLab>();
			ArrayList<Topic> topicList = new ArrayList<Topic>();
			
			Element node = (Element)nList.item(i);
			//week
			week = node.getAttribute("week");
			
			//number
			number = node.getAttribute("number");
			
			//date
			Node dateNode = node.getElementsByTagName("date").item(0);
			date = dateNode.getFirstChild().getNodeValue();
			
			//day
			Node dayNode = node.getElementsByTagName("day").item(0);
			day = dayNode.getFirstChild().getNodeValue();
			
			//labs
			NodeList labNodeList = node.getElementsByTagName("labs");
			if(labNodeList.getLength()!=0){
				Element labsNode = (Element)node.getElementsByTagName("labs").item(0);
				MainLabParser mlp = new MainLabParser(doc, labsNode);
				labList = mlp.parse();
			}
			
			
			//topics
			Element topicsNode = (Element) node.getElementsByTagName("topics").item(0);
			TopicParser tp = new TopicParser(doc, topicsNode);
			topicList = tp.parse();
			
			MainLecture createLecture = new MainLecture(week, number, textbookList, date, day, labList, topicList);
			lectureList.add(createLecture);
		}
		return lectureList;
	}
}
