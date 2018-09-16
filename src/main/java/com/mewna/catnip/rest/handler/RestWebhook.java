package com.mewna.catnip.rest.handler;

import com.google.common.collect.ImmutableMap;
import com.mewna.catnip.entity.Webhook;
import com.mewna.catnip.internal.CatnipImpl;
import com.mewna.catnip.rest.ResponsePayload;
import com.mewna.catnip.rest.RestRequester.OutboundRequest;
import com.mewna.catnip.rest.Routes;
import com.mewna.catnip.rest.webhook.WebhookData;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author gabixdev
 * @since 9/16/18
 */
@SuppressWarnings({"unused", "WeakerAccess", "TypeMayBeWeakened"})
public class RestWebhook extends RestHandler {
    public RestWebhook(final CatnipImpl catnip) {
        super(catnip);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> createWebhook(@Nonnull final String channelId, @Nonnull final WebhookData webhookData) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.CREATE_WEBHOOK.withMajorParam(channelId),
                ImmutableMap.of(), webhookData.toJson()))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<List<Webhook>> getChannelWebhooks(@Nonnull final String channelId) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.GET_CHANNEL_WEBHOOKS.withMajorParam(channelId),
                        ImmutableMap.of(), null))
                .thenApply(ResponsePayload::array)
                .thenApply(mapObjectContents(getEntityBuilder()::createWebhook))
                .thenApply(Collections::unmodifiableList);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<List<Webhook>> getGuildWebhooks(@Nonnull final String guildId) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.GET_GUILD_WEBHOOKS.withMajorParam(guildId),
                ImmutableMap.of(), null))
                .thenApply(ResponsePayload::array)
                .thenApply(mapObjectContents(getEntityBuilder()::createWebhook))
                .thenApply(Collections::unmodifiableList);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> getWebhook(@Nonnull final String webhookId) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.GET_WEBHOOK.withMajorParam(webhookId),
                ImmutableMap.of(), null))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> getWebhookWithToken(@Nonnull final String webhookId, @Nonnull final String token) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.GET_WEBHOOK_WITH_TOKEN.withMajorParam(webhookId),
                ImmutableMap.of("webhook.token", token), null))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> modifyWebhook(@Nonnull final String webhookId, @Nonnull final WebhookData webhookData) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.MODIFY_WEBHOOK.withMajorParam(webhookId),
                ImmutableMap.of(), webhookData.toJson()))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> modifyWebhookWithToken(@Nonnull final String webhookId, @Nonnull final String token, @Nonnull final WebhookData webhookData) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.MODIFY_WEBHOOK_WITH_TOKEN.withMajorParam(webhookId),
                ImmutableMap.of("webhook.token", token), webhookData.toJson()))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> deleteWebhook(@Nonnull final String webhookId) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.DELETE_WEBHOOK.withMajorParam(webhookId),
                ImmutableMap.of(), null))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    @Nonnull
    @CheckReturnValue
    public CompletableFuture<Webhook> deleteWebhookWithToken(@Nonnull final String webhookId, @Nonnull final String token) {
        return getCatnip().requester().queue(new OutboundRequest(Routes.DELETE_WEBHOOK_WITH_TOKEN.withMajorParam(webhookId),
                ImmutableMap.of("webhook.token", token), null))
                .thenApply(ResponsePayload::object)
                .thenApply(getEntityBuilder()::createWebhook);
    }
    
    // TODO: execute webhook + file sending
}
