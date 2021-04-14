import java.util.Scanner;

/** Simplified test of matching tags in an HTML document. */
public class StackMatchHTML {

  /** Tests if every opening tag has a matching closing tag in HTML string. */
  public static boolean isHTMLMatched(String html) throws Exception {
    Stack<String> buffer = new Stack<>();
    int j = html.indexOf('<');                   // find first '<' character (if any)
    while (j != -1) {
      int k = html.indexOf('>', j+1);            // find next '>' character
      if (k == -1)
        return false;                            // invalid tag
      String tag = html.substring(j+1, k);       // strip away < >
      if (!tag.startsWith("/"))                  // this is an opening tag
        buffer.push(tag);
      else {                                     // this is a closing tag
        if (buffer.isEmpty())
          return false;                          // no tag to match
        if (!tag.substring(1).equals(buffer.pop()))
          return false;                          // mismatched tag
      }
      j = html.indexOf('<', k+1);                // find next '<' character (if any)
    }
    return buffer.isEmpty();                     // were all opening tags matched?
  }

  private final static String example = ""
    + "<body>" + "\n"
    + "<center>" + "\n"
    + "<h1> The Little Boat </h1>" + "\n"
    + "</center>" + "\n"
    + "<p> The storm tossed the little" + "\n"
    + "boat like a cheap sneaker in an" + "\n"
    + "old washing machine.  The three" + "\n"
    + "drunken fishermen were used to" + "\n"
    + "such treatment, of course, but" + "\n"
    + "not the tree salesman, who even as" + "\n"
    + "a stowaway now felt that he" + "\n"
    + "had overpaid for the voyage. </p>" + "\n"
    + "<ol>" + "\n"
    + "<li> Will the salesman die? </li>" + "\n"
    + "<li> What color is the boat? </li>" + "\n"
    + "<li> And what about Naomi? </li>" + "\n"
    + "</ol>" + "\n"
    + "</body>";

  /**
   * First test the example html string.
   * Then test command-line input string terminated with Ctr-Z on Windows or Ctr-d on Linux
   */
  public static void main(String[] args) throws Exception {
  	if (!isHTMLMatched(example))
      System.out.println("Error on the given example");
    String input = new Scanner(System.in).useDelimiter("\\A").next();  // read multi-line input at once
    // terminate command line input with Ctr-Z on Windows or Ctr-d on Linux
    if (isHTMLMatched(input))
      System.out.println("The input text is a matched HTML document.");
    else
      System.out.println("The input text is not a matched HTML document.");
  }
}
