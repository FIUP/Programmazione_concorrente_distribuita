package pcdapp1.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Utility class you can use to generate a synthetic dataset. The dataset consists of words of size <= WORD_SIZE, 
 * generated randomly from an alphabet (ALPHABET).
 */
public final class GenerateCommaSeparatedDataset {

	public static final String dataset = "in.txt";
    public static final String ALPHABET = "ABC";
    public static final int WORD_SIZE = 3;

	public static void generate() {

		File f = new File(dataset);
		if(f.exists())
			f.delete();
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
			for(int l = 0; l < Math.pow(2, 21); l++) 
				writer.write(getSaltString() + ",");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSaltString() {

        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() <= WORD_SIZE) { // length of the random string.
            int index = (int) (rnd.nextFloat() * ALPHABET.length());
            salt.append(ALPHABET.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
