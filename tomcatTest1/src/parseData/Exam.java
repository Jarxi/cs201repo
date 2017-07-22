package parseData;

import java.util.ArrayList;

public class Exam {
	private ArrayList<Term> _termList;

	public ArrayList<Term> getTermList(){
		return _termList;
	}
	
	public Exam(ArrayList<Term> termList){
		_termList = termList;
	}
}
