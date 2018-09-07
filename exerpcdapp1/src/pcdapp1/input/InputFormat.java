package pcdapp1.input;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Interface common to all input providers.
 */
public interface InputFormat {

	/**
	 * @return A Stream<String> representing the contents of the dataset
	 */
	public Stream<String> getResourceAsStream() throws IOException;
}
