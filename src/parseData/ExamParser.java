package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class ExamParser {
	private Document doc;
	private Element node;
	public ExamParser(Document doc, Element node){
		this.doc = doc;
		this.node = node;
	}
	
	public Exam parse(){
		Element termsNode = (Element)node.getElementsByTagName("terms").item(0);
		TermParser tp = new TermParser(doc, termsNode);
		ArrayList<Term> termList= new ArrayList<Term>();
		termList = tp.parse();
		Exam createExam = new Exam(termList);
		return createExam;
	}
	
}
