using System;

namespace org.tec.algo.sort {
	public class MainTest<T> where T : IComparable<T> {
		
		public static void Main() {

			Bubble<int> buble = new Bubble<int>();
			Selection<int> selection = new Selection<int>();
			Insert<int> insertion = new Insert<int>();
			Merge<int> merge = new Merge<int>();
			Quicksort<int> quick = new Quicksort<int>();
			Shell<int> shell = new Shell<int>();
			Bench<int> bench = new Bench<int>();


			// PARA GRAFICAR
			double[] numeroElementos = new double[10];
			numeroElementos[0] = 50;
			numeroElementos[1] = 100;
			numeroElementos[2] = 200;
			numeroElementos[3] = 300;
			numeroElementos[4] = 400;
			numeroElementos[5] = 500;
			numeroElementos[6] = 600;
			numeroElementos[7] = 700;
			numeroElementos[8] = 800;
			numeroElementos[9] = 900;

			int[] array50 = new int[50];
			int[] array100 = new int[100];
			int[] array200 = new int[200];
			int[] array300 = new int[300];
			int[] array400 = new int[400];
			int[] array500 = new int[500];
			int[] array600 = new int[600];
			int[] array700 = new int[700];
			int[] array800 = new int[800];
			int[] array900 = new int[900];

			Random rndRandom = new Random();

			for (int x = 0; x < array50.Length; x++)
				array50[x] = (int) (rndRandom.Next() * 50) + 1;

			for (int x = 0; x < array100.Length; x++)
				array100[x] = (int) (rndRandom.Next() * 100) + 1;

			for (int x = 0; x < array200.Length; x++)
				array200[x] = (int) (rndRandom.Next() * 200) + 1;

			for (int x = 0; x < array300.Length; x++)
				array300[x] = (int) (rndRandom.Next() * 300) + 1;

			for (int x = 0; x < array400.Length; x++)
				array400[x] = (int) (rndRandom.Next() * 400) + 1;

			for (int x = 0; x < array500.Length; x++)
				array500[x] = (int) (rndRandom.Next() * 500) + 1;

			for (int x = 0; x < array600.Length; x++)
				array600[x] = (int) (rndRandom.Next() * 600) + 1;

			for (int x = 0; x < array700.Length; x++)
				array700[x] = (int) (rndRandom.Next() * 700) + 1;

			for (int x = 0; x < array800.Length; x++)
				array800[x] = (int) (rndRandom.Next() * 800) + 1;

			for (int x = 0; x < array900.Length; x++)
				array900[x] = (int) (rndRandom.Next() * 900) + 1;

			double[] quicksortN = new double[10];
			quicksortN[0] = bench.medir(quick, array50.Clone() as int[]);
			quicksortN[1] = bench.medir(quick, array100.Clone() as int[]);
			quicksortN[2] = bench.medir(quick, array200.Clone()as int[]);
			quicksortN[3] = bench.medir(quick, array300.Clone() as int[]);
			quicksortN[4] = bench.medir(quick, array400.Clone()as int[]);
			quicksortN[5] = bench.medir(quick, array500.Clone()as int[]);
			quicksortN[6] = bench.medir(quick, array600.Clone()as int[]);
			quicksortN[7] = bench.medir(quick, array700.Clone()as int[]);
			quicksortN[8] = bench.medir(quick, array800.Clone()as int[]);
			quicksortN[9] = bench.medir(quick, array900.Clone()as int[]);
			double[] bubblesortN = new double[10];
			bubblesortN[0] = bench.medir(buble, array50.Clone()as int[]);
			bubblesortN[1] = bench.medir(buble, array100.Clone()as int[]);
			bubblesortN[2] = bench.medir(buble, array200.Clone()as int[]);
			bubblesortN[3] = bench.medir(buble, array300.Clone()as int[]);
			bubblesortN[4] = bench.medir(buble, array400.Clone()as int[]);
			bubblesortN[5] = bench.medir(buble, array500.Clone()as int[]);
			bubblesortN[6] = bench.medir(buble, array600.Clone()as int[]);
			bubblesortN[7] = bench.medir(buble, array700.Clone()as int[]);
			bubblesortN[8] = bench.medir(buble, array800.Clone()as int[]);
			bubblesortN[9] = bench.medir(buble, array900.Clone()as int[]);
			double[] insertionsortN = new double[10];
			insertionsortN[0] = bench.medir(insertion, array50.Clone()as int[]);
			insertionsortN[1] = bench.medir(insertion, array100.Clone()as int[]);
			insertionsortN[2] = bench.medir(insertion, array200.Clone()as int[]);
			insertionsortN[3] = bench.medir(insertion, array300.Clone()as int[]);
			insertionsortN[4] = bench.medir(insertion, array400.Clone()as int[]);
			insertionsortN[5] = bench.medir(insertion, array500.Clone()as int[]);
			insertionsortN[6] = bench.medir(insertion, array600.Clone()as int[]);
			insertionsortN[7] = bench.medir(insertion, array700.Clone()as int[]);
			insertionsortN[8] = bench.medir(insertion, array800.Clone()as int[]);
			insertionsortN[9] = bench.medir(insertion, array900.Clone()as int[]);
			double[] mergesortN = new double[10];
			mergesortN[0] = bench.medir(merge, array50.Clone()as int[]);
			mergesortN[1] = bench.medir(merge, array100.Clone()as int[]);
			mergesortN[2] = bench.medir(merge, array200.Clone()as int[]);
			mergesortN[3] = bench.medir(merge, array300.Clone()as int[]);
			mergesortN[4] = bench.medir(merge, array400.Clone()as int[]);
			mergesortN[5] = bench.medir(merge, array500.Clone()as int[]);
			mergesortN[6] = bench.medir(merge, array600.Clone()as int[]);
			mergesortN[7] = bench.medir(merge, array700.Clone()as int[]);
			mergesortN[8] = bench.medir(merge, array800.Clone()as int[]);
			mergesortN[9] = bench.medir(merge, array900.Clone()as int[]);
			double[] selectionsortN = new double[10];
			selectionsortN[0] = bench.medir(selection, array50.Clone()as int[]);
			selectionsortN[1] = bench.medir(selection, array100.Clone()as int[]);
			selectionsortN[2] = bench.medir(selection, array200.Clone()as int[]);
			selectionsortN[3] = bench.medir(selection, array300.Clone()as int[]);
			selectionsortN[4] = bench.medir(selection, array400.Clone()as int[]);
			selectionsortN[5] = bench.medir(selection, array500.Clone()as int[]);
			selectionsortN[6] = bench.medir(selection, array600.Clone()as int[]);
			selectionsortN[7] = bench.medir(selection, array700.Clone()as int[]);
			selectionsortN[8] = bench.medir(selection, array800.Clone()as int[]);
			selectionsortN[9] = bench.medir(selection, array900.Clone()as int[]);
			double[] shellsortN = new double[10];
			shellsortN[0] = bench.medir(shell, array50.Clone()as int[]);
			shellsortN[1] = bench.medir(shell, array100.Clone()as int[]);
			shellsortN[2] = bench.medir(shell, array200.Clone()as int[]);
			shellsortN[3] = bench.medir(shell, array300.Clone()as int[]);
			shellsortN[4] = bench.medir(shell, array400.Clone()as int[]);
			shellsortN[5] = bench.medir(shell, array500.Clone()as int[]);
			shellsortN[6] = bench.medir(shell, array600.Clone()as int[]);
			shellsortN[7] = bench.medir(shell, array700.Clone()as int[]);
			shellsortN[8] = bench.medir(shell, array800.Clone()as int[]);
			shellsortN[9] = bench.medir(shell, array900.Clone()as int[]);

			//for (i = 0;)

			Console.WriteLine(" ");
			Console.WriteLine("QUICKSORT ");
			for (int i = 0; i < quicksortN.Length; i++) {
				Console.WriteLine(quicksortN[i] / 1000);
			}
			Console.WriteLine(" ");
			Console.WriteLine("SELECTIONSORT ");
			for (int i = 0; i < selectionsortN.Length; i++) {
				Console.WriteLine(selectionsortN[i] / 1000);
			}
			Console.WriteLine(" ");
			Console.WriteLine("INSERTIONSORT ");
			for (int i = 0; i < insertionsortN.Length; i++) {
				Console.WriteLine(insertionsortN[i] / 1000);
			}
			Console.WriteLine(" ");
			Console.WriteLine("SHELLSORT ");
			for (int i = 0; i < shellsortN.Length; i++) {
				Console.WriteLine(shellsortN[i] / 1000);
			}
			Console.WriteLine(" ");
			Console.WriteLine("MERGESORT ");
			for (int i = 0; i < mergesortN.Length; i++) {
				Console.WriteLine(mergesortN[i] / 1000);
			}
			Console.WriteLine(" ");
			Console.WriteLine("BUBBLESORT ");
			for (int i = 0; i < bubblesortN.Length; i++) {
				Console.WriteLine(bubblesortN[i] / 1000);
			}

		}
	}
}