package org.wizork.sample.validation.validator;

import org.wizork.sample.validation.ValidationError;

import java.util.List;

/**
 * Created by hari_om on 27/9/15.
 */
public interface Validator {
    public List<? extends ValidationError> validate(Object object);
}
