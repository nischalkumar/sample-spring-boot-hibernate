package org.wizork.sample.service;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.wizork.sample.validation.ValidationError;
import org.wizork.sample.validation.ValidatorEnum;
import org.wizork.sample.validation.ValidatorFactory;
import org.wizork.sample.validation.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hari_om on 12/10/15.
 */
public class ValidatorService {
    @Autowired
    ValidatorFactory validatorFactory;

    public List<ValidationError> validate(Map<ValidatorEnum, Object> validatorContextMap) {
        List<ValidationError> validationErrors= new ArrayList();
        if(MapUtils.isNotEmpty(validatorContextMap)) {
            for (Map.Entry<ValidatorEnum, Object> entry : validatorContextMap.entrySet()) {
                Validator validator = validatorFactory.getValidator(entry.getKey());
                Object validationContext = entry.getValue();
                List<? extends ValidationError> individualValidationErrorList = validator.validate(validationContext);
                if (CollectionUtils.isNotEmpty(individualValidationErrorList)) {
                    validationErrors.addAll(individualValidationErrorList);
                }
            }
        }
        return validationErrors;
    }
}
