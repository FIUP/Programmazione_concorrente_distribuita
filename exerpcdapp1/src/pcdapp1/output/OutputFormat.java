package pcdapp1.output;

import java.io.IOException;
import java.util.Map;

/**
 * Interface common to all output providers.
 */
public interface OutputFormat {

	/**
	 * Dumps the resulting word frequency map in the output stream.
	 * 
	 * @param wordCount The ordered (by key) map object of the dump operation.
	 */
	public void dumpWordCount(Map<String, Integer> wordCount) throws IOException;
}
