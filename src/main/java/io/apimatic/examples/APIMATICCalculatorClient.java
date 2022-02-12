/*
 * APIMATICCalculatorLib
 *
 * This file was automatically generated by APIMATIC v3.0 ( https://www.apimatic.io ).
 */

package io.apimatic.examples;

import io.apimatic.examples.controllers.SimpleCalculatorController;
import io.apimatic.examples.http.client.HttpClient;
import io.apimatic.examples.http.client.HttpClientConfiguration;
import io.apimatic.examples.http.client.OkClient;
import io.apimatic.examples.http.client.ReadonlyHttpClientConfiguration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Gateway class for the library.
 * This class acts as a factory for Controllers.
 * It holds the state of the SDK.
 */
public final class APIMATICCalculatorClient implements Configuration {

    /**
     * Private store for controllers.
     */
    private SimpleCalculatorController simpleCalculator;

    /**
     * Current API environment.
     */
    private final Environment environment;

    /**
     * The HTTP Client instance to use for making HTTP requests.
     */
    private final HttpClient httpClient;

    /**
     * Http Client Configuration instance.
     */
    private final ReadonlyHttpClientConfiguration httpClientConfig;

    /**
     * Map of authentication Managers.
     */
    private Map<String, AuthManager> authManagers;

    private APIMATICCalculatorClient(Environment environment, HttpClient httpClient,
            ReadonlyHttpClientConfiguration httpClientConfig,
            Map<String, AuthManager> authManagers) {
        this.environment = environment;
        this.httpClient = httpClient;
        this.httpClientConfig = httpClientConfig;

        this.authManagers = (authManagers == null) ? new HashMap<>() : new HashMap<>(authManagers);


        simpleCalculator = new SimpleCalculatorController(this, this.httpClient, this.authManagers);
    }

    /**
     * Shutdown the underlying HttpClient instance.
     */
    public static void shutdown() {
        OkClient.shutdown();
    }

    /**
     * Get the instance of SimpleCalculatorController.
     * @return simpleCalculator
     */
    public SimpleCalculatorController getSimpleCalculatorController() {
        return simpleCalculator;
    }

    /**
     * Current API environment.
     * @return environment
     */
    public Environment getEnvironment() {
        return environment;
    }

    /**
     * The HTTP Client instance to use for making HTTP requests.
     * @return httpClient
     */
    private HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Http Client Configuration instance.
     * @return httpClientConfig
     */
    public ReadonlyHttpClientConfiguration getHttpClientConfig() {
        return httpClientConfig;
    }

    /**
     * The timeout to use for making HTTP requests.
     * @deprecated This method will be removed in a future version. Use
     *             {@link #getHttpClientConfig()} instead.
     *
     * @return timeout
     */
    @Deprecated
    public long timeout() {
        return httpClientConfig.getTimeout();
    }

    /**
     * Get base URI by current environment.
     * @param server Server for which to get the base URI
     * @return Processed base URI
     */
    public String getBaseUri(Server server) {
        return environmentMapper(environment, server);
    }

    /**
     * Get base URI by current environment.
     * @return Processed base URI
     */
    public String getBaseUri() {
        return getBaseUri(Server.ENUM_DEFAULT);
    }

    /**
     * Base URLs by environment and server aliases.
     * @param environment Environment for which to get the base URI
     * @param server Server for which to get the base URI
     * @return base URL
     */
    private static String environmentMapper(Environment environment, Server server) {
        if (environment.equals(Environment.PRODUCTION)) {
            if (server.equals(Server.ENUM_DEFAULT)) {
                return "https://examples.apimatic.io/apps/calculator";
            }
        }
        return "https://examples.apimatic.io/apps/calculator";
    }

    /**
     * Converts this APIMATICCalculatorClient into string format.
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return "APIMATICCalculatorClient [" + "environment=" + environment + ", httpClientConfig="
                + httpClientConfig + ", authManagers=" + authManagers + "]";
    }

    /**
     * Builds a new {@link APIMATICCalculatorClient.Builder} object.
     * Creates the instance with the state of the current client.
     * @return a new {@link APIMATICCalculatorClient.Builder} object
     */
    public Builder newBuilder() {
        Builder builder = new Builder();
        builder.environment = getEnvironment();
        builder.httpClient = getHttpClient();
        builder.authManagers = authManagers;
        builder.httpClientConfig(configBldr -> configBldr =
                ((HttpClientConfiguration) httpClientConfig).newBuilder());
        return builder;
    }

    /**
     * Class to build instances of {@link APIMATICCalculatorClient}.
     */
    public static class Builder {

        private Environment environment = Environment.PRODUCTION;
        private HttpClient httpClient;
        private Map<String, AuthManager> authManagers = null;
        private HttpClientConfiguration.Builder httpClientConfigBuilder =
                new HttpClientConfiguration.Builder();


        /**
         * Current API environment.
         * @param environment The environment for client.
         * @return Builder
         */
        public Builder environment(Environment environment) {
            this.environment = environment;
            return this;
        }

        /**
         * The timeout to use for making HTTP requests.
         * @deprecated This method will be removed in a future version. Use
         *             {@link #httpClientConfig(Consumer) httpClientConfig} instead.
         * @param timeout must be greater then 0.
         * @return Builder
         */
        @Deprecated
        public Builder timeout(long timeout) {
            this.httpClientConfigBuilder.timeout(timeout);
            return this;
        }

        /**
         * Setter for the Builder of httpClientConfiguration, takes in an operation to be performed
         * on the builder instance of HTTP client configuration.
         * 
         * @param action Consumer for the builder of httpClientConfiguration.
         * @return Builder
         */
        public Builder httpClientConfig(Consumer<HttpClientConfiguration.Builder> action) {
            action.accept(httpClientConfigBuilder);
            return this;
        }

        /**
         * Builds a new APIMATICCalculatorClient object using the set fields.
         * @return APIMATICCalculatorClient
         */
        public APIMATICCalculatorClient build() {
            HttpClientConfiguration httpClientConfig = httpClientConfigBuilder.build();
            httpClient = new OkClient(httpClientConfig);

            return new APIMATICCalculatorClient(environment, httpClient, httpClientConfig,
                    authManagers);
        }
    }
}