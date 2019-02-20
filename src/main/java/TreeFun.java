/*
https://www.codewars.com/kata/fun-with-trees-lists-edition
You are given a binary tree, where every node points to a head of an integer linked list. Implement the method flatten which returns a sorted linked list from the values of all the lists, without duplicates.

For example, given the following tree:

    17->1
   /  \
  3    1
 /    / \
2    16  7->3
The method should return 1 -> 2 -> 3 -> 7 -> 16 -> 17.

The classes TreeNode & ListNode are available for you:

class TreeNode {
  public TreeNode left;
  public TreeNode right;
  public ListNode listNode;

  TreeNode(ListNode listNode, TreeNode left, TreeNode right) {
    this.listNode = listNode;
    this.left = left;
    this.right = right;
  }

  TreeNode(ListNode listNode) {
    this(listNode, null, null);
  }
}

class ListNode {
  public int listNode;
  public ListNode next;

  ListNode(int listNode, ListNode next) {
    this.listNode = listNode;
    this.next = next;
  }

  ListNode(int listNode) {
    this(listNode, null);
  }
}
This kata is sponsored by fun with trees & fun with lists collections!

Check out Fun with lists: trees edition as well!
 */

import java.util.*;

public class TreeFun {
    static ListNode flatten(TreeNode root) {
        if (root == null) return null;
        SortedSet<ListNode> sortedSet = inOrderAddToSet(root, new TreeSet<>(Comparator.comparingInt(x -> x.data)));
        ListNode flattenedListHead = linkNodes(sortedSet);
        return flattenedListHead;
    }


    private static SortedSet<ListNode> inOrderAddToSet(TreeNode node, SortedSet<ListNode> sortedSet) {
        if (node.left != null) {
            inOrderAddToSet(node.left, sortedSet);
        }
        sortedSet = addListToSet(node.value, sortedSet);
        if (node.right != null) {
            inOrderAddToSet(node.right, sortedSet);
        }
        return sortedSet;
    }
    private static SortedSet<ListNode> addListToSet(ListNode listNode, SortedSet<ListNode> sortedSet) {
        ListNode currentNode = listNode;
        sortedSet.add(currentNode);
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            sortedSet.add(currentNode);
        }
        return sortedSet;
    }

    private static ListNode linkNodes(SortedSet<ListNode> sortedSet) {
        Iterator<ListNode> iterator = sortedSet.iterator();
        ListNode head = sortedSet.first();
        ListNode currentNode = head;
        while (iterator.hasNext()) {
            currentNode.next = iterator.next();
            currentNode = currentNode.next;
        }
        currentNode.next = null;
        return head;
    }
}

class TreeNode {
    public TreeNode left;
    public TreeNode right;
    public ListNode value;

    TreeNode(ListNode value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    TreeNode(ListNode value) {
        this(value, null, null);
    }
}

class ListNode {
    public int data;
    public ListNode next;

    ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    ListNode(int data) {
        this(data, null);
    }
}
