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

import io.dropwizard.views.View;
import lombok.Getter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 * Serves the content of Swagger's index page which has been "templatized" to support replacing the
 * directory in which Swagger's static content is located (i.e. JS files) and the path with which
 * requests to resources need to be prefixed.
 */
public class SwaggerView extends View {

    private static final String SWAGGER_URI_PATH = "/swagger-static";

    @Getter
    private final String swaggerAssetsPath;

    @Getter
    private final String contextPath;

    private final SwaggerViewConfiguration viewConfiguration;

    public SwaggerView(@Nonnull final String contextRoot,
                       @Nonnull final String urlPattern,
                       @Nonnull SwaggerViewConfiguration viewConfiguration) {
        super(viewConfiguration.getTemplateUrl(), StandardCharsets.UTF_8);

        String contextRootPrefix = "/".equals(contextRoot) ? "" : contextRoot;

        // swagger-static should be found on the root context
        if (!contextRootPrefix.isEmpty()) {
            swaggerAssetsPath = contextRootPrefix + SWAGGER_URI_PATH;
        } else {
            swaggerAssetsPath =
                    (urlPattern.equals("/") ? SWAGGER_URI_PATH : (urlPattern + SWAGGER_URI_PATH));
        }

        contextPath = urlPattern.equals("/") ? contextRootPrefix : (contextRootPrefix + urlPattern);

        this.viewConfiguration = viewConfiguration;
    }

    /**
     * Returns the title for the browser header
     *
     * @return String
     */
    @Nullable
    public String getTitle() {
        return viewConfiguration.getPageTitle();
    }

    /**
     * Returns the location of the validator URL or null to disable
     *
     * @return String
     */
    @Nullable
    public String getValidatorUrl() {
        return viewConfiguration.getValidatorUrl();
    }
}
