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

    DUPLICATE_ENTRY,
    INVALID_ENTRY,
    INVALID_REQUEST,
    UNKNOWN
}
