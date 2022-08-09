package com.example.hplus.controller;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class HPlusExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        ErrorType  type = null;
        if(ex instanceof DataIntegrityViolationException){
            type = ErrorType.valueOf("BAD_REQUEST");
        }else{
            type = ErrorType.valueOf("INTERNAL_ERROR");
        }
        return GraphqlErrorBuilder.newError(env)
                .message("Received Message: " + ex.getMessage())
                .errorType(type)
                .build();
    }
}
