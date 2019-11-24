class Abschnitt {
    public boolean Reservierung;
    public int Belegung;
    public Signal richtung;

    public Abschnitt() {
        Reservierung = false;    // der Abschnitt ist nicht reserviert
        Belegung = 0;            // auf dem Abschnitt f�hrt zur Zeit kein Zug
        richtung = new Signal();
    }

    public void setzeRichtungssignal(String s){
        if (Belegung!=0){
            richtung.faerbe(s);
        }
        System.out.println("Zug kommt aus " + s);
    }

}