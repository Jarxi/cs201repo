package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
public class TextbookParser {
	Element mainTextbookNode;
	Document doc;
	public TextbookParser(Document doc, Element mainTextbookNode){
		this.mainTextbookNode = mainTextbookNode;
		this.doc = doc;
	}
	
	public ArrayList<Textbook> parse(){
		NodeList nList = mainTextbookNode.getElementsByTagName("textbook");
		int length = nList.getLength();
		ArrayList<Textbook> textbookList = new ArrayList<Textbook>();
		for (int i=0; i<length; i++){
			Element textbookNode =(Element) nList.item(i);
			//number
			String number = textbookNode.getAttribute("number");
			
			//author
			Node authorNode = textbookNode.getElementsByTagName("author").item(0);
			String author = authorNode. getFirstChild().getNodeValue();
			
			//title
			Node titleNode = textbookNode.getElementsByTagName("title").item(0);
			String title=titleNode.getFirstChild().getNodeValue();
			
			//publisher
			Node publisherNode = textbookNode.getElementsByTagName("publisher").item(0);
			String publisher = publisherNode.getFirstChild().getNodeValue();
			
			//year
			Node yearNode = textbookNode.getElementsByTagName("year").item(0);
			String year = yearNode.getFirstChild().getNodeValue();
			
			//isbn
			Node isbnNode = textbookNode.getElementsByTagName("isbn").item(0);
			String isbn = isbnNode.getFirstChild().getNodeValue();
			Textbook createTextbook = new Textbook(number, author, title,publisher, year, isbn);
			textbookList.add(createTextbook);
		}
		return textbookList;
	}
}
