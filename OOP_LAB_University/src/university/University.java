package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	public static final int INIT_Student=10000;
	public static final int INIT_Course=10;

    private int student_id=INIT_Student;
    private int course_id=INIT_Course;
    private String name;
    private String first;
    private String last;
    
    private Student[] students=new Student[INIT_Student];
    private Course[] courses=new Course[INIT_Course];
    private Student stmp;
    private Course ctmp;
    
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		//TODO: to be implemented
		this.name=name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		//TODO: to be implemented
		return this.name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.first=first;
		this.last=last;
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return this.first +" "+this.last;
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		Student st=new Student(first,last,student_id);
		students[student_id-INIT_Student]=st;
		
		return student_id++;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
		return students[id-INIT_Student].toString();
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		Course c=new Course(title,teacher,course_id);
		courses[course_id-INIT_Course]=c;
		
		return course_id++;
		
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		return courses[code-INIT_Course].toString();
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		for(int i=0;i<1000;i++) {
			if(students[i].ID==studentID)
			{ stmp=students[i];
			  break;
			}
		}
		
		for(int i=0;i<50;i++) {
			if(courses[i].course_id==courseCode)
			{ ctmp=courses[i];
			  break;
			}
		}
      
		stmp.enroll(ctmp);
		ctmp.enroll(stmp);
		
		
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		
		for(int i=0;i<50;i++) {
			if(courses[i].course_id==courseCode)
			{
				ctmp=courses[i];
				break;
			}
		}
		return ctmp.list();
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		
		for(int i=0;i<1000;i++) {
			if(students[i].ID==studentID)
			{
				stmp=students[i];
				break;
			}
		}
		return stmp.list();
		
	}
}
