package com.br.projects.controller.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

  private List<FieldMessage> errors = new ArrayList<>();

  public List<FieldMessage> getErrors() {
    return this.errors;
  }

  public void addErrors(String fieldName, String message) {
    errors.add(new FieldMessage(fieldName, message));
  }

}