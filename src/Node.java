
public class Node 
{
	private String Name;
	private String email;
	private String phoneNumber;
	private Node next;
	
	public Node()
	{
		Name = null;
		email = null;
		phoneNumber = null;
		next = null;
	}
	
	public Node(String Name, String email, String phoneNumber, Node next)
	{
		this.Name = Name;	
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.next = next;
	}
	
	
	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

}
