import java.io.*;
import java.util.*;
import java.time.LocalDate;

//<-- Login class [User gets id & password from here] -->

class login{
	public String id;
	public String pass;
	public String password;

	public void getid() {
		// <-- For taking input from user -->
		Scanner sc = new Scanner(System.in);

		// <-- For displaying date -->
		LocalDate myDate = LocalDate.now();

		System.out.println("\nCreate your id:");
		id = sc.nextLine();

		System.out.println("Create the Password: ");
		password = sc.nextLine();

		System.out.println("\nYou set password on date: " + myDate);
		pass = password;
	}
}

//<-- Detail class [Adding train details] -->
class Detail {
	public long tno;
	public String tname;
	public String bp;
	public String dest;

	public int d, m, y;

	// <-- Method for getting train details-->
	public void getDetail() {
		Scanner sc = new Scanner(System.in);
		InputStreamReader inputStrObj = new InputStreamReader(System.in);
		BufferedReader bufrObj = new BufferedReader(inputStrObj);

		try {
			System.out.println("\n--Add New details--\n");

			System.out.println("Train no: ");
			tno = sc.nextLong();
			
			System.out.println("Train Name: ");
			tname = bufrObj.readLine();

			System.out.println("Boarding point: ");
			bp = bufrObj.readLine();

			System.out.println("Destination pt: ");
			dest = bufrObj.readLine();

			System.out.println("Date of travel\n");
			System.out.println("Day: ");
			d = sc.nextInt();

			System.out.println("Month: ");
			m = sc.nextInt();

			System.out.println("Year: ");
			y = sc.nextInt();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

//<-- reser class [Reservation details]-->
class reser {
	
	InputStreamReader inputStrObj = new InputStreamReader(System.in);
	BufferedReader bufrObj = new BufferedReader(inputStrObj);
	
	public String fname;
	public int sno,pid;
	public long tno;
	public int amt;

	Scanner sc=new Scanner(System.in);
	void getresdet() throws IOException
	{
		
	System.out.print("\n\n Enter passenger name: ");
	fname=bufrObj.readLine();
	System.out.print(" Enter passenger id=");
	pid=sc.nextInt();
	System.out.print(" Enter passenger seat no=");
	sno=sc.nextInt(); 
	System.out.print(" Enter passenger train no=");
	tno=sc.nextLong();

    amt = sno+ 200;
    System.out.println("You should pay: "+ amt + "rs");
	   
	}
}

class Sample{
	reser b = new reser();
	login a = new login();
	
	Scanner sc = new Scanner(System.in);
	
	InputStreamReader inputStrObj = new InputStreamReader(System.in);
	BufferedReader bufrObj = new BufferedReader(inputStrObj);
	
	// <-- Method for user management-->
	public void manage() throws IOException {
		int ch;
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("UserData.txt", true));
		DataInputStream dis = new DataInputStream(new FileInputStream("UserData.txt"));
		

		System.out.println("\n---WELCOME TO THE USER MANAGEMENT MENU---\n");
		do {
			System.out.println("1. Add User details\n");
			System.out.println("2. Display details\n");
			System.out.println("3. Return to the main\n");
			System.out.println("Enter your choice: ");
			ch = sc.nextInt();

			switch (ch) {
			case 1:
				try {
						a.getid();
						dos.writeUTF(a.id);
						dos.writeUTF(a.pass);
						dos.flush();
						System.out.println("\n*****************User details added succesfully!!*************");
				} catch (FileNotFoundException e) {
					System.out.println("Cannot open the output file!!");
					return;
				}
				break;

			case 2:
				// if any data avialable in file
				while (dis.available() > 0) {
					try {
						System.out.println("\n| User ID: \t |Password:\n");
						System.out.println("| " + dis.readUTF() + "\t | " + dis.readUTF() + "\n\n");
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				break;
			}
		} while (ch <= 2);
		dos.close();
		dis.close();
		
	} // end of manage method
	
	
	// <-- Method for user login-->
	public void user() throws IOException {
		
		DataInputStream dis = new DataInputStream(new FileInputStream("UserData.txt"));
		
			int ch;
			int d = 0;
			String password;
			String id;
			
			System.out.println("\n-----Login-----\n");

			System.out.println("Enter your ID : ");
			id = bufrObj.readLine();
			System.out.println("Enter your Password : ");
			password = bufrObj.readLine();

			try {
				while (dis.available() > 0) {
					
					String idReal = dis.readUTF();
					String passReal = dis.readUTF();

					if ((idReal.equals(id)) && (passReal.equals(password))) {
						do {
							System.out.println("\n1.Reserve\n2.Cancel\n3.Enquiry\n4.Return to the main menu\n");
							System.out.println("Enter your choice:");
							ch = sc.nextInt();
							switch (ch) {
							case 1:
								reserve();
								break;
							case 2:
								cancel();
								break;
							case 3:
								enquiry();
								break;
							}
						} while (ch <= 3);
					} else {
						d = 1;
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (d == 1) {
				System.out.println("Enter your user id and password correctly\n");
			}
			
			 dis.close();
	}// end of user method
	
	 public void cancel() {
		 int id;
		 System.out.print("\n\nEnter passenger id to cancel the ticket: ");
		 id=sc.nextInt();
		 System.out.println("Ticket cancelled!!");
	 }
	 
	// <-- Method for handling admin operations after login menu -->
		public void database() throws IOException {
			
			Detail a = new Detail();
			Scanner sc = new Scanner(System.in);
			InputStreamReader inputStrObj = new InputStreamReader(System.in);
			BufferedReader bufrObj = new BufferedReader(inputStrObj);
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("TrainData.txt", true));
			DataInputStream dis = new DataInputStream(new FileInputStream("TrainData.txt"));

			int ch;
			String c;
			String password;
			String pass = "admin";

			System.out.println("\nEnter the Admin Password: ");
			password = sc.nextLine();

			if (!(pass.equals(password))) {
				System.out.println("Enter the password correctly!! \n");
				System.out.println("You are not permitted to login this mode\n");
			}

			else {
				do {
					System.out.println("\n --- ADMINISTRATOR MENU --- \n");
					System.out.println("1. Add Train details \n");
					System.out.println("2. Display Train details \n");
					System.out.println("3. User Management \n");
					System.out.println("4. Display Passenger details \n");
					System.out.println("5. Return to Main Menu \n");
					System.out.println("Enter your choice : ");
					ch = sc.nextInt();

					switch (ch) {
					case 1:
						try {
								a.getDetail();
				
								dos.writeLong(a.tno);
								dos.writeUTF(a.tname);
								dos.writeUTF(a.bp);
								dos.writeUTF(a.dest);

								dos.writeInt(a.d);
								dos.writeInt(a.m);
								dos.writeInt(a.y);
								dos.flush();
								System.out.println("\n*****************Train details added succesfully!!*************");
						} catch (FileNotFoundException e) {
							System.out.println("Cannot open the output file!!");
							return;
						}
						break;

					case 2:
						display();
						break;

					case 3:
						manage();
						break;

					case 4:
						passengerDisplay();
						break;
					}
				} while (ch <= 4);
			}
			dos.close();
			dis.close();
		}// end of database method
		
		public void passengerDisplay() throws IOException {
			
			DataInputStream dis1 = new DataInputStream(new FileInputStream("PassengerData.txt"));
			
			System.out.println(" ********List of passenger*********");
			System.out.println(" ============================================================");
			
			
			while (dis1.available() > 0) {		
				try {

					System.out.println("\n|Pname" + "\t" + "|PId" + "\t\t" + "|Seat No.." + "\t "
							+ "|Train No." + "\n");
//		

					System.out.println("|" + dis1.readUTF() + "\t\t" + "|" + dis1.readInt() + "\t\t" + "|"
							+ dis1.readInt() + "\t\t" + "|" + dis1.readLong() +  "\n");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		// Method for display train details
		public void display() throws IOException {
			DataInputStream dis = new DataInputStream(new FileInputStream("TrainData.txt"));
			while (dis.available() > 0) {
//				
				try {

					System.out.println("\n|Train No." + "\t" + "|Train Name" + "\t\t" + "|Boarding pt." + "\t\t "
							+ "|Destination pt."  + "\t"+ "|Day" + "-" + "Month" + "-" + "Year" + "\n");

					System.out.println("|" + dis.readLong() + "\t\t" + "|" + dis.readUTF() + "\t" + "|"
							+ dis.readUTF() + "\t" + "|" + dis.readUTF()  + "\t\t"
							+ "|" + dis.readInt() + "-" + dis.readInt() + "-" + dis.readInt() + "\n");
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}
		
		// <-- Method for displaying reservation menu -->
		public void reserve() throws Exception {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("PassengerData.txt", true));
			try {
				b.getresdet();

				dos.writeUTF(b.fname);
				dos.writeInt(b.pid);
				dos.writeInt(b.sno);
				dos.writeLong(b.tno);
				dos.flush();
				System.out.println("\n*****************Ticket reserved succesfully!!*************");
			} catch (FileNotFoundException e) {
			System.out.println("Cannot open the output file!!");
			return;
			}
			dos.close();
		}// End of reserve method

		// <-- Method for displaying train details in user menu -->
		public void enquiry() throws IOException {
		    display();
		}// End of enquiry method
}
public class RailwayReservationSystem extends Sample {
	
	public static void main(String args[]) throws Exception{
		
		RailwayReservationSystem RRS = new RailwayReservationSystem();

		 Scanner sc = new Scanner(System.in);
		 int ch;

		 System.out.println("----- RAILWAY RESERVATION SYSTEM ----- \n");
		 do{
			 System.out.println("\n MAIN MENU \n");
			 System.out.println("1.Admin mode\n2.Reserve and Cancel Ticket for user\n3.Exit \n");

			 System.out.println("Enter your choice : ");
			 ch = sc.nextInt();

			 switch(ch){
			 	case 1:
			 		RRS.database();
			 		break;
			 	case 2:
			 		RRS.user();
			 		break;
			 	default:
			 		break;
			 }
		 } while(ch<3);
	}
}