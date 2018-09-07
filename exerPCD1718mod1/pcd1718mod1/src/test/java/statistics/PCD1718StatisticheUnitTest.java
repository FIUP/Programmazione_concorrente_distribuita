package statistics;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import statistics.MultiMap;
import statistics.PCD1718StatisticheAnnuali;


public class PCD1718StatisticheUnitTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	  
	  
	@Test
	public void testNoArgs() throws IOException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Parameters are not valid");
		PCD1718StatisticheAnnuali.produceSynthetizedBalanceSheet(null, null);
	}

	@Test
	public void testInexistentIntputfile() throws IOException {
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Input file does not exist");
		PCD1718StatisticheAnnuali.produceSynthetizedBalanceSheet(String.valueOf(System.currentTimeMillis()), "output.txt");
	}

	
	@Test
	public void parseCorrectInputFormat() throws IOException {
		String fileAbsPath = null;
		try {
			fileAbsPath = ProduceCorrectBalance.produceTestFile(null);
		} catch (IOException ioex) {throw ioex;}
		
		if(fileAbsPath != null) {
			MultiMap<Integer, Double> map = new MultiMap<>();
			try {
				PCD1718StatisticheAnnuali.buildMapFromInput(map, fileAbsPath);
			} catch (IOException ioex) {throw ioex;}
			
			assertEquals("Default: should have produced two keys", true, map.keySet().size() == 2);
			assertEquals("Default: should have produced 12 entries for year 0", true, map.get(0).size() == 12);
		}
	}
}
