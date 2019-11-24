public class Zugfahrt extends Thread {
    private Strecke dieStrecke;
    private boolean bRichtung;
    private int zugnummer;

    public Zugfahrt(Strecke eineStrecke, int zugnummer, boolean bRichtung) {
        this.dieStrecke = eineStrecke;       // einspurige Strecke
        this.bRichtung = bRichtung;   // Richtung
        this.zugnummer = zugnummer;
    }

    public void fahre(boolean bRichtung) {

        // Die Z�ge starten mit einer unterschiedlichen Verz�gerung       
        // Die Fahrtrichtung wird angezeigt

        if (bRichtung == true) {
            System.out.println("Zug " + zugnummer + " startet im Westen");
        } else {
            System.out.println("Zug " + zugnummer + " startet im Osten");
        }

        // die Anzahl der Streckenabschnitte wird ermittelt
        int len = dieStrecke.getStreckenLaenge();

        boolean weiterfahrt = false;
        int i;  // Position des Zuges (abh�ngig von der Richtung)

        for (i = 1; i < len; i++) {
            weiterfahrt = false;
            int anzTrials = Simulation.ANZ_VERSUCHE;
            do {
                weiterfahrt = dieStrecke.reserviere(zugnummer, bRichtung, i);

                if (weiterfahrt == true) {   // Zug darf weiterfahren
                    try {
                        // Fahren auf dem alten Abschnittes
                        Thread.sleep((int) (Math.random() * 50));
                    } catch (InterruptedException e) {
                    }
                    // alten Abschnitt verlassen und freigeben
                    System.out.println("Zug " + zugnummer + " wechselt jetzt auf " + dieStrecke.getAbschnitt(bRichtung, i));
                    dieStrecke.wechseln(zugnummer, bRichtung, i - 1, i);
                    // Fahren auf dem neuen Abschnitt
                    wartenUnterschiedlich(5);
                    dieStrecke.freigeben(zugnummer, bRichtung, i - 1);
                } else {   // Zug darf noch nicht weiterfahren
                    if (!dieStrecke.dieAbschnitte[dieStrecke.getAbschnitt(bRichtung,i)].richtung.equals(dieStrecke.dieAbschnitte[dieStrecke.getAbschnitt(bRichtung,i-1)].richtung)){
                        System.out.println("Fuer Zug "+ zugnummer + " ist die Weiterfahrt nicht moeglich");
                        anzTrials = 0;
                        break;
                    } else
                        anzTrials--;
                    System.out.println("Zug " + zugnummer + " wartet");
                    warten(100);   // Zugf�hrer schaut nach einer Zeit wieder auf das Signal
                }
            } while (weiterfahrt == false && anzTrials > 0);

            if (anzTrials == 0) break;
        } //eo-for

        if (weiterfahrt == true) {
            System.out.println("Zug " + zugnummer + " verlaesst " + dieStrecke.getAbschnitt(bRichtung, len - 1));
            dieStrecke.verlassen(zugnummer, bRichtung);
            dieStrecke.freigeben(zugnummer, bRichtung, len - 1); // alten Abschnitt freigeben
        } else {
            System.out.println("Zug " + zugnummer + " gibt auf in Abschnitt"
                    + dieStrecke.getAbschnitt(bRichtung, i - 1));
        }
    }

    public void run() {
        fahre(bRichtung);
    }

    public void wartenUnterschiedlich(int n) {
        try {
            Thread.sleep((int) (Math.random() * n));
        } catch (InterruptedException e) {
        }
    }

    // vorgegeben Zeit warten (in msec)

    public void warten(int n) {
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
        }
    }
}
