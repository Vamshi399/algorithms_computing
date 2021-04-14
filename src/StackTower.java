public class StackTower {
	
  class ActivationRecord {
	int n;
	char from;
	char to;
	char help;	
	
	public ActivationRecord(int n, char from, char to, char help) {
		this.n = n; this.from = from; this.to = to; this.help = help;
	}
  }
	
  public StackTower(String[] args) throws Exception {
	int n = 0;  // input integer
  try {
    n = Integer.parseInt(args[0]);
    if (n < 1)
      throw new Exception("Input value is not positive");
    if (n > 15)
      throw new Exception("Input value is too large");
  }
  catch (Exception e) {
    System.out.println(e);
    System.out.println("Usage: java  TowerStack  [positive-integer < 16]");
    System.exit(-1);
  } 
  long startTime = System.currentTimeMillis(); // record start time
  tower(n, 'A', 'C', 'B');
  long stopTime = System.currentTimeMillis();  // record stop time
  System.out.println("Problem is solved in "
                   + (stopTime - startTime) + " milliseconds");
}

  public void tower(int n, char from, char to, char help) throws Exception {
	if (n <= 0) return;
	Stack<ActivationRecord> s = new Stack<ActivationRecord>();
	s.push(new ActivationRecord(n, from, to, help));
	while (!s.isEmpty()) {
		ActivationRecord r = s.pop();
		if (r.n <= 0) continue;
		if (r.n == 1) {
			System.out.println("Move top disk" + " from peg " + r.from + " to peg " + r.to);
			continue;
		}
		s.push(new ActivationRecord(r.n-1, r.help, r.to, r.from));	
		s.push(new ActivationRecord(1, r.from, r.to, r.help));
		s.push(new ActivationRecord(r.n-1, r.from, r.help, r.to));		
	}
  }

  public static void main(String args[]) throws Exception {
	new StackTower(args);
  }
}
