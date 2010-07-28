package parchis.exp;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author sortega
 */
public class TurnAdvances {

    public static void main(String[] arguments) {
        System.out.println(turnAdvancesDistribution(1, true, false));
        System.out.println(turnAdvancesDistribution(1, false, false));
        System.out.println(turnAdvancesDistribution(1, false, true));
    }

    private static Distribution turnAdvancesDistribution(int rollNumber, 
            boolean creatingPawns, boolean bonus7) {
        Distribution distro = new Distribution();

        double p = 1d/6d;
        for (int roll=1; roll<6; roll++) {
            if (roll == 5 && creatingPawns) {
                continue;
            }

            distro.put(roll, p);
        }
        
        if (rollNumber != 3) {
            Distribution distro6 = turnAdvancesDistribution(rollNumber + 1, creatingPawns, bonus7);
            for (Entry<Integer, Double> entry: distro6.entrySet()) {
                int advances = entry.getKey() + ((bonus7)? 7 : 6);
                double prob = entry.getValue() * p;

                distro.addProb(advances, prob);
            }
        }

        return distro;
    }

    private static class Distribution extends TreeMap<Integer, Double> {

        private void addProb(int advances, double prob) {
            Double currentProb = this.get(advances);
            if (currentProb == null) {
                currentProb = 0d;
            }

            this.put(advances, currentProb + prob);
        }

        public double average() {
            double average = 0d;
            for (Entry<Integer, Double> entry: this.entrySet()) {
                average += entry.getKey() * entry.getValue();
            }
            return average;
        }

        @Override
        public String toString() {
            StringBuffer buffer = new StringBuffer();

            buffer.append("Probability distribution:\n");
            List<Integer> advances = new ArrayList<Integer>(this.keySet());
            Collections.sort(advances);

            for (int advance: advances) {
                buffer.append(String.format("% 3d\t%01.3f\n", advance, get(advance)));
            }

            buffer.append(String.format("Average: %01.3f\n", average()));

            return buffer.toString();
        }

    }
}
