import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDemo {
	
	public static void main(String[] args) throws IOException {
        FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("d:/123.txt");
			fw = new FileWriter("d:/123.txt");
			int n = 0;
		} catch (FileNotFoundException e) {
			System.out.println("未创建！");
			e.printStackTrace();
		}
	}
}
