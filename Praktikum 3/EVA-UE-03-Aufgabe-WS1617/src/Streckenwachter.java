public class Streckenwachter extends Thread {
    Strecke dieStrecke;
    int rechnerkennung = 10;
    static int sequenznummer = 1;

    public Streckenwachter(Strecke eineStrecke) {
        this.dieStrecke = eineStrecke;
    }

    public void run() {
        while (!this.isInterrupted()) {
            Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Request: AnzahlZüge");
            sequenznummer++;
            System.out.println(nachricht.toString());
            System.out.println("Anzahl Zuege: " + dieStrecke.getAnzahlZuege());
            warten(Simulation.ABSTAND_BEOBACHTUNG);
        }
    }

    public void warten(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            this.interrupt();
        }
    }
}