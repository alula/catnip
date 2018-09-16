package com.mewna.catnip.rest.webhook;

import com.mewna.catnip.util.JsonConvertible;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;

/**
 * @author gabixdev
 * @since 9/16/18
 */

@Accessors(fluent = true)
@Getter
@Setter
public class WebhookData implements JsonConvertible {
    private String name;
    private String avatar;
    private String channelId;
    
    @Nonnull
    @CheckReturnValue
    public static WebhookData create() {
        return new WebhookData();
    }
    
    @Nonnull
    @Override
    public JsonObject toJson() {
        return new JsonObject()
                .put("name", name)
                .put("avatar", avatar)
                .put("channel_id", channelId);
    }
}
