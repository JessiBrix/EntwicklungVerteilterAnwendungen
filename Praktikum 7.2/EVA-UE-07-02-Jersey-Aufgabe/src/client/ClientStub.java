
package client;

import common.StreckeI;
import server.MethodResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("client")
public class ClientStub implements StreckeI // Stub
{

	private long seqNr = 1;

    @PUT
    @Path("reservierung")
    public Boolean reserviere(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt)
	{

	    new MethodResponse();
	    return true;
    }

    @PUT
    @Path("startgleis")
    public String wechselnVon(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt)
	{
        new MethodResponse();
        return "";
    }

    @PUT
    @Path("zielgleis")
    public String wechselnNach(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt)
	{
        new MethodResponse();
        return "";
    }

    @PUT
    @Path("freigabegleis")
    public Boolean freigeben(Integer zugnummer, Boolean bRichtung, Integer iAbschnitt)
	{
        new MethodResponse();
        return true;
    }

    @PUT
    @Path("verlassengleis")
    public String verlassen(Integer zugnummer, Boolean bRichtung)
	{
        new MethodResponse();
        return "";
    }

    @GET
    @Path("streckenlaenge")
    public Integer getStreckenLaenge()
	{
        new MethodResponse();
        return 0;
    }

    @GET
    @Path("abschnitt")
    public Integer getAbschnitt(Boolean bRichtung, Integer iAbschnitt)
	{
        new MethodResponse();
        return 0;
    }
}
