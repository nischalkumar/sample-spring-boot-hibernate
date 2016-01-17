package org.wizork.sample.validation.validator;

import org.wizork.sample.common.Enums.Role;
import org.wizork.sample.common.Enums.ValidationErrorCode;
import org.wizork.sample.validation.PolicyValidatorContext;
import org.wizork.sample.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari_om on 17/1/16.
 */
public class PolicyValidator implements Validator {
    @Override
    public List<? extends ValidationError> validate(Object object) {
        List<ValidationErrorCode> validationErrorCodeList = new ArrayList<>();
        PolicyValidatorContext policyValidatorContext = (PolicyValidatorContext) object;
        if(policyValidatorContext.getUser().getRoles().contains(Role.ADMIN))
            return new ArrayList<>();
        for( Role role: policyValidatorContext.getRoleList()) {
            if(!policyValidatorContext.getUser().getRolesSet().contains(role)) {
                validationErrorCodeList.add(ValidationErrorCode.InvalidRole);
            }
        }
        return validationErrorCodeList;
    }
}
