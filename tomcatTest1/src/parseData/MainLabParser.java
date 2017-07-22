package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class MainLabParser {
	private Document doc;
	private Element mainLabNode;
	
	public MainLabParser(Document doc, Element mainLabNode){
		this.doc = doc;
		this.mainLabNode = mainLabNode;
	}
	
	public ArrayList<MainLab> parse(){
		ArrayList<MainLab> labList = new ArrayList<MainLab>();
		NodeList nList = mainLabNode.getElementsByTagName("lab");
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			Element node = (Element)nList.item(i);
			//number
			String number = node.getAttribute("number");
			
			//title
			Node titleNode = node.getElementsByTagName("title").item(0);
			String title = titleNode.getFirstChild().getNodeValue();
			
			//url
			Node urlNode = node.getElementsByTagName("url").item(0);
			String url = urlNode.getFirstChild().getNodeValue();
			
			//files
			NodeList filesNodeList = node.getElementsByTagName("files");
			ArrayList<File> fileList = new ArrayList<File>();
			if (filesNodeList.getLength()!=0){
				Element filesNode =(Element) node.getElementsByTagName("files").item(0);
				
				FileParser fp = new FileParser(doc, filesNode);
				fileList = fp.parse();
			}

			MainLab createLab = new MainLab(number, title, url, fileList);
			labList.add(createLab);			
		}
		return labList;
	}
}
