package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import util.MasterSeed;

public class TestReadFile2 {

	public static void main(String args[]) throws IOException {
		String fileName = "data/RCV1-x/rcv1x_test-small.txt";
		Path p = Paths.get(fileName);
		File f = new File(fileName);
		int fileLength = (int) f.length();
		RandomAccessFile file = new RandomAccessFile(f, "r");
		String line = file.readLine();
		String[] tokens = line.split(" ");
		int ni, di, mi = 0;
		ni = Integer.parseInt(tokens[0]);
		di = Integer.parseInt(tokens[1]);
		mi = Integer.parseInt(tokens[2]);
		Random r = MasterSeed.nextRandom();
		boolean allLinesRead = false;
		while(!allLinesRead){
			long pos = r.nextInt(fileLength);
			file.seek(pos);
			int i = 1;
			String biggerLine = " ";
			char c = ' ';
			do{
				file.seek(pos-i);
				c = file.readChar();
				biggerLine = file.readLine();
				int k = file.readInt();
				i++;
			}while(!biggerLine.isEmpty());
			file.seek(pos-i+2);
			String realLine = file.readLine();
			System.out.println(realLine);
		}
	}

}