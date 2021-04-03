package university;

import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");
	public int sum;

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
	}
@Override
	public int enroll(String first, String last){
		int student_id;
		student_id=super.enroll(first, last);
		logger.info("New student enrolled: "+ student_id+ ","+ first +" "+last );
		
		return student_id;
	}
@Override
  public int activate(String title, String teacher){
	int course_id;
	course_id=super.activate(title, teacher);
	logger.info("New course activated: "+course_id+ ", "+ title +" "+teacher );
	
	return course_id;
	
}

@Override
public void register(int studentID, int courseCode){
	super.register(studentID, courseCode);
	logger.info("Student "+studentID+ " signed up for course " + courseCode );
	
}


	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		
				
		/*for (Student student : students) {
			if(student.getID()==studentId) 
			{ 
				for (Course course : student.getCourse()) {
					   if(course.getId()==courseID) {
						   student.addSgrade(courseID,grade);
						   student.addCount();
						   break;
					   }
						 
					   }
				break;
				}
			}*/          students[studentId-10000].addSgrade(courseID,grade);
			             students[studentId-10000].addCount();
		
		
						   courses[courseID-10].addCgrade(studentId,grade);
						   courses[courseID-10].addCount();
						   
			logger.info("Student "+students[studentId-10000].getID() + " took an exam in course " + courseID+" with grade " + grade );
				
		
		/*for (Student student : this.students) {
			if(student.getID()==studentId) 
			{ 
				for (Course course : student.getCourse()) {
					   if(course.getId()==courseID) {
						   course.addgrade(grade);
						   student.addCount();
						   break;
					   }
						 
					   }
				break;
				}
			}
		for (Course course : this.courses) {
			if(course.getId()==courseID) 
			{ 
				for (Student student : course.getStudent()) {
					   if(student.getID()==studentId) {
						   student.addgrade(grade);
						   course.addCount();
						   break;
					   }
						 }break;
				}}*/
		/*
		for(int i=0;i<1000;i++)
		{	if(students[i].getID()==studentId) 
				{ for(int j=0;j<50;j++) {
			       if(students[i].getCourse()[j].course_id==courseID)
					{ students[i].getCourse()[j].addgrade(grade);
			         students[i].addCount();
			         break;}
			       }
				 break;
				}
				}
		
		/*for(int i=0;i<50;i++)
		{ if(this.courses[i].getcourse_id()==courseID)
				{ for(int j=0;j<1000;j++) {
			      if(this.courses[i].getStudent()[j].getID()==studentId)
					 {this.courses[i].getStudent()[j].addgrade(grade);
			          this.courses[i].addCount();
			         break;}
			      }
				break;
				 }
				}*/
		
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		
		float avg=0;
		avg=students[studentId-10000].getAverage();
		
		
		if (students[studentId-10000].getCount()>0 && avg>0)
			return "Student " + studentId +" : " + students[studentId-10000].getAverage();
		else 
			return "Student "+ studentId +" hasn't taken any exams";		

		
		
		/*for (Student student : students) {
			if(student!=null) {
			student.setSAverage(student.getCount());
			if(student.getID()==studentId && student.getCount()>0) {
				 
				
				return "Student " + studentId +" : " + student.getAverage(); 
			}}
			
		}*/

		
		/*float sum=0;
		for(int i=0;i<1000;i++)
		{
			if(this.students[i].getID()==studentId && this.students[i].getCount()>0)
			{
				for(int j=0;j<this.students[i].getCount();j++)
				{ 
					sum+=this.students[i].getCourse()[j].getGrade();
				}
				   this.students[i].setAverage(sum/this.students[i].getCount());
				   return "Student " + studentId +" : " +  this.students[i].getAverage();
			}
			
			
			 if(this.students[i].getID()==studentId && this.students[i].getCount()==0)
				return "Student " +studentId +" hasn't taken any exams";

			
		} */
		
		/*for (Student student : students) {
			if(student.getID()==studentId && student.getCount()>0) {
				for (int i=0;i<student.getCount();i++) {
					sum+=course[i]getGrade();
				}
				student.setAverage(sum/student.getCount());
				return "Student " + studentId +" : " + student.getAverage();
			}
		*/
						
		
	 /*return "Student "+ studentId +" hasn't taken any exams "  ;*/
		
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		
		
		float avg=0;
		avg=courses[courseId-10].getAverage();
		String title=new String();
		title=courses[courseId-10].getTitle();
		
		if (avg==0  || courses[courseId-10].getCount()==0)
			return "No student has taken the exam in " + title;
		else 
			return "The average for the course " + title + " is: " + avg;		
		/*for (Course course : courses) {
			if(course!=null) {
			course.setCAverage(course.getCount());
			if(course.getcourse_id()==courseId ) {
				if(course.getAverage()==0)
		
					title=course.getTitle();
				   
				
				else
				return  "The average for the course " + course.getTitle() +" is : " + course.getAverage(); 
			}
			
				 
			
			
		  }
			
		}*/

		
		/*float sum=0;
		
		for(int i=0;i<50;i++)
		{
			if(this.courses[i].getcourse_id()==courseId && this.courses[i].getCount()>0)
			{
				for(int j=0;j<this.courses[i].getCount();j++)
				{ 
					sum+=this.courses[i].getStudent()[j].getGrade();
				}
				   this.courses[i].setAverage((float)sum/this.courses[i].getCount());
				   return "The average for the course " + this.courses[i].getAverage();	
				
			}
			
		}*/
		
		/*return 	"No student has taken the exam in " + title ;*/
	
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		Student tmp=new Student(null,null,0);
		String result=new String();
		for(int i=0;i<1000 && students[i]!=null;i++)
		{
			for(int j=i+1;j<1000 && students[j]!=null; j++)
			{
				if(students[i].getFinalscore()<students[j].getFinalscore() || Float.isNaN(students[i].getFinalscore()))
				{
					tmp = students[i];
					students[i] = students[j];
					students[j] = tmp;

				}
			}
		}
		
		for(int i=0;i<3;i++) {
			if(students[i]!=null && students[i].getFinalscore()>0)
		result=result+ students[i].getFirst() + " " + students[i].getLast() + " " + students[i].getFinalscore() + "\n";
			/*else
		result=result + "There are only "+ i +" number of students" +"\n";*/
		}
		
		return result;
	}
}
