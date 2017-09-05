package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class TopicParser {
	private Document doc;
	private Element topicNode;
	
	public TopicParser(Document doc, Element topicNode){
		this.doc = doc;
		this.topicNode = topicNode;
	}
	
	public ArrayList<Topic> parse(){
		
		ArrayList<Topic> topicList =new ArrayList<Topic>();
		NodeList nList = topicNode.getElementsByTagName("topic");
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			String number ="";
			String title = "";
			String url = "";
			String chapter = "";
			ArrayList<File> fileList = new ArrayList<File>();
			
			Element node = (Element) nList.item(i);
			//number
			number = node.getAttribute("number");
			
			//title
			Node titleNode = node.getElementsByTagName("title").item(0);
			title = titleNode.getFirstChild().getNodeValue();
			
			//url
			NodeList urlNodeList= node.getElementsByTagName("url");
			if(urlNodeList.getLength()!=0){
				Node urlNode = node.getElementsByTagName("url").item(0);
				url = urlNode.getFirstChild().getNodeValue();
			}
			
			
			//chapter
			NodeList chapterNodeList = node.getElementsByTagName("chapter");
			if (chapterNodeList.getLength()!=0){
				Node chapterNode = node.getElementsByTagName("chapter").item(0);
				chapter = chapterNode.getFirstChild().getNodeValue();
			}

			//files
			NodeList programNodeList = node.getElementsByTagName("program");
			if(programNodeList.getLength()!=0){
				Element programNode = (Element)node.getElementsByTagName("program").item(0);
				FileParser fp = new FileParser(doc,programNode);
				fileList = fp.parse();
			}
			
			
			Topic createTopic = new Topic(number, title, url, chapter, fileList);
			topicList.add(createTopic);
		}
		return topicList;
	}
}
