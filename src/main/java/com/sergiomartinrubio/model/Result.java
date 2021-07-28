package com.sergiomartinrubio.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION, defaultImpl = Message.class)
@JsonSubTypes({@JsonSubTypes.Type(User.class)})
public class Result {
}
