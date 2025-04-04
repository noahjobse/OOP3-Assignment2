**XML Parser – Assignment 2**  
**Southern Alberta Institute of Technology**

---

**Group Name:** Ganon  
**Course:** OOP3  
**Instructor:** Maryam Moussavi  
**Java Version:** Java 8  
**Submission Part:** Part 2 – Parser, Data Structures, Evaluation

---

### **Description**

This project is an XML document parser built as part of Assignment 2. It validates the syntactic structure of XML files using custom-built Abstract Data Types and data structures, including:

- Stack (based on MyArrayList)
- Queue (based on MyDLL)
- MyArrayList (array-backed list)
- MyDLL (doubly linked list)

The parser reads XML files and identifies structural issues such as:

- Missing closing tags  
- Unexpected closing tags  
- Incorrect nesting  
- Improper root or intercrossing tags

The program prints detailed error messages directly to the console in the order they occur in the input file.

---

### **Usage Instructions**

1. Ensure Java 8 is installed.
2. Open a terminal or command prompt.
3. Navigate to the folder containing the `Parser.jar` file.
4. Run the application using the following format:

```
java -jar Parser.jar res/sample2.xml
```

- You may use **any valid file path** to your XML file.
- The program will output parsing results and errors to the **console only**.
- **No files are created or modified** during execution.

---

### **Sample Output**

```
Line 8: Missing </i>
Line 8: Missing </Language>
Line 8: Missing </Driver>
Line 8: Missing </Category>
Line 8: Missing </Submission>
Line 8: Missing </XMLDATA>
Line 9: Unexpected </Language>
Line 18: Missing </PackageCreationLocation>
Line 21: Missing </i>
Line 21: Missing </b>
Line 21: Missing </Language>
Line 22: Unexpected </Language>
Line 29: Unexpected </Driver>
Line 30: Unexpected </Category>
Line 31: Unexpected </Submission>
Line 32: Unexpected </XMLDATA>
Line 8: Unexpected </i>
Line 21: Unexpected </I>
```

---

### **Included Files**

The submission `.zip` folder contains:

- `Parser.jar` (compiled executable)
- `readMe.rtf` (this file)
- `/doc` directory with full Javadoc (`-private` flag enabled)
- Eclipse project folder: *[FILL IN NAME]*  
- Completed Marking Criteria: `MarkingCriteria_Assignment2_Ganon.docx`

---

### **Key Features**

- Supports self-closing tags (`<tag/>`)
- Ignores tag attributes (e.g. `name="value"`)
- Ignores XML processing instructions (`<?xml version="1.0"?>`)
- Enforces case sensitivity
- Ensures proper nesting (no intercrossing tags)
- Confirms presence of a single root tag
