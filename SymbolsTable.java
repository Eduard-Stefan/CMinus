package cup.table;

import java.util.HashMap;
import java.util.Map;

import cup.example.Tree;
import cup.example.Node;

public class SymbolsTable {

	private Tree syntaxTree;
	private HashMap<String, SymbolDetails> table = new HashMap<String, SymbolDetails>();

	private void extractSymbolsFromNode(Node node, String currentContext, IdentifierScope scope) {
		String context = currentContext;
		IdentifierScope localScope = scope;
		if (node.getData().equals("var_declaration") || node.getData().equals("param")) {
			SymbolDetails details = new SymbolDetails();
			details.contextName = currentContext;
			details.symbolName = node.getExtraData();
			if (node.getChildren().size() > 0) {
				details.dataType = node.getChildren().getFirst().getExtraData();
			}
			details.symbolScope = scope;
			details.symbolType = SymbolType.Variable;
			table.put(details.symbolName, details);

		}
		if (node.getData().equals("fun_declaration")) {
			SymbolDetails details = new SymbolDetails();
			details.contextName = currentContext;
			details.symbolName = node.getExtraData();
			if (node.getChildren().size() > 0) {
				details.dataType = node.getChildren().getFirst().getExtraData();
			}
			details.symbolScope = scope;
			details.symbolType = SymbolType.Function;
			context = details.symbolName;
			localScope = IdentifierScope.Local;
			table.put(details.symbolName, details);
		}
		for (int i = 0; i < node.getChildren().size(); i++) {
			extractSymbolsFromNode(node.getChildren().get(i), context, localScope);
		}
	}

	public SymbolsTable(Tree syntaxTree) {
		this.syntaxTree = syntaxTree;
	}

	public void createTable() {
		extractSymbolsFromNode(syntaxTree.getRoot(), "Global", IdentifierScope.Global);
	}

	public SymbolDetails getSymbol(String symbol) {
		if (table.containsKey(symbol)) {
			return table.get(symbol);
		}
		return null;
	}

	public void printTable() {
		System.out.println("\nSymbols Table:");
		for (Map.Entry<String, SymbolDetails> mapEntry : table.entrySet()) {
			String symbol = mapEntry.getKey();
			SymbolDetails details = mapEntry.getValue();
			System.out.println("------------ SYMBOL: " + symbol + " -----------------");
			System.out.println("Data Type: " + details.dataType);
			System.out.println("Context: " + details.contextName);
			System.out.println("Symbol Type: " + details.symbolType);
			System.out.println("Symbol Scope: " + details.symbolScope);
		}
	}
}
