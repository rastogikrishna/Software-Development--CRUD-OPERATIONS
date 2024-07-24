
import java.io.*;
import java.util.*;

public class TelephoneDirectory {
    public static final String fileName= "directory.txt";
    public static void main(String[]args){
        Scanner sc= new Scanner(System.in);
        while(true){
            System.out.println("Telephone Directory:");
            System.out.println("1. Add Contact");
            System.out.println("2. Delete Contact");
            System.out.println("3. View Contact");
            System.out.println("4. Update Contact");
            System.out.println("5. Exit");
            System.out.print("Choose an option:");

            int choice= sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                addContact(sc);
                break;
                case 2:
                deleteContact(sc);
                break;
                case 3:
                viewContact();
                break;
                case 4:
                updateContact(sc );
                break;
                case 5:
                System.out.println("exiting...");
                return;
                default: 
                System.out.println("Invalid input");
        }
    }
}

private static void addContact(Scanner sc){
    System.out.print("Enter name:");
    String name= sc.nextLine();
    System.out.print("Enter phone no.");
    String phoneNumber= sc.nextLine();

    try(BufferedWriter writer= new BufferedWriter(new FileWriter(fileName, true))){
        writer.write(name+","+phoneNumber);
        writer.newLine();
        System.out.println("Contact added successfully");
    }catch(IOException e){
        System.out.println("Error adding contact"+ e.getMessage());
    }
}

private static void deleteContact(Scanner sc){
    System.out.print("Enter name to delete:");
    String nameToDelete = sc.nextLine();
    File inputFile= new File(fileName);
    File tempFile= new File("temp.txt");


    try(BufferedReader reader= new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer= new BufferedWriter(new FileWriter(tempFile))){
            String currentLine;
            boolean found= false;


            while((currentLine= reader.readLine())!=null){
                String[] contact= currentLine.split(",");
                if(!contact[0].equalsIgnoreCase(nameToDelete)){
                    writer.write(currentLine);
                    writer.newLine();
                }else{
                    found= true;
                }
                }
                if (found){
                    inputFile.delete();
                    tempFile.renameTo(inputFile);
                    System.out.println("Contact deleted successfully");
                }else{
                    tempFile.delete();
                    System.out.println("Contact not found");
                }
                }catch(IOException e){
                    System.out.println("Error deleting contact:"+ e.getMessage());
                }
                }


private static void viewContact(){
                    
    try(BufferedReader reader= new BufferedReader(new FileReader(fileName))){
                String currentLine;
                    while((currentLine=reader.readLine())!=null){
                    System.out.println(currentLine);
                 }
                 }catch(IOException e){
                 System.out.println("Error viewing contact"+ e.getMessage());
                    }
                }
                private static void updateContact(Scanner sc){
                    System.out.print("Enter name to update:");
                       String nameToUpdate = sc.nextLine();
                       File inputFile= new File(fileName);
                       File tempFile= new File("temp.txt");
                  
        try(BufferedReader reader= new BufferedReader(new FileReader(inputFile));
                   BufferedWriter writer= new BufferedWriter(new FileWriter(tempFile))){
                   String currentLine;
                   boolean found= false;
                  
                       while((currentLine= reader.readLine())!=null){
                       String[] contact= currentLine.split(",");
                                   if(!contact[0].equalsIgnoreCase(nameToUpdate)){
                                       System.out.print("Enter a new phone no.:");
                                       String newPhoneNumber= sc.nextLine();
                                       writer.write(contact[0]+","+newPhoneNumber);
                                       writer.newLine();
                                       found=true;
                                   }else{
                                       writer.write(currentLine);
                                       writer.newLine();
                                   }
                                   }
                                   if (found){
                                       inputFile.delete();
                                       tempFile.renameTo(inputFile);
                                       System.out.println("Contact updated successfully");
                                   }else{
                                       tempFile.delete();
                                       System.out.println("Contact not found");
                                   }
                                   }catch(IOException e){
                                       System.out.println("Error updating contact:"+ e.getMessage());
                                   }
                                }    
               }
   