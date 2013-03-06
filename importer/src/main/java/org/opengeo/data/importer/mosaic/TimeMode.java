package org.opengeo.data.importer.mosaic;

import java.util.Date;

import org.opengeo.data.importer.Dates;

/**
 * Enumeration for handling timestamps for granules. 
 *   
 * @author Justin Deoliveira, OpenGeo
 */
public enum TimeMode {
    /**
     * Extract the timestamp from the filename, via {@link FilenameTimeHandler} 
     */
    FILENAME, MANUAL, AUTO, NONE;

    public TimeHandler createHandler() {
        if (this == FILENAME) {
            return new FilenameTimeHandler();
        }

        return new TimeHandler() {
            @Override
            public Date computeTimestamp(Granule g) {
                switch(TimeMode.this) {
                case AUTO:
                    return Dates.matchAndParse(g.getFile().getName());
                case MANUAL:
                    return g.getTimestamp();
                }
                return null;
            }
        };
    }
}