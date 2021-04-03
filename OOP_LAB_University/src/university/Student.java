package university;

public class Student {
private String first;
private String last;
private int ID;
private Course[] course;
private int length=1000;
private String row;
private int count=0;
private float average;
private int[] sGrades;
private float bonus;

public Student(String first,String last, int id) {
	this.first=first;
	this.last=last;
	this.ID=id;
    this.course=new Course[50];
    this.sGrades = new int[50];

}
public String getFirst() {
	return first;
}
public String getLast() {
	return last;
}
public void addSgrade(int code, int grade) {
	sGrades[code-10]=grade;
}
public void setBonus()
{ int register=0;
  for(int i=0;i<50;i++)
  {
	  if(this.course[i]!=null)
		  register++;}
  this.bonus=(this.count/register)*10;
  }

public float getBonus()
{ 
	return this.bonus;
  }

public float getFinalscore()
{ this.setBonus();
  return this.getAverage()+ this.getBonus();
	}




public String toString() {
	return this.getID()+ " "+ this.first+ " "+ this.last;
}
	
public void enroll(Course c) {
	
	for(int i=0;i<getCourse().length;i++) {
		if(getCourse()[i]==null) {
			getCourse()[i]=c;
			break;
		}
	}
}

public String list() {
	if(getCourse()[0]!=null)
		row=getCourse()[0].toString()+ "\n";
	for(int i=1;getCourse()[i]!=null;i++) {
		row=row+getCourse()[i].toString()+ "\n";
	}
	
	return row;
}
public int getID() {
	return ID;
}

public void setSAverage () {
	float sum=0, avg=0;
 	int cnt=0;
for (int i=0; i<50; ++i) {
	if (this.sGrades[i] != 0) {
		sum += this.sGrades[i];
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

public Course[] getCourse() {
	return course;
}
public void addCount() {
	this.count++;
}
public int getCount() {
	return this.count;
}

public float getAverage() {
	this.setSAverage();
	return this.average;

}

	
}
