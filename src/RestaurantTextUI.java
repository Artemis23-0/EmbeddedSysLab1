// EGR 326, Homework 3 (Restaurant)
// Instructor-provided code.
// You SHOULD heavily modify this file to make it interface with your own classes.

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * This class represents the text user interface (UI) for the restaurant
 * program, allowing the user to view and manage the restaurant and its objects.
 * 
 * @author Marty Stepp
 * @version Spring 2011 v1.0
 */
public class RestaurantTextUI {
	// file name from which to read the restaurant data
	private static final String DEFAULT_RESTAURANT_FILENAME = "/Users/artemis1/Desktop/IdeaProjects/hw3-Artemis23-0/tables.txt";
	private static final DecimalFormat decfor = new DecimalFormat("0.00");
	Restaurant restaurant = new Restaurant();
	
	/**
	 * Constructs a new text user interface for managing a restaurant.
	 */
	public RestaurantTextUI() {
		System.out.println("EGR 326 Restaurant Simulator");

		// TODO: initialization code can go here
	/*	if (readRestaurantData()) {
			mainMenu();
		}*/

	}
	
	/**
	 * Reads the information about the restaurant from the default restaurant
	 * file.
	 * @return true if the data was read successfully; false if there were any errors
	 */
	public boolean readRestaurantData() {
		File restaurantFile = ValidInputReader.getValidFile(
				"File name for restaurant data [" + DEFAULT_RESTAURANT_FILENAME + "]?",
				DEFAULT_RESTAURANT_FILENAME);

		// TODO: read restaurant info from tables file;
		// return true if it was successful and false if not
		restaurant.producer(restaurantFile.getAbsolutePath());
		if (restaurant.getTables().isEmpty()) {
			System.out.println("Unable to read restaurant data: file not found.");
			return false;
		}
		// when there is an error reading the file,
		System.out.println();
		return true;
	}
	
	/**
	 * Displays the main menu of choices and prompts the user to enter a choice.
	 * Once a valid choice is made, initiates other code to handle that choice.
	 */
	public void mainMenu() {
		// main menu
		displayOptions();
		while (true) {
			String choice = ValidInputReader.getValidString(
					"Main menu, enter your choice:",
					"^[sSaAdDrRpPtTcCwWqQ!?]$").toUpperCase();
			if (choice.equals("S")) {
				serversOnDuty();
			} else if (choice.equals("A")) {
					addServer();
			} else if (choice.equals("D")) {
				dismissServer();
			} else if (choice.equals("R")) {
				cashRegister();
			} else if (choice.equals("P")) {
				partyToBeSeated();
			} else if (choice.equals("T")) {
				tableStatus();
			} else if (choice.equals("C")) {
				checkPlease();
			} else if (choice.equals("W")) {
				waitingList();
			} else if (choice.equals("Q")) {
				break;
			} else if (choice.equals("?")) {
				displayOptions();
			} else if (choice.equals("!")) {
				rickRoll();
			}
			System.out.println();
		}
	}
	
	// Displays the list of key commands the user can use.
	private void displayOptions() {
		System.out.println();
		System.out.println("Main System Menu");
		System.out.println("----------------");
		System.out.println("S)ervers on duty");
		System.out.println("A)dd server");
		System.out.println("D)ismiss server");
		System.out.println("R)egister");
		System.out.println("P)arty has arrived");
		System.out.println("T)ables status");
		System.out.println("C)heck, please");
		System.out.println("W)aiting list");
		System.out.println("?) display this menu of choices again");
		System.out.println("Q)uit");
	}
	
	// Called when S key is pressed from main menu.
	// Displays all servers who are currently working.
	private void serversOnDuty() {
		System.out.println("Servers currently on duty:");
		int serverCount = 0;
		// TODO: display current servers, e.g.:
		for (int i = 0; i < restaurant.getServers().size(); i++) {
			Server currServer = restaurant.getServers().get(i);
			System.out.println("Server #" + currServer.getServerNumber() + " ($" + decfor.format(currServer.getTotalTips()) + " in total tips)");
			serverCount++;
		}
		System.out.println("Current server count: " + serverCount);
		// Server #1 ($49.76 in total tips)
	}
	
	// Called when A key is pressed from main menu.
	// Adds one more server to the system.
	private void addServer() {
		System.out.println("Adding a new server to our workforce:");
		restaurant.addServer(new Server());

		// TODO: add server and display current count of servers, e.g.:
		System.out.println("Current server count: " + restaurant.getServers().size());
	}
	
	// Called when D key is pressed from main menu.
	// Sends one server home for the night (if possible).
	private void dismissServer() {
		// when there are no servers,
		if (restaurant.getServers().isEmpty()) {
			System.out.println("No servers to dismiss.");
		}
		
		// when only one server remains with tables remaining,
		if (restaurant.getServers().size() == 1 && restaurant.tablesHavePeople()) {
			System.out.println("Sorry, the server cannot cash out now;");
			System.out.println("there are still tables remaining and this is the only server left.");
			return;
		}
		
		// when the server is able to be dismissed,
		System.out.println("Dismissing a server:");
		
		// TODO: cash out server and display current count of servers
		Server cashedOutServer = restaurant.getNextServerToCashOut();
		System.out.println("Server #" + cashedOutServer.getServerNumber() + " cashes out with $" +
						decfor.format(cashedOutServer.getTotalTips())
				 + " in total tips.");
		// Server #2 cashes out with $47.95 in total tips.
		restaurant.reallocateServers(cashedOutServer.getServerNumber());
		restaurant.getServers().remove(cashedOutServer);

		System.out.println("Servers now available: " + restaurant.getServers().size());
		// Servers now available: 3
	}
	
	// Called when R key is pressed from main menu.
	// Displays how much money is in the restaurant's cash register.
	private void cashRegister() {

		System.out.println("Cash register:");

		// TODO: display total money earned, e.g.:
		System.out.println("Total money earned = $" + decfor.format(restaurant.totalCash()));
	}
	
	// Called when T key is pressed from main menu.
	// Displays the current status of all tables.
	private void tableStatus() {
		System.out.println("Tables status:");

		// TODO: show restaurant's table statuses, e.g.:
		for (int i = 0; i < restaurant.getTables().size(); i++) {
			Table currTable = restaurant.getTables().get(i);
			if (currTable.isOccupied()) {
				System.out.println("Table " + (i + 1) + " (" + currTable.getTableSize() + " top): " +
						currTable.getAssignedParty().getPartyName() + " party of " + currTable.getAssignedParty().getPartySize() +
						" - Server #" + currTable.getAssignedParty().getAssignedServer());
			} else {
				System.out.println("Table " + (i + 1) + " (" + currTable.getTableSize() + " top): empty");
			}
		}
		// Table 5 (2-top): Jones party of 2 - Server #2
		// Table 6 (4-top): empty
	}
	
	// Called when C key is pressed from main menu.
	// Helps process a party's check to leave the restaurant.
	private void checkPlease() {
		System.out.println("Send the check to a party that has finished eating:");
		String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");
		int serverNumber = -1;

		Boolean nameIsContained = false;
		for (int i = 0; i < restaurant.getTables().size(); i++) {
			Table currTable = restaurant.getTables().get(i);
			if (currTable.isOccupied()) {
				Party p = currTable.getAssignedParty();
				if (p.getPartyName().equals((partyName))) {
					nameIsContained = true;
					serverNumber = p.getAssignedServer();
					break;
				}
			}
		}

		if (!nameIsContained) {
			// when such a party is NOT sitting at a table in the restaurant,
			System.out.println("There is no party by that name.");
			System.out.println();
			return;
		}



		// when such a party is sitting at a table in the restaurant,
		double subtotal = ValidInputReader.getValidDouble("Bill subtotal?", 0.0, 9999.99);
		double tip = ValidInputReader.getValidDouble("Tip?", 0.0, 9999.99);


		for (int i = 0; i < restaurant.getTables().size(); i++) {
			Table currTable = restaurant.getTables().get(i);
			if (currTable.isOccupied()) {
				Party p = currTable.getAssignedParty();

				// TODO: give tip to server, e.g.:
				// Gave tip of $9.50 to Server #2.
				if (p.getPartyName().equals(partyName)) {
					System.out.println("Gave tip 0f $" + decfor.format(tip) + " to Server #" + p.getAssignedServer());

					for (int j = 0; j < restaurant.getServers().size(); j++) {
						if (restaurant.getServers().get(j).getServerNumber() == p.getAssignedServer()) {
							restaurant.getServers().get(j).setTotalTips(tip);
							break;
						}
					}
					currTable.setOccupied(false);
					break;
				}
			}
		}

			
		// update restaurant's cash register, e.g.
		restaurant.addToCashRegister(subtotal);
		// Gave total of $39.75 to cash register.
		System.out.println("Gave total of $" + decfor.format((1.10 * subtotal)) + " to cash register.");

		if (!restaurant.getWaitingList().isEmpty()) {
		System.out.println("Seating from waiting list:");
			for (int i = 0; i < restaurant.getWaitingList().size(); i++) {
				int smallestTable = restaurant.getSmallestAvailableTable(restaurant.getWaitingList().get(i).getPartySize());
				if (smallestTable != -1) {
					Party partyToBeSeated = restaurant.getWaitingList().get(i);
					restaurant.getTables().get(smallestTable).setAssignedParty(partyToBeSeated);
					restaurant.getTables().get(smallestTable).getAssignedParty().setAssignedServer(serverNumber);
					restaurant.getWaitingList().remove(i);
					System.out.println("Table " + (smallestTable + 1) + " (" + restaurant.getTables().get(smallestTable).getTableSize() + " top): " +
							restaurant.getTables().get(smallestTable).getAssignedParty().getPartyName() + " party of " +
							restaurant.getTables().get(smallestTable).getAssignedParty().getPartySize() +
							" - Server #" + restaurant.getTables().get(smallestTable).getAssignedParty().getAssignedServer());
					restaurant.iterator.next();
					// Table 6 (6-top): Erickson party of 5 - Server #2
					break;
				}
			}
		}
		System.out.println();
		return;
	}
	
	// Called when W key is pressed from main menu.
	// Displays the current waiting list, if any.
	private void waitingList() {
		System.out.println("Waiting list:");
		
		// TODO: show restaurant's waiting list, e.g.:

		// Johnson party of 7
		// Erickson party of 6
		if (!restaurant.getWaitingList().isEmpty()) {
			for (int i = 0; i < restaurant.getWaitingList().size(); i++) {
				Party currParty = restaurant.getWaitingList().get(i);
				System.out.println(currParty.getPartyName() + " party of " + currParty.getPartySize());
			}
		} else {
			// when there is nobody on the waiting list,
			System.out.println("empty");
		}
	}
	
	// Called when P key is pressed from main menu.
	// Helps seat a newly arriving party at a table in the restaurant.
	private void partyToBeSeated() {
		// when there are no servers,
		if (restaurant.getServers().isEmpty()) {
			System.out.println("Sorry, there are no servers here yet to seat this party");
			System.out.println("and take their orders.  Add servers and try again.");
			System.out.println();
			return;
		}


		// when there is at least one server,
		String partyName = ValidInputReader.getValidString("Party's name?", "^[a-zA-Z '-]+$");
		for (Party p : restaurant.getPartiesInRestaurant()) {
			if (partyName.equals(p.getPartyName())) {
				// when a duplicate party name is found,
				System.out.println("We already have a party with that name in the restaurant.");
				System.out.println("Please try again with a unique party name.");
				partyToBeSeated();
				break;
			}
		}

		int partySize = ValidInputReader.getValidInt("How many people in the party?", 1, 99999);

		// when the restaurant doesn't have any tables big enough to ever seat this party,
		if (partySize > restaurant.getLargestTable()) {
			System.out.println("Sorry, the restaurant is unable to seat a party of this size.");
			System.out.println();
			return;
		}

		Party party = new Party(partyName, partySize);
		restaurant.getPartiesInRestaurant().add(party);

		// TODO: try to seat this party

		int smallestTable = restaurant.getSmallestAvailableTable(partySize);
		if (smallestTable == -1) {
			System.out.println("Sorry, there is no open table that can seat this party now.");
			boolean wait = ValidInputReader.getYesNo("Place this party onto the waiting list? (y/n)");
			if (wait) {
				restaurant.addToWaitingList(party);
			}
		} else {
			restaurant.getTables().get(smallestTable).setAssignedParty(party);
			restaurant.allocateServers();
		}
		// when all tables large enough to accommodate this party are taken,
	}
	
	
	// You know what this method does.
	private void rickRoll() {
		// TODO: tell you how I'm feeling; make you understand
		System.out.println("We're no strangers to love");
		System.out.println("You know the rules and so do I");
		System.out.println("A full commitment's what I'm thinking of");
		System.out.println("You wouldn't get this from any other guy");
		System.out.println("I just wanna tell you how I'm feeling");
		System.out.println("Gotta make you understand");
		System.out.println();
		System.out.println("Never gonna give you up");
		System.out.println("Never gonna let you down");
		System.out.println("Never gonna run around and desert you");
		System.out.println("Never gonna make you cry");
		System.out.println("Never gonna say goodbye");
		System.out.println("Never gonna tell a lie and hurt you");
	}
}
