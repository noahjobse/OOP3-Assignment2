import implementations.MyQueue;
import implementations.MyStack;
import exceptions.EmptyQueueException;
import java.io.*;

public class Parser {
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
                    continue; // ignore blank lines and XML declarations
                }

                String[] parts = trimmed.split("(?=<)|(?<=>)");

                for (String part : parts) {
                    if (!part.startsWith("<")) continue;

                    // self-closing tag
                    if (part.matches("<[^>]+/>")) {
                        continue;
                    }

                    // start tag
                    else if (part.matches("<[^/!][^>]*>")) {
                        String tagName = extractTagName(part);
                        stack.push(tagName);
                    }

                    // end tag
                    else if (part.matches("</[^>]+>")) {
                        String tagName = extractTagName(part);

                        try {
                            if (!stack.isEmpty() && stack.peek().equals(tagName)) {
                                stack.pop();
                            } else if (!errorQ.isEmpty() && errorQ.peek().equals(tagName)) {
                                errorQ.dequeue(); // skip it
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
                            // should not occur here, but handled just in case
                            System.out.println("Unexpected queue error on line " + lineNum);
                        }
                    }
                }

                lineNum++;
            }

            // Remaining unclosed tags
            while (!stack.isEmpty()) {
                errorQ.enqueue("Unclosed tag: <" + stack.pop() + ">");
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

    private static String extractTagName(String tag) {
        tag = tag.replaceAll("<|>|/", "").trim();
        if (tag.contains(" ")) {
            tag = tag.substring(0, tag.indexOf(" "));
        }
        return tag;
    }
}
