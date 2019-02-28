import java.io.IOException;
import java.util.Scanner;

public class UI {

	public static void main(String[] args) throws IOException {
          //Terminal t = new Terminal();
    	  Parser p = new Parser();

          //Parser p = new Parser();
          while(true) {
        	  System.out.print("<" + p.t.dir + "> :  ");
        	  Scanner in = new Scanner(System.in);
        	  String instr = in.nextLine();
        	  p.pars012(instr);
          }
	}

}
