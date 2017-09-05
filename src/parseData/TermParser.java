package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class TermParser {
	private Document doc;
	private Element node;
	public TermParser(Document doc, Element node){
		this.doc = doc;
		this.node = node;
	}
	
	public ArrayList<Term> parse(){
		
		ArrayList<Term> termList = new ArrayList<Term>();
		NodeList nList = node.getElementsByTagName("term");
		int length = nList.getLength();
		for(int i=0; i<length; i++){
			String semester = "";
			String year = "";
			ArrayList<ExamPaper> examPaperList =  new ArrayList<ExamPaper>();
			Element theNode =(Element) nList.item(i);
			//semester
			semester = theNode.getAttribute("semester");
			//year
			year = theNode.getAttribute("year");
			
			//exampaper
			ExamPaperParser epp = new ExamPaperParser(doc, theNode);
			examPaperList = epp.parse();
			
			Term createTerm = new Term(semester, year, examPaperList);
			termList.add(createTerm);
			
		}
		return termList;
	}
}
