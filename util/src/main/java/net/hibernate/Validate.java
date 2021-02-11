package net.hibernate;

import java.util.*;
import java.io.*;
import org.apache.commons.validator.GenericValidator;
import org.apache.commons.lang3.StringUtils;
import java.text.*;

public class Validate {
	public int numberInputValidate(Scanner getInput) {

		int choice = 0;
		boolean isNumber;
		do {
			if (getInput.hasNextInt()) {
				choice = getInput.nextInt();
				isNumber = true;
			} else {
	    		System.out.println("Please Input a valid number!");
	    		isNumber = false;
	    		getInput.next();
	    	}
	    } while (!(isNumber));
	    return choice;
	}

	public int validateInput (String message,int inputMin, int inputMax,Scanner scanner) {
		boolean choiceChecker = false;
		int choice = 0;

		do {
			System.out.print(message);
			String input = scanner.nextLine();
			while(!StringUtils.isNumeric(input)) {
				System.out.println("Invalid Input\n");
				System.out.println("Select between " + inputMin + " and " + inputMax);
			    System.out.print(message);
				input = scanner.nextLine();
			} 
			choice = Integer.parseInt(input);
			if (choice >= inputMin && choice <= inputMax){
				choiceChecker = true;
			} else {
				System.out.println("Invalid Input\n");
				System.out.println("Select between " + inputMin + " and " + inputMax);
			}
		} while (choiceChecker == false);
		return choice;
	}

	public int validateInput (String message,Scanner scanner) {
		String input = "";
		int returnVal = 0;
		do {
			System.out.print(message);
			input = scanner.nextLine();
			if(!StringUtils.isNumeric(input)) {
				System.out.println("Please input numbers only!");
				System.out.print(message);
			} else {
				returnVal = Integer.parseInt(input);
			}
			
		} while (!StringUtils.isNumeric(input));
		return returnVal;
	}

	public String notNullString(String message,Scanner scanner) {
		String strChecker = null;
		do {
			System.out.print(message);
			strChecker = scanner.nextLine();
			if(StringUtils.isBlank(strChecker)) {
				System.out.println(" cannot be blank!");
			}
		} while(StringUtils.isBlank(strChecker));
		return strChecker;
	}

	public double inputDouble(String message,Scanner scanner) {
		DecimalFormat df2 = new DecimalFormat("#.##");
		
		System.out.print(message);
		while (!scanner.hasNextDouble())
		{
			System.out.println(message + "must be numeric!!!");
			System.out.print(message);
			scanner.next();
		}
		double numeric = scanner.nextDouble(); 
		return Double.parseDouble(df2.format(numeric));
	}

	public Date inputDate(Scanner scanner) {
		Date date = new Date();
		String strChecker = "";
		boolean checker = true;
		do {
			checker = true;
			strChecker = scanner.nextLine();
			if(!GenericValidator.isDate(strChecker, "yyyy-MM-dd", true)) {
				//System.out.println("invalid input");
				checker = false;
			} else {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
				try {
					date = (Date)formatter.parse(strChecker);
				}catch(ParseException e){
					checker = false;
				}
			}
		} while(checker == false);
		return date;
	}

	public boolean validatePersonRole(Set<Roles> roles,String personRole) {
		boolean checker = false;
		if(!roles.isEmpty()){			
			for (Roles role : roles) {
				if(role.getRole().toUpperCase().equals(personRole.toUpperCase())){
					checker = true;
				}
			}
		}
		
		return checker;
	}

	public boolean inputBoolean(Scanner getInput){

		boolean returnVal = true;
		String strChecker = getInput.nextLine();
		while(!(strChecker.equalsIgnoreCase("Y") ||strChecker.equalsIgnoreCase("N"))){
			
			strChecker = getInput.nextLine();
		}

		if(strChecker.equalsIgnoreCase("Y")) {
			returnVal = true;
		} else {
			returnVal = false;
		}

		return returnVal;
	}

	public String emailValid(Scanner getInput){
	
		String email = "";
		boolean isEmail;
		do {
			System.out.print("Enter Email: ");
			email = getInput.nextLine();
			if (GenericValidator.isEmail(email)) {
				isEmail = true;
			} else {
	    		System.out.println("Invalid Email!!");
	    		isEmail = false;
	    	}
	    } while (!(isEmail));

		return email;
	}

	public long validMobileNumber(String message, Scanner getInput) {

		Long choice = null;
		boolean isNumber;
		
		do {
			System.out.print(message);
			if (getInput.hasNextLong()) {
				choice = getInput.nextLong();
				isNumber = true;
			} else {
	    		System.out.println("Please Input a valid number!");
	    		isNumber = false;
	    		getInput.nextLong();
	    	}
	    	if (String.valueOf(choice).length() > 10) {
		    	System.out.println("Max of 11 Numbers");
		    	isNumber = false;
			}

	    } while (!(isNumber));
	    return choice;
	}
}
