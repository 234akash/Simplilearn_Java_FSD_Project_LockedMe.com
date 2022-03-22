
 import java.io.*;
import java.util.*;

public class LockedMe {
	
	public static void directoryCreate() {
		Directory = System.getProperty("user.dir");
		createFile = new File(Directory+"/files");
		if(!createFile.exists()) 
			createFile.mkdirs();
		System.out.println("File Path " + Directory);
	}
	
	private static Scanner input;
	private static Scanner readDbFile;
	private static UserDetails details;
	private static PrintWriter collect;
	static String Directory;
	static File createFile;
	
	public static void main(String[] args) throws IOException {
		tools();
		welcomeMessage();
		mainMenu();
	    }

	public static void mainMenu() throws IOException {
		
		System.out.println("      WELCOME TO MAIN MENU	    ");
		System.out.println("================================");
		
		System.out.println("Please Enter your choice\n 1.Sign in\n 2.Sign up\n 3.Exit");
	try {
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:{ signIn(); }
		
		case 2:{ signUp(); }
		
		case 3:{ break; }
		
		default:{ System.out.println("Invalid Input, Please Try Again"); mainMenu(); }
		}
		}catch(Exception e) {
			System.out.println("Invalid Input, Please Try Again");
			mainMenu();
		}
		
	    }
	
    public static void signIn() throws IOException {
		System.out.println("       SIGN IN TO CONTINUE	        ");
		System.out.println("=====================================");
	try {	
		System.out.println("Enter Your Username : ");
		String name = input.next();
		boolean found = false;
		while(readDbFile.hasNext() && !found) {
			if(readDbFile.next().equals(name)) {
				System.out.println("Enter Your Pasword : ");
				String password = input.next();
				if(readDbFile.next().equals(password)) {
					System.out.println("You've successfully signed in\n");
					found = true;
                directoryCreate();
                LockerMenu();
				}
			}
			}if(!found) {
				System.out.println("The username or password entered was incorrect or not found in our database!!");
				System.out.println("\n1.Return Main Menu\n2.Close the application\n");
				Scanner sc1 = new Scanner(System.in);
				int chance = sc1.nextInt();
				switch(chance) {
				case 1:{ main(null);}break;
				case 2:{break; }
				default:{ System.out.println("\n** Invalid Input **");  main(null); break;}
				}
				}
			
	}catch (Exception e) {
		
		mainMenu();
	}
    }
	public static void signUp() throws IOException {
		System.out.println("	ENTER YOUR DETAILS TO SIGN UP    ");
		System.out.println("===================================== ");
	try {	
		System.out.println("Enter Username: ");
		String username = input.next();
		while(readDbFile.hasNext()) {
			if(readDbFile.next().equals(username)) {
			System.out.println("\n  Username Already Exists in our database!!\n");
			signUp();
			}}
		details.setUserName(username);
		
		System.out.println("Enter Password : ");
		String password = input.next();
		details.setPassword(password);
		
		System.out.println("You've successfully signed up!!");
		
		collect.println(details.getUserName());
		collect.println(details.getPassword());
		
		collect.close();
		mainMenu();
	   }catch(Exception e) {
		   signUp();
	   }
	   }
	
	public static void LockerMenu() throws IOException {
		System.out.println("=====Entered Locker=====");
		System.out.println("1.List Files in Directory\n2.Business Operations in Directory\n3.Exit");
		try {
		Scanner sc3 = new Scanner(System.in);
		int choice1 = sc3.nextInt();
		switch(choice1) {
		case 1:{ display(); }
		try {
		Scanner sc4 = new Scanner(System.in);
		System.out.println("1.Back to Locker\n2.Exit");
		int option1 = sc4.nextInt();
		switch(option1) {
		case 1 :{ LockerMenu(); }
		case 2 :{ break; }
		default:{ System.out.println("Invalid Input"); LockerMenu();}
		}
		}catch(Exception e) {
			System.out.println("Invalid Input"); LockerMenu();
		}
		case 2:{ LockerOperations(); }
		
		case 3:{ System.out.println("\n Are you sure you want to exit ? ");
		System.out.println("  (Y) ==> Yes    (N) ==> No        ");
		Scanner sc5 = new Scanner(System.in);
		char ans = sc5.nextLine().toUpperCase().charAt(0);
		if(ans == 'Y') {
			System.out.println("\n");
			System.out.println("=====THANK YOU FOR VISITING LOCKEDME.COM=====");
			System.exit(1);
		}else if(ans == 'N') {
			System.out.println("\n");
			LockerMenu();
		}else {
			System.out.println("\nInvalid Input \nValid Inputs :(Y/N)\n");
			LockerMenu();
		} }
		
		default:{ System.out.println("Invalid Input"); LockerMenu();}
		}}catch(Exception e) {
			LockerMenu();
		}
		
	}
	
	public static void display() {
	try {
		if(createFile.list().length==0) {
			System.out.println("Folder is empty");
		}else {
		System.out.println("\nThe Files Available in " + Directory + " are as following:\n ");
		String[] lists = createFile.list();
		Arrays.sort(lists);
		for(String view : lists) {
			System.out.println(view);
		}
		}
	}catch(Exception e) {
		e.getMessage();
	}
	}
	
	public static void LockerOperations() throws IOException {
		
		System.out.println("\n1.Add New File\n2.Delete Existing File\n3.Search File\n4.Return to Locker");
		try {	
		Scanner sc6 = new Scanner(System.in);
		int choice2 = sc6.nextInt();
		
		switch(choice2) {
		
		case 1:{ System.out.println("Enter File Name You Want to Add : ");
		Scanner sc7 = new Scanner(System.in);
		String Name = sc7.next().trim().toLowerCase(); 
		addFile(Name);
		LockerMenu();
		break;
		}
			case 2:{ System.out.println("Enter File Name You Want to Delete : ");
			Scanner sc8 = new Scanner(System.in);
			String Name2 = sc8.next().trim();
			deleteFile(Name2);
			LockerMenu();
			break;
			}
			case 3:{System.out.println("Enter the File Name to Check Status : ");
			Scanner sc9 = new Scanner(System.in);
			String sc10 = sc9.next().trim();
			searchFile(sc10);
			LockerMenu();
			break;
			}
			case 4:{ LockerMenu();}
			default:{ System.out.println("Invalid Input");	}
			LockerOperations();
			break;
		}
	}catch(Exception e) {
		System.out.println("Invalid Input");
		LockerOperations();
	}
	}
	
	public static void addFile(String name) throws IOException {
		
		File filec = new File(createFile+"/"+name);
		String list1[] = createFile.list();
		for(String namecheck : list1) {
			if(name.equalsIgnoreCase(namecheck)) {
				System.out.println("File already exists in the folder");
			return;
			}
		}
		filec.createNewFile();
		boolean res = filec.createNewFile();
		if(!res) System.out.println("Your File is Successfully Created");
		
	}
	
	public static void deleteFile(String name) {
		File file = new File(createFile+"/"+name);
		String[] list = createFile.list();
		for(String view1 : list) {
			if(name.equals(view1) && file.delete()) {
				System.out.println("Your File is Successfully Deleted from our Database");
				return;
			}
		}
		System.out.println("File Not Found");
	}
	
	public static void searchFile(String Name) {
		File f2 = new File(createFile+"/"+Name); 
		String[] list = createFile.list();
		for(String str : list) {
			if(Name.equals(str)) {
				System.out.println("The Required File is Found");
				return;
			}
		}
		System.out.println("Sorry, the Required File is Not Found in our Database");
	} 
	
	public static void welcomeMessage() {
		System.out.println("********  LockedMe.com   ********");
		System.out.println("********  Developer:- AKASH  ********");
		System.out.println("=================================");
	    }
	
	public static void tools() throws IOException{
		File file = new File("UsersDB.txt");
		file.createNewFile();
		try {	
			input = new Scanner(System.in);
			readDbFile = new Scanner(file);
			file.createNewFile();
			collect = new PrintWriter(new FileWriter(file,true));
			details = new UserDetails();
		}catch(Exception e) {
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
	   }
}

