package com.sergiomartinrubio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * This class is used as a parent class for building a successful response.
 *
 * @author Sergio Martin Rubio
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SuccessfulResponse extends Response {
    /**
     * Contains information about a successful response.
     */
    private Result result;
}
