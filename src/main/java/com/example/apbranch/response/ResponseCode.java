package com.example.apbranch.response;

public abstract class ResponseCode {
    public static Integer CONFLICT = 409;
    public static Integer OK = 200;
    public static Integer FORBIDDEN = 403;
    public static Integer NOT_FOUND = 404;
    public static Integer CREATED = 201;
    public static Integer BAD_REQUEST = 400;
    public static Integer NO_CONTENT = 204;
}
