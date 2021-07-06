import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		//reader object
		Reader reader = new Reader();
		// read command.txt
		String[] lines = reader.readfile(args[0]);
		// read queue input
		reader.read_queue();
		// reak stack input
		reader.read_stack();
		// reak process
		reader.process(lines);
		
	}

}
