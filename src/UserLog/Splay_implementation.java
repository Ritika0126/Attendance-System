package UserLog;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

class Node{
	String name;
	int RollNo;
	Node left;
	Node right;
	Date in;
	Date out;
	
	Node(String name,int rollno){
		
		this.name=name;
		RollNo=rollno;
		left=right=null;
		if(in==null){
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		    in = new Date();
		    System.out.println(df.format(in));
		}
		/*else{
			DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		    out = new Date();
		    System.out.println(df.format(out));
		}*/
	}
}

/*public class Splay_implementation {
	Splay_implementation(){
		//Node root=new Node(100);
		splay sp=new splay();
		sp.insert("a",10);
		sp.insert("b",30);
		sp.insert("c",40);
		sp.insert("d",60);
		sp.insert("e",5);
		sp.preorder(sp.root);
		sp.delete(40);
		System.out.println();
		sp.preorder(sp.root);
		//return 0;
	}
}*/

public class Splay_implementation{
	Node root=null;
	
	public Node rightRotate(Node x){
		Node y=x.left;
		x.left=y.right;
		y.right=x;
		return y;
	}
	public Node leftRotate(Node x){
		Node y=x.right;
		x.right=y.left;
		y.left=x;
		return y;
	}
	
	public Node splay_node(Node root,int k){
		if(root==null || root.RollNo==k)
			return root;
		if(root.RollNo>k){ //k is in left subtree
			if(root.left==null)
				return root;
			if(root.left.RollNo>k){ //zig-zig rotation
				root.left.left=splay_node(root.left.left,k);
				root=rightRotate(root);
			}
			else if(root.left.RollNo<k){ //zig zag rotation
				root.left.right=splay_node(root.left.right,k);
				if(root.left.right!=null)
					root.left=leftRotate(root.left);
			}
			return (root.left==null)?root:rightRotate(root);
		}
		else{ // k is in right subtree
			if(root.right==null)
				return root;
			
			if(root.right.RollNo>k){ // Zig-Zag rotation
				root.right.left=splay_node(root.right.left,k);
				if(root.right.left!=null)
					root.right=rightRotate(root.right);
			}
			else if(root.right.RollNo<k){ //Zag-Zag rotation
				root.right.right=splay_node(root.right.right,k);
				root=leftRotate(root);
			}
			return (root.right==null)? root:leftRotate(root);
		}
	}
	public int insert(String s,int k){
		Node n=new Node(s,k);
		if(root==null)
			{
			root=n;
			return 1;
			}
			
		
		root=splay_node(root,k);
		if(root.RollNo==k)
			return 0;
		
		if(root.RollNo>k){
			n.right=root;
			n.left=root.left;
			root.left=null;
			root=n;
		}
		else{
			n.left=root;
			n.right=root.right;
			root.right=null;
			root=n;
		}
		return 1;
	}
	public int delete(int k){
		Node temp;
		if(root==null){
			return 0;
		}
		root=splay_node(root,k);
		if(k!=root.RollNo){
			return 0;
		}
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	    root.out = new Date();
	    //System.out.println(df.format(root.out));
		if(root.left==null){
			temp=root;
			root=root.right;
		}
		else{
			temp=root;
			root=splay_node(root.left,k);
			root.right=temp.right;
		}
		return 1;
	}
	public void preorder(Node root){
		if(root==null)
			return;
		System.out.print(root.RollNo+" "+root.name+"  " +root.in+" "+root.out);
		preorder(root.left);
		preorder(root.right);
	}
	
}



