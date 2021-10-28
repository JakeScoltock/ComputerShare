/**
 * 
 */
package stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;



/**
 * @author Jake Scoltock
 * Program that will work out the best day to buy and sell from a dataset.
 *
 */
public class Trading {
	double buy;
	double sell;
/**
 * Main method which calls the ShowMenu method
 * @param args
 */
	public static void main(String[] args) {
		showMenu();

	}
/**
 * Method to allow user to chose dataset and exit the program
 */
	public static void showMenu() {
		File file = new File("ChallengeSampleDataSet1.txt");
		File file2 = new File("ChallengeSampleDataSet2.txt");
		System.out.println();
		Scanner scanner = new Scanner(System.in);
		int option;
		System.out.println("Computershare - Playing the stock market ");
		do {
			System.out.println("Menu");
			System.out.println("Enter 1 to view trade results for Dataset 1.");
			System.out.println("Enter 2 to view trade results for Dataset 2.");
			System.out.println("Enter 3 to exit the program.");

			option = scanner.nextInt();

			switch (option) {

			case 1:
				System.out.println("Dataset 1 Trades");

				double[] passedPrices = fileReader(file);
				tradeSelector(passedPrices);
				break;
			case 2:
				System.out.println("Dataset 2 Trades");
				double[] passedprices2 = fileReader(file2);
				tradeSelector(passedprices2);
				break;
			case 3:
				System.out.println("Quitting");
				break;

			default:
				System.out.println("Try options again...");
			}
		} while (option != 3);
		scanner.close();
	}
/**
 * Method that takes in a datapath, reads the desired file and adds data to an array
 * @param file
 * @return
 */
	public static double[] fileReader(File file) {
		String line = null;
		double[] prices = {};
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((line = br.readLine()) != null) {
				double[] cost = Arrays.stream(line.split(",")).mapToDouble(Double::parseDouble).toArray();
				prices = cost.clone();

			}
			br.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prices;

	}
/**
 * method to find the index of a variable in a given array
 * @param array
 * @param val
 * @return
 */
	public static int findIndex(double[] array, double val) {

		return IntStream.range(0, array.length).filter(i -> array[i] == val).findFirst().orElse(-1);
	}
/**
 * Method that loops through given array and finds the best day to buy and sell a stock
 * @param array
 * @return
 */
	public static String tradeSelector(double[] array) {
		double maxprice = 0;
		double minprice = 0;
		double maxprofit = 0;
		int buyday =0;
		int sellday =0;
		String result ="";

		for (int loop = 0; loop < array.length - 1; loop++) {
			for (int looper = loop + 1; looper < array.length; looper++) {
				double profit = array[looper] - array[loop];

				if (profit > maxprofit) {

					maxprice = array[looper];
					minprice = array[loop];
					
					maxprofit = profit;
				}

			}

		}
		
		buyday = findIndex(array, minprice)+1;
		sellday = findIndex(array, maxprice)+1;
		result = String.format("%s(%s),%s(%s)",buyday,minprice,sellday,maxprice);
		System.out.println(result);
		return result;
	}
}

