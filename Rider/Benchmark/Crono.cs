using System;
using System.Diagnostics;

namespace Benchmark {
    public class Crono {

        public long medir(Sorting<T> sort, T[] array) {
            long total;
            long starTime = nanoTime();
            //sort.execute(array);
            long endTime = nanoTime();
            total = endTime - starTime;
            return total;

        }

        private static long nanoTime() {
            long nano = 10000L * Stopwatch.GetTimestamp();
            nano /= TimeSpan.TicksPerMillisecond;
            nano *= 100L;
            return nano;
        }
    }
}