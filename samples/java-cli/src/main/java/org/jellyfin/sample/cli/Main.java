package org.jellyfin.sample.cli;

import org.jellyfin.sdk.Jellyfin;
import org.jellyfin.sdk.JellyfinOptions;
import org.jellyfin.sdk.api.client.ApiClient;
import org.jellyfin.sdk.api.client.compatibility.JavaResponseContinuation;
import org.jellyfin.sdk.api.client.extensions.UserApiExtensionsKt;
import org.jellyfin.sdk.api.operations.UserApi;
import org.jellyfin.sdk.model.ClientInfo;
import org.jellyfin.sdk.model.DeviceInfo;
import org.jellyfin.sdk.model.api.AuthenticationResult;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        JellyfinOptions.Builder options = new JellyfinOptions.Builder();
        options.setClientInfo(new ClientInfo("Jellyfin Sample: Kotlin CLI", "DEV"));
        options.setDeviceInfo(new DeviceInfo("cli", "cli"));
        Jellyfin jellyfin = new Jellyfin(options);

        // TODO Replace below with a proper CLI that compares to the kotlin-cli sample
        Logger logger = LoggerFactory.getLogger(Main.class);
        ApiClient client = jellyfin.createApi("https://demo.jellyfin.org/stable/");

        CountDownLatch latch = new CountDownLatch(1);
        UserApi userApi = new UserApi(client);
        UserApiExtensionsKt.authenticateUserByName(userApi, "demo", "", new JavaResponseContinuation<AuthenticationResult>() {
            @Override
            public void onResponse(@NotNull AuthenticationResult response) {
                client.setAccessToken(response.getAccessToken());

                logger.info("Got access token: {}", response.getAccessToken());
                latch.countDown();
            }

            @Override
            public void onError(@NotNull Throwable error) {
                logger.error("Unable to get access token", error);
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
