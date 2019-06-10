/***
Compile with javac algorithms.java
run with java algorithms
***/

import java.util.*;

public class algorithms
{
  public static void main(String[] args) {


    System.out.println(matchString("hello", "ksjdkfhelloksjk"));
  }

    public static int matchString(String pattern, String sequence)
    {
      /***
      finds the first occurrence of pattern in sequence.
      For simplicity assumes that sequence is only lower case alphabet

      ***/
      int size = pattern.length();
      int[] shiftTable = new int[26];
      for (int i=0;i<26;i++)
      {
        shiftTable[i] = size;
      }
      for (int i=0;i<size-1;i++)
      {
        shiftTable[Character.getNumericValue(pattern.charAt(i))-10] = size - i - 1;
      }
      int i = size-1;
      int n = sequence.length();
      while (i < n)
      {
        int j=0;
        while (sequence.charAt(i-j)==pattern.charAt(size-1-j))
        {
          if (j==size-1)
          {
            return i-j;
          }
          j++;
        }
        i+=shiftTable[Character.getNumericValue(sequence.charAt(i-j))-10];
      }
      return -1;
    }

    public static boolean isMagic(int[][] square)
    {
      /***
      input: 3x3 array of integers
      output: true if square is a magic square, false else
      definition of magic square: 3x3 array of integers, using each
      of the numbers 1,...,9 exactly once, such that the sum across
      each row, column, and diagonal is the same
      ***/
      int[] items = new int[9];
      for (int i=0;i<3;i++)
      {
        for (int j=0;j<3;j++)
        {
          int item = square[i][j];
          if (item<1 || item>9)
          {
            return false;
          }
          else if(items[item-1]==1)
          {
            return false;
          }
          else
          {
            items[item-1] = 1;
          }
        }

      }
      int constant = square[0][0] + square[0][1] + square[0][2];
      if (constant !=  square[1][0] + square[1][1] + square[1][2])
      {
        return false;
      }
      if (constant !=  square[2][0] + square[2][1] + square[2][2])
      {
        return false;
      }
      if (constant !=  square[0][0] + square[1][0] + square[2][0])
      {
        return false;
      }
      if (constant !=  square[0][1] + square[1][1] + square[2][1])
      {
        return false;
      }
      if (constant !=  square[0][2] + square[1][2] + square[2][2])
      {
        return false;
      }
      if (constant !=  square[0][0] + square[1][1] + square[2][2])
      {
        return false;
      }
      if (constant !=  square[0][2] + square[1][1] + square[2][0])
      {
        return false;
      }
      return true;

    }

  public static int[] CountSort(int[] A, int min, int max)
  {
    int[] distribution = new int[max-min+1];
    for (int a:A)
    {
      distribution[a-min] += 1;
    }
    for (int i=1;i<max-min+1;i++)
    {
      distribution[i] += distribution[i-1];
    }
    int[] result = new int[A.length];
    for (int j=A.length-1;j>=0;j--)
    {
      int i = A[j]-min;
      result[distribution[i]-1] = A[j];
      distribution[i]--;
    }
    return result;


  }

  public static List<Integer> quickSort(List<Integer> A)
  {
    if (A.size() <=1)
    {
      return A;
    }
    //_____________
    int n = A.size();
    List<Integer> B = new ArrayList<Integer>();
    List<Integer> C = new ArrayList<Integer>();
    int x = A.get(0).intValue();
    int xs = 0;
    for (int i=0;i<n;i++)
    {
      int y = A.get(i).intValue();
      if (y < x)
      {
        B.add(A.get(i));
      }
      else if (y==x)
      {
        xs++;
      }
      else
      {
        C.add(A.get(i));
      }
    }
    List <Integer> sortB = quickSort(B);
    for (int i=0;i<xs;i++)
      {
        sortB.add(A.get(0));
      }
    List <Integer> sortC = quickSort(C);
    sortB.addAll(sortC);
    return sortB;
  }

  public static int[][] heapPerm(int n)
  /***Finds all permutations of the array {0,1,2,...,n}
  returns as an array of arrays, each array one permutation
  ***/
  {
    Heap H = new Heap(n);
    H.perm(n);
    return H.getResult();
  }

  public static ArrayList<Integer> inorder(Node r){
    if (r==null)
    {
      return new ArrayList<Integer>();
    }
    else
    {
      ArrayList<Integer> result = new ArrayList<Integer>(inorder(r.left));
      result.add(r.data);
      result.addAll(inorder(r.right));
      return result;
    }
  }


  public static ArrayList<Integer> postorder(Node r){
    if (r==null)
    {
      return new ArrayList<Integer>();
    }
    else
    {
      ArrayList<Integer> result = new ArrayList<Integer>(postorder(r.left));
      result.addAll(postorder(r.right));
      result.add(r.data);
      return result;
    }
  }

}

class Node
{
  public Integer data;
  public Node left;
  public Node right;

  public Node(int _data)
  {
    data = new Integer(_data);
  }

  public Node(Integer _data)
  {
    data = _data;
  }

  public int getData()
  {
    return data.intValue();
  }
}

class Heap
{
  /***Class for computing permutations of arrays
  ***/
  public int[] A;
  public int index;
  public int[][] result;

  public Heap(int n)
  {
    A = new int[n];
    for (int i=0;i<n;i++)
    {
      A[i] = i+1;
    }
    index = 0;
    result = new int[factorial(n)][n];
  }

  public int[][] getResult()
  {
    return result;
  }

  public void perm(int n)
  {
    if (n==1)
    {
      for (int i=0;i<A.length;i++)
      {
        result[index][i] = A[i];
      }
      index ++;
    }
    else
    {
      for (int i=0;i<n;i++)
      {
        perm(n-1);
        if (n%2==0)
        {
          //swap n and i
          int x = A[i];
          A[i] = A[n-1];
          A[n-1] = x;
        }
        else
        {
          //swap n and 0
          int x = A[0];
          A[0] = A[n-1];
          A[n-1] = x;
        }
      }
    }
  }

  public static int factorial(int n)
  {
    int result = 1;
    for (int i=1;i<=n;i++)
    {
      result *= i;
    }
    return result;
  }


}
