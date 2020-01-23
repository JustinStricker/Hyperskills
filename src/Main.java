import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


    public class Main {
        public static void main(String[] args) throws FileNotFoundException {

            String[] array1 = new String[1014130];
            String[] array2 = new String[500];
            int count = 0;

            // input phone numbers and phone numbers + names into two arrays (1014130)

            // ARRAY CREATION FROM FILES //

            System.out.println("Start searching (linear search)...");

            File file1 = new File("C:\\Users\\Justin\\Desktop\\directory.txt");
            File file2 = new File("C:\\Users\\Justin\\Desktop\\find.txt");


            try (Scanner scanner = new Scanner(file1)) {

                for (int i = 0; i < array1.length; i++) {
                    array1[i] = scanner.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + "C:\\Users\\Justin\\Desktop\\directory.txt");
            }

            try (Scanner scanner = new Scanner(file2)) {

                for (int i = 0; i < array2.length; i++) {
                    array2[i] = scanner.nextLine();
                }
            } catch (FileNotFoundException e) {
                System.out.println("No file found: " + "C:\\Users\\Justin\\Desktop\\find.txt");
            }


            // START OF LINEAR SEARCH //

            long start = System.currentTimeMillis();


            for (int i = 0; i < array2.length; i++) {
                for (int j = 0; j < array1.length; j++) {
                    if (array1[j].contains(array2[i])) {
                        count++;
                    }
                }
            }

            long end = System.currentTimeMillis();

            System.out.println("Found " + (count - 2) + " / " + 500 + " entries.");

            long result = end - start;

            System.out.println(result);

            long minutes = TimeUnit.MILLISECONDS.toMinutes(result);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(result) - (TimeUnit.MILLISECONDS.toMinutes(result) * 60);
            long milliseconds = result - (TimeUnit.MILLISECONDS.toSeconds(result) * 1000);


            System.out.println("Time taken: " + minutes + " min." + " " + seconds + " sec." + " " + milliseconds + " ms.");


            // BUBBLE SORT STARTS BELOW //

            String space = "";
            long current = 0;
            boolean tooLong = false;

            long start2 = System.currentTimeMillis();

            System.out.println("Start searching (bubble sort + jump search)...");

            for (int i = 0; i < array1.length - 1; i++) {
                for (int j = 0; j < array1.length - i - 1; j++) {
                    /* if a pair of adjacent elements has the wrong order it swaps them */

                    current = System.currentTimeMillis();

                    if (current - start2 > result * 10) {
                        tooLong = true;
                        break;
                    } else {

                        String[] part1 = array1[j].split(" ");
                        String[] part2 = array1[j + 1].split(" ");
                        if (part1.length > 2 && part1[2] != null && part2.length > 2 && part2[2] != null) {
                            if (part1[1].compareToIgnoreCase(part2[1]) + part1[2].compareToIgnoreCase(part2[2]) > 0) {
                                String temp = array1[j];
                                array1[j] = array1[j + 1];
                                array1[j + 1] = temp;
                            }

                        } else if (part1.length > 2 && part1[2] != null) {

                            if (part1[1].compareToIgnoreCase(part2[1]) + part1[2].compareToIgnoreCase(space) > 0) {
                                String temp = array1[j];
                                array1[j] = array1[j + 1];
                                array1[j + 1] = temp;
                            }

                        } else if (part2.length > 2 && part2[2] != null) {
                            if (part1[1].compareToIgnoreCase(part2[1]) + space.compareToIgnoreCase(part2[2]) > 0) {
                                String temp = array1[j];
                                array1[j] = array1[j + 1];
                                array1[j + 1] = temp;
                            }
                        } else {
                            if (part1[1].compareToIgnoreCase(part2[1]) + space.compareToIgnoreCase(space) > 0) {
                                String temp = array1[j];
                                array1[j] = array1[j + 1];
                                array1[j + 1] = temp;


                            }
                        }
                    }
                }
            }

            //LINEAR SEARCH CONTINUATION BELOW//

            count = 0;


            if (tooLong == true) {

                long end2 = System.currentTimeMillis();
                long result2 = end2 - start2;
                long start3 = System.currentTimeMillis();

                for (int i = 0; i < array2.length; i++) {
                    for (int j = 0; j < array1.length; j++) {
                        if (array1[j].contains(array2[i])) {
                            count++;
                        }
                    }
                }

                long end3 = System.currentTimeMillis();
                long result3 = end3 - start3;
                long bubblePlusLinear = result2 + result3;


                System.out.println("Found " + (count - 2) + " / " + 500 + " entries.");


                long minutesBubble = TimeUnit.MILLISECONDS.toMinutes(result2);
                long secondsBubble = TimeUnit.MILLISECONDS.toSeconds(result2) - (TimeUnit.MILLISECONDS.toMinutes(result2) * 60);
                long millisecondsBubble = result2 - (TimeUnit.MILLISECONDS.toSeconds(result2) * 1000);

                long minutesLinear = TimeUnit.MILLISECONDS.toMinutes(result3);
                long secondsLinear = TimeUnit.MILLISECONDS.toSeconds(result3) - (TimeUnit.MILLISECONDS.toMinutes(result3) * 60);
                long millisecondsLinear = result3 - (TimeUnit.MILLISECONDS.toSeconds(result3) * 1000);

                long minutesLinearSort = TimeUnit.MILLISECONDS.toMinutes(bubblePlusLinear);
                long secondsLinearSort = TimeUnit.MILLISECONDS.toSeconds(bubblePlusLinear) - (TimeUnit.MILLISECONDS.toMinutes(bubblePlusLinear) * 60);
                long millisecondsLinearSort = bubblePlusLinear - (TimeUnit.MILLISECONDS.toSeconds(bubblePlusLinear) * 1000);


                System.out.println("Time taken: " + minutesLinearSort + " min." + " " + secondsLinearSort + " sec." + " " + millisecondsLinearSort + " ms."); // Bubble sort + linear search
                System.out.println("Sorting Time: " + minutesBubble + " min." + " " + secondsBubble + " sec." + " " + millisecondsBubble + " ms. - STOPPED, moved to linear search"); //Bubble sort
                System.out.println("Searching time: " + minutesLinear + " min." + " " + secondsLinear + " sec." + " " + millisecondsLinear + " ms."); //Linear search


            } else {
                System.out.println("something went wrong");
            }

            // ASSIGN ZERO TO ABSENT LAST NAMES IN NEW ARRAY //

            String[] array3 = new String[1014130];

            String[] part1;

            for (int i = 0; i < array1.length; i++) {
                part1 = array1[i].split(" ");
                if (part1.length > 2 && part1[2] != null) {
                    array3[i] = part1[0] + " " + part1[1] + " " + part1[2];
                } else {
                    array3[i] = part1[0] + " " + part1[1] + " " + "0";
                }
            }

            String[] array4 = new String[500];
            for (int i = 0; i < array2.length; i++) {
                part1 = array2[i].split(" ");
                if (part1.length > 1 && part1[1] != null) {
                    array4[i] = part1[0] + " " + part1[1];
                } else {
                    array4[i] = part1[0] + " " + "0";
                }
            }


            //QUICK SORT BEGINS BELOW//
            quickSort(array3, 0, array3.length - 1);

            // System.out.println(Arrays.toString(array3)); // quickSort results printed


            int binaryResult = 0;

            for(int i = 0; i < array4.length; i++) {
                binaryResult = binarySearch(array3, array4[i], 0, array3.length - 1);

                System.out.println("____________________________");
                //System.out.println(binaryResult);
            }



        }


        public static void quickSort(String[] array, int left, int right) {
            if (left < right) {
                int pivotIndex = partition(array, left, right); // the pivot is already on its place
                quickSort(array, left, pivotIndex - 1);  // sort the left subarray
                quickSort(array, pivotIndex + 1, right); // sort the right subarray
            }
        }

        private static int partition(String[] array, int left, int right) {
            String pivot = array[right];  // choose the rightmost element as the pivot
            int partitionIndex = left; // the first element greater than the pivot

            /* move large values into the right side of the array */
            for (int i = left; i < right; i++) {

                //insert split here
                String[] pivotSplit = pivot.split(" ");
                String[] arrayValue = array[i].split(" ");


                if (arrayValue[1].compareTo(pivotSplit[1]) + arrayValue[2].compareTo(pivotSplit[2]) < 0) { // may be used '<' as well
                    swap(array, i, partitionIndex);
                    partitionIndex++;
                }
            }

            swap(array, partitionIndex, right); // put the pivot on a suitable position

            return partitionIndex;
        }

        private static void swap(String[] array, int i, int j) {
            String temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        // edit this method //
        public static int binarySearch(String[] array, String elem, int left, int right) {

            while (left <= right) {
                int mid = left + (right - left) / 2; // the index of the middle element

                System.out.println(left + " " + right);

                String[] elemSplit = elem.split(" "); // split for elem
                String[] midSplit = array[mid].split(" "); // insert split for mid

                System.out.println(midSplit[1] + " " + midSplit[2]);
                System.out.println(elemSplit[0] + " " + elemSplit[1]);

                long stuff = midSplit[2].compareTo(elemSplit[1]);
                long stuff2 = midSplit[1].compareTo(elemSplit[0]);
                long stuff3 = stuff + stuff2;

                System.out.println(stuff + " " + stuff2 + " " + stuff3);



                if (stuff3 == 0) { // (moi) needs to be edited
                    return mid; // the element is found, return its index
                } else if (stuff3 < 0) {
                    left = mid + 1;  // go the the right subarray
                    System.out.println("right");
                } else { // (moi) needs to be edited
                    right = mid - 1; // go to the left subarray
                    System.out.println("left");
                }

            }
            return -1; // the element is not found
        }
    }




