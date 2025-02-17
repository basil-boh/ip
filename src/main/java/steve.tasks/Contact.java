package steve.tasks;

/**
 * Represents a contact in a task management system.
 */
public class Contact extends Task {
    private final String name;

    /**
     * Constructs a Contact with the specified name.
     *
     * @param name The name of the contact.
     */
    public Contact(String name) {
        super(name);
        this.name = name;
    }

    /**
     * Validates a phone number.
     *
     * @param phone The phone number to validate.
     * @return The validated phone number if valid.
     * @throws IllegalArgumentException If the phone number is negative or does not have 8 digits.
     */
    public static String validatePhone(String phone) {
        int phoneNumLength = phone.length();
        if (Integer.parseInt(phone) < 0) {
            throw new IllegalArgumentException(negativePhoneNumMessage());
        } else if (phoneNumLength != 8) {
            throw new IllegalArgumentException(invalidPhoneLengthMessage());
        }
        return phone;
    }

    /**
     * Gets the name of the contact.
     *
     * @return The name of the contact.
     */
    public String getName() {
        return this.name;
    }

    //MESSAGE METHODS
    /**
     * Returns a success message when a contact is added.
     *
     * @return A message confirming the contact has been added.
     */
    public String messageString() {
        return "Successfully added: " + this.name + " to contact list";
    }

    /**
     * Returns an error message for invalid phone number length.
     *
     * @return A message indicating the phone number length is incorrect.
     */
    public static String invalidPhoneLengthMessage() {
        return "Invalid phone number entered! Please enter an 8-digit phone number";
    }

    /**
     * Returns an error message for negative phone numbers.
     *
     * @return A message indicating the phone number cannot be negative.
     */
    public static String negativePhoneNumMessage() {
        return "Invalid phone number entered! Phone number cannot be negative";
    }
}
