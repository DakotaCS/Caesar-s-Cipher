/***********************************************************************
 * Assignment1, COMP400
 * Class: CaesarCipher.java
 * 
 * Purpose: This program uses Caesar's Cipher to encrypt and decrypt a plaintext message. Users are given the option of either: 
 * 	1. Encrypting a plaintext message with a key of their choice.
 * 	2. Decrypt a ciphertext message with a key of their choice. 
 * Valid keys are from A through Z inclusive. The program will not allow any keys other then letters. I utilized Wikipedia for a quick
 * backgrounder on Caesar's Cipher (https://en.wikipedia.org/wiki/Caesar_cipher#Breaking_the_cipher). However, as stated in the 
 * requirements, no generators were used and the code below is solely mine. 
 * 
 * @author: Dakota Soares
 * 
 * Student ID: 3318342
 * @date: March 26th, 2020
 * 
 * Notes: see included documentation for test cases, required parameters, etc. 
 * 
 */
//import relevant libraries
import java.io.*;
import java.util.*;  

public class CaesarCipher 
{
	//encrypted and decrypted string arrays
	static ArrayList<Character> encrypted = new ArrayList<Character>(); 
	static ArrayList<Character> decrypted = new ArrayList<Character>(); 
	//an array of character values representing the letters in the alphabet
	static  char[] alphabet = new char[26];
	//our shift (key) value
	static int shift = 0; 
	
	public static void main(String[] args)
	{
		//populate our alphabet array with letters from A - Z
		int j = 0;
		for (char i = 'A' ; i <= 'Z' ; i++)  
		{
		   alphabet[j] = i;
		   j++;
		}
		//create a buffered reader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//introduce the user to the program
		System.out.println("This cipher program will use Caesar's Cipher \n"
				+ "a well-known and straight-foward cipher, to encrypt a string"
				+ " of alphabetic characters.");
		//get the user's option
		System.out.println("Type 1 to Encrypt, Type 2 to Decrypt"); 
		try
		{
			//read the input and parse it to an integer
			String input = br.readLine(); 
			int option = Integer.parseInt(input); 
			//if the optio is 1 then encrypt
			if (option == 1)
			{
				//we need the key
				System.out.println("Please enter a letter representing the number of places to shift"
						+ "\n(i.e. D equates to a 3 letter shift to the right");
				//read in the value
				String key = br.readLine();
				//put the key into uppercase format
				key = key.toUpperCase(); 
				//get the first character
					char c = key.charAt(0);
					//for every letter in the alphabet try to find a match
					for (int i =0; i < alphabet.length; i++)
					{
						//if there is a match that is our shift value
						if (c == alphabet[i])
						{
							shift = i + 1; 
						}
					}
					//get the message to encrypt
					System.out.println("Enter your message in letters only, then press Enter:"); 
					String input2 = br.readLine(); 
					br.close(); 
					//if the message has only spaces and letters then we're good
					if (input2.matches("^[ A-Za-z]+$"))
					{
						//force uppercase
						input2 = input2.toUpperCase(); 
						System.out.println("You inputted: " + input2);
						//encrypt the message
						CaesarCipherEncrypt(input2); 
						//print out the encrypted message
						System.out.print("The encrypted message is: "); 
						for (char c2 : encrypted)
						{
							System.out.print(c2); 
						}
					}
					//otherwise something went wrong
					else 
					{
						System.out.println("Incorrect String, or no String entered.. Exiting Program."); 
						System.exit(0);
					}	
			}
			//if the second option was chosen (to decrypt)
			if (option == 2)
			{
				//get the key from the user
				System.out.println("Please enter a letter representing the number of places to shift"
						+ "\n(i.e. D equates to a 3 letter shift to the right)"); 
				String key = br.readLine(); 
				//force uppercase
				key = key.toUpperCase(); 
					char c = key.charAt(0); 
					//try to find the cooresponding letter index
					for (int i =0; i < alphabet.length; i++)
					{
						//if there is a match then we have our shift value
						if (c == alphabet[i])
						{
							shift = i + 1; 
						}
					}
					//have them enter the encrypted message
					System.out.println("Enter the encrypted message in upper-case letters only, then press Enter:"); 
					String input2 = br.readLine(); 
					br.close(); 
					//make sure it conforms to our rules
					if (input2.matches("^[ A-Za-z]+$"))
					{
						//force uppercase
						input2 = input2.toUpperCase(); 
						System.out.println("You inputted: " + input2);
						//decrypt the message
						CaesarCipherDecrypt(input2); 
						//pring out the decrypted message
						System.out.println("NOTE: The decyrypted message will note have spaces."); 
						System.out.print("The decrypted message is: "); 
						for (char c2 : decrypted)
						{
							System.out.print(c2); 
						}
					}
					//if something goes wrong do the following
					else 
					{
						System.out.println("Incorrect String, or no String entered.. Exiting Program."); 
						System.exit(0);
					}
			}
			
		}
		//catch IO exceptions
		catch (Exception e)
		{
			System.out.println("Incorrect Option. Exiting Program."); 
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	//Encrypt a string of text
	private static void CaesarCipherEncrypt(String input)
	{
		//for every character in the string
		for (int i = 0; i < input.length(); i++)
		{
			//get the character
			char c = input.charAt(i); 
			//for every letter in the alphabet
			for (int j = 0; j < alphabet.length; j++) 
			{
				//if there is a match
				if (c == alphabet[j])
				{
					//get it and add it to the shift value
					int get = j + shift;
					//the modulo allows us to not "fall off" the end of the array
					int mod = get % 26; 
					//add it to the encrypted arraylist
					encrypted.add(alphabet[mod]); 
				}
			}	
		}
	}
	
	//Decrypt a string of text
	private static void CaesarCipherDecrypt(String input) 
	{
		//for every character in the string
		for (int i = 0; i < input.length(); i++)
		{
			//get the character
			char c = input.charAt(i); 
			//for every letter in the alphabet
			for (int j = 0; j < alphabet.length; j++) 
			{
				//if there is a match
				if (c == alphabet[j])
				{
					//get it and add it to the shift value
					int get = j - shift; 
					//the modulo allows us to not "fall off" the end of the array
					int mod = (get + alphabet.length) % 26; 
					//add it to the decrypted arraylist
					decrypted.add(alphabet[mod]); 
				}
			}	
		}	
	}
//end of class CaesarCipher
}
