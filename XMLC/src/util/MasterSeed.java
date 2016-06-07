package util;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Timo Klerx
 *
 */
public class MasterSeed {
	private static Logger logger = LoggerFactory.getLogger(MasterSeed.class);

	private static long seed = 7222525536004714236L;
	private static Random r = new Random(seed);
	private static boolean wasSet = false;

	public static void setSeed(long seed) {
		MasterSeed.seed = seed;
		if (wasSet) {
			System.err.println("Replacing Random object with new seed "+ seed);
		}
		r = new Random(seed);
		wasSet = true;
	}

	public static long nextLong() {
		return r.nextLong();
	}

	public static Random nextRandom() {
		long temp = r.nextLong();
		logger.info("Creating new Random object with seed={}",temp);
		return new Random(temp);
	}

	public static void reset(){
		r = new Random(seed);
	}
}
