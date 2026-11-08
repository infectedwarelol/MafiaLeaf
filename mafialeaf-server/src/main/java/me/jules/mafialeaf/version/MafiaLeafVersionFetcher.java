package me.jules.mafialeaf.version;

import com.destroystokyo.paper.util.VersionFetcher;
import java.util.concurrent.TimeUnit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class MafiaLeafVersionFetcher implements VersionFetcher {

    private static final long CACHE_TIME_MILLIS = TimeUnit.HOURS.toMillis(1L);

    @Override
    public long getCacheTime() {
        return CACHE_TIME_MILLIS;
    }

    @Override
    public Component getVersionMessage() {
        return Component.text("Dedicated PigFFA fork of Leaf, Paper and Purpur. Update checks are disabled.", NamedTextColor.GRAY);
    }
}
