import java.util.Scanner;

public class method 
{
	private Node head;
	private String name;
	private String email;
	private String phonenumber;

	public void userCommand()
	{
		Scanner input = new Scanner( System.in );
		
		userInterface(); //print out menu.
		System.out.print("\nPlease enter your command (Option numbers above): ");
		int userInput = input.nextInt();	
		
		while( userInput != 6) //6 will exit the program.
		{
			switch(userInput)
			{
			    case 1: //add a node.
			    addBySort();
			    break;
			
			    case 2: //delete a node.
			    deleteByIndex();
			    break;
	 		
			    case 3: //search a name.
			    searchByName();
			    break;
			    
			    case 4: //search an email.
			    searchByEmail();
			    break;
			    
			    case 5: //print out the list.
			    print();
			    break;
			    
			    default: //all other inputs are invalid.
				System.out.println("Invalid input, please try again");
			    break;	
			}
			
			userInterface();			
			System.out.print("\nPlease enter your command (Option numbers above): "); //prompt user to input command.
			userInput = input.nextInt();	
		}
		
		if(userInput == 6) //6 will terminate program.
		{
			System.out.print("Program terminated.");
			System.exit(0);			
		}
	}
	
	private void userInterface() //print out menu.
	{
		System.out.printf("%40s\n","User Menu");
		for(int i = 0; i < 70; i++)
			System.out.print("="); 
		
		System.out.printf("\n%27s%35s", "1. Add a new record", "2. Delete a record");
		System.out.printf("\n%24s%38s", "3. Search a name", "4. Search an email");
		System.out.printf("\n%21s%30s", "5. Print list", "6. Exit");
		
		System.out.println();
		for(int i = 0; i < 70; i++)
			System.out.print("=");
		System.out.println();
	}
	
	
	private void addBySort() //add a new record and sorted.
	{
		name = nameInput(); //prompt user to input name.
		email = emailInput();
		phonenumber = phonenumberInput();
	
		Node current = head;
		Node previous = null;
		Node newNode = new Node(name, email, phonenumber, null);
		
		if( totalSize() == 0)
		{
    		addToHead(newNode);
		}
		else
		{
			while(totalSize() > 0)
			{
				if( current == head && getLastName(newNode.getName()).compareToIgnoreCase(getLastName(current.getName())) <= 0)
				{
		    		addToHead(newNode);
		    		break;
				}
				else if (getLastName(newNode.getName()).compareToIgnoreCase(getLastName(current.getName())) <= 0 && getLastName(newNode.getName()).compareToIgnoreCase(getLastName(previous.getName())) >= 0)
			    {
					addInBetween(newNode, current, previous);
					break;
			    }
				else if ( current.getNext() == null && getLastName(newNode.getName()).compareToIgnoreCase(getLastName(current.getName())) >= 0)
			    {
					addToTail(newNode, current);
					break;
			    }
				else
			    {
					previous = current;
					current = current.getNext();
			    }
			}
		}
		
	}
	
	private String nameInput() //prompt user to input name.
	{
		Scanner input = new Scanner( System.in );
		String[] splitName; //Array used to get last name after split the name.
		
		System.out.println("Please enter a Name with a whitespace between first name and last name: ");
		name = input.nextLine();
		splitName = name.split(" "); //split the name.
		
		while( splitName.length < 2 ) //if the length of split name is less than 2, such as just 1 word, need to re-enter.
		{
			System.out.println("Invalid input. Please try again.");
			System.out.println("\nPlease enter a Name with a whitespace between first name and last name: ");
			name = input.nextLine();
			splitName = name.split(" ");
		}
		
		return name;
	}
	
	private String emailInput() //prompt user to input email.
	{
		Scanner input = new Scanner( System.in );	
		
		System.out.println("Please enter the email address: ");	
		email = input.nextLine();
		
		while( !email.contains("@")) //if there is no @ in the input, need to re-enter.
		{
			System.out.println("Invalid input. Please try again.");
			System.out.println("\nPlease enter the email address: ");	
			email = input.nextLine();
		}
		
		return email;
	}
	
	private String phonenumberInput() //prompt user to input phone number.
	{
		Scanner input = new Scanner( System.in );
	    
		System.out.println("Please enter the phone number: ");	
		phonenumber = input.nextLine();
		
		while( phonenumber.replaceAll("[^\\d.]", "").length() != phonenumber.length()) //if there are any letters in input, need to re-enter.
		{
			System.out.println("Invalid input. Please try again.");
			System.out.println("\nPlease enter the phone number: ");	
			phonenumber = input.nextLine();
		}
		
		return phonenumber;
	}

	private String getLastName(String name) //get last name.
	{
		String[] splitedName = name.split(" ");
		String lastName = splitedName[splitedName.length - 1]; //last name will be in the last position in the Array.
		
		return lastName;
	}
	
	private void addToHead(Node newNode) //add a new node to head.
	{	
		newNode.setNext(head);
		head = newNode;
	}

	private void addInBetween(Node newNode, Node current, Node previous) //add a new node between nodes.
	{	
		newNode.setNext(current);
		previous.setNext(newNode);
	}
	
	private void addToTail(Node newNode, Node current) //add a new node to the end of the list.
	{
		current.setNext(newNode);
	}
	
	
	private int totalSize() // get the total number of nodes.
	{
		Node current = head;
		int size = 0;
		
		if(current == null) //if there are no nodes, size will be 0.
			size = 0;
		else
		{
			while(current.getNext() != null)
			{
				current = current.getNext();
				size++;
			}
			
			size = size + 1; //add the last node.
		}
		return size;
	}

	
	private void deleteByIndex() //delete a node by index.
	{
		Scanner input = new Scanner( System.in );
		Node current = head;
		Node previous = null;
		int indexNumber = 1;

		if( totalSize() == 0) //if there are no nodes, print out no record.
		{			
			System.out.println();
			System.out.println("No records to delete");
			
		}
		else
		{
			print();
			System.out.println();
			System.out.println("Please enter the index number of the record you want to delete: ");
			int userInput = input.nextInt(); //prompt user to input a index number to delete corresponding node. 
			while( userInput < 1 || userInput > totalSize()) //if input is less than 1 or greater than total nodes, re-enter.
			{
				System.out.println("Invalid input, please try again.");
				System.out.println("\nPlease enter the index number of the record you want to delete: ");
				userInput = input.nextInt();
			}
			
			while(indexNumber != userInput) //find the index number in the node list.
			{
				previous = current;
				current = current.getNext();
				indexNumber++;
			}

			if( indexNumber == 1 ) //the first node will use deleteHead method.
			{
				deleteHead(current);
			}
			else if (indexNumber == totalSize()) //the last node will use deleteTail method.
			{
				deleteTail(previous);
			}
			else
			{
				deleteInBetween(current, previous); //all other nodes will use deleteInBetween method.
			}
			
		}
	}
	
	private void deleteInBetween(Node current, Node previous) //delete node between nodes.
	{	
		previous.setNext(current.getNext());
	}

	private void deleteHead(Node current) //delete a head node.
	{	
		head = head.getNext();
	}
	
	private void deleteTail(Node previous) //delete a tail node.
	{	
		previous.setNext(null);;
	}
	
	private void searchByName() //search a name.
	{
		Scanner input = new Scanner( System.in );
		Node current = head;
        int index = 1;
        int found = 0;
		
		if(current == null) //if there are no nodes, print out no record.
		{
			System.out.println("No records to search.");
		}
		else
		{
			System.out.println("\nPlease enter the name you want to search: ");
			String userInput = input.nextLine();

			for(int i = 0; i < 70; i++)
				System.out.print("=");    
			System.out.printf("\n%-9s%-20s%-25s%s", "Index", "Name", "Email", "Phone Number"); //print out heading.
			
			while(current.getNext() != null)
			{
				if(current.getName().contains(userInput)) //if node contain the search keyword, print out the node.
				{
				    System.out.printf("\n%-9d%-20s%-25s%s", index, "<" + current.getName() + ">" , "<" + current.getEmail() + ">", "<" + current.getPhoneNumber() + ">");
				    found++;
				}
				current = current.getNext();
				index++;
			}
			
			if(current.getName().contains(userInput)) //if last node contain the search keyword, print out the node.
			{
			    System.out.printf("\n%-9d%-20s%-25s%s", index, "<" + current.getName() + ">" , "<" + current.getEmail() + ">", "<" + current.getPhoneNumber() + ">");
			    found++;
			}
			
			if(found ==0) //if nothing found in the node list, print out no records.
				System.out.printf("%20s","\nNo records found.");
			System.out.println();
			for(int i = 0; i < 70; i++)
				System.out.print("=");
			System.out.println();
		}
	}
	
	private void searchByEmail() //search an email.
	{
		Scanner input = new Scanner( System.in );
		Node current = head;
        int index = 1;
        int found = 0;
		
		if(current == null) //if there are no nodes, print out no record.
		{
			System.out.println("No records to search.");
		}
		else
		{
			System.out.println("\nPlease enter the email you want to search: ");
			String userInput = input.nextLine();
			
			for(int i = 0; i < 70; i++)
				System.out.print("=");    
			System.out.printf("\n%-9s%-20s%-25s%s", "Index", "Name", "Email", "Phone Number"); //print out heading.
			
			while(current.getNext() != null)
			{
				if(current.getEmail().contains(userInput)) //if node contain the search keyword, print out the node.
				{
				    System.out.printf("\n%-9d%-20s%-25s%s", index, "<" + current.getName() + ">" , "<" + current.getEmail() + ">", "<" + current.getPhoneNumber() + ">");
				    found++;
				}
				current = current.getNext();
				index++;
			}
			
			if(current.getEmail().contains(userInput)) //if last node contain the search keyword, print out the node.
			{
			    System.out.printf("\n%-9d%-20s%-25s%s", index, "<" + current.getName() + ">" , "<" + current.getEmail() + ">", "<" + current.getPhoneNumber() + ">");
			    found++;
			}
			
			if(found ==0) //if nothing found in the node list, print out no records.
				System.out.printf("%20s","\nNo records found.");
			System.out.println();
			for(int i = 0; i < 70; i++)
				System.out.print("=");
			System.out.println();
		}
	}
	
	private String print() //print out the list.
	{
		Node current = head;
        int index = 1;
		
		if(totalSize() == 0) //print out Empty List if there are no nodes.
		{
			System.out.println("Empty List");
		}
		else
		{
			for(int i = 0; i < 70; i++)
				System.out.print("=");    
			System.out.printf("\n%-9s%-20s%-25s%s", "Index", "Name", "Email", "Phone Number"); //print out heading.

				while (current.getNext() != null) //print out each node.
				{
					name =  "<" + current.getName() + ">"; 
					email =  "<" + current.getEmail() + ">";
					phonenumber =  "<" + current.getPhoneNumber() + ">";

				    System.out.printf("\n%-9d%-20s%-25s%s", index, name, email, phonenumber); 	    
				    current = current.getNext(); 
				    index++;
				}
				
				name =  "<" + current.getName() + ">"; 
				email =  "<" + current.getEmail() + ">";
				phonenumber =  "<" + current.getPhoneNumber() + ">";
			    System.out.printf("\n%-9d%-20s%-25s%s\n", index, name, email, phonenumber); //print out last node
				for(int i = 0; i < 70; i++)
					System.out.print("="); 
				System.out.println();
		}
		return "";
	}

}
