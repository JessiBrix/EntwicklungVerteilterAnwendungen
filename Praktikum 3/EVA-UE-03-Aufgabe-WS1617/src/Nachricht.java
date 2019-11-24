import java.text.SimpleDateFormat;
import java.util.Date;

public class Nachricht {

    private Date datum;
    String uhrzeit;
    private int rechnerkennung; //Sender und Empfänger einer Nachricht
    private int sequenznummer; //fortlaufende Nummer je Rechner
    private String nachricht;
    private SimpleDateFormat dateFormatter =
            new SimpleDateFormat("dd.MM.yyyy");
    SimpleDateFormat uhrzeitFormatter = new SimpleDateFormat("HH:mm:ss");


    public Nachricht(int rechnerkennung, int sequenznummer, String nachricht) {
        datum = java.util.Calendar.getInstance().getTime();
        uhrzeit = uhrzeitFormatter.format(new Date());
        this.rechnerkennung = rechnerkennung;
        this.sequenznummer = sequenznummer;
        this.nachricht = nachricht;
    }

    public String toString() {
        return "Servernachricht: \n\tDatum: " + dateFormatter.format(datum)
                + "\n\tUhrzeit: " + uhrzeit + "\n\tSequenznummer: " + sequenznummer + "\n\tInhalt: " + nachricht;
    }
}
