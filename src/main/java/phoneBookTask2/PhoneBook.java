package phoneBookTask2;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    private final Map<User, Set<String>> phoneBook = new HashMap<>();

    public void addNewUser(String userName, String userLastName, String... phoneNumbers) {
        User user = new User(userName, userLastName);
        Set<String> phoneNumberSet = formSetFromUniquePhoneNumbers(phoneNumbers);
        if (phoneBook.containsKey(user)) {
            phoneBook.get(user).addAll(phoneNumberSet);
        } else {
            phoneBook.put(user, phoneNumberSet);
        }
    }

    public boolean addNewNumbersToUser(String userName, String userLastName, String... phoneNumbers) {
        User user = new User(userName, userLastName);
        if (!phoneBook.containsKey(user)) {return false;}
        Set<String> phoneNumberSet = formSetFromUniquePhoneNumbers(phoneNumbers);
        phoneBook.get(user).addAll(phoneNumberSet);
        return true;
    }

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        for (Map.Entry<User, Set<String>> entry : phoneBook.entrySet()) {
            if (entry.getValue().contains(phoneNumber)) {
                return  Optional.of(entry.getKey());
            }
        }
        return Optional.empty();
    }

    public Set<String> getAllUserNumbers (String userName, String userLastName) {
        return phoneBook.get(new User(userName, userLastName));
    }

    private boolean isPhoneNumberNotExists(String phoneNumber) {
        return phoneBook.values().stream().noneMatch(phoneNumbers -> phoneNumbers.contains(phoneNumber));
    }

    private Set<String> formSetFromUniquePhoneNumbers(String... phoneNumbers) {
        return Arrays.stream(phoneNumbers)
                .filter(number -> isPhoneNumberNotExists(number))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return phoneBook.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + entry.getValue().stream().collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
    }
}