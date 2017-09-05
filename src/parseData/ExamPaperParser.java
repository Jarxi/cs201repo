package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class ExamPaperParser {
	private Document doc;
	private Element termNode;
	public ExamPaperParser(Document doc, Element termNode){
		this.doc = doc;
		this.termNode = termNode;
	}
	public String info(Element node, String name){
		NodeList nList = node.getElementsByTagName(name);
		if (nList.getLength()!=0){
			Node desNode = nList.item(0);
			return desNode.getFirstChild().getNodeValue();
		}
		return "";
	}
	public ArrayList<ExamPaper> parse(){
		NodeList nList = termNode.getElementsByTagName("exam");
		int length = nList.getLength();
		ArrayList<ExamPaper> examPaperList = new ArrayList<ExamPaper>();
		for(int i=0; i<length; i++){
			Element node =(Element) nList.item(i);
			if(i==0){
				String title2 = info((Element) nList.item(i+1),"title");
				if(info(node,"title").equals(title2)){
					continue;
				}
			}
			String title = "";
			ArrayList<File> fileList = new ArrayList<File>();
			
			//title
			title = info(node,"title");

			//files
			NodeList fileNodeList = node.getElementsByTagName("files");
			if(fileNodeList.getLength()!=0){
				Element theNode = (Element)fileNodeList.item(0);
				FileParser fp = new FileParser(doc,theNode);
				fileList = fp.parse();
			}
			ExamPaper createExamPaper = new ExamPaper(title, fileList);
			examPaperList.add(createExamPaper);
		}
		return examPaperList;
	}
	
}
