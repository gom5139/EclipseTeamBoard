package project.member.command;

public class MemberActionCommand {
	private boolean redirect = false;
	private String path = null;
	
	public boolean isRedirect() {
		return redirect;	
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
}
