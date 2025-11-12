package phoneBookTask2;

import java.util.Optional;
import java.util.Set;

public class UsePhoneBook {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addNewUser("Robbin", "Williams", "+380501234567", "+380671234567");
        //додаємо нового користувача, перший номер телефону такий як і в попереднього
        phoneBook.addNewUser("Robbin", "Hood", "+380501234567", "+380681234567");

        phoneBook.addNewNumbersToUser("Robbin", "Hood", "+380671114567");
        //додаємо номер неіснуючому юзеру
        boolean resultOfAddingNewNumber = phoneBook.addNewNumbersToUser("Zak", "Snider", "+380673334567");
        if(!resultOfAddingNewNumber) System.out.println("User Zak Snider does not exist in the phonebook");

        phoneBook.addNewUser("Zak", "Snider");

        Set<String> allZakSniderNumbers = phoneBook.getAllUserNumbers("Zak", "Snider");
        if(allZakSniderNumbers.isEmpty()) System.out.println("Zak Snider does not have any numbers");

        Set<String> allRobbinWilliamsNumbers = phoneBook.getAllUserNumbers("Robbin", "Williams");
        if(!allRobbinWilliamsNumbers.isEmpty()) {
            System.out.println("All Robbin Williams numbers:");
            allRobbinWilliamsNumbers.forEach(System.out::println);
        }

        Optional<User> userByPhoneNumber = phoneBook.getUserByPhoneNumber("+380681234567");
        userByPhoneNumber.ifPresent(System.out::println);

        System.out.println(phoneBook);

    }
}