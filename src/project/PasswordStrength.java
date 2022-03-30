package project;
import java.lang.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordStrength {

    public PasswordStrength(){
    }

    // public static boolean hasDigit(String password){
    //     for(char c : password){
    //         if(c.isDigit()){
    //             return true;
    //         }
    //     }
    //  }

    // public static boolean isUpperCase(String password){
    //     for(char c : password){
    //         if(c.isUpperCase()){
    //             return true;
    //         }
    //     }
    // }

    // public static boolean isLowerCase(String password){
    //     for(char c : password){
    //         if(c.isLowerCase()){
    //             return true;
    //         }
    //     }
    // }

    // public static hasSpecial(String password) {
    //     Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    //     Matcher m = p.matcher(password);
    //     return m.find();
    // }


    // Without regex 
    // public static boolean hasSpecial(String password) {
    //     String inputString = password;
    //     String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
    //     for (int i=0; i < inputString.length() ; i++) {
    //         char ch = inputString.charAt(i);
    //         if(specialCharactersString.contains(Character.toString(ch))) {
    //             return true;
    //             // System.out.println(inputString+ " contains special character");
    //             // break;
    //         }    
    //         else if(i == inputString.length()-1)     
    //             return false;    
    //             // System.out.println(inputString+ " does NOT contain special character");
    //     }
    // }


    public static int[] Password_Validation(String password){

        int flag[] = {0, 0, 0, 0, 0};

        Pattern upperCase = Pattern.compile("[A-Z]");
        Pattern lowerCase = Pattern.compile("[a-z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[^a-zA-Z0-9]");
        //Pattern eight = Pattern.compile (".{8}");
        
        
        Matcher hasUpper = upperCase.matcher(password);
        Matcher hasLower = lowerCase.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);

        if(password.length()>=8){
            flag[0] = 1;
        }
        if (hasUpper.find()){
            flag[1] = 1;
        }
        if (hasLower.find()){
            flag[2] = 1;
        }
        if (hasDigit.find()){
            flag[3] = 1;
        }
        if (hasSpecial.find()){
            flag[4] = 1;
        }

        return flag;
            // return hasLetter.find() && hasDigit.find() && hasSpecial.find();

    }

    public static int SumArray(int[] array){
        int sum = 0;
        for (int value : array) {
            sum += value;
        }    
        return sum;
    }

    public static void PrintReason(int[] flag){ 
        if (flag[0] != 1) {
            System.out.println("Password length too short!");
        }
        if (flag[1] != 1) {
            System.out.println("Password has no upper case letter!");
        }
        if (flag[2] != 1) {
            System.out.println("Password has no lower case letter!");
        }
        if (flag[3] != 1) {
            System.out.println("Password has no numbers!");
        }
        if (flag[4] != 1) {
            System.out.println("Password has no special characters!");
        }
    }


    public static void DeclareStrength(String password){

        int flag[] = Password_Validation(password);
        int sum = SumArray(flag);

        if (sum == 0){
            System.out.println("Terrible password!");
            PrintReason(flag);
        } else if (sum == 1) {
            System.out.println("Very Weak password");
            PrintReason(flag);
        } else if (sum == 2) {
            System.out.println("Weak password");
            PrintReason(flag);
        } else if (sum == 3) {
            System.out.println("Moderate password");
            PrintReason(flag);
        } else if (sum == 4) {
            System.out.println("Strong password");
            PrintReason(flag);
        } else if (sum == 5) {
            System.out.println("Very Strong password");
            PrintReason(flag);
        }

    }

    public static void main(String[] args) {
        String password = "passwOrd8@";
        PasswordStrength.DeclareStrength(password);
    }

}