package com.trade.project.common.snippet;

import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.Operation;
import org.springframework.restdocs.payload.AbstractFieldsSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadSubsectionExtractor;

import java.util.List;
import java.util.Map;

public class CustomResponseFieldSnippet extends AbstractFieldsSnippet {

    public CustomResponseFieldSnippet(
            String type,
            List<FieldDescriptor> descriptors,
            Map<String, Object> attributes,
            boolean ignoreUndocumentedFields) {
        super(type, descriptors, attributes, ignoreUndocumentedFields);
    }

    @Override
    protected MediaType getContentType(Operation operation) {
        return operation.getResponse().getHeaders().getContentType();
    }

    @Override
    protected byte[] getContent(Operation operation) {
        return operation.getResponse().getContent();
    }

}
