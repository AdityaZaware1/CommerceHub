package com.ben.apis.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openEndPoints = List.of(
            "/auth/register",
            "/auth/login",
            "/eureka",
            "swagger-ui.html"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openEndPoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
