/* Author: Tytus Felbor
 * Program prints out in the console a parse table that is build using a textbook algorithm and its grammar G9, that's been modified to accept "id=E".
 * G9: 	1. P	-> 	id=E;
 *		2. E 	-> 	T E'
 * 		3. E' 	-> 	+ T E'
 * 		4. E' 	-> 	epsilon
 * 		5. T 	-> 	F T'
 *		6. T' 	-> 	* F T'
 * 		7. T' 	-> 	epsilon
 * 		8. F 	-> 	(E)
 * 		9. F 	-> 	int
 * 		10. F	->	id
 */
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Read_Scan_Parse {

	static ArrayList<String> terminals;
	static ArrayList<String> nonterminals;
	// Rule #:         			1    					2					3					4						5					6				7					8				9		10
	static String[] left = {	"P",					"E",				"E'",				"E'",					"T",				"T'",			"T'",				"F",			"F", 	"F"};
	static String[][] right = {{"id","=", "E", ";"}, 	{"T","E'"},			{"+","T","E'"},		{"epsilon"},			{"F","T'"},			{"*","F","T'"},	{"epsilon"},	{"(","E",")"},	{"int"}, {"id"}};
	//								P					E					E'					T						T'					F
	static String[][] firstSets = {{"id"},				{"id", "int", "("},	{"+", "epsilon"},	{"id", "int", "("},		{"*", "epsilon"},	{"id", "int", "("}};
	static String[][] followSets = {{"$"}, 				{"$", ")"},   		{"$", ";", ")"}, 		{"$", "+", ")"}, 		{"$", ";", "+", ")"}, 	{"$", "+", ")"} };
	// Variables for the scanner function
	static Scanner scan;
	static ArrayList<Pattern> grammar = new ArrayList<>();
	static HashMap<Pattern, String> map = new HashMap<>();
	static int[][] parseTableG9;

	// Building the transition table method
	public static int[][] build() {
		
		// Parse Table T[A, a], where A is a nonterminal which is a aprt G9, and a is a terminal derived from the terminals variable
				int [][] T = new int[nonterminals.size()][terminals.size()];
				int ruleNr = 1;

				// #1. For each rule A -> α in G:
				for (String A : left) {
					System.out.println("Rule number: " + ruleNr + "\tA = " + A + "");
					String alpha = right[ruleNr-1][0];
					System.out.println("\t\tα = " + alpha);
					
					
					/* #2. For each terminal a (excepting epsilon) in FIRST(α)
						α is a terminal ==> FIRST(α) == α */
					if (terminals.contains(alpha) && !(alpha.equals("epsilon"))) {
						String terminal_a = "" + alpha;
						System.out.println("\t\tα == FIRST(α)");
						System.out.println("\t\t'" + alpha + "' != epsilon & is terminal \n"
								+ "\t\tT[" + nonterminals.indexOf(A) + "][" + terminals.indexOf(alpha) + "]" + " = " + ruleNr + "\n");
						// Add A -> α to T[A, a]
						T[nonterminals.indexOf(A)][terminals.indexOf(alpha)] = ruleNr;
					} // For each terminal a (excepting epsilon) in FIRST(α) 
					else if (nonterminals.contains(alpha)) {
						for (String a : firstSets[nonterminals.indexOf(alpha)]) {
							System.out.println("\t\t'" + a + "' != epsilon\n"
									+ "\t\tT[" + nonterminals.indexOf(A) + "][" + terminals.indexOf(a) + "]" + " = " + ruleNr + "\n");

							// Add A -> α to T[A, a]
							T[nonterminals.indexOf(A)][terminals.indexOf(a)] = ruleNr;
						}
					} else if (alpha.equals("epsilon")) {
						// For each terminal b (including $) in FOLLOW(A)
						for (String b : followSets[nonterminals.indexOf(A)]) {
							System.out.println("\t\tb == '" + b + "' ");
							System.out.println("\t\tT[" + nonterminals.indexOf(A) + "][" + terminals.indexOf(b) + "]" + " = " + ruleNr + "\n");
							// Add A -> α to T[A, b]
							T[nonterminals.indexOf(A)][terminals.indexOf(b)] = ruleNr;
						}
					}
					ruleNr++;
				}
				System.out.println("---------------DONE----------------------------\n");
				System.out.println("Parsing table for modified G9 grammar:\n");
				System.out.println("\tid\tint\t(\t)\t+\t*\t$\t;");
				for (String nonTerminal : nonterminals) {
					System.out.print(nonTerminal + "\t");
					for (String terminal : terminals) {
						System.out.print(T[nonterminals.indexOf(nonTerminal)][terminals.indexOf(terminal)] + "\t");
					}
					System.out.println();
				}
				System.out.println();
				return T; 
	}

	// Scanning tokens from a txt file method
	public static ArrayList<String> scanFile(String fileName) {

		Pattern number = Pattern.compile("[0-9]+");
		Pattern operators = Pattern.compile("[*|+]");
		Pattern parenthesis = Pattern.compile("[(|)]+");
		Pattern semicolor = Pattern.compile("[;]+");
		Pattern equals = Pattern.compile("[=]");
		Pattern id = Pattern.compile("[a-z]+");

		map.put(number, "int");
		map.put(operators, "operator");
		map.put(parenthesis, "paren.");
		map.put(semicolor, ";");
		map.put(equals, "=");
		map.put(id, "id");

		grammar.add(number);
		grammar.add(operators);
		grammar.add(parenthesis);
		grammar.add(semicolor);
		grammar.add(equals);
		grammar.add(id);

		ArrayList<String> tokens = new ArrayList<>(); 
		boolean matched = false;
		try {
			scan = new Scanner(new File(fileName));
			System.out.println("Reading tokens from the file... " + fileName
					+ "\n------------SCANNING--START--------------------");
			while (scan.hasNext()) {
				String token = scan.next();
				matched = false;
				for (Pattern regEx : grammar) {
					if (regEx.matcher(token).matches()) {
						System.out.println(map.get(regEx) + " found:\t\t" + token + "\t" + "(Regex: " + regEx + ")");
						matched = true;
						if (map.get(regEx).equals("int")) {
							tokens.add("int");
						} else if (map.get(regEx).equals("id")) {
							tokens.add("id");
						}
						else {
							tokens.add(token);
						}
						break;
					}
				}
				if (!matched) {
					System.out.println("Error: Unrecognized token found: " + token);
					break;
				}
			}
			map.clear();
			grammar.clear();
			scan.close();
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		System.out.println("------------SCANNING--DONE---------------------");
		System.out.println("Scanned tokens:\t\t\t" + tokens.toString() + "\n"
				+ "-----------------------------------------------");
		return tokens;
	}
	// Parsing method
	public static void parse(ArrayList<String> input) {
		// Parse table
		int[][] table = parseTableG9.clone();

		boolean done = false;
		
		// Initialize
		Stack<String> stack = new Stack<String>();
		stack.push("$");
		stack.push("P");
		// Main loop
		while (!stack.empty()) {
			String X = stack.peek();
			String c = input.get(0);
			if (stack.toString().equals("")) {
				
			}
			if (X.equals(c)) {
				System.out.println("\tMATCH '" + c + "'");
				stack.pop();
				input.remove(0);
			} else {
				stack.pop();
				System.out.println("Stack:\t" + stack.toString() + "\nInput:\t" + input.toString());
				int rowIndex = nonterminals.indexOf(X);
				System.out.println("X = " + X.toString());
				int columnIndex = terminals.indexOf(c);
				System.out.println("c = " + c.toString());
				int ruleNumber = table[rowIndex][columnIndex] - 1;
				System.out.println("Apply\t#" + (ruleNumber + 1) + "\n");
				String[] theta = right[ruleNumber];
				for (int i = theta.length - 1; i >= 0; i--) {
					if (!(theta[i].equals("epsilon"))) {
						stack.push(theta[i]);
					}
				}
			}
		} System.out.println("Stack:\t" + stack.toString() + "\nInput:\t" + input.toString() + "\nDONE!");
	}
	public static void main(String[] args) {
		// Initiate & populate the terminals arraylist
		terminals = new ArrayList<String>();
		terminals.add("id");
		terminals.add("int");
		terminals.add("(");
		terminals.add(")");
		terminals.add("+");
		terminals.add("*");
		terminals.add("$");
		terminals.add(";");
		
		// Initiate & populate the non-terminals arraylist
		nonterminals = new ArrayList<String>();
		nonterminals.add("P");
		nonterminals.add("E");
		nonterminals.add("E'");
		nonterminals.add("T");
		nonterminals.add("T'");
		nonterminals.add("F");

		// Variable to hold the file name
		String fileName = "expression.txt";

		System.out.println("1. Scanning an input file: '" + fileName + "'.\n");
		ArrayList<String> tokens = scanFile(fileName);
		System.out.println("\n2. Building a Parsing Table.\n");
		parseTableG9 = build();
		System.out.println("3. Parse the input tokens using the newly computed table.\n");
		System.out.println("Tokens to parse: " + tokens + ",\tadding final token '$'...\n");
		tokens.add("$");
		parse(tokens);
	}
}
