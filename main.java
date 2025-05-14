import java.io.*;
import java.util.*;
class Node {
        int data;
        int height;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {            
            this.data = data;
            this.height = 0;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    };

  class BinaryTree {
        Node root;
        int maxHeight;
        public BinaryTree() {
            this.root = null;
            maxHeight =-1;
        }

        public void insert(int data) {
            this.root = _insertRecursive(this.root, data,0);
        }

        private Node _insertRecursive(Node current, int data, int height) {
            if (current == null) {
                Node ret = new Node(data);
                ret.height = height;
                if(maxHeight<=height)
                    maxHeight=height;
                return ret;
            }

            if (data < current.data) {
                current.left = _insertRecursive(current.left, data, height+1);
                current.left.parent = current;
            } else if (data > current.data) {
                current.right = _insertRecursive(current.right, data, height+1);
                current.right.parent = current;
            } else {
                return current;
            }
            return current;
        
        }
        public  Node find(int data) {
            if(root==null)
                return null;
            return _findRecursive(root, data);
        }
        private Node _findRecursive(Node current, int data) {
                        
            if (current == null) {
                return null;
            }
            //System.out.printf("Finding %d, current is %d,  parent is %d!\n", data, current.data, current.parent==null?-1:current.parent.data);
            if (data < current.data) {
                return _findRecursive(current.left, data);
            } else if (data > current.data) {
                return _findRecursive(current.right, data);
            } else {
                return current;
            }
        }        
        public Integer findLCA(Integer v1, Integer v2) {
            Node n1= find(v1);
            Node n2 = null;
            if(n1==null)
                return -1;
            //System.out.printf("Found v1, its parent is %d!\n", n1.parent==null?-1:n1.parent.data);
            
            while(true){            
                n2 = _findRecursive(n1, v2);
                if(n2!=null){
                    return n1.data;
                }
                n1 = n1.parent;
                if(n1==null){
                    //System.out.printf("Reached top of tree, no lower common ancestor\n");
                    return root.data;
                }                         
            }            
        }
    };
    
public class Solution {
    private static void _printStrArray(String[] str_nodes_data){
        System.out.printf("[");
        for (String val : str_nodes_data) {
            System.out.printf("%s,",val);
        }
        System.out.printf("]\n");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str_n = scanner.nextLine();
        Integer n=-1;
        try{
            n = Integer.valueOf(str_n);
            
        }catch(NumberFormatException ex){
            System.out.printf("N exception : %s \n", ex.getMessage());
        }
                
        //System.out.printf("n:%d,",n);
        String str_nodes = scanner.nextLine();
        //System.out.printf("str_nodes:%s,",str_nodes);        
        String[] str_nodes_data = str_nodes.split(" ");
        //_printStrArray(str_nodes_data);
        String str_v1_v2 = scanner.nextLine();
        //System.out.printf("str_v21_v2:%s,",str_v1_v2);        
        scanner.close();
        String[] ar_v1_v2 = str_v1_v2.split(" ");
        Integer v1=-1;
        Integer v2=-1;
        BinaryTree tree = new BinaryTree();
        try{
            v1 = Integer.valueOf(ar_v1_v2[0]);
            
        }catch(NumberFormatException ex){
            System.out.printf("V1 exception : %s \n", ex.getMessage());
        }
        try{
            v2 = Integer.valueOf(ar_v1_v2[1]);
            
        }catch(NumberFormatException ex){
            System.out.printf("V2 exception : %s \n", ex.getMessage());
        }
                
        for (String str_data : str_nodes_data) {
            try{
                int data = Integer.valueOf(str_data);
                tree.insert(data);
            }catch(NumberFormatException ex){
                System.out.printf("Data exception : %s \n", ex.getMessage());
            }
        }
        Integer LCA = tree.findLCA(v1, v2);
        System.out.printf("%d",LCA);
        
    }
}