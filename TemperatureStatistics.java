import java.util.Arrays;

public class TemperatureStatistics {
    public static void main(String[] args) {
        double[] temperatures = {23.5, 25.3, 22.1, 24.7, 26.2, 27.5, 28.9, 21.8, 20.4, 25.7};

        double min = computeMin(temperatures);
        double max = computeMax(temperatures);
        double median = computeMedian(temperatures);
        double mean = computeMean(temperatures);
        double stdDev = computeStandardDeviation(temperatures);
        double q25 = computePercentile(temperatures, 25);
        double q75 = computePercentile(temperatures, 75);
        
        double[] regressionResult = linearRegression(temperatures);
        double slope = regressionResult[0];
        double intercept = regressionResult[1];
        double errorSlope = regressionResult[2];

        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Median: " + median);
        System.out.println("Mean: " + mean);
        System.out.println("Standard Deviation: " + stdDev);
        System.out.println("25th Percentile: " + q25);
        System.out.println("75th Percentile: " + q75);
        System.out.println("Slope: " + slope);
        System.out.println("Intercept: " + intercept);
        System.out.println("Error of the slope: " + errorSlope);
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

    public static double[] linearRegression(double[] x) {
        int n = x.length;
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        for (double value : x) {
            sumX += value;
            sumY += value * value;
            sumXY += value * value;
            sumX2 += value * value;
        }

        double slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double intercept = (sumY - slope * sumX) / n;

        double sumResidualsSquared = 0;
        for (double value : x) {
            double residual = value - (slope * value + intercept);
            sumResidualsSquared += residual * residual;
        }
        double errorSlope = Math.sqrt(sumResidualsSquared / ((n - 2) * sumX2));

        return new double[]{slope, intercept, errorSlope};
    }
}
