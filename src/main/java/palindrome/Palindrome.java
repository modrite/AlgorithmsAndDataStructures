package palindrome;

import java.util.Scanner;

// 2 points
/** Write a program that checks whether a given string is a palindrome. */

public class Palindrome {

    public static void main(String[] args) {

        System.out.print("Please enter any string you wish to check if it is a palindrome or not: ");
        Scanner in = new Scanner(System.in);

        String originalString = in.nextLine();
        int length = originalString.length();

        boolean isPalindrome = true;

        for (int beginIndex = 0; beginIndex < length; beginIndex++) {
            if (originalString.charAt(beginIndex) != originalString.charAt(length - 1 - beginIndex)) {
                System.out.println("This String is not a palindrome.");
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("This String is a palindrome.");
        }
    }
}
