package com.github.sbouclier.jaads.string;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Levenshtein distance test
 * 
 * @author Stéphane Bouclier
 *
 */
public class LevenshteinDistanceTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void should_return_0_for_same_strings() {
		assertEquals(0, new LevenshteinDistance("", "").computeWithWagnerFischerAlgorithm());
		assertEquals(0, new LevenshteinDistance("kitten", "kitten").computeWithWagnerFischerAlgorithm());
	}
	
	@Test
	public void should_return_distance() {
		assertEquals(1, new LevenshteinDistance("kitten", "sitten").computeWithWagnerFischerAlgorithm());
		assertEquals(1, new LevenshteinDistance("sitten", "sittin").computeWithWagnerFischerAlgorithm());
		assertEquals(1, new LevenshteinDistance("sittin", "sitting").computeWithWagnerFischerAlgorithm());
		assertEquals(3, new LevenshteinDistance("kitten", "sitting").computeWithWagnerFischerAlgorithm());
	}
	
	@Test
	public void should_print_matrix() {
		LevenshteinDistance ld = new LevenshteinDistance("sitting", "kitten");
		ld.computeWithWagnerFischerAlgorithm();
		ld.printMatrix();
		
		StringBuilder expectedOut = new StringBuilder();
		expectedOut.append("0123456").append("\n");
		expectedOut.append("1123456").append("\n");
		expectedOut.append("2212345").append("\n");
		expectedOut.append("3321234").append("\n");
		expectedOut.append("4432123").append("\n");
		expectedOut.append("5543223").append("\n");
		expectedOut.append("6654332").append("\n");
		expectedOut.append("7765443").append("\n");

		assertEquals(expectedOut.toString(), outContent.toString());
	}
}
