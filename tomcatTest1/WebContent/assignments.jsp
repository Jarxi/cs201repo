<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assignments</title>
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
   		//lecture
   		ArrayList<MainLecture> lectureList= course.getLectures();
   		//TextBook
   		ArrayList<Textbook> textBookList = course.getLectures().get(0).getTextbook();
   %>
</div>
<table>
	<tbody>
		<tr>
			<!-- column for left side of page -->
			<td width="180" valign="top" align="right">
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
 					<font size="+1">Assignments</font>
 					<br>
 					<br>
 					<font size="+1"><a href="exams.jsp">Previous Exams</font>
 					<br>
 					<br>
 				
			</td>
			<!-- center column to sep-arate other two columns -->			
			<td width="5"><br></td>
			<!-- large column in center of page with dominant centent -->
			<td align = "baseline" width = "615">
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
 					<b>Assignments</b>
 					
				<table border="2" cellpadding="5" width="590">
					<tbody>
						<tr>
							<th align="center">Homework #</th>
							<th align="center">Assigned</th>
							<th align="center">Due</th>
							<th align="center">Assignment</th>
							<th align="center" >% Grade</th>
							<th align="center">Grading Criteria</th>
							<th>Solution</th>
						</tr>
						<% int assignmentLength = course.getAssignments().size();
							ArrayList<Assignment> assignmentList = course.getAssignments();
							for(int i=0; i<assignmentLength;i++){%>
							<tr>
								<!-- Homework # -->
								<td align ="center">
									<%=assignmentList.get(i).getNumber() %>
								</td>
								
								<!-- Assigned -->
								<td align ="center">
									<%=assignmentList.get(i).getAssignedDate() %>
								</td>
								<%if(!assignmentList.get(i).getNumber().equals("Final Project")){ %>
								<!-- Due -->
								<td align ="center">
									<%=assignmentList.get(i).getDueDate() %>
								</td>
								
								<!-- Assignment -->
								<td align ="center">
									<%if(!assignmentList.get(i).getTitle().equals("")){ %>
									<a href=<%=assignmentList.get(i).getUrl() %>>
												<%=assignmentList.get(i).getTitle() %>
									</a>
									<hr>
									<%} %>
									<%
										if(assignmentList.get(i).getFileList()!=null){
										int fileLength = assignmentList.get(i).getFileList().size();
										/* ArrayList<File> fileList = assignmentList.get(i).getFileList(); */
										
										for (int j=0;j<fileLength;j++){
											if(j!=fileLength-1){
									%>
												<a href=<%=assignmentList.get(i).getFileList().get(j).getURL() %>>
												<%=assignmentList.get(i).getFileList().get(j).getTitle() %>
												</a>
												<hr>
									<%}else{ %>
										<a href=<%=assignmentList.get(i).getFileList().get(fileLength-1).getURL() %>>
												<%=assignmentList.get(i).getFileList().get(fileLength-1).getTitle() %> 
									<%} %>
									<%} %>
									<%} %>
								</td>
								
								<!-- grade percentage -->
								<td align = "center">
									<%=assignmentList.get(i).getGradingPercentage() %>
								</td>
								
								<td align = "center">
									<%=assignmentList.get(i).getGradingCriteria() %>
								</td>
								
								<td align = "center">
									<%=assignmentList.get(i).getSolution() %>
								</td>
								<%} else{  
									ArrayList<Deliverable> deliverableList = assignmentList.get(i).getDeliverableList();
									int delLength = deliverableList.size();
								%>
								<td colspan="5">
									<table border="1" cellpadding="5">
										<tbody>
										<!-- Assignment -->
								<tr>
								<td align ="center" colspan="3">
									<%if(!assignmentList.get(i).getTitle().equals("")){ %>
									<a href=<%=assignmentList.get(i).getUrl() %>>
												<%=assignmentList.get(i).getTitle() %>
									</a>
									<hr>
									<%} %>
									<%
										if(assignmentList.get(i).getFileList()!=null){
										int fileLength = assignmentList.get(i).getFileList().size();
										/* ArrayList<File> fileList = assignmentList.get(i).getFileList(); */
										
										for (int j=0;j<fileLength;j++){
											if(j!=fileLength-1){
									%>
												<a href=<%=assignmentList.get(i).getFileList().get(j).getURL() %>>
												<%=assignmentList.get(i).getFileList().get(j).getTitle() %>
												</a>
												<hr>
									<%}else{ %>
										<a href=<%=assignmentList.get(i).getFileList().get(fileLength-1).getURL() %>>
												<%=assignmentList.get(i).getFileList().get(fileLength-1).getTitle() %> 
									<%} %>
									<%} %>
									<%} %>
								</td>
								</tr> <!-- files tr -->
								
								<%for (int j=0; j<delLength; j++){ %>
								<tr>
									<!-- date -->
									<td>
									<%=deliverableList.get(j).getDueDate() %>
									</td>
									
									<!-- title -->
									<td>
									<%=deliverableList.get(j).getTitle() %>
									</td>
									
									<!-- gradepercentage -->
									<td>
									<%=deliverableList.get(j).getGradePercentage()%>
									</td>
								</tr>
								<%} %><!-- delLength -->
										</tbody>
									</table>
								</td>
								<%} %>
							</tr>
						<%} %>
					</tbody>
				</table>
			</td><!-- right td -->
		</tr>
	</tbody>
</table>
</body>
</html>