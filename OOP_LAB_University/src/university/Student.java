package university;

public class Student {
public String first;
public String last;
public int ID;
public Course[] course;
public int length=1000;
String row;

public Student(String first,String last, int id) {
this.first=first;
this.last=last;
this.ID=id;
course=new Course[50];

}

public String toString() {
	return this.ID+ " "+ this.first+ " "+ this.last;
}
	
public void enroll(Course c) {
	
	for(int i=0;i<course.length;i++) {
		if(course[i]==null) {
			course[i]=c;
			break;
		}
	}
}

public String list() {
	if(course[0]!=null)
		row=course[0].toString()+ "\n";
	for(int i=1;course[i]!=null;i++) {
		row=row+course[i].toString()+ "\n";
	}
	
	return row;
}

	
	
}
