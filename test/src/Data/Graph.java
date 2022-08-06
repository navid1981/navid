package Data;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

	private LinkedList<String>[] lists;
	private String type;
	private int vCount = 0;

	public Graph(int i, String type) {
		this.lists = new LinkedList[i];
		for (int j = 0; j < lists.length; j++) {
			lists[j] = new LinkedList<String>();
		}
		this.type = type;
	}

	public static void main(String[] args) {
		Graph myGraph = new Graph(5, "directed");
		myGraph.addVertex("State");
		myGraph.addVertex("Avenel");
		myGraph.addVertex("Elm");
		myGraph.addVertex("Pocono");
		myGraph.addVertex("William");

		myGraph.addEdge("Avenel", "Pocono");
		myGraph.addEdge("State", "Elm");
		myGraph.addEdge("Elm", "Avenel");
		myGraph.addEdge("Elm", "William");
		myGraph.addEdge("William", "State");
		myGraph.addEdge("William", "Pocono");
		myGraph.addEdge("Pocono", "Elm");
		myGraph.addEdge("State", "Avenel");

		myGraph.print();

	}

	private void print() {
		for (int i = 0; i < lists.length; i++) {
			LinkedList<String> list = lists[i];
//			ListIterator<String> l = list.listIterator();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.print(string+" --> ");
			}
			System.out.println("");
		}

	}

	private void addEdge(String string, String string2) {
		for (int i = 0; i < lists.length; i++) {
			if (lists[i].peek() == string) {
				lists[i].addLast(string2);
				if (this.type.equalsIgnoreCase("undirected")) {
					addSecondEdge(string2, string);
				}
				return;
			}
		}

	}

	private void addSecondEdge(String string, String string2) {
		for (int i = 0; i < lists.length; i++) {
			if (lists[i].peek() == string) {
				lists[i].addLast(string2);
				return;
			}
		}

	}

	private void addVertex(String string) {
		LinkedList<String> list = lists[vCount];
		list.add(0, string);
		vCount++;
	}

}
