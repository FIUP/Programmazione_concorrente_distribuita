package pcdapp1.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

/**
 * Class that saves the result of the computation in an output file.
 * Consider the following running example:
 * 	Input dataset: 	B, A, A
 *  Output file: 	
 *  				A:2
 *  				B:1
 */
public class NewLineFileOutputFormat implements OutputFormat {
	
	private String outputFile;
	/**
	 * Sole constructor of the class.
	 * 
	 * @param outputFile	A String denoting the absolute filePath of the output file
	 */
	public NewLineFileOutputFormat(String outputFile) {
		this.outputFile = outputFile;
	}
			
	/**
	 * Dumps the resulting word frequency map in the output stream. Each entry stands in its own line.
	 * 
	 * @param wordCount The ordered (by key) map object of the dump operation.
	 */
	public void dumpWordCount(Map<String, Integer> wordCount) throws IOException {
		Path path = Paths.get(outputFile); // outputFile filepath
		try(BufferedWriter writer = 
				Files.newBufferedWriter(
					path, 
					StandardCharsets.UTF_8,
					StandardOpenOption.WRITE
				)
			) {
			for (Map.Entry<String, Integer> entry : wordCount.entrySet()) { // Iterates over wordCount
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
		}
	}

	
}
