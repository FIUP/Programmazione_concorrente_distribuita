package statistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ProduceCorrectBalance {

	public static final int[] BALANCCE_YEARS = new int[]{0, 1};
	public static final String BALANCE_INPUT_FILE_NAME = "input.txt";
	public static final String BALANCE_INPUT_FILE_HEADER;
	public static final int MONTHS_YEAR = 12;
	public static final double[][] balance = { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
												{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

	static {
			final StringBuilder builder = new StringBuilder();
			builder.append("\tGennaio");
			builder.append("\tFebbraio");
			builder.append("\tMarzo");
			builder.append("\tAprile");
			builder.append("\tMaggio");
			builder.append("\tGiugno");
			builder.append("\tLuglio");
			builder.append("\tAgosto");
			builder.append("\tSettembre");
			builder.append("\tOttobre");
			builder.append("\tNovember");
			builder.append("\tDicembre");
			builder.append("\n");
			BALANCE_INPUT_FILE_HEADER = builder.toString();
	}
	
	public static String produceTestFile(String args[]) throws IOException {
		
		File fIn = new File(BALANCE_INPUT_FILE_NAME);
		if(fIn.exists()) {
			fIn.delete();
		}
		
		try {
			fIn.createNewFile();
		} catch (IOException ioex) {
			System.err.println("Could not create balance input file - check if the specified path is correct and retry");
			throw ioex;
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append(BALANCE_INPUT_FILE_HEADER);
		for(int year: BALANCCE_YEARS) {
			builder.append(year + "\t");
			for(int m=0; m<MONTHS_YEAR; m++) {
				builder.append( (m==MONTHS_YEAR-1)? balance[year][m]:(balance[year][m] + "\t") );				
			}
			builder.append("\n");
		}
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fIn))) {
			writer.write(builder.toString());
		}catch(IOException ioex) {
			System.err.println("IOError: could not write content to file");
			throw ioex;
		}		

		return fIn.getAbsolutePath();
	}
}
