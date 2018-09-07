package pcdapp1.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class that produces a Stream<String> from an input read from a Stream Socket.
 * The Socket Stream is comprised of space separated bag of words representing our dataset.
 */

public class NetworkSpaceSepInputFormat implements InputFormat {

	private Socket socket;

	/**
	 * Sole constructor of the class
	 *
	 *  @param addr A String representing the address of the remote end-point feeding the data
	 *  @param port A char (unsigned 16b) representing the port of the remote end-point feeding the data
	 */
	public NetworkSpaceSepInputFormat(String addr, char port) throws UnknownHostException, IOException {
		this.socket = new Socket(addr, (int)port); // Connects to the ServerSocket
	}

	/**
	 * Produces a finite Stream<String> from the underlying dataset assuming
	 * it can fit entirely on memory.
	 *
	 * @return A Stream<String> representing the contents of the dataset
	 */
	public Stream<String> getResourceAsStream() throws IOException {
		try (BufferedReader reader = new BufferedReader(
			new InputStreamReader(socket.getInputStream())))
		{
			String str; // Temporary String
			StringBuffer result = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				result.append(str);
			}
			// Returns a Stream of words splitted on commas
			return Stream.of(result.toString().split(","));
		}
	}
}
