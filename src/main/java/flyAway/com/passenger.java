package flyAway.com;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Passenger")
public class passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="User_Name")
	private String puname;
	@Column(name="Passenger_Name")
	private String pname;
	@Column(name="Passenger_Gender")
	private String pgender;
	@Column(name="Passenger_Age")
	private String page;
	
	public String getPuname() {
		return puname;
	}
	public void setPuname(String puname) {
		this.puname = puname;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPgender() {
		return pgender;
	}
	public void setPgender(String pgender) {
		this.pgender = pgender;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	

}
