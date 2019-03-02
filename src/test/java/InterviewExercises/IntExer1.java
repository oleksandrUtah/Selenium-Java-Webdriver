package InterviewExercises;

import org.testng.annotations.Test;

import java.util.Arrays;

public class IntExer1 {

    @Test (priority = 001)              // Loop that will return even numbers from 1-10
    public void testPrintEvenNumbers() {
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0)
                System.out.println(i);
        }
    }
    @Test (priority = 002)              // To print "hello world" at even numbers
    public void printHelloAtEvenNumbers() {
        for (int i = 1; i <= 10; i++) {
            if (i%2==0)
                System.out.println(i + " - hello world");
        }
    }

    @Test (priority = 003)          // Swap two numbers in an array
    public void swapTwoNumbersInArray() {
        int[] array = {1, 2, 3, 4};     // initialise of array
        System.out.println(Arrays.toString(array));
        swap(array, 1, 2);      // the main logic of this test
        System.out.println(Arrays.toString(array));
    }
    private void swap(int[] array, int i, int i1) {     //rotation
        int temp = array[i];
        array[i] = array[i1];
        array[i1] = temp;
    }
    @Test (priority = 004)      // count the number of letters 'l'
    public void testCountLetters() {
        String input = "hello world";
        int result = 0;
        for(char eachChar : input.toCharArray()){
            if(eachChar== 'l') result++;
        }
        System.out.println(result);
    }
}

