package com.mewna.catnip.rest;

import com.mewna.catnip.internal.CatnipImpl;
import com.mewna.catnip.rest.handler.*;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * @author amy
 * @since 9/1/18.
 */
@Accessors(fluent = true)
@Getter
@SuppressWarnings({"WeakerAccess", "unused"})
public class Rest {
    private final RestChannel channel;
    private final RestGuild guild;
    private final RestUser user;
    private final RestEmoji emoji;
    private final RestInvite invite;
    private final RestVoice voice;
    private final RestWebhook webhook;
    
    public Rest(final CatnipImpl catnip) {
        channel = new RestChannel(catnip);
        guild = new RestGuild(catnip);
        user = new RestUser(catnip);
        emoji = new RestEmoji(catnip);
        invite = new RestInvite(catnip);
        voice = new RestVoice(catnip);
        webhook = new RestWebhook(catnip);
    }
}
