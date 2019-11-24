public class Strecke {
    // Zur Synchronisation werden drei Objekte angelegt, um fuer drei Aspekte getrennt zu synchronisieren.
    int rechnerkennung = 30;
    static int sequenznummer = 1;

    Abschnitt dieAbschnitte[];

    public Strecke(int anzAbschnitte) {
        dieAbschnitte = new Abschnitt[anzAbschnitte];
        for (int i = 0; i < anzAbschnitte; i++) {
            dieAbschnitte[i] = new Abschnitt();
        }
    }

// ----------------------------------------------------------------------------------------------

    public synchronized boolean reserviere(int zugnummer, boolean bRichtung, int n) {
        /* Aufgabe 4: Durch die Synchronisation wird von dem Thread die Methode so lange blockiert, bis dieser sie verlässt.
        Dadurch müssen andere Threads so lange warten, bis der Bereich freigegeben ist, bevor sie diesen betreten können.
        Die Laufzeit verlängert sich dadurch um die Wartezeit, also die Performance sinkt. Je mehr Threads gestartet werden, umso größer ist der Performanceverlust. */

        boolean reservierung;
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: reserviere");
        sequenznummer++;
        System.out.println(nachricht.toString());

        // Fallunterscheidung, ob Ost oder West
        if (bRichtung == false) {
            n = dieAbschnitte.length - 1 - n;   // Abschnitte von hinten nach vorne
            Nachricht nachricht2 = new Nachricht(rechnerkennung, sequenznummer, "Request: setzeRichtungssignal");
            sequenznummer++;
            System.out.println(nachricht2.toString());
            dieAbschnitte[n].setzeRichtungssignal("ost");
        }
        else {
            Nachricht nachricht2 = new Nachricht(rechnerkennung, sequenznummer, "Request: setzeRichtungssignal");
            sequenznummer++;
            System.out.println(nachricht2.toString());
            dieAbschnitte[n].setzeRichtungssignal("west");
        }

        System.out.println("Versuche Abschnitt " + n + " fuer Zug " + zugnummer + " zu reservieren");
        Nachricht nachricht2 = new Nachricht(rechnerkennung, sequenznummer, "Request: Reservierung");
        sequenznummer++;
        System.out.println(nachricht2.toString());
        reservierung = dieAbschnitte[n].Reservierung;  // ermittle die Reservierung

        if (reservierung == false)  // kein Zug auf dem Abschnitt
        {
            dieAbschnitte[n].Reservierung = true;
            System.out.println("Abschnitt " + n + " wird fuer Zug " + zugnummer + " reserviert");
            zeigeStreckenabschnitt();
            return true;
        } else
            System.out.println("Abschnitt " + n + " fuer Zug " + zugnummer + " besetzt");
        return false;
    }

    // in dieser Methode werden die Zuege koordiniert, die nach Westen fahren

    // diese Methode wird von den Zuegen aufgerufen, die diesen Streckenabschnitt verlassen
    public void leaveAB(int zugnummer) {
        System.out.println(zugnummer + " verlaesst Streckenabschnitt");
    }

// ----------------------------------------------------------------------------------------------

    public int getAbschnitt(boolean bRichtung, int n) {
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: getAbschnitt");
        sequenznummer++;
        System.out.println(nachricht.toString());
        if (bRichtung == true) {
            return n;
        } else {
            return dieAbschnitte.length - 1 - n;
        }
    }

// ----------------------------------------------------------------------------------------------

    public int getAnzahlZuege() {
        int counter = 0;

        for (int i = 0; i < dieAbschnitte.length; i++) {
            if (dieAbschnitte[i].Belegung != 0) counter += 1;
        }
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: getAnzahlZuege");
        sequenznummer++;
        System.out.println(nachricht.toString());
        return counter;
    }

// ----------------------------------------------------------------------------------------------

    public synchronized void zeigeStreckenabschnitt() {

        for (int i = 0; i < dieAbschnitte.length; i++) {
            if (dieAbschnitte[i].Reservierung == true) {
                System.out.print("r|");
            } else {
                System.out.print(" |");
            }
        }
        System.out.println("");
        for (int i = 0; i < dieAbschnitte.length; i++) {
            System.out.print(dieAbschnitte[i].Belegung + "|");
        }
        System.out.println("");
    }

// ----------------------------------------------------------------------------------------------

    public int getStreckenLaenge() {

        return dieAbschnitte.length;
    }

// ----------------------------------------------------------------------------------------------

    public void freigeben(int zugnummer, boolean bRichtung, int n) {
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: freigeben");
        sequenznummer++;
        System.out.println(nachricht.toString());
        if (bRichtung == false) {
            n = dieAbschnitte.length - 1 - n;   // Abschnitte von hinten nach vorne
        }
        dieAbschnitte[n].Reservierung = false;
        System.out.println("Abschnitt " + n + " wird freigegeben");
        zeigeStreckenabschnitt();
    }

// ----------------------------------------------------------------------------------------------

    /**
     * Aus Gr�nden der Synchronisation wurde
     * public void wechselnVon(int zugnummer, boolean bRichtung, int from)
     * public void wechselnNach(int zugnummer, boolean bRichtung, int to)
     * ersetzt durch
     * public void wechseln(int zugnummer, boolean bRichtung, int from, int to)
     */
//    public void wechselnVon(int zugnummer, boolean bRichtung, int from) {
//        if (bRichtung == false) {
//            from = dieAbschnitte.length - 1 - from;   // Abschnitte von hinten nach vorne
//        }
//        dieAbschnitte[from].Belegung = 0;
//        System.out.println("Zug verlaesst Abschnitt" + from);
//        // zeigeStreckenabschnitt();
//    }
//
//    public void wechselnTo(int zugnummer, boolean bRichtung, int to) {
//        if (bRichtung == false) {
//            to = dieAbschnitte.length - 1 - to;     // Abschnitte von hinten nach vorne
//        }
//
//        dieAbschnitte[to].Belegung = zugnummer;
//        System.out.println("Zug befaehrt jetzt " + to);
//        zeigeStreckenabschnitt();
//    }
    public void wechseln(int zugnummer, boolean bRichtung, int from, int to) {
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: wechseln");
        sequenznummer++;
        System.out.println(nachricht.toString());
        if (bRichtung == false) {
            from = dieAbschnitte.length - 1 - from;   // Abschnitte von hinten nach vorne
        }
        dieAbschnitte[from].Belegung = 0;
        System.out.println("Zug verlaesst Abschnitt " + from);
        if (bRichtung == false) {
            to = dieAbschnitte.length - 1 - to;     // Abschnitte von hinten nach vorne
        }

        dieAbschnitte[to].Belegung = zugnummer;
        System.out.println("Zug befaehrt jetzt " + to);
        zeigeStreckenabschnitt();
    }
// ----------------------------------------------------------------------------------------------

    public void ankommen(int zugnummer, boolean bRichtung) {
        int first = 0;
        if (bRichtung == false) {
            first = dieAbschnitte.length - 1 - first;   // Abschnitte von hinten nach vorne
        }

        dieAbschnitte[first].Belegung = zugnummer;
        System.out.println("Zug kommt nach " + first);
        zeigeStreckenabschnitt();
    }

// ----------------------------------------------------------------------------------------------

    public void verlassen(int zugnummer, boolean bRichtung) {
        Nachricht nachricht2 = new Nachricht(rechnerkennung, sequenznummer, "Response: verlassen");
        sequenznummer++;
        System.out.println(nachricht2.toString());
        int last = 0;
        if (bRichtung == true) {
            last = dieAbschnitte.length - 1 - last;   // Abschnitte von hinten nach vorne
        }

        dieAbschnitte[last].Belegung = 0;
        System.out.println("Zug verlaesst " + last);
        zeigeStreckenabschnitt();
    }
}

/**
 * Dies Klasse dient dazu, zwei Objekte zur Synchronisation zu definieren
 */

class SyncObjekt {
    // Dies Klasse ben�tigt weder Attribute noch Methoden
}


