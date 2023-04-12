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

        insert_iterative(newNode, this.root);
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
    
    public void stampa(Node node){
        if (node != null) {
            stampa(node.left);
            System.out.println("Node: " + node.key);
            stampa(node.right);
        }
        // System.out.println("Root: " + root.key);
        // Node current = root; 
        // Node parent = null;
        // while(current != null){
        //     current = current.right;
        //     System.out.println("Nodo: " + current.key);  
        // }
    }



    public static void main(String arg[]){
        System.out.println("Creo alberto ...");

        Bst b = new Bst(100); // istanza della classe Albero binario di ricerca
        
        System.out.println("inserisco elementi ...");
        b.insert(5);
        b.insert(101);
        b.insert(105);
        b.insert(103);

        System.out.println("Stampa ricorsiva: ");
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