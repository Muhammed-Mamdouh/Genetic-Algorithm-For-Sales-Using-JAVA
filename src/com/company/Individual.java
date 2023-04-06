package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.*;



public class Individual
{
  public int[] solution;
  public double fitness_score;

  public Individual(int[]sol)
  {
    this.solution = sol;
    this.fitness_score = Double.POSITIVE_INFINITY;
  }

  public void fitness(Configuration conf)
  {
    //function to evaluate a single chromosome
    double c_sum = 0;
    double c_product = 1;
    double[] prices = conf.getPrices ();
    for (int i = 0; i < this.solution.length; i++)
      {
	if (this.solution[i] == 0)
	  {
	    c_sum += prices[i];
	  }
	else
	  {
	    c_product *= prices[i];
	  }
      }
    double s1 = (c_sum - conf.getSum ()) / conf.getSum ();
    double p1 = (c_product - conf.getProduct ()) / conf.getProduct ();
    this.fitness_score = Math.abs (s1) + Math.abs (p1);
  }

  public Individual[] crossover1points (Individual other)
  {
    //function to perform single point crossover on 2 Chromosomes
    //get random split point
    int split = new Random ().nextInt (this.solution.length);
    int[] child1Sol = new int[this.solution.length];
    int[] child2Sol = new int[this.solution.length];
    for (int i = 0; i < this.solution.length; i++)
      {
	if (i < split)
	  {
	    child1Sol[i] = this.solution[i];
	    child2Sol[i] = other.solution[i];
	  }
	else
	  {
	    child1Sol[i] = other.solution[i];
	    child2Sol[i] = this.solution[i];
	  }
      }
    Individual child1 = new Individual(child1Sol);
    Individual child2 = new Individual(child2Sol);
    return new Individual[]
    {
    child1, child2};
  }

  public Individual[] crossover2points (Individual other)
  {
    //function to perform double point crossover on 2 Chromosomes

    //get 2 random split point
    int split1 = new Random ().nextInt (this.solution.length - 4);
    int split2 = new Random ().nextInt (this.solution.length);

    //make sure split point 2 is bigger than split point 1
    while (split2 <= split1)
      {
	split2 = new Random ().nextInt (this.solution.length);
      }
    int[] child1Sol = new int[this.solution.length];
    int[] child2Sol = new int[this.solution.length];
    for (int i = 0; i < this.solution.length; i++)
      {
	if (i > split1 & i < split2)
	  {
	    child1Sol[i] = this.solution[i];
	    child2Sol[i] = other.solution[i];
	  }
	else
	  {
	    child1Sol[i] = other.solution[i];
	    child2Sol[i] = this.solution[i];
	  }
      }
    Individual child1 = new Individual(child1Sol);
    Individual child2 = new Individual(child2Sol);
    return new Individual[]
    {
    child1, child2};
  }

  public Individual[] crossover (Individual other, Configuration conf)
  {
    //function to choose the type of crossover
    if (conf.getCrossoverType () == Crossover_type.xo1point)
      {
	return this.crossover1points (other);
      }
    else
      {
	return this.crossover2points (other);
      }

  }

  public void mutate ()
  {
    //function to mutate a single chromosome
    int mutated_gene = new Random ().nextInt (this.solution.length);
    this.solution[mutated_gene] = Math.abs (this.solution[mutated_gene] - 1);
  }

  @Override public String toString ()
  {
    return "solution: " + Arrays.toString (this.solution) + ", Fitness: " +
      this.fitness_score;
  }

  public void display (Configuration conf)
  {
    //function to display the 2 sets of the solution of a chromosome
    List < Integer > set1 = new ArrayList <> ();
    List < Integer > set2 = new ArrayList <> ();
    double[] prices = conf.getPrices ();
    for (int i = 0; i < this.solution.length; i++)
      {
	if (this.solution[i] == 0)
	  {
	    set1.add ((int) prices[i]);
	  }
	else
	  {
	    set2.add ((int) prices[i]);
	  }
      }
    System.out.println ("set 1:");
    System.out.println (set1);
    System.out.println ("set 2:");
    System.out.println (set2);


  }

  public double getFitness_score()
  {
    return fitness_score;
  }
}
