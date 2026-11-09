package org.dreeam.leaf.async.chunk;

import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import net.minecraft.network.protocol.game.ClientboundLevelChunkWithLightPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dreeam.leaf.util.queue.MpmcQueue;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Supplier;

@NullMarked
public final class AsyncChunkSender {

    private static final Logger LOGGER = LogManager.getLogger("MafiaLeaf");
    private static final int CAPACITY = 255;

    private final MpmcQueue<Result> channel;
    private final Long2LongOpenHashMap pendingTickets;
    private long lastTicket;
    private int inFlight;

    public AsyncChunkSender() {
        this.channel = new MpmcQueue<>(Result.class, CAPACITY);
        this.pendingTickets = new Long2LongOpenHashMap();
    }

    public boolean canSubmit() {
        return this.inFlight < CAPACITY;
    }

    public boolean contains(long chunkKey) {
        return this.pendingTickets.containsKey(chunkKey);
    }

    public void remove(long chunkKey) {
        this.pendingTickets.remove(chunkKey);
    }

    public void clear() {
        this.pendingTickets.clear();
        while (this.recv() != null) ;
    }

    public void submit(long chunkKey, int changeCount, Supplier<ClientboundLevelChunkWithLightPacket> packetFactory) {
        long ticket = ++this.lastTicket;
        this.pendingTickets.put(chunkKey, ticket);
        this.inFlight++;
        AsyncChunkSend.POOL.execute(() -> {
            ClientboundLevelChunkWithLightPacket packet = null;
            try {
                packet = packetFactory.get();
            } catch (Throwable throwable) {
                LOGGER.warn("Failed to build chunk packet off the main thread, it will be sent synchronously", throwable);
            }
            Result result = new Result(chunkKey, ticket, changeCount, packet);
            while (!this.channel.send(result)) {
                Thread.onSpinWait();
            }
        });
    }

    public @Nullable Result recv() {
        final Result result = this.channel.recv();
        if (result != null) {
            this.inFlight--;
        }
        return result;
    }

    public boolean complete(Result result) {
        if (this.pendingTickets.get(result.chunkKey()) != result.ticket()) {
            return false;
        }
        this.pendingTickets.remove(result.chunkKey());
        return true;
    }

    public record Result(long chunkKey, long ticket, int changeCount, @Nullable ClientboundLevelChunkWithLightPacket packet) {
    }
}
