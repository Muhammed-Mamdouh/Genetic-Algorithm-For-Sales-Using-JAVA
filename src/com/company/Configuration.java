package com.company;



public class Configuration {
private final double[] prices;
private final double sum;
private final double product;
private final double elite_ratio;
private final int pop_size;
private final int tourn_size;
private final double mutation_ratio;
private final int chrom_size;
private final double crossover_rate;
private final int max_iterations;
private final Crossover_type crossover_type;


public Configuration(double[] prices, double target_sum, double target_product, double elite_ratio, int pop_size,
        int tourn_size, double mutation_ratio, int chrom_size, double crossover_rate, int max_iterations, Crossover_type crossover_type) {
    this.prices = prices;
    this.sum = target_sum;
    this.product = target_product;
    this.elite_ratio = elite_ratio;
    this.pop_size = pop_size;
    this.tourn_size = tourn_size;
    this.mutation_ratio = mutation_ratio;
    this.chrom_size = chrom_size;
    this.crossover_rate = crossover_rate;
    this.max_iterations = max_iterations;
    this.crossover_type = crossover_type;
}

public double[] getPrices() {
    return prices;
}

public double getSum() {
    return sum;
}

public double getProduct() {
    return product;
}

public double getEliteRatio() {
    return elite_ratio;
}

public int getPopSize() {
    return pop_size;
}

public int getTournSize() {
    return tourn_size;
}

public double getMutationRatio() {
    return mutation_ratio;
}

public int getChromSize() {
    return chrom_size;
}

public double getCrossoverRate() {
    return crossover_rate;
}

public Crossover_type getCrossoverType() {
    return crossover_type;
}

public double getMaxIterations() {
    return max_iterations;
}

}