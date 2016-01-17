package org.wizork.sample.service;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.wizork.sample.common.Enums.Role;
import org.wizork.sample.common.response.UserResponse;
import org.wizork.sample.domain.User;
import org.wizork.sample.utils.UserUtils;
import org.wizork.sample.validation.PolicyValidatorContext;
import org.wizork.sample.validation.ValidatorContextMapBuilder;
import org.wizork.sample.validation.ValidatorEnum;
import org.wizork.sample.validation.validator.PolicyValidator;
import org.wizork.sample.worker.UserWorker;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by hari_om on 6/18/15.
 */
@Service
public class UserService {

    @Autowired
    UserWorker userWorker;
    @Autowired
    UserUtils userUtils;
    @Autowired
    ValidatorService validatorService;
    @Autowired
    PolicyValidator policyValidator;

    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;

    public UserService() {}

    public UserResponse getUser(String login, User user) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user, Arrays.asList(Role.ADMIN));
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();
        validatorService.validate(validatorContextMap);
        return userUtils.getEncryptedUser(userWorker.getUser(login));
    }

    public UserResponse getUser(User user) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(user, Arrays.asList(Role.ANY));
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();
        validatorService.validate(validatorContextMap);
        return userUtils.getEncryptedUser(user);
    }

    public UserResponse insertUser(User user, User autherizer) {
        PolicyValidatorContext policyValidatorContext = new PolicyValidatorContext(autherizer);
        policyValidatorContext.addRole(Role.ADMIN);
        Map<ValidatorEnum, Object> validatorContextMap = new ValidatorContextMapBuilder()
                .addValidator(ValidatorEnum.PolicyValidator, policyValidatorContext)
                .build();
        validatorService.validate(validatorContextMap);
        user.setId(0);
        return userUtils.getEncryptedUser(userWorker.insertUser(user, user.getRoles()));
    }
}
