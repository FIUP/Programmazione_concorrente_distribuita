package pcdapp1.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Class that produces a Stream<String> from an input comma separated dataset of words.
 */
public class CommaSepFileInputFormat implements InputFormat {

	// private String[] dataset;
	// private ArrayList<String> dataset;
	private Path dataset;

	/**
	 * Sole constructor of the class.
	 *
	 * @param dataset A String denoting the absolute path of the dataset
	 */
	public CommaSepFileInputFormat(String dataset) {
		this.dataset = Paths.get(dataset);
	}

	/**
	 * Produces a finite Stream<String> form the underlying dataset assuming
	 * it can fit entirely on memory.
	 *
	 * @return A Stream<String> representing the contents of the dataset
	 */
	public Stream<String> getResourceAsStream() throws IOException {
		try(BufferedReader reader = Files.newBufferedReader(dataset)) {
			return Arrays
				.asList(reader.readLine().split(",")) // Returns a List from the String parameter
				.stream(); // Returns a stream from the Collection parameter
		}

	}
}
