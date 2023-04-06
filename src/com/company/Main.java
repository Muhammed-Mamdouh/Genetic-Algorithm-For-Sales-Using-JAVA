package com.company;
import java.util.Collections;
import java.util.*;



public class Main
{


  public static Individual genetic_algorithm (Configuration conf)
  {
    //function to perform genetic algorithm
    //initialize the first generation Population
    Population population = Population.initialize (conf);
    //evaluate the first population
      population.evaluate (conf);
    int i = 0;
    //repeat generations
    while (i < conf.getMaxIterations ())
      {
	//make new generation Population by crossover of the previous population
	Population new_population = population.crossover (conf);
	//apply mutataion
	  new_population.mutate (conf);
	//add unmutated elites of the previous generation population
	  new_population.addElites (population);
	//evaluate new generation population
	  new_population.evaluate (conf);
	  population = new_population;
	  i++;
      }
    Individual c = Collections.min (population.getPopulation (),
				    Comparator.comparingDouble (Individual::getFitness_score));
    c.display (conf);
    return c;
  }


  public static void main (String[]args)
  {

    double[] prices = new double[]{ 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
    double target_sum = 360;
    double target_product = 36000000;
    double elite_ratio = 0.08;
    int pop_size = 50;
    int tourn_size = 2;
    double mutation_ratio = 0.1;
    int chrom_size = 10;
    double crossover_rate = 0.7;
    int max_iterations = 1000;
    Crossover_type xo = Crossover_type.xo2points;
//    Crossover_type xo = Crossover_type.xo1point;  //for single point crossover

    // make Configuration
    Configuration conf =
      new Configuration (prices, target_sum, target_product, elite_ratio,
			 pop_size, tourn_size, mutation_ratio, chrom_size,
			 crossover_rate, max_iterations, xo);
    // run genetic algorithm
    Individual c = genetic_algorithm (conf);
    System.out.println (c);
  }
}
