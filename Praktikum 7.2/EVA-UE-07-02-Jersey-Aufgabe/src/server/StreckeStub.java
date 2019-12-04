package server;


import common.StreckeI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("strecke")
public class StreckeStub
{
	private Strecke strecke;

	public StreckeStub() {
		this.strecke = Strecke.getInstance();
	}

	@POST
	@Path("reserviere/{zugnummer}/{bRichtung}/{n}")
	@Produces(MediaType.APPLICATION_JSON)
	public MethodResponse reserviere(@PathParam("zugnummer") Integer zugnummer,@PathParam("bRichtung") Boolean bRichtung, @PathParam("n") Integer n) {
		MethodResponse response= new MethodResponse();
		response.setValue(strecke.reserviere(zugnummer, bRichtung, n).toString());
		return response;
	}

	@POST
	@Path("freigeben/{zugnummer}/{bRichtung}/{n}")
	@Produces(MediaType.APPLICATION_JSON)
	public MethodResponse freigeben(@PathParam("zugnummer") Integer zugnummer, @PathParam("bRichtung") Boolean bRichtung, @PathParam("n") Integer n) {
		MethodResponse response= new MethodResponse();
		response.setValue(strecke.freigeben(zugnummer, bRichtung, n).toString());
		return response;
	}

	@GET
	@Path("abschnitt/{bRichtung}/{n}")
	@Produces(MediaType.APPLICATION_JSON)
	public MethodResponse getAbschnitt(@PathParam("bRichtung") Boolean bRichtung, @PathParam("n") Integer n) {
		MethodResponse response= new MethodResponse();
		response.setValue(strecke.getAbschnitt(bRichtung, n).toString());
		return response;
	}

	@GET
	@Path("streckenlaenge/")
	@Produces(MediaType.APPLICATION_JSON)
	public MethodResponse getStreckenLaenge() {
		MethodResponse response= new MethodResponse();
		response.setValue(strecke.getStreckenLaenge().toString());
		return response;

	}

	@POST
	@Path("wechselnVon/{zugnummer}/{bRichtung}/{from}")
	@Produces(MediaType.APPLICATION_JSON)
	public String wechselnVon(@PathParam("zugnummer") Integer zugnummer,@PathParam("bRichtung") Boolean bRichtung,@PathParam("from") Integer from) {
		return strecke.wechselnVon(zugnummer, bRichtung, from);
	}

	@POST
	@Path("wechselnNach/{zugnummer}/{bRichtung}/{to}")
	@Produces(MediaType.APPLICATION_JSON)
	public String wechselnNach(@PathParam("zugnummer") Integer zugnummer,@PathParam("bRichtung") Boolean bRichtung,@PathParam("to") Integer to) {
		return strecke.wechselnNach(zugnummer, bRichtung, to);
	}

	@POST
	@Path("verlassen/{zugnummer}/{bRichtung}/")
	@Produces(MediaType.APPLICATION_JSON)
	public String verlassen(@PathParam("zugnummer") Integer zugnummer,@PathParam("bRichtung") Boolean bRichtung) {
		return strecke.verlassen(zugnummer, bRichtung);
	}
/**
 *
 * Der Strecke-Stub muss so implementiert werden, dass
 * bei der �bergabe der entsprechenden URI
 * ein Response-Objet zur�ckgegeben wird
 *
 */
}