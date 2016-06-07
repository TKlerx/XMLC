
package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author Timo Klerx
 *
 */
public class IoUtils {


	public static Object deserialize(Path path) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (FileInputStream fileIn = new FileInputStream(path.toFile()); ObjectInputStream in = new ObjectInputStream(fileIn)) {
			final Object o = in.readObject();
			return o;
		}
	}


	public static void serialize(Object o, Path path) throws IOException {
		try (OutputStream fileOut = Files.newOutputStream(path); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(o);
			out.close();
		}
	}

}
