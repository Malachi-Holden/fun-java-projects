
import java.util.*;
public class ConstructTree
{
  public static void main(String[] args) {


    Node a = new Node(0);
    Node b = new Node(1);
    Node c = new Node(2);
    Node d = new Node(3);
    Node e = new Node(4);
    Node f = new Node(5);
    Node g = new Node(6);
    b.right = c;
    b.left = a;
    f.right = g;
    f.left = e;
    d.right = f;
    d.left = b;

    ArrayList<Integer> post = postorder(d);
    ArrayList<Integer> in = inorder(d);
    System.out.println(post);
    System.out.println(in);
    System.out.println("__________");
    try
    {
      Node root = constructTree(in, post);
      ArrayList<Integer> newpost = postorder(root);
      ArrayList<Integer> newin = inorder(root);
      System.out.println(newpost);
      System.out.println(newin);
    }
    catch (ListsDoNotFormTreeException ex)
    {
      System.out.println("those lists don't work");
    }

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

  public static Node constructTree(List<Integer> inList,
    List<Integer> postList) throws ListsDoNotFormTreeException
  {
    if (inList.isEmpty())
    {
      if (postList.isEmpty())
      {
        return null;
      }
      else
      {
        throw new ListsDoNotFormTreeException();
      }
    }
    if (inList.size() == 1)
    {
      if (postList.equals(inList))
      {
        return new Node(inList.get(0));
      }
      else
      {
        throw new ListsDoNotFormTreeException();
      }
    }
    Integer root = postList.get(postList.size()-1);
    int r = inList.indexOf(root);
    List<Integer> inLeft = inList.subList(0, r);
    List<Integer> inRight = inList.subList(r+1, inList.size());


    HashSet<Integer> rightSet = new HashSet<Integer>();
    for (int i=r+1;i<inList.size();i++) //creates a set containing everything to the right of the root
    {
      rightSet.add(inList.get(i));
    }

    int i = 0;
    while (!(rightSet.contains(postList.get(i))))//finds the first occurrence of something to the right of the root
    {
      i++;
    }

    if (rightSet.size() != inList.size()-i-1)
    {
      System.out.println(rightSet);
      System.out.println(inList.subList(i, inList.size()-1));
      throw new ListsDoNotFormTreeException();
    }

    List<Integer> postLeft = postList.subList(0,i);
    List<Integer> postRight = postList.subList(i, postList.size()-1);
    Node left = constructTree(inLeft, postLeft);
    Node right = constructTree(inRight, postRight);
    Node rootNode =  new Node(root);
    rootNode.left = left;
    rootNode.right = right;
    return rootNode;
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

class ListsDoNotFormTreeException extends Exception
{
  public ListsDoNotFormTreeException(String s)
  {
    super(s);
  }

  public ListsDoNotFormTreeException()
  {
    super("");
  }
}
