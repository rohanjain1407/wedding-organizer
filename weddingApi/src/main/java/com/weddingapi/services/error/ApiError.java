package com.weddingapi.services.error;


/**
 * @author Rohan Jain
 *
 * Enumeration of different weddingapi error constants
 * that may occur when processing service request.
 * These constants should be identified on client and
 * appropriately handled
 */
public enum ApiError {

    DUPLICATE_RECORD(404),
    RECORD_NOT_FOUND(404),
    INVALID_REQUEST(400),
    UNKNOWN(400);

    private final int status;

    ApiError(int status) {
        this.status=status;
    }

    public int getStatus() {
        return this.status;
    }
}
