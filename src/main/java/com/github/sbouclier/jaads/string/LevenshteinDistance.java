package com.github.sbouclier.jaads.string;

/**
 * Measure the difference between two sequences
 * 
 * @author Stéphane Bouclier
 * @see <a href=
 *      "https://en.wikipedia.org/wiki/Levenshtein_distance">https://en.wikipedia.org/wiki/Levenshtein_distance</a>
 */
public class LevenshteinDistance {

	private int[][] matrix;
	private String str1, str2;

	public LevenshteinDistance(String str1, String str2) {
		this.str1 = str1;
		this.str2 = str2;
	}

	public int computeWithWagnerFischerAlgorithm() {
		int m = str1.length();
		int n = str2.length();

		matrix = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			matrix[i][0] = i;
		}

		for (int j = 0; j <= n; j++) {
			matrix[0][j] = j;
		}

		for (int j = 1; j <= n; j++) {
			for (int i = 1; i <= m; i++) {
				int substitutionCost = 0;
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					substitutionCost = 0;
				} else {
					substitutionCost = 1;
				}
				matrix[i][j] = min(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + substitutionCost);
			}
		}

		return matrix[m][n];
	}

	private int min(int n1, int n2, int n3) {
		return Math.min(n1, Math.min(n2, n3));
	}

	public void printMatrix() {
		for (int i = 0; i <= str1.length(); i++) {
			for (int j = 0; j <= str2.length(); j++) {
				System.out.print("" + matrix[i][j]);
			}
			System.out.println();
		}
	}
}
