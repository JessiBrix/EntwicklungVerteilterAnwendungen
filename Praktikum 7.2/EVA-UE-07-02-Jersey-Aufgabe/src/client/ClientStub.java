
package client;

import com.sun.jersey.api.client.Client;
import common.StreckeI;
import server.MethodResponse;


public class ClientStub implements StreckeI // Stub
{


    private long seqNr = 1;

    public Boolean reserviere(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt) {
        final MethodResponse response = Client.create().resource("http://localhost:8080/rest/strecke/reserviere/" + zugnummer + "/" + bRichtung + "/" + iAbschnitt).post(MethodResponse.class);
        return new Boolean(response.getValue());
    }


    public String wechselnVon(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt) {
        return Client.create().resource("http://localhost:8080/rest/strecke/wechselnVon/" + zugnummer + "/" + bRichtung + "/" + iAbschnitt).post(String.class);
    }


    public String wechselnNach(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt) {
        return Client.create().resource("http://localhost:8080/rest/strecke/wechselnNach/" + zugnummer + "/" + bRichtung + "/" + iAbschnitt).post(String.class);
    }

    public Boolean freigeben(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt) {
        final MethodResponse response = Client.create().resource("http://localhost:8080/rest/strecke/freigeben/" + zugnummer + "/" + bRichtung + "/" + iAbschnitt).post(MethodResponse.class);
        return new Boolean(response.getValue());
    }


    public String verlassen(Integer zugnummer, Boolean bRichtung) {
        return Client.create().resource("http://localhost:8080/rest/strecke/verlassen/" + zugnummer + "/" + bRichtung).post(String.class);
    }

    public Integer getStreckenLaenge() {
        final MethodResponse response = Client.create().resource("http://localhost:8080/rest/strecke/streckenlaenge/").get(MethodResponse.class);
        return new Integer(response.getValue());
    }

    public Integer getAbschnitt(Boolean bRichtung, Integer iAbschnitt) {
        final MethodResponse response = Client.create().resource("http://localhost:8080/rest/strecke/abschnitt/" + bRichtung + "/" + iAbschnitt).get(MethodResponse.class);
        return new Integer(response.getValue());
    }


}
