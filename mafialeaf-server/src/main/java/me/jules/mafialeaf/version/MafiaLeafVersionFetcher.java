package me.jules.mafialeaf.version;

import com.destroystokyo.paper.util.VersionFetcher;
import io.papermc.paper.ServerBuildInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public final class MafiaLeafVersionFetcher implements VersionFetcher {

    @Override
    public long getCacheTime() {
        return Long.MAX_VALUE;
    }

    @Override
    public Component getVersionMessage() {
        final ServerBuildInfo build = ServerBuildInfo.buildInfo();
        return Component.text()
            .append(Component.text("This server is running " + build.brandName() + " " + build.asString(ServerBuildInfo.StringRepresentation.VERSION_FULL), NamedTextColor.WHITE))
            .append(Component.newline())
            .append(Component.text("Dedicated PigFFA fork of Leaf, Paper and Purpur. Update checks are disabled.", NamedTextColor.GRAY))
            .build();
    }
}
