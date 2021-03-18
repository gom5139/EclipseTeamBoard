package project.admin.model;

public class AdminVO {
	private String m_name;
	private String m_id;
	private String m_password;
	private String m_passwordcheck;
	private String m_email;
	private String m_phonenumber;
	private String m_address;
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_passwordcheck() {
		return m_passwordcheck;
	}
	public void setM_passwordcheck(String m_passwordcheck) {
		this.m_passwordcheck = m_passwordcheck;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_phonenumber() {
		return m_phonenumber;
	}
	public void setM_phonenumber(String m_phonenumber) {
		this.m_phonenumber = m_phonenumber;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	@Override
	public String toString() {
		return "AdminVO [m_name=" + m_name + ", m_id=" + m_id + ", m_password=" + m_password + ", m_passwordcheck="
				+ m_passwordcheck + ", m_email=" + m_email + ", m_phonenumber=" + m_phonenumber + ", m_address="
				+ m_address + "]";
	}
	
}
