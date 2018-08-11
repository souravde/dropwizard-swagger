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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.Nullable;

/**
 * Contains all configurable parameters required to render the SwaggerUI View from the default
 * template
 */
@Getter
@Setter
@NoArgsConstructor
public class SwaggerViewConfiguration {

    private static final String DEFAULT_TITLE = "Swagger UI";
    private static final String DEFAULT_TEMPLATE = "index.ftl";

    private String pageTitle = DEFAULT_TITLE;

    private String templateUrl = DEFAULT_TEMPLATE;

    @Nullable
    private String validatorUrl;
}
