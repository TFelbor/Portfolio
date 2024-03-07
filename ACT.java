// Author: Tytus Felbor
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ACT {
    private Node root;

    // Constructor to initialize the ACT with a root node
    public ACT() {
        root = new Node('*', true);
    }

    // Method to insert a string into the ACT
    public void insert(String s) {
        char[] letters = s.toCharArray();
        Node iter = root;
        for (int i = 0; i < letters.length; i++) {
            if (!Character.isLowerCase(letters[i])) {
                continue; // Ignore non-lowercase letters
            }
            int index = letters[i] - 'a';
            // Traverse the tree to find the appropriate position for insertion
            while (iter.kids[index] != null) {
                iter = iter.kids[index];
                i++;
                if (i == letters.length) {
                    return; // String already exists
                }
                if (!Character.isLowerCase(letters[i])) {
                    continue; // Ignore non-lowercase letters
                }
                index = letters[i] - 'a';
            }
            // Insert a new node representing the character
            Node n = new Node(letters[i], false);
            iter.kids[index] = n;
            iter = iter.kids[index];
        }
        // Mark the last node as a word
        iter.isWord = true;
    }

    // Method to perform auto-completion for a given prefix
    public List<String> autoComplete(String s) {
        List<String> map = new ArrayList<>();
        Node temp = root;
        for (char c : s.toCharArray()) {
            if (!Character.isLowerCase(c)) {
                break; // Ignore non-lowercase letters
            }
            int index = c - 'a';
            // Traverse the tree to find the node corresponding to the prefix
            if (temp.kids[index] == null) {
                return map; // No words with the given prefix
            }
            temp = temp.kids[index];
        }
        // Perform DFS to find all words with the given prefix
        dfs(temp, new StringBuilder(s), map);
        return map;
    }

    // Depth-first search to find words starting from a given node
    private void dfs(Node n, StringBuilder path, List<String> map) {
        if (n.isWord) {
            map.add(path.toString()); // Add the word to the list
        }
        // Explore all children of the current node
        for (int i = 0; i < n.kids.length; i++) {
            if (n.kids[i] != null) {
                path.append(n.kids[i].value);
                dfs(n.kids[i], path, map);
                path.setLength(path.length() - 1);
            }
        }
    }

    // Method to build the ACT from a dictionary file
    public void buildTree(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        // Read each word from the file and insert it into the ACT
        while ((line = reader.readLine()) != null) {
            insert(line.trim().toLowerCase());
        }
        reader.close();
    }

    // Inner class representing a node in the ACT
    private class Node {
        char value;
        boolean isRoot;
        boolean isWord;
        Node[] kids = new Node[26]; // Children nodes for each lowercase letter

        // Constructor to create a node with a given value and type
        public Node(char v, boolean iR) {
            this.value = v;
            this.isRoot = iR;
        }
    }

    // Main method to test the ACT
    public static void main(String[] args) throws IOException {
        ACT act = new ACT();
        // Build the ACT from a dictionary file
        act.buildTree("Dictionary2.txt");
        Scanner sc = new Scanner(System.in);
        while (true) {
            // Prompt the user to enter search queries
            System.out.print("$> Please type search queries:\n$> ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Thanks for using ;)");
                break;
            }
            // Perform auto-completion for the input and display the options
            List<String> words = act.autoComplete(input);
            System.out.println("$> Your options are:");
            for (String word : words) {
                System.out.println("$> " + word);
            }
        }
    }
}
