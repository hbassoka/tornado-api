package com.jdevhub.intranet.api.core.auth.domain.dto;

public record RegisterRequest (String noms, String prenoms, String username,String email, String password, Boolean conditionAccepted, Boolean newsletterAccepted){


}
