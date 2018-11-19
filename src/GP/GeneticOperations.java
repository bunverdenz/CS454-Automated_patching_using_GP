package GP;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticOperations {

    //Selection

    public Individual fittestInTournament(List<Individual> pop) {
        //Select the most fittest individual
        int index = 0;
        double min = Double.MIN_VALUE;
        for (int i = 0; i < pop.size(); i++) {
            if (pop.get(i).getFitnessVal() >= min) {
                index = i;
                min = pop.get(i).getFitnessVal();
            }
        }
        return pop.get(index);
    }

    public List<Individual> tournamentSelection(List<Individual> pop) {
        int tournament_size = 7;
        int tournament_each = pop.size() / tournament_size;
        List<Individual> tourament;
        List<Individual> selected = new ArrayList<Individual>();
        for (int i = 0; i < tournament_size; i++) {
            tourament = new ArrayList<Individual>();
            for (int j = 0; j < tournament_each; j++) {
                Random rn = new Random();
                int Individual = rn.nextInt(pop.size() - 1) + 1;
                tourament.add(j, pop.get(Individual));
            }
            selected.add(fittestInTournament(tourament));
        }
        return selected;
    }

    // Crossover of parents
    public List<Individual> crossover(Individual in1, List<Individual> pop) {
        List<Individual> newPop = new ArrayList<Individual>();
        List<Individual> newGen = new ArrayList<Individual>();

        for (int ii = 0; ii < pop.size(); ii++) {
            Random rn = new Random();
            int p2 = rn.nextInt(pop.size() - 1) + 1;

            Individual parent1 = pop.get(ii);
            Individual parent2 = pop.get(p2);

//            for (int j = 0; j < pop.size(); j++) {

            List<Patch> offspring1 = new ArrayList<Patch>();
            List<Patch> offspring2 = new ArrayList<Patch>();
            List<Patch> offspring11 = new ArrayList<Patch>();
            List<Patch> offspring22 = new ArrayList<Patch>();
            List<Patch> offspring12 = new ArrayList<Patch>();
            List<Patch> offspring21 = new ArrayList<Patch>();
            List<Patch> offspring01 = new ArrayList<Patch>();
            List<Patch> offspring02 = new ArrayList<Patch>();

            int cutpoint = crossoverPoint(parent1);

            for (int i = 0; i < cutpoint; i++) {
                offspring1.add(parent1.getAllPatches().get(i));
                offspring2.add(parent2.getAllPatches().get(i));
                offspring01.add(parent1.getAllPatches().get(i));
                offspring02.add(parent2.getAllPatches().get(i));

            }

            for (int k = cutpoint; k < parent1.patchSize(); k++) {
                if (k < parent2.patchSize()) {
                    offspring1.add(parent2.getAllPatches().get(k));
                    offspring22.add(parent2.getAllPatches().get(k));
                    offspring12.add(parent2.getAllPatches().get(k));
                    offspring02.add(parent2.getAllPatches().get(k));
                }

                offspring2.add(parent1.getAllPatches().get(k));
                offspring11.add(parent1.getAllPatches().get(k));
                offspring21.add(parent1.getAllPatches().get(k));
                offspring01.add(parent1.getAllPatches().get(k));
            }
            for (int l = 0; l < cutpoint; l++) {
                offspring01.add(parent2.getAllPatches().get(l));
                offspring02.add(parent1.getAllPatches().get(l));

                offspring11.add(parent2.getAllPatches().get(l));
                offspring22.add(parent1.getAllPatches().get(l));

                offspring12.add(parent1.getAllPatches().get(l));
                offspring21.add(parent2.getAllPatches().get(l));
            }

            newPop.add(new Individual(offspring1));
            newPop.add(new Individual(offspring2));
            newPop.add(new Individual(offspring11));
            newPop.add(new Individual(offspring22));
            newPop.add(new Individual(offspring12));
            newPop.add(new Individual(offspring21));
            newPop.add(new Individual(offspring01));
            newPop.add(new Individual(offspring02));
//            }

//            newGen = new ArrayList<Individual>(newPop);
            newGen.addAll(newPop);

        }
        newGen.add(in1);
        return newGen;
    }

    //Mutation operation
    public Individual mutate(List<Individual> pop) {
        List<Integer> source_list;
        Individual mutated;
        Patch pts = new Patch();
        Random rn = new Random();
        int index = rn.nextInt(pop.get(1).patchSize() - 1) + 1;

        int x, y, z;
        x = rn.nextInt(10) + 1;
        y = rn.nextInt(10) + 1;
        z = rn.nextInt(10) + 1;
        pts.addEdit(x, y, z);
        // Adding new patch
        pop.get(1).addEdit(pts);
        mutated = pop.get(1);
        return mutated;

    }

    int crossoverPoint(Individual in) {
        Random rn = new Random();
        int val1 = rn.nextInt(in.patchSize() - 1);
        return val1;
    }

}

