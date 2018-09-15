package com.mewna.catnip.entity.impl;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * @author Julia Rogers
 * @since 9/2/18
 */
@Accessors(fluent = true)
public enum Permission {
    CREATE_INSTANT_INVITE(0x00000001, true, true),
    KICK_MEMBERS(0x00000002),
    BAN_MEMBERS(0x00000004),
    ADMINISTRATOR(0x00000008),
    MANAGE_CHANNELS(0x00000010, true, true),
    MANAGE_GUILD(0x00000020),
    ADD_REACTIONS(0x00000040, true, false),
    VIEW_AUDIT_LOG(0x00000080),
    VIEW_CHANNEL(0x00000400, true, true),
    SEND_MESSAGES(0x00000800, true, false),
    SEND_TTS_MESSAGES(0x00001000, true, false),
    MANAGE_MESSAGES(0x00002000, true, false),
    EMBED_LINKS(0x00004000, true, false),
    ATTACH_FILES(0x00008000, true, false),
    READ_MESSAGE_HISTORY(0x00010000, true, false),
    MENTION_EVERYONE(0x00020000, true, false),
    USE_EXTERNAL_EMOJI(0x00040000, true, false),
    CONNECT(0x00100000, false, true),
    SPEAK(0x00200000, false, true),
    MUTE_MEMBERS(0x00400000, false, true),
    DEAFEN_MEMBERS(0x00800000, false, true),
    MOVE_MEMBERS(0x01000000, false, true),
    USE_VAD(0x02000000, false, true),
    PRIORITY_SPEAKER(0x00000100, false, true),
    CHANGE_NICKNAME(0x04000000),
    MANAGE_NICKNAME(0x08000000),
    MANAGE_ROLES(0x10000000, true, true),
    MANAGE_WEBHOOKS(0x20000000, true, true),
    MANAGE_EMOJI(0x40000000);

    Permission(final int value) {
        this.value = value;
        this.channel = false;
        this.text = false;
        this.voice = false;
    }
    
    Permission(final int value, boolean text, boolean voice) {
        this.value = value;
        this.channel = true;
        this.text = text;
        this.voice = voice;
    }

    @Getter
    private final int value;
    @Getter
    private final boolean channel;
    @Getter
    private final boolean text;
    @Getter
    private final boolean voice;
    
    /* Static convenience methods */

    public static int fromSet(Set<Permission> permissions) {
        int mask = 0;
        
        for (Permission p : permissions)
            mask |= p.value;
        
        return mask;
    }
    
    //TODO: why long, if permission bitmask doesn't exceed integer size?
    public static Set<Permission> toSet(final long asLong) {
        final Set<Permission> perms = EnumSet.noneOf(Permission.class);

        for (final Permission p : values()) {
            if ((asLong & p.value) == p.value) {
                perms.add(p);
            }
        }

        return perms;
    }
}
