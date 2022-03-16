/*
Author: Yakupguly Malikov
 */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class pyramidWithNumbers {

  public static boolean isPrime(int n) {
    if (n <= 1) return false;

    for (int i = 2; i <= Math.sqrt(n); i++) if (n % i == 0) return false;

    return true;
  }

  public static int findSum(
    ArrayList<Integer> list,
    ArrayList<Integer> rows,
    int i
  ) {
    // check if the number is prime or not
    if (isPrime(list.get(i))) return 0;

    /* finding row number of given index
    row is to find the children of the nodes
    for example to find 2 and 3 when index is 1
    1
    2 3
    */
    int row = -1, sum = 0;
    for (int j = 0; j < rows.size(); j++) if (i <= rows.get(j)) {
      row = j + 1;
      break;
    }

    // controls if we reached to the end of the list
    if (row + i >= list.size() - 1) return list.get(i);

    // calling left and right child of the node
    int lc = findSum(list, rows, row + i);
    int rc = findSum(list, rows, row + i + 1);

    // adding the greater child to the sum
    sum += list.get(i) + Math.max(lc, rc);
    return sum;
  }

  public static void main(String[] args) throws Exception {
    // enter the file location
    String fileLocation = ".\\defaultInput.txt";
    File file = new File(fileLocation);
    Scanner scan = new Scanner(file);

    ArrayList<Integer> list = new ArrayList<>();
    ArrayList<Integer> rows = new ArrayList<>();

    // getting inputs from file
    while (scan.hasNextLine()) {
      String s = scan.nextLine().trim();

      // remove if there are 2 or more whitespace in the string
      s = s.replaceAll("\\s+", " ");
      String[] str = s.split(" ");

      for (String a : str) {
        list.add(Integer.parseInt(a));
      }

      // storing row number of index for later use
      rows.add(list.size() - 1);
    }

    scan.close();

    int sum = findSum(list, rows, 0);
    System.out.println("The maximum sum of the numbers: " + sum);
  }
}
