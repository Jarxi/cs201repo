package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class FileParser {
	private Document doc;
	private Element fileNode;
	public FileParser(Document doc, Element fileNode){
		this.doc = doc;
		this.fileNode=fileNode;
	}
	
	public ArrayList<File> parse(){
		ArrayList<File> fileList = new ArrayList<File>();
		NodeList nList = fileNode.getElementsByTagName("file");
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			Element node = (Element) nList.item(i);
			//number
			String number =node.getAttribute("number");
			
			//title
			Node titleNode = node.getElementsByTagName("title").item(0);
			String title = titleNode.getFirstChild().getNodeValue();
			
			//url
			Node urlNode = node.getElementsByTagName("url").item(0);
			String url = urlNode.getFirstChild().getNodeValue();
			
			
			File createFile = new File(number, title, url);
			fileList.add(createFile);
		}
		return fileList;
	}
}
