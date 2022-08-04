package com.bridgelabz_IO;
import java.util.*;
import java.util.stream.Collectors;
import java.util.function.Function;

public class AddressBook {
    public static ArrayList<Contacts> contactList = new ArrayList<>();
    public static Map<String, Contacts> nameHashMap = new HashMap<>();
    public static Map<String, Contacts> cityHashMap = new HashMap<>();
    public static Map<String, Contacts> stateHashMap = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public boolean addContact(Contacts contact) {
        List<Contacts> checkByName = searchByName(contact.getFirstName());
        for (Contacts equalName : checkByName)
            if (equalName.equals(contact))
                return false;
        contactList.add(contact);
        return true;
    }
    public List<Contacts> searchByName(String name) {
        return contactList.stream().filter(person -> person.getFirstName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Contacts> searchByCity(String city) {
        return contactList.stream().filter(person -> person.getCity().equalsIgnoreCase(city))
                .collect(Collectors.toList());
    }

    public List<Contacts> searchByState(String state) {
        return contactList.stream().filter(person -> person.getState().equalsIgnoreCase(state))
                .collect(Collectors.toList());
    }
    public static void viewByName(Map<String, Contacts> nameHashMap) {
        nameHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByCity(Map<String, Contacts> cityHashMap) {
        cityHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static void viewByState(Map<String, Contacts> stateHashMap) {
        stateHashMap.entrySet().stream().forEach(e -> System.out.println(e.getKey() + "=" + e.getValue().toString()));
    }

    public static List<Contacts> sortBy(Function<? super Contacts, ? extends String> key) {
        return contactList.stream().sorted(Comparator.comparing(key)).collect(Collectors.toList());
    }

    public void editContact(Contacts current, Contacts edit) {
        if (!contactList.contains(current))
            return;
        contactList.remove(current);
        contactList.add(edit);
    }
    public void deleteContact(Contacts contacts) {
        contactList.remove(contacts);
    }
    @Override
    public String toString() {
        if (contactList.isEmpty())
            return "No contacts found!";
        StringBuilder result = new StringBuilder();
        for (Contacts contacts : contactList) {
            result.append(" ").append(contacts);
        }
        return result.toString();
    }
    public static Contacts readContact() {
        System.out.print("Enter First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter City: ");
        String city = sc.nextLine();
        System.out.print("Enter State: ");
        String state = sc.nextLine();
        System.out.print("Enter Zip Code: ");
        String zip = sc.next();
        sc.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNumber = sc.next();
        sc.nextLine();
        System.out.print("Enter Email ID: ");
        String email = sc.nextLine();
        return new Contacts(firstName, lastName, address, city, state, zip, phoneNumber, email);
    }
    public static void addressBookOptions(AddressBook addressBook) {
        while (true) {
            System.out.println("\n-------------------------- Address Book Contact Option --------------------------");
            System.out.println("1. Add contact details");
            System.out.println("2. Edit contact details");
            System.out.println("3. Delete contact details");
            System.out.println("4. Show contacts details");
            System.out.println("5. Search Contact Details");
            System.out.println("6. View Contact Details");
            System.out.println("7. Sort Contact Details");
            System.out.println("8. Count Contact Details");
            System.out.println("9. Back to main menu");
            System.out.print("Enter Your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    if (addressBook.addContact(readContact()))
                        System.out.println("Contact Added Successfully");
                    else
                        System.out.println("Contact Already Exists");
                    break;
                case 2:
                    System.out.print("Enter First name: ");
                    String name = sc.nextLine();
                    List<Contacts> equalName = addressBook.searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found");
                    else if (equalName.size() == 1) {
                        addressBook.editContact(equalName.get(0), readContact());
                        System.out.println("Contact data modified");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.print("Enter index: ");
                        int i = sc.nextInt();
                        sc.nextLine();
                        addressBook.editContact(equalName.get(i), readContact());
                        System.out.println("Contact Modified");
                    }
                    break;
                case 3:
                    System.out.print("Enter First name: ");
                    name = sc.nextLine();
                    equalName = addressBook.searchByName(name);
                    if (equalName.isEmpty())
                        System.out.println("Data Not Found");
                    else if (equalName.size() == 1) {
                        addressBook.deleteContact(equalName.get(0));
                        System.out.println("Contact data deleted");
                    } else {
                        equalName.forEach(x -> System.out.println(equalName.indexOf(x) + "  " + x.toString()));
                        System.out.print("Enter index: ");
                        int index = sc.nextInt();
                        sc.nextLine();
                        addressBook.deleteContact(equalName.get(index));
                        System.out.println("Contact data deleted");
                    }
                    break;
                case 4:
                    System.out.println(addressBook.toString());
                    break;
                case 5:
                    addressBook.searchByOptions();
                    break;
                case 6:
                    viewByOption();
                case 7:
                    sortByOption();
                case 8:
                    countByOption();
                case 9:
                    return;
                default:
                    System.out.println("Invalid Choice!");
                    break;
            }
        }
    }

    public void searchByOptions() {
        System.out.println("1. By name");
        System.out.println("2. By city");
        System.out.println("3. By state");
        System.out.println("4. Back");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                contactList.forEach(book -> searchByName(name).forEach(System.out::println));
                break;
            case 2:
                System.out.print("Enter city: ");
                String city = sc.nextLine();
                contactList.forEach(book -> searchByCity(city).forEach(System.out::println));
                break;
            case 3:
                System.out.print("Enter state: ");
                String state = sc.nextLine();
                contactList.forEach(book -> searchByState(state).forEach(System.out::println));
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public static void viewByOption() {
        System.out.println("1. View By name");
        System.out.println("2. View By city");
        System.out.println("3. View By state");
        System.out.println("4. Back");
        System.out.print("Enter Your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                viewByName(nameHashMap);
                break;
            case 2:
                viewByCity(cityHashMap);
                break;
            case 3:
                viewByState(stateHashMap);
                break;
            case 4:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
    public static void countByOption() {
        System.out.println("1. Count City ");
        System.out.println("2. Count State");
        System.out.println("3. Back ");
        System.out.print("Enter Your Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                Map<String, Long> countCity = contactList.stream()
                        .collect(Collectors.groupingBy(Contacts::getCity, Collectors.counting()));
                System.out.println(countCity + "\n");
                break;
            case 2:
                Map<String, Long> countState = contactList.stream()
                        .collect(Collectors.groupingBy(Contacts::getState, Collectors.counting()));
                System.out.println(countState + "\n");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Option");
        }
    }

    public static void sortByOption() {
        System.out.println("1. By first name");
        System.out.println("2. By last name");
        System.out.println("3. By city");
        System.out.println("4. By state");
        System.out.println("5. By zip");
        System.out.println("6. Back");
        System.out.print("Your choice: ");

        int choice = sc.nextInt();
        sc.nextLine();
        switch (choice) {
            case 1:
                sortBy(Contacts::getFirstName).forEach(System.out::println);
                break;
            case 2:
                sortBy(Contacts::getLastName).forEach(System.out::println);
                break;
            case 3:
                sortBy(Contacts::getCity).forEach(System.out::println);
                break;
            case 4:
                sortBy(Contacts::getState).forEach(System.out::println);
                break;
            case 5:
                sortBy(Contacts::getZip).forEach(System.out::println);
                break;
            case 6:
                return;
            default:
                System.out.println("INVALID CHOICE!");
        }
    }
}