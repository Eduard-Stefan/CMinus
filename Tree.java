package cup.example;

public class Tree {
	private Node root;

	public Tree() {
		root = null;
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void printTree() {
		if (root != null) {
			System.out.println("\nTree:");
			printTree(root, 0);
		} else {
			System.out.println("\nTree is empty.");
		}
	}

	private void printTree(Node node, int depth) {
		if (node == null) {
			return;
		}
		for (int i = 0; i < depth; i++) {
			System.out.print(" ");
		}
		System.out.println(node.getData());
		for (Node child : node.getChildren()) {
			printTree(child, depth + 1);
		}
	}
}