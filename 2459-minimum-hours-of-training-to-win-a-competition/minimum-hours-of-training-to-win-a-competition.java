class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int trainingHours = 0;
        int currentEnergy = initialEnergy;
        int currentExperience = initialExperience;

        for (int i = 0; i < energy.length; i++) {
            if (currentEnergy <= energy[i]) {
                int neededEnergy = energy[i] - currentEnergy + 1;
                trainingHours += neededEnergy;
                currentEnergy += neededEnergy;
            }
            if (currentExperience <= experience[i]) {
                int neededExperience = experience[i] - currentExperience + 1;
                trainingHours += neededExperience;
                currentExperience += neededExperience;
            }
            currentEnergy -= energy[i];
            currentExperience += experience[i];
        }

        return trainingHours;
    }
}