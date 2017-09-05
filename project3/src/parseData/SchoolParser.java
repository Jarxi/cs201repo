package parseData;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
public class SchoolParser {
	private Document doc;
	private ArrayList<School> schoolList = new ArrayList<School>();
	public SchoolParser(Document doc){
		this.doc = doc;
	}
	public ArrayList<School> parse(){
		NodeList nList = doc.getElementsByTagName("school");
		int length = nList.getLength();
		for (int i=0; i<length; i++){
			Element schoolNode = (Element) nList.item(i);
			//school name
			String schoolName = ((Element) schoolNode).getAttribute("name");
			
			//school image
			String schoolImage = ((Element) schoolNode).getAttribute("image");
			//department
			DepartmentParser dp = new DepartmentParser(doc,schoolNode);
			ArrayList<Department> departmentList = dp.parse();
			
			School createSchool = new School(schoolName, departmentList,schoolImage);
			schoolList.add(createSchool);
		}
		return schoolList;

	}
}
