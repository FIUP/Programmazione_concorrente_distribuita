package pcdapp1.main;

import pcdapp1.input.*;
import pcdapp1.output.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;



/**
 * A singleton MapReduce job used to compute the word frequency given a dataset of words.
 */
public class MapReduce {
	private String inputPath;
	private String outputPath;
	private Class<? extends InputFormat> inputFormat; // InputFormat selector
	private Class<? extends OutputFormat> outputFormat; // OutputFormat selector
	/**
	 * Private (!) noarg constructor
	 */
	private MapReduce() {
		// default values
		this.inputFormat = CommaSepFileInputFormat.class;
		this.outputFormat = NewLineFileOutputFormat.class;
	}

	/**
	 *
	 */
    public static synchronized MapReduce getInstance() {
		return new MapReduce();
    }

	/**
	 * Initializes this object to the current configuration provided in input
	 *
	 * @param args Parameters needed to setup and initialize the MapReduce jon
	 */
	public void init(String[] args) throws IllegalArgumentException {
		if (args.length < 2)
			throw new IllegalArgumentException("Needs 2 parameters");

		inputPath = args[0];
		outputPath = args[1];
	}

	/**
	 * Setter method used to configure the InputFormat for this job (refer to input.*)
	 *
	 * @param inputFormat Class type of the InputFormat used to feed the data for this job
	 */
	public <T> void setInputFormat(Class<? extends InputFormat> inputFormat) {
			this.inputFormat = inputFormat;
	}

	/**
	 * Getter method used to configure the OutputFormat for this job (refer to output.*)
	 *
	 * @param outputFormat Class type of the OutputFormat used to persist the output of this job
	 */
	public <T> void setOutputFormat(Class<? extends OutputFormat> outputFormat) {
			this.outputFormat = outputFormat;
	}

	/**
	 * Implements the logic of the word frequency computation. It is a blocking method
	 * whose output is fed to the OutputFormat of this job.
	 * The produced output is ordered by key. Consider the running example:
	 * Input dataset: B A A
	 * Persisted Output:
	 * 					A:2
	 * 					B:1
	 * @throws IOException
	 */
	public void awaitActivityTermination() throws IOException {
		InputFormat in = null;
		OutputFormat out = null;
		try {
			// Output format initialization
			if (outputFormat == NewLineFileOutputFormat.class)
				out = new NewLineFileOutputFormat(outputPath);

			// Input format initialization
			if (inputFormat == CommaSepFileInputFormat.class)
				in = new CommaSepFileInputFormat(inputPath);
			else if (inputFormat == NetworkSpaceSepInputFormat.class) {
				// inputPath: host address
				// port: 3000, it can be changed to whatever
				in = new NetworkSpaceSepInputFormat(inputPath, (char)3000); 
			}

			// Something went wrong
			if (in == null || out == null)
				throw new IOException("Error: in or out references are null");

			// Gets the Stream resource as a String Stream
			Stream<String> res = in.getResourceAsStream();

			// Stores occurences in a TreeMap, so pairs can be sorted by keys
			TreeMap<String, Integer> map = new TreeMap<>();
			res.sorted().forEach(s -> {
				Integer value = map.get(s);
				if (value == null) { // New pair, first key's instance
					map.put(s, 1);
				} else { // Increment key's count by 1
					map.put(s, value+1);
				}
			});

			// Creates output File if outputPath doesn't exist
			Path outPath = Paths.get(outputPath); // Throws InvalidPathException
			if (Files.notExists(outPath))
				Files.createFile(outPath);

			try(BufferedWriter writer // Write buffer initialization
					= Files.newBufferedWriter(
						outPath,
						StandardCharsets.UTF_8,
						StandardOpenOption.TRUNCATE_EXISTING))
			{
				// Writes key-value pairs in outputPath file
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					writer.write(entry.getKey() + ":" + entry.getValue().toString());
					writer.newLine();
				}
			}
		} finally {}
	}
}
