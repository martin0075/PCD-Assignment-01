
/**
* BST - BinarySearchTree
*/
public class Bst {
    public static  Node root;

    public Bst(int key){
        this.root = new Node(key);
    }


    public void insert(int key){
        Node newNode = new Node(key);

        //insert_iterative(newNode, this.root);
        insert_ricorsiva(newNode, this.root);
    }

    // public void insert_ricorsiva(Node newNode, Node current){
    //     if(current!=null){
    //         if (newNode.key > current.key) {
    //             insert_ricorsiva(newNode,current.right);
    //         }else if(newNode.key < current.key){
    //             insert_ricorsiva(newNode,current.left);
    //         }
    //     }
    //     else
    //         link(current.parent,newNode);
    // }
    
    public void insert_ricorsiva(Node newNode, Node root) 
    {
    	if(root.right != null) 
    	{
    		if (newNode.key > root.key) 
    			insert_ricorsiva(newNode, root.right);
    	}      
        else if(root.left!=null) 
        {
        	if(newNode.key < root.key)
            	insert_ricorsiva(newNode, root.left);
        }
    	else link(root,newNode);
    		
    }


    
    // public void insert_ricorsiva(Node newNode, Node current){
    //     if (newNode.key > current.key) {
    //         if (current.right == null) {
    //             link(current,newNode);
    //         }else{
    //             insert_ricorsiva(newNode,current.right);
    //         }
    //     }else if(newNode.key < current.key){
    //         if (current.left == null) {
    //             link(current,newNode);
    //         }else{
    //             insert_ricorsiva(newNode,current.left);
    //         }
    //     }
    // }

    public void stampa(Node node){
        if (node != null) {
            stampa(node.left);
            System.out.println("Node: " + node.key);
            stampa(node.right);
        }
    }

    public void insert_iterative(Node newNode, Node root){
        Node current = root; 
        Node parent = null;

        // trovare riferimento 
        while(current != null){
            parent = current;
            if (newNode.key > current.key) {
                current = current.right;
            }else if(newNode.key < current.key){
                current = current.left;
            }
        }
        
        // attacca il nodo nuovo all'albero
        link(parent,newNode);
    }

    public void link(Node p, Node newNode){
        if (newNode != null) {
            newNode.parent = p;
        } // aggiorniamo il padre del nodo nuovo.
        if (p != null) {
            if (newNode.key < p.key) {
                p.left = newNode;
            }else{
                p.right = newNode;
            }
        } // aggiorno il figlio del nodo di riferimento (trovato dal while)
    }

    public void unlink(Node p, Node newNode){
        if (newNode != null) {
            newNode.parent = null;
        } // aggiorniamo il padre del nodo nuovo.
        if (p != null) {
            if (newNode.key < p.key) {
                p.left = null;
            }else{
                p.right = null;
            }
        } // aggiorno il figlio del nodo di riferimento (trovato dal while)
    }
    
 

    public boolean lookup(Node root, int key){
        if(root == null){
            return false;
        }else if (root.key == key) {
            return true;
        }
        else{
            return lookup((root.key > key) ? root.left : root.right, key);
        }
    }

    public Node lookupNode(Node root, int key){
        if(root == null){
            return null;
        }else if (root.key == key) {
            return root;
        }
        else{
            return lookupNode((root.key > key) ? root.left : root.right, key);
        }
    }

    public void remove(Node root, int key){
        Node u = lookupNode(root,key);
        if (u != null) {
            if (u.left == null && u.right == null) {
                if(u.parent != null){
                    unlink(u.parent, u);
                }
            }
            
        }

    }

    public static void main(String arg[]){
        System.out.println("Creo alberto ...");

        Bst b = new Bst(100); // istanza della classe Albero binario di ricerca
        
        System.out.println("inserisco elementi ...");
        b.insert(5);
        b.insert(101);
        b.insert(105);
        b.insert(108);

        System.out.println("Stampa ricorsiva: ");
        b.stampa(b.root);

        int keyx = 101;
        System.out.println("Existenza del "+ keyx+ " è: "+b.lookup(b.root, keyx));

        int keyy = 201;
        System.out.println("Existenza del "+ keyy+ " è: "+b.lookup(b.root, keyy));

        b.remove(b.root,108);

        b.stampa(b.root);
        // System.out.println("Chiave della radice " + b.root.key);

    }
}

class Node{
    int key;
    int value; // lo facciamo dopo
   
    Node left; // figlio 1
    Node right; // figlio 2
    Node parent;
    
    public Node(int key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    // public Node(int key, int value){
    //     this.key = key;
    //     this.left = null;
    //     this.right = null;
    //     this.value = value;
    // }

}