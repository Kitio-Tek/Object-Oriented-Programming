package university;

public class Course {
	private String title;
	private String teacher;
	private int course_id=0;
	private int length=50;
	private Student[] student;
	private String row;
	private int count=0;
	private float average=0;
	private int[] cGrades;

public Course(String title,String teacher, int id) {
this.title=title;
this.teacher=teacher;
this.course_id=id;
student=new Student[1000];
this.cGrades = new int[1000];

}



public String getTitle() {
	return title;
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
public void addCgrade(int code, int grade) {
	cGrades[code-10000]=grade;
}
	
public String list() {
	if(student[0]!=null)
		row=student[0].toString()+ "\n";
	for(int i=1;student[i]!=null;i++) {
		row=row+student[i].toString()+ "\n";
	}
	
	return row;
}

public int getId() {
	// TODO Auto-generated method stub
	return course_id;
}

public  Student[] getStudent() {
	// TODO Auto-generated method stub
	return student;
}

public void addCount() {
	this.count++;
	
}

public int getCount() {
	// TODO Auto-generated method stub
	return this.count;
}


public float getAverage() {
	// TODO Auto-generated method stub
	this.setCAverage();
	return this.average;
}
public void setCAverage () {
	 	float sum=0, avg=0;
	 	int cnt=0;
	for (int i=0; i<1000; ++i) {
		if (this.cGrades[i] != 0) {
			sum += this.cGrades[i];
			cnt++;
		} else {
			sum += 0;
		}
	}
	if(cnt==0)
		this.average=0;
	avg = sum / cnt;
	this.average=avg;
	

}

public int getcourse_id() {
	// TODO Auto-generated method stub
	return this.course_id;
}




}
