import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

import java.util.ArrayList;
import java.util.Scanner;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int result = 0;
    public int getResult() {
      	//implement this
        return this.result;
    }

    public void visitNode(TreeNode node) {
      	//implement this
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
          result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    private final int M = 1000000007;
    private long result = 1;
    public int getResult() {
      	//implement this
        return (int) this.result;
    }

    public void visitNode(TreeNode node) {
      	//implement this
          if (node.getColor() == Color.RED) {
            result = (result * node.getValue()) % M;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
          if (leaf.getColor() == Color.RED) {
            result = (result * leaf.getValue()) % M;
        }
    }
}

class FancyVisitor extends TreeVis {
    private int countGreen = 0;
    private int countNonLeaf = 0;
    public int getResult() {
      	//implement this
        return Math.abs(countNonLeaf - countGreen);
    }

    public void visitNode(TreeNode node) {
    	//implement this
        if (node.getDepth() % 2 == 0) {
            countNonLeaf += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
    	//implement this
        if (leaf.getColor() == Color.GREEN) {
            countGreen += leaf.getValue();
        }
    }
}

public class Solution {
  
    public static Tree solve() {
        //read the tree from STDIN and return its root as a return value of this function
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Tree[] nodes = new Tree[n];
        boolean[] isNode = new boolean[n];
        int[] depth = new int[n];
        int[] value = new int[n];
        Color[] color = new Color[n];
        int[] parent = new int[n];
        boolean[] isVisited = new boolean[n];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) { adj.add(new ArrayList<Integer>()); }
        for (int i = 0; i < n; i++) { value[i] = sc.nextInt(); }
        for (int i = 0; i < n; i++) { color[i] = (sc.nextInt() == 0) ? Color.RED : Color.GREEN; }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt(); int v = sc.nextInt();
            u--; v--;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Perform a DFS
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        isVisited[0] = true;
        isNode[0] = true;
        depth[0] = 0;
        while (!stack.empty()) {
            int current = stack.pop();
            for (int i = 0; i < adj.get(current).size(); i++) {
                int child = adj.get(current).get(i);
                if (!isVisited[child]) {
                    depth[child] = depth[current] + 1;
                    parent[child] = current;
                    isNode[current] = true;
                    isVisited[child] = true;
                    stack.push(child);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (isNode[i]) {
                nodes[i] = new TreeNode(value[i], color[i], depth[i]);
            } else {
                nodes[i] = new TreeLeaf(value[i], color[i], depth[i]);
            }
        }

        for (int i = 1; i < n; i++) {
            TreeNode current = (TreeNode) nodes[parent[i]];
            current.addChild(nodes[i]);
        }

        return nodes[0];
    }


    public static void main(String[] args) {
      	Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);
	}
}