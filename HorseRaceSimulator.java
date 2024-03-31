class HorseRaceSimulator {
    public static void main(String[] args) {
        Horse horse1 = new Horse('♘', "GLITTERHOOF", 0.78);
        Horse horse2 = new Horse('♞', "RAINBOW DASH", 3);
        Horse horse3 = new Horse('马', "HORSE 3", -0.65);
        
        horse1.setConfidence(0.78);
        horse2.setConfidence(3);
        horse3.setConfidence(-0.65);

        printHorseInformation(horse1);
        printHorseInformation(horse2);
        printHorseInformation(horse3);
    }

    public static void printHorseInformation (Horse horse) {
        System.out.println("HORSE NAME: " + horse.getName());
        System.out.println("HORSE SYMBOL: " + horse.getSymbol());
        System.out.println("HORSE CONFIDENCE: " + horse.getConfidence());
        System.out.println("");
    }
}