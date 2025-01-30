  # Parsing Table Generator and Expression Parser
  
  This Java program implements a parsing table generator and expression parser based on a modified G9 grammar. It's designed to process simple arithmetic expressions with assignments    (e.g., `id=E`), performing lexical analysis (scanning) and parsing using a bottom-up parsing approach.
  
  
  ## Table of Contents
  
    1. Grammar
    2. Features
    3. Requirements
    4. Usage
    5. Output
    6. Project Structure
    7. Implementation Analysis
  
  
  ## 1. Grammar
  
  The program uses a modified version of grammar G9 with the following productions:
  ```
  1. P  -> id=E;
  2. E  -> T E'
  3. E' -> + T E'
  4. E' -> epsilon
  5. T  -> F T'
  6. T' -> * F T'
  7. T' -> epsilon
  8. F  -> (E)
  9. F  -> int
  10. F -> id
  ```
  
  
  ## 2. Features
  
  - Lexical analysis (Scanner) that recognizes:
    - Identifiers (lowercase letters)
    - Integers
    - Operators (+, *)
    - Parentheses
    - Semicolon
    - Assignment operator (=)
  - Parsing table generation using FIRST and FOLLOW sets
  - Expression parsing using the generated parsing table
  - Detailed console output showing the parsing process
  
  
  ## 3. Requirements
  
  - Java Development Kit (JDK) 8 or higher
  - Text file containing the expression to parse
  
  
  ## 4. Usage
  
  1. Create a text file (e.g., `expression.txt`) containing the expression to parse
  2. Ensure the text file is in the same directory as the Java program
  3. Run the program:
  ```
  javac Read_Scan_Parse.java
  java Read_Scan_Parse
  ```
  
  
  ## 5. Input Format
  
  The input file should contain a simple arithmetic expression following these rules:
  - Identifiers must be lowercase letters
  - Integers must be whole numbers
  - Supported operators: + (addition), * (multiplication)
  - Expression must end with a semicolon
  - Spaces between tokens are required
  
  Example input (`expression.txt`):
  ```
  b = ( 1 + a ) * 3 ;
  ```
  
  
  ## 6. Output
  
  The program provides detailed console output showing:
  1. Scanning process and identified tokens
  2. Parsing table construction
  3. Step-by-step parsing process
  
  
  ## 7. Project Structure
  
  - `Read_Scan_Parse.java` → Main program file containing scanner and parser implementation
  - `expression.txt` → Input file containing the expression to parse
  
  
  ## 8. Implementation Details
  
  The program is divided into three main components:
  1. **Scanner** → Performs lexical analysis using regular expressions
  2. **Parsing Table Generator** → Builds the parsing table using FIRST and FOLLOW sets
  3. **Parser** → Parses the input using the generated parsing table
  
