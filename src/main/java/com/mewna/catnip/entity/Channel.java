package com.mewna.catnip.entity;

import lombok.Getter;

import javax.annotation.Nonnull;

/**
 * @author natanbc
 * @since 9/12/18
 */
@SuppressWarnings("ClassReferencesSubclass")
public interface Channel extends Snowflake {
    ChannelType type();
    
    default boolean isText() {
        return type() == ChannelType.TEXT;
    }
    
    default boolean isVoice() {
        return type() == ChannelType.VOICE;
    }
    
    default boolean isCategory() {
        return type() == ChannelType.CATEGORY;
    }
    
    default boolean isGuild() {
        return type().isGuild();
    }
    
    default boolean isUserDM() {
        return type() == ChannelType.DM;
    }
    
    default boolean isGroupDM() {
        return type() == ChannelType.GROUP_DM;
    }
    
    default boolean isDM() {
        return !type().isGuild();
    }
    
    default GuildChannel asGuildChannel() {
        if(!isGuild()) {
            throw new UnsupportedOperationException("Not a guild channel");
        }
        return (GuildChannel)this;
    }
    
    default TextChannel asTextChannel() {
        if(!isText()) {
            throw new UnsupportedOperationException("Not a text channel");
        }
        return (TextChannel)this;
    }
    
    default VoiceChannel asVoiceChannel() {
        if(!isVoice()) {
            throw new UnsupportedOperationException("Not a voice channel");
        }
        return (VoiceChannel)this;
    }
    
    default Category asCategory() {
        if(!isCategory()) {
            throw new UnsupportedOperationException("Not a category");
        }
        return (Category)this;
    }
    
    default DMChannel asDMChannel() {
        if(!isDM()) {
            throw new UnsupportedOperationException("Not a DM channel");
        }
        return (DMChannel)this;
    }
    
    default UserDMChannel asUserDMChannel() {
        if(!isUserDM()) {
            throw new UnsupportedOperationException("Not an user DM channel");
        }
        return (UserDMChannel)this;
    }
    
    default GroupDMChannel asGroupDMChannel() {
        if(!isGroupDM()) {
            throw new UnsupportedOperationException("Not a group DM channel");
        }
        return (GroupDMChannel)this;
    }
    
    enum ChannelType {
        TEXT(0, true), DM(1, false), VOICE(2, true), GROUP_DM(3, false), CATEGORY(4, true);
        
        @Getter
        private final int key;
        @Getter
        private final boolean guild;
        
        ChannelType(final int key, final boolean guild) {
            this.key = key;
            this.guild = guild;
        }
    
        @Nonnull
        public static ChannelType byKey(final int key) {
            for(final ChannelType level : values()) {
                if(level.key == key) {
                    return level;
                }
            }
            throw new IllegalArgumentException("No channel type for key " + key);
        }
    }
}