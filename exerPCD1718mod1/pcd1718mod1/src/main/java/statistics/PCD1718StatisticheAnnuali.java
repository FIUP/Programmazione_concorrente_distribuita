package statistics;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;


/**
 * This class can be considered as a final helper class used to compute the balance sheet of a company.  
 * The program takes as arguments 2 filenames corresponding to the balance_input_file and balance_output_file,
 * respectively. 
 * An entry in the balance input file represents the monthly budget of the company for that specific year.
 * The output file format is a synthesized version of the former, reporting the mean and average balance for 
 * a particular year.
 * For more insights on the file format refer to the provided {input, output}.txt files.
 */

public final class PCD1718StatisticheAnnuali {

	/**
	 * Fills in the MultiMap data structure containing the raw data read from the input file.
	 * 
	 * @param map:			the map structure to fill with data
	 * @param inputFilePath	the path of the input balance sheet file 
	 * 
	 * @throws IOException  if the file denoted by the inputFilePath parameter does not exist
	 */
	public static void buildMapFromInput(MultiMap<Integer, Double> map, String inputFilePath) throws IOException {
		File in = new File(inputFilePath);
		if(!in.exists())	throw new IllegalArgumentException("Input file does not exist");
		buildMapFromInput(map,in);
	}

	/**
	 * Fills in the MultiMap data structure containing the raw data read from the input file.
	 * 
	 * @param map:			the map structure to fill with data
	 * @param inputFile		the path of the input balance sheet file 
	 * 
	 * @throws IOException  if the file denoted by the inputFilePath parameter does not exist
	 * @throws IllegalArgumentException if the input parameters are not valid
	 */
	public static void buildMapFromInput(MultiMap<Integer, Double> map, File inputFile) throws IOException {
		Scanner inscan = new Scanner(inputFile);
		if(inscan.hasNext()) inscan.nextLine();

		while(inscan.hasNextLine()) {
			String line = inscan.nextLine();
			String[] s = line.split("\t");
			for(int i=1; i< s.length; ++i)
				map.put(Integer.valueOf(s[0]),Double.valueOf(s[i]));
		}
		inscan.close();
	}

	/**
	 * Produces the synthesized version of the input balance file from the data stored in the multimap data structure.
	 * 
	 * @param map:			a MultiMap containing the balance data
	 * @param balanceOut:	file where the synthesized version of the balance sheet is stored
	 * 
	 * @throws IOException: if the I/O operation fails
	 * @throws IllegalArgumentException: if the input parameters are not safe
	 */
	public static void outputStatisticsFile(MultiMap<Integer, Double> map, File balanceOut) throws IOException {
		PrintWriter outprint = new PrintWriter(balanceOut);

		Set<Integer> set = map.keySet();
		if(set.size()>0) {
			outprint.println("\tMean\tStdDev");
			for (Integer i : set) {
				List<Double> list = map.get(i);
				Double mean = new Double(0);
				Double StdDev = new Double(0);
				for (Double d : list)
					mean += d;
				mean = mean / (list.size());
				//Standard deviation (or Variance) calculus.. the average of the squared differences from the mean.
				for (Double d : list)
					StdDev += Math.pow(mean - d, 2.0);
				StdDev = StdDev / (list.size());

				outprint.println(i + "\t" + mean + "\t" + StdDev);
			}
		}
		else	outprint.println("Non ci sono dati di almeno un anno da analizzare vezz :(");
		outprint.close();
	}
	
	/**
	 * Produces a synthesized version of the input balance sheet. This method can be seen as a sequential pipeline
	 * combining the buildMapFromInput(...) method and the outputStatisticsFile(...)
	 * 
	 * @param inputFile:	denotes the balance sheet file path	
	 * @param outputFile:	denotes the file where the output balance sheet should be stored
	 * 
	 * @throws IOException: in case some I/O error occurred e.g., the file does not adhere to a specific format or an
	 * 						unexpected error during read/write.
	 * @throws IllegalArgumentException: in case input parameters are not valid
	 */
	public static void produceSynthetizedBalanceSheet(String inputFile, String outputFile) throws IOException {
		if(inputFile == null || outputFile == null || inputFile.length() == 0 || outputFile.length() == 0) throw new IllegalArgumentException("Parameters are not valid");

		File out = new File(outputFile);
		MultiMap<Integer, Double> map = new MultiMap<>();
		buildMapFromInput(map,inputFile);

		outputStatisticsFile(map,out);
	}	
}
