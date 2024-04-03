class HorseRaceSimulator {
    public static void main(String[] args) {
        Horse horse1 = new Horse('\u265E', "GLITTERHOOF", 0.78);
        Horse horse2 = new Horse('R', "RAINBOW DASH", 0.3);
        Horse horse3 = new Horse('B', "BOJACK", 0.65);
        
        horse1.setConfidence(0.78);
        //horse2.setConfidence(3);
        //horse3.setConfidence(-0.65);


        /*printHorseInformation(horse1);
        printHorseInformation(horse2);
        printHorseInformation(horse3);*/

        Race race1 = new Race(10);
         race1.addHorse(horse1, 1);
        race1.addHorse(horse2, 2);
        race1.addHorse(horse3, 3);
        race1.startRace();
    }

    public static void printHorseInformation (Horse horse) {
        System.out.println("HORSE NAME: " + horse.getName());
        System.out.println("HORSE SYMBOL: " + horse.getSymbol());
        System.out.println("HORSE CONFIDENCE: " + horse.getConfidence());
        System.out.println("");
    }
}