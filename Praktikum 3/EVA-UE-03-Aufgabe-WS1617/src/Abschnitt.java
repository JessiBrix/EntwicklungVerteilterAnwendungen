class Abschnitt {
    public boolean Reservierung;
    public int Belegung;
    public Signal richtung;
    private int rechnerkennung = 50;
    private static int sequenznummer = 1;

    public Abschnitt() {
        Reservierung = false;    // der Abschnitt ist nicht reserviert
        Belegung = 0;            // auf dem Abschnitt fuehrt zur Zeit kein Zug
        richtung = new Signal();
    }

    public void setzeRichtungssignal(String s){
        if (Belegung!=0){
            richtung.faerbe(s);
        }
        Nachricht nachricht = new Nachricht(rechnerkennung, sequenznummer, "Response: setzeRichtungssignal");
        sequenznummer++;
        System.out.println(nachricht.toString());
        System.out.println("Der Zug kommt aus " + s);
    }
}