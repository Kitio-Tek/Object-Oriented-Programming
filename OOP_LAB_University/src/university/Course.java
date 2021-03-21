package university;

public class Course {
public String title;
public String teacher;
public int course_id;
public int length=50;
public Student[] student;
String row;

public Course(String title,String teacher, int id) {
this.title=title;
this.teacher=teacher;
this.course_id=id;
student=new Student[1000];

}



public String toString() {
	return this.course_id+ ","+ this.title+ ","+ this.teacher;
}
	
public void enroll(Student s) {
	
	for(int i=0;i<student.length;i++) {
		if(student[i]==null) {
			student[i]=s;
			break;
		}
	}
}
	
public String list() {
	if(student[0]!=null)
		row=student[0].toString()+ "\n";
	for(int i=1;student[i]!=null;i++) {
		row=row+student[i].toString()+ "\n";
	}
	
	return row;
}




}
