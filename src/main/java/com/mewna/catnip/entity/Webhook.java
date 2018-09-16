package com.mewna.catnip.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author gabixdev
 * @since 9/16/18
 */
public interface Webhook extends Snowflake {
    /**
     * The snowflake ID of the guild id this webhook is for.
     *
     * @return String representing the guild ID, possibly null.
     */
    @Nullable
    String guildId();
    
    /**
     * The snowflake ID of the channel id this webhook is for.
     *
     * @return String representing the channel ID. Never null.
     */
    @Nonnull
    String channelId();
    
    /**
     * The user this webhook was created by (not returned when getting a webhook with its token)
     *
     * @return The owner of this webhook, not returned when getting by token.
     */
    @Nullable
    User user();
    
    /**
     * The default name of this webhook.
     *
     * @return String representing the default webhook name, null if not provided.
     */
    @Nullable
    String name();
    
    /**
     * The default avatar URL of this webhook.
     *
     * @return String representing the default webhook avatar URL, null if not provided.
     */
    @Nullable
    String avatar();
    
    /**
     * The secret token of this webhook.
     *
     * @return String representing the token of this webhook. Never null.
     */
    @Nonnull
    String token();
    
    
}
