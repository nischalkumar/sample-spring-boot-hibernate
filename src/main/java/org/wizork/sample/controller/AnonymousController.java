package org.wizork.sample.controller;

import com.wordnik.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by nischal.k on 07/01/16.
 */
@RestController
@RequestMapping(value = "/v1/anon")
@Api(value = "Anonymous Controller", description = "No Oauth Security for /v1/anon Controller")
public class AnonymousController {

    final static Logger logger = LoggerFactory.getLogger(AnonymousController.class);


}
