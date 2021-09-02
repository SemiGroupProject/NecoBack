package com.trade.project;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.trade.project.common.document.SecurityDocumentationConfig;
import com.trade.project.common.snippet.CustomRequestFieldSnippet;
import com.trade.project.common.snippet.CustomResponseFieldSnippet;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.FieldDescriptor;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;


import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@Import(SecurityDocumentationConfig.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
@AutoConfigureRestDocs
@ActiveProfiles("test")
public abstract class ProjectApplicationTests {

    protected MockMvc mockMvc;
    protected RestDocumentationResultHandler document;

    private final String host = "http://localhost:8080";
    private final Pattern hostPattern = Pattern.compile("(http[s]?)://([\\w-.]+)([:]([\\d]+))?");

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {

        Matcher hostMatcher = hostPattern.matcher(host);

        String ip = null;
        String port = null;

        if (hostMatcher.matches()) {
            ip = hostMatcher.group(2);
            port = hostMatcher.group(4);
        }

        this.document = document(
                "{class-name}/{method-name}",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint())
        );
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withHost(ip)
                        .withPort(toInt(port))
                )
                .apply(springSecurity())
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

    public static int toInt(String str) {
        return toInt(str, 0);
    }


    public static int toInt(String str, int defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }
}
