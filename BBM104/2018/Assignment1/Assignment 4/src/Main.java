import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Board tahta = new Board();
		
		if(args.length==1) {
			readfile dosya_okuma = new readfile(tahta,args);
		}
		else {
			readfile dosya_okuma = new readfile(tahta);

		}
	}
}
