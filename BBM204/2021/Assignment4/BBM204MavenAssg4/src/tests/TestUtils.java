import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;


public class TestUtils {
	interface ForAllParam {

	    public void doIt(String inputDir, String arg);
	}

	public static void forAll(ForAllParam param) {
        for (int i = 0; i < 5; i++) {
            for (int j = 10; j < 50; j+=10) {
                String inputDir = String.format("total/samples%d/input%d",i,j);
                String arg = String.format("%s/input%d.json", inputDir, j);
                param.doIt(inputDir, arg);
            }
        }
	}
	
	public static void pass() {
		System.out.println("PASS");
		System.exit(0);
	}
	
	public static void fail() {
		System.out.println("FAIL");
		System.exit(0);
	}
}
