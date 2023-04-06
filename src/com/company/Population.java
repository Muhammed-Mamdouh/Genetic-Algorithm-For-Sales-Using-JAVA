package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;


public class Population
{
  private final List <Individual> pop;
  private List <Individual> elite;

  public static Population initialize (Configuration conf)
  {
    //function to initialize the first population
    List <Individual> pop = new ArrayList <> ();
    for (int i = 0; i < conf.getPopSize (); i++)
      {
	//make Chromosome
	int[] sol = new int[conf.getChromSize ()];
	for (int j = 0; j < conf.getChromSize (); j++)
	  {
	    sol[j] = new Random ().nextInt (2);
	  }
	//add Chromosome to Population
	pop.add (new Individual(sol));
      }
    return new Population (pop);
  }

  public Population (List <Individual> pop)
  {
    this.pop = pop;
    this.elite = new ArrayList <> ();
  }

  public void evaluate (Configuration conf)
  {
    // function to evaluate each chromosome in Population and get best chromosomes(elites)
  for (Individual c:this.pop)
      {
	c.fitness(conf);
      }

    //sort population to get elites
    this.elite = new ArrayList <> (this.pop);
    this.elite.sort (Comparator.comparingDouble(c -> c.fitness_score));
    this.elite =
      this.elite.subList (0,
			  (int) (conf.getEliteRatio () * conf.getPopSize ()));
  }

  public Individual select (Configuration conf)
  {
    //function to select chromosomes for tournment
    List <Individual> contestants = new ArrayList <> ();
    for (int i = 0; i < conf.getTournSize (); i++)
      {
	contestants.add (this.
			 pop.get (new Random ().nextInt (this.pop.size ())));
      }
    //select the winner of the tournment (best fitness in tournment)
    return contestants.stream ().min (Comparator.comparingDouble(c -> c.fitness_score)).
      get ();
  }

  public Population crossover (Configuration conf)
  {
    //function to perform crossover

    List <Individual> newPop = new ArrayList <> ();
    int numOffspring =
      (int) ((1 - conf.getEliteRatio ()) * conf.getPopSize () / 2);
    for (int i = 0; i < numOffspring; i++)
      {
	//select both perants
	Individual perant1 = this.select (conf);
	Individual perant2 = this.select (conf);

	//crossover 2 perants to get 2 children (with specified crossover rate)
	Individual child1, child2;
	if (Math.random () < conf.getCrossoverRate ())
	  {
	    Individual[]children = perant1.crossover (perant2, conf);
	    child1 = children[0];
	    child2 = children[1];
	  }
	else
	  {
	    //if crossover doesn't occur perants pass to the next generation as the are
	    child1 = perant1;
	    child2 = perant2;
	  }
	newPop.add (child1);
	newPop.add (child2);
      }
    //new population without the elites
    return new Population (newPop);
  }

  public void mutate (Configuration conf)
  {
    //function to perform mutation with specified mutation rate
    int numMutations = (int) (conf.getPopSize () * conf.getMutationRatio ());
    for (int i = 0; i < numMutations; i++)
      {
	Individual c =
	  this.pop.get (new Random ().nextInt (this.pop.size ()));
	c.mutate ();
      }
  }

  public void addElites (Population oldPop)
  {
    //fucntion to add elites of the old Population to the new Population after mutation
    this.pop.addAll (oldPop.elite);
  }

  public List <Individual> getPopulation ()
  {
    return pop;
  }

}
