package com.jdevhub.tornado.api.auth.domain.dto;

public record RegisterRequest (String noms, String prenoms, String username,String email, String password, Boolean conditionAccepted, Boolean newsletterAccepted){


}
