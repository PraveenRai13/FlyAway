package flyAway.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.type.TrueFalseType;

@Entity
@Table(name="Customer")
public class cxList {
	@Id
	@Column(name="User_Name")
	private String uname;
	@Column(name="First_Name")
	private String fname;
	@Column(name="Last_Name")
	private String lname;
	private String Gender;
	private int Age;
	@Column(name="Mo_Num",unique = true)
	private long umonum;
	@Column(name="Email")
	private String uemail;
	@Column(name="Password")
	private String upass;
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		this.Gender = gender;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		this.Age = age;
	}
	public long getUmonum() {
		return umonum;
	}
	public void setUmonum(long umonum) {
		this.umonum = umonum;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}	

}
