import implementations.MyQueue;
import implementations.MyStack;
import exceptions.EmptyQueueException;
import java.io.*;

/**
 * The Parser class reads and validates an XML file for properly nested and matched tags.
 * It uses a stack to track opening tags and queues to report any mismatches,
 * unexpected tags, or missing closing tags.
 *
 * <p>Tags are parsed line by line and categorized as:
 * - Self-closing tags (ignored)
 * - Valid opening tags (pushed onto stack)
 * - Closing tags (matched against the stack or recorded as errors)
 *
 * <p>Error messages are printed to standard output if issues are found.
 * If the XML is valid, a success message is printed.
 */
public class Parser {

    /**
     * Entry point for the XML parser.
     *
     * Precondition: A valid file path to an XML file is provided as the first command-line argument.
     * Postcondition: The file is parsed, and errors (if any) are printed to standard output.
     *
     * @param args Command-line arguments, where args[0] should be the path to the XML file
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Parser <filename>");
            return;
        }

        String filename = args[0];
        File xmlFile = new File(filename);
        if (!xmlFile.exists() || !xmlFile.canRead()) {
            System.out.println("Error: The file does not exist or cannot be read.");
            return;
        }

        MyStack<String> stack = new MyStack<>();
        MyQueue<String> errorQ = new MyQueue<>();
        MyQueue<String> extrasQ = new MyQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 1;

            while ((line = br.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || (trimmed.startsWith("<?") && trimmed.endsWith("?>"))) {
                    lineNum++;
                    continue;
                }

                String[] parts = trimmed.split("(?=<)|(?<=>)");

                for (String part : parts) {
                    if (!part.startsWith("<")) continue;

                    // Self-closing tag
                    if (part.matches("<[^>]+/>")) {
                        continue;
                    }

                    // Opening tag
                    else if (part.matches("<[^/!][^>]*>")) {
                        String tagName = extractTagName(part);
                        stack.push(tagName);
                    }

                    // Closing tag
                    else if (part.matches("</[^>]+>")) {
                        String tagName = extractTagName(part);

                        try {
                            if (!stack.isEmpty() && stack.peek().equals(tagName)) {
                                stack.pop();
                            } else if (!errorQ.isEmpty() && errorQ.peek().equals(tagName)) {
                                errorQ.dequeue();
                            } else if (stack.isEmpty()) {
                                errorQ.enqueue("Line " + lineNum + ": Unexpected </" + tagName + ">");
                            } else {
                                boolean matchFound = false;
                                MyStack<String> tempStack = new MyStack<>();

                                while (!stack.isEmpty()) {
                                    String popped = stack.pop();
                                    if (popped.equals(tagName)) {
                                        matchFound = true;
                                        break;
                                    } else {
                                        errorQ.enqueue("Line " + lineNum + ": Missing </" + popped + ">");
                                        tempStack.push(popped);
                                    }
                                }

                                if (!matchFound) {
                                    extrasQ.enqueue("Line " + lineNum + ": Unexpected </" + tagName + ">");
                                }
                            }
                        } catch (EmptyQueueException e) {
                            System.out.println("Unexpected queue error on line " + lineNum);
                        }
                    }
                }

                lineNum++;
            }

            // Handle any tags left open
            while (!stack.isEmpty()) {
                errorQ.enqueue("Unclosed tag (end of file): <" + stack.pop() + ">");
            }

            boolean errorsFound = false;

            try {
                while (!errorQ.isEmpty()) {
                    System.out.println(errorQ.dequeue());
                    errorsFound = true;
                }
            } catch (EmptyQueueException e) {
                System.out.println("Error while printing error queue: " + e.getMessage());
            }

            try {
                while (!extrasQ.isEmpty()) {
                    System.out.println(extrasQ.dequeue());
                    errorsFound = true;
                }
            } catch (EmptyQueueException e) {
                System.out.println("Error while printing extras queue: " + e.getMessage());
            }

            if (!errorsFound) {
                System.out.println("XML parsed successfully. No errors found.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Extracts the tag name from a full tag string, removing angle brackets and attributes.
     *
     * Precondition: The input is a well-formed XML tag like {@code <tag>} or {@code </tag>}.
     * Postcondition: Returns the tag name without angle brackets or attributes.
     *
     * @param tag the full XML tag string
     * @return the clean tag name
     */
    private static String extractTagName(String tag) {
        tag = tag.replaceAll("<|>|/", "").trim();
        if (tag.contains(" ")) {
            tag = tag.substring(0, tag.indexOf(" "));
        }
        return tag;
    }
}
