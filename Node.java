package cup.example;

import java.util.ArrayList;

public class Node {
	private String data;
	private String extraData;
	private ArrayList<Node> children;

	public Node(String data) {
		this.data = data;
		this.children = new ArrayList<>();
	}
	
	public Node(String data, String extraData) {
		this.data = data;
		this.extraData = extraData;
		this.children = new ArrayList<>();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public void addChild(Node child) {
		this.children.add(child);
	}
}