package consolProject;

import java.io.File;
import java.io.FileWriter;

public class LogWriter {
	static public void logWriter(String str) {
		String path = "D:/Java Project/TextRPG.txt";
		File file = new File(path);
		FileWriter fileWriter = null;
		
		try {
			if (file.exists() == false) {
				file.createNewFile();
			}
			
			fileWriter = new FileWriter(file, true);
			fileWriter.write(str + "\n");
			fileWriter.close();
			
		} catch (Exception e) {
			System.out.println("등록에 실패");
		}
	}
}
