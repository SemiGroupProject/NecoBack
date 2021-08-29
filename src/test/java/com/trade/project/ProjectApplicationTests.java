package com.trade.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.trade.project.common.snippet.CustomRequestFieldSnippet;
import com.trade.project.common.snippet.CustomResponseFieldSnippet;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
@ActiveProfiles("test")
public abstract class ProjectApplicationTests {

    protected MockMvc mockMvc;
    protected RestDocumentationResultHandler document;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.document = document(
                "{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .alwaysDo(document)
                .build();
    }

    /**
     * request, response filed custom
     * * **/
    protected static CustomRequestFieldSnippet commonRequestFields(
            FieldDescriptor... descriptors) {
        return new CustomRequestFieldSnippet(
                "request", Arrays.asList(descriptors), null, true);
    }

    protected static CustomRequestFieldSnippet customRequestFields(FieldDescriptor... descriptors) {
        return commonRequestFields(descriptors);
    }

    protected static CustomResponseFieldSnippet commonResponseFields(FieldDescriptor... descriptors) {
        return new CustomResponseFieldSnippet(
                "response", Arrays.asList(descriptors), null, true);
    }

    protected static CustomResponseFieldSnippet customResponseFields(FieldDescriptor... descriptors) {
        return commonResponseFields(descriptors);
    }
    protected String toJson(Object request) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(request);
    }
}
