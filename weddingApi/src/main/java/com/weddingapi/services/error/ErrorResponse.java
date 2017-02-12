package com.weddingapi.services.error;

import org.json.JSONObject;

import javax.ws.rs.core.Response;

/**
 * @author Rohan Jain
 */
public class ErrorResponse {

    private final static String ERROR_PARAM = "error";
    private final static String ERROR_DESCRIPTION_PARAM = "errorDescription";

    private ApiError error;
    private String errorDescription;

    public ErrorResponse(ApiError error) {
        this.error = error;
    }

    public ErrorResponse(ApiError error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }

    /**
     * Call this after instantiating {@link ErrorResponse} to
     * create a json error response for the client
     *
     * @return
     */
    public Response createErrorResponse() {
        return Response.status(200).entity(createJsonError()).build();
    }

    private String createJsonError() {
        JSONObject errorObject = new JSONObject();
        errorObject.put(ERROR_PARAM, error.name());
        errorObject.put(ERROR_DESCRIPTION_PARAM, errorDescription);
        return errorObject.toString();
    }
}
