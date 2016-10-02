package com.weddingapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/weddinginfo")
public class WeddingListener {
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getInfo() {
		Wedding w1 = new Wedding("WED1", "ABC", "XYwerwZ", "204 10th Street", "");
		System.out.println("Recieved it mahn");
		return Response.status(200)
				.header("Access-Control-Allow-Origin", "*")
				.entity(w1).build();
			
	}
}