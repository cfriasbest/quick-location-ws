package com.quick.location.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.quick.location.service.PlaceDetailsServiceApi;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cfriasb
 */
@RestController
@Slf4j
public class WsApiController extends AbstractController {

    @Autowired
    PlaceDetailsServiceApi placeDetailsServiceApi;

}
