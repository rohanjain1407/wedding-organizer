package com.weddingapi.services.error;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

/**
 * @author Rohan Jain
 */
public class ErrorResponse {

    private final static String ERROR_PARAM = "error";
    private ApiError error;

    public ErrorResponse(ApiError error) {
        this.error = error;
    }

    /**
     * Call this after instantiating {@link ErrorResponse} to
     * create a json error response for the client
     *
     * @return
     */
    public Response createErrorResponse() {
        return Response.status(error.getStatus()).entity(createJsonError()).build();
    }

    private String createJsonError() {
        JSONObject errorObject = new JSONObject();
        errorObject.put(ERROR_PARAM, error.name());
        return errorObject.toString();
    }
}
