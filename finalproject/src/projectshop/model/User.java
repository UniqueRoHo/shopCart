package projectshop.model;

public class User {
	    private int ID;
	    private String password;
	    private String role;
	    private String email;
	    
	    public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public int getID() {
	        return ID;
	    }
	    public void setID(int iD) {
	        ID = iD;
	    }
	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getRole() {
	        return role;
	    }
	    public void setRole(String role) {
	        this.role = role;
	    }
}
