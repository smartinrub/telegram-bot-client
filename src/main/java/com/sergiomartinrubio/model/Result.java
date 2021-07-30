package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Marker interface for creating a result depending on the {@link Response}.
 *
 * @author Sergio Martin Rubio
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = Message.class)
@JsonSubTypes({@JsonSubTypes.Type(User.class)})
public interface Result {
}
