import java.util.Arrays;

public class Sens_slope_Mann_Kendall_test {
    public static void main(String[] args) {
        double[] temperatures = {23.5, 25.3, 22.1, 24.7, 26.2, 27.5, 28.9, 21.8, 20.4, 25.7};

        double min = computeMin(temperatures);
        double max = computeMax(temperatures);
        double median = computeMedian(temperatures);
        double mean = computeMean(temperatures);
        double stdDev = computeStandardDeviation(temperatures);
        double q25 = computePercentile(temperatures, 25);
        double q75 = computePercentile(temperatures, 75);
        
        double theilSenSlope = computeTheilSenSlope(temperatures);
        double theilSenSlopeConfidenceLevel = computeTheilSenSlopeConfidenceLevel(temperatures);
        double intercept = computeIntercept(temperatures);
        boolean mannKendallTestResult = mannKendallTest(temperatures);

        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Median: " + median);
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stdDev);
        System.out.println("25th Percentile: " + q25);
        System.out.println("75th Percentile: " + q75);
        System.out.println("Theil-Sen Slope: " + theilSenSlope);
        System.out.println("Theil-Sen Slope Confidence Level: " + theilSenSlopeConfidenceLevel);
        System.out.println("Intercept: " + intercept);
        System.out.println("Mann-Kendall Test Result: " + mannKendallTestResult);
    }

    public static double computeMin(double[] data) {
        Arrays.sort(data);
        return data[0];
    }

    public static double computeMax(double[] data) {
        Arrays.sort(data);
        return data[data.length - 1];
    }

    public static double computeMedian(double[] data) {
        Arrays.sort(data);
        int middle = data.length / 2;
        if (data.length % 2 == 0) {
            return (data[middle - 1] + data[middle]) / 2.0;
        } else {
            return data[middle];
        }
    }

    public static double computeMean(double[] data) {
        double sum = 0;
        for (double value : data) {
            sum += value;
        }
        return sum / data.length;
    }

    public static double computeStandardDeviation(double[] data) {
        double mean = computeMean(data);
        double sumOfSquaredDifferences = 0;
        for (double value : data) {
            sumOfSquaredDifferences += Math.pow(value - mean, 2);
        }
        return Math.sqrt(sumOfSquaredDifferences / data.length);
    }

    public static double computePercentile(double[] data, double percentile) {
        Arrays.sort(data);
        int index = (int) Math.ceil((percentile / 100) * data.length);
        return data[index - 1];
    }

    public static double computeTheilSenSlope(double[] x) {
        double[] slopes = new double[x.length * (x.length - 1) / 2];
        int index = 0;

        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                slopes[index++] = (x[j] - x[i]) / (j - i);
            }
        }

        Arrays.sort(slopes);
        return computeMedian(slopes);
    }

    public static double computeTheilSenSlopeConfidenceLevel(double[] x) {
        double[] slopes = new double[x.length * (x.length - 1) / 2];
        int index = 0;

        for (int i = 0; i < x.length; i++) {
            for (int j = i + 1; j < x.length; j++) {
                slopes[index++] = (x[j] - x[i]) / (j - i);
            }
        }

        Arrays.sort(slopes);
        double median = computeMedian(slopes);
        double sigma = computeMedian(Arrays.copyOfRange(slopes, 0, slopes.length / 2)) - computeMedian(Arrays.copyOfRange(slopes, slopes.length / 2, slopes.length));
        return 1.96 * sigma / Math.sqrt(x.length);
    }

    public static double computeIntercept(double[] x) {
        double slope = computeTheilSenSlope(x);
        double meanX = computeMean(x);
        double meanY = meanX * slope;
        return meanY - slope * meanX;
    }

    public static boolean mannKendallTest(double[] x) {
        int n = x.length;
        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (x[j] > x[i]) {
                    count++;
                } else if (x[j] < x[i]) {
                    count--;
                }
            }
        }

        double expectedValue = 0;
        double variance = (double) n * (n - 1) * (2 * n + 5) / 18.0;
        double standardDeviation = Math.sqrt(variance);

        if (count > expectedValue + 1.96 * standardDeviation || count < expectedValue - 1.96 * standardDeviation) {
            return true; // Reject the null hypothesis
        } else {
            return false; // Cannot reject the null hypothesis
        }
    }
}
