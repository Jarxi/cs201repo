<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Previous Exam</title>
</head>
<body text = "#333333" bgcolor = "#EEEEEE">
<%@ page import="java.io.*,java.util.*" %>
<%@ page import="parseData.*" %>
<%@ page import="parseData.School" %>
<%! 
  parseData pd = new parseData();
%>
<div>
   <% 
   
   		//school
		School school=(School)request.getSession().getAttribute("school");
		//course
		Course course = school.getDepartment().get(0).getCourses().get(0);
		//department
		Department department =school.getDepartment().get(0);
   		
   		//header
   		String courseName = department.getPrefix()+" "+course.getNumber();
   		String title = course.getTitle();
   		String unit = course.getUnit()+" units";
   		String term = course.getTerm()+ " "+course.getYear();
   		
   		//meetings
   		ArrayList<Meeting> meetings = course.getMeetings();
   		int numMeeting = meetings.size();
   		
   		//staffMembers
   		ArrayList<StaffMember> staffMembers = course.getStaffMember();
   		int numStaffMember = staffMembers.size();
   		//instructor image
   		String instructorImage = "";
   		String instructorName = "";
   		String instructorOffice = "";
   		String instructorOfficeHour ="";
   		String instructorPhone = "";
   		String instructorEmail = "";
   		ArrayList<StaffMember> instructorList = new ArrayList<StaffMember>();
   		
   		//Exam
   		Exam exam = course.getExam();
   		
   		//Exam terms
   		ArrayList<Term> termList = new ArrayList<Term>();
   		if(exam!=null){
   			termList = exam.getTermList();
   		}
   %>
</div>
<table>
	<tbody>
		<tr>
			<!-- column for left side of page -->
			<td width="120" valign="top" align="right">
				<a href= "http://www.usc.edu">
 						<img src=<%=school.getImage() %> border="0">
 					</a>
 					<br>
 					<br>
 					<font size="+1">
 						<a href="http://cs.usc.edu"><%=department.getPrefix()+" Department"%></a>
 					</font>
 					<br>
 					<br>
 					<font size="+1"><a href="http://localhost:8080/tomcatTest1/test.jsp"><%=department.getPrefix()+" "+course.getNumber()+ " Home" %></a></font>
 					<br>
 					<br>
 					<font size="+1"><a href=<%=course.getSyllabus() %>>Syllabus</a></font>
 					<br>
 					<br>
 					<font size="+1"><a href="lectures.jsp">Lectures</a></font>
 					<br>
 					<br>
 					<font size="+1"><a href="assignments.jsp">Assignments</font>
 					<br>
 					<br>
 					<font size="+1">Previous Exams</font>
 					<br>
 					<br>
 				
			</td>
			<!-- center column to sep-arate other two columns -->			
			<td width="5"><br></td>
			<!-- large column in center of page with dominant centent -->
			<td align = "baseline" width="615">
				<br>
 					<p>
 						<b>
 							<font size="+3"><% out.println(courseName); %></font>
 						</b>
 						<br>
 						<b>
 							<i>
 								<font size= "+1"><% out.println(title+" - "+unit); %></font>
 							</i>
 						</b>
 						<br>
 						<b>
 							<i>
 							
 								<font size="+1"><% out.println(term); %></font>
 							</i>
 						</b>
 					</p>
 					<p></p>
 					<hr size = "4"> 
 					<p></p>
 					<p></p>
 					
				<table border="2" cellpadding="5" width="590">
					<tbody>
						<tr>
							<th align="center">Semester</th>
							<th align="center">Written Exam #1</th>
							<th align="center">Programming Exam #1</th>
							<th>Written Exam #2</th>
							<th >Programming Exam #2</th>
							
						</tr>
						<%for(int i=0; i<termList.size();i++){ %>
						<tr>
						<!-- semester -->
						<td align = "center"><%=termList.get(i).getSemester()+" "+termList.get(i).getYear() %></td>
						
						
						<%ArrayList<ExamPaper> examPaperList = termList.get(i).getExamList();
							if(examPaperList!=null){
							for(int j=0; j<examPaperList.size(); j++){
						%>
						<!-- written exam 1 -->
						
						<%if (examPaperList.get(j).getTitle().equals("Written Exam #1")){ 
							/* ArrayList<File> fileList = examPaperList.get(j).getFileList(); */
							int fileLength=0;
							if(examPaperList.get(j).getFileList()!=null){
							fileLength =  examPaperList.get(j).getFileList().size();%>
							<td align="center">
							<%for(int k=0; k<fileLength; k++){%>
						
							<%if (k!=fileLength-1){%>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<hr>
							<%} else{%>
								<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<% }%>
						<%} %>
						</td>
						<%} %>
						<%} %>
						
						<!-- Programming Exam #1 -->
						<%if (examPaperList.get(j).getTitle().equals("Programming Exam #1")){ 
							/* ArrayList<File> fileList = examPaperList.get(j).getFileList(); */
							int fileLength=0;
							if(examPaperList.get(j).getFileList()!=null){
							fileLength =  examPaperList.get(j).getFileList().size();%>
							<td align="center">
							<%for(int k=0; k<fileLength; k++){%>
							<%if (k!=fileLength-1){%>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<hr>
							<%} else{ %>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<%} %>
						<%} %>
						</td>
						<%} %>
						<%} %>
						<!-- Written Exam #1 -->
						<%if (examPaperList.get(j).getTitle().equals("Written Exam #2")){ 
							/* ArrayList<File> fileList = examPaperList.get(j).getFileList(); */
							int fileLength=0;
							if(examPaperList.get(j).getFileList()!=null){
							fileLength =  examPaperList.get(j).getFileList().size();%>
							<td align="center">
							<%for(int k=0; k<fileLength; k++){%>
							<%if (k!=fileLength-1){%>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<hr>
							<%} else{ %>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<%} %>
						<%} %>
						</td>
						<%} %>
						<%} %>
						<!-- Programming Exam #2 -->
						<%if (examPaperList.get(j).getTitle().equals("Programming Exam #2")){ 
							int fileLength=0;
							if(examPaperList.get(j).getFileList()!=null){
							fileLength =  examPaperList.get(j).getFileList().size();%>
							<td align="center">
							<%for(int k=0; k<fileLength; k++){%>
							<%if (k!=fileLength-1){%>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<hr>
							<%} else{ %>
							<a href=<%=examPaperList.get(j).getFileList().get(k).getURL() %>>
								<%=examPaperList.get(j).getFileList().get(k).getTitle() %>
							</a>
							<%} %>
						<%} %>
						</td>
						<%} %>
						<%} %>
						
						<%} %><!-- j forloop -->
						<%} %>
						</tr>
						<%} %>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>