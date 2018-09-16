package com.mewna.catnip.entity.impl;

import com.mewna.catnip.Catnip;
import com.mewna.catnip.entity.User;
import com.mewna.catnip.entity.Webhook;
import lombok.*;
import lombok.experimental.Accessors;

import javax.annotation.Nonnull;

/**
 * @author gabixdev
 * @since 9/16/18
 */

@Getter
@Setter
@Builder
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class WebhookImpl implements Webhook, RequiresCatnip {
    private transient Catnip catnip;
    
    private String id;
    private String guildId;
    private String channelId;
    private User user;
    private String name;
    private String avatar; // TODO: not sure it's avatar hash or URL
    private String token;
    
    @Override
    public void catnip(@Nonnull final Catnip catnip) {
        this.catnip = catnip;
    }
}
