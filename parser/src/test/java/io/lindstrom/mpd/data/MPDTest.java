package io.lindstrom.mpd.data;

import java.time.Duration;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MPDTest {

    @Test
    void itUsesTheSuppliedFunctionToModifyTheBuilder() {
        MPD mpd = MPD.builder()
                .withType(PresentationType.DYNAMIC)
                .withAvailabilityStartTime(OffsetDateTime.parse("2024-03-27T13:00:00Z"))
                .withPublishTime(OffsetDateTime.parse("2024-03-27T13:00:00Z"))
                .apply(builder -> {
                    if (OffsetDateTime.now().isAfter(OffsetDateTime.MIN)) {
                        builder.withMaxSegmentDuration(Duration.ofMinutes(1));
                    }
                    return builder;
                })
                .build();

        assertEquals(Duration.ofMinutes(1), mpd.getMaxSegmentDuration());
    }

}
