public class Streckenwachter extends Thread {
    Strecke dieStrecke;

    public Streckenwachter(Strecke eineStrecke) {
        this.dieStrecke = eineStrecke;
    }

    public void run() {
        while (!this.isInterrupted()) {
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