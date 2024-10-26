package fall24.hsf301.slot02.pojo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name", nullable = false, length = 20)
	private String firstName;
	
	@Column(name="last_name", nullable = false, length = 20)
	private String lastName;
	
	//@Column(name="mark")
	private int mark;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "Student_id")
	private Set<Book> books;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(Long id, String firstName, String lastName, int mark) {
		super();
		this.books = new HashSet<Book>();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mark = mark;
	}
	
	public Student(String firstName, String lastName, int mark) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mark = mark;
	}


	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getMark() {
		return mark;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, mark);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName)
				&& mark == other.mark;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mark=" + mark + "]";
	}
	
}
