/*
 * Copyright © 2014 Federico Recio (N/A)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Copyright (C) 2014 Federico Recio
/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * <p>http://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.federecio.dropwizard.swagger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Strings;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For the meaning of all these properties please refer to Swagger documentation or {@link SwaggerConfiguration}
 */
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwaggerBundleConfiguration {

    /**
     * This is the only property that is required for Swagger to work correctly.
     *
     * <p>It is a comma separated list of the all the packages that contain the {@link io.swagger.v3.oas.annotations.tags.Tag} annotated resources
     */
    @NotEmpty
    @JsonProperty
    private String resourcePackage = "";

    @Nullable
    @JsonProperty
    private String title;

    @Nullable
    @JsonProperty
    private String version;

    @Nullable
    @JsonProperty
    private String description;

    @Nullable
    @JsonProperty
    private String termsOfService;

    @Nullable
    @JsonProperty
    private Contact contact;

    @Nullable
    @JsonProperty
    private License license;

    /**
     * For most of the scenarios this property is not needed.
     *
     * <p>This is not a property for Swagger but for bundle to set up Swagger UI correctly. It only
     * needs to be used of the root path or the context path is set programmatically and therefore
     * cannot be derived correctly. The problem arises in that if you set the root path or context
     * path in the run() method in your Application subclass the bundle has already been initialized
     * by that time and so does not know you set the path programmatically.
     */
    @Nullable
    private String uriPrefix;

    private SwaggerViewConfiguration swaggerViewConfiguration = new SwaggerViewConfiguration();
    private boolean prettyPrint = true;

    private String contextRoot = "/";
    private String[] schemes = new String[]{"http"};
    private boolean enabled = true;
    private boolean includeSwaggerResource = true;

    @JsonIgnore
    SwaggerConfiguration getSwaggerConfiguration() {
        if (Strings.isNullOrEmpty(resourcePackage)) {
            throw new IllegalStateException(
                    "Resource package needs to be specified for Swagger to correctly detect annotated resources");
        }

        OpenAPI oas = new OpenAPI();
        oas.info(
                new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                        .contact(contact)
                        .license(license)
                        .termsOfService(termsOfService)
        );

        return new SwaggerConfiguration()
                .openAPI(oas)
                .prettyPrint(prettyPrint)
                .readAllResources(false)
                .resourcePackages(
                        Stream.of(resourcePackage.split(","))
                                .collect(Collectors.toSet())
                );
    }
}
