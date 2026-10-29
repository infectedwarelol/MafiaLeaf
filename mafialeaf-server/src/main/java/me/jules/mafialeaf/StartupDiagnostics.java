package me.jules.mafialeaf;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class StartupDiagnostics {

    private static final Logger LOGGER = LogManager.getLogger("MafiaLeaf");
    private static final int MINIMUM_RECOMMENDED_PROCESSORS = 4;
    private static final long GIBIBYTE = 1024L * 1024L * 1024L;

    private StartupDiagnostics() {
    }

    public static void report() {
        final int processors = Runtime.getRuntime().availableProcessors();
        final long maxHeap = Runtime.getRuntime().maxMemory();
        final List<String> collectors = ManagementFactory.getGarbageCollectorMXBeans().stream().map(GarbageCollectorMXBean::getName).toList();
        final String collectorNames = String.join(", ", collectors);

        LOGGER.info("MafiaLeaf runtime: {} usable processors, {} MiB max heap, GC [{}]", processors, maxHeap / (1024L * 1024L), collectorNames);

        if (processors < MINIMUM_RECOMMENDED_PROCESSORS) {
            LOGGER.warn("The JVM only sees {} processor(s). Netty, chunk, tracker and GC threads will all share them and MSPT will spike.", processors);
            LOGGER.warn("Check taskset/cpuset/container CPU limits and -XX:ActiveProcessorCount; the panel may be pinning the server to one core.");
        }
        if (collectors.stream().anyMatch(name -> name.contains("Serial") || name.equals("Copy") || name.equals("MarkSweepCompact"))) {
            LOGGER.warn("Serial GC is active, usually because the JVM detected one CPU or under 1792 MiB of memory. Use -XX:+UseG1GC or -XX:+UseZGC.");
        }
        if (maxHeap > 32L * GIBIBYTE && collectors.stream().anyMatch(name -> name.startsWith("G1"))) {
            LOGGER.warn("G1 with a heap above 32 GiB disables compressed oops and raises memory use. Prefer up to 31G or use generational ZGC.");
        }
    }
}
