class RainfallHistogram {

    /*
     * Rainfall Histogram Program         by J M Bishop April 2000
     * --------------------------         adapted from GrowthPlan in
     *                                    JG2E Oct 1997
     * Displays a histogram of rainfall for each month
     * where the rainfall is generated randomly.
     *
     * Illustrates parameter passing of variables
     * and for-loops.
     */

     RainfallHistogram () {

     // The headings
        System.out.println("Savanna Rainfall");
        System.out.println("================");
        System.out.println("for 2000");

     // Display a bar for each year and then the final axis
        for (int year = 5; year <= 15; year++) {
            bar(year, Math.random()*40);
        }
        axis ();
    }

     void bar(int label, double h) {
     // Draws a single histogram bar labelled
     // with the years and consisting of the given
     // number of stars

        System.out.print(label+"\t|");
        int stop = (int) Math.round(h);
        for (int star = 0; star < stop; star++)
            System.out.print('*');
        System.out.println(" " + (long) h);
    }

     void axis () {
     // Draws a horizontal axis with ticks+1 divisions
     // labelled in steps of 10. Each division is 10
     // characters wide.

        int ticks = 5;

     // Print the line
        System.out.print('\t');
        for (int line = 0; line < ticks*10; line++)
            System.out.print("=");
        System.out.println("=");

     //Print the ticks
        System.out.print('\t');
        for (int n = 0; n < ticks; n++)
            System.out.print("+         ");
        System.out.println('+');
     // Label the ticks, including the last one
        System.out.print('\t');
        for (int n = 0; n <= ticks; n++)
            System.out.print(n*10 + "        ");
        System.out.println();

     // Label the whole axis
        System.out.println("\t\t\t\tcm");
    }

    public static void main (String [] args) {
      new RainfallHistogram ();
    }
}
