package org.example.leetcode_sg.design;

import java.util.*;

/**
 * <a href="https://www.hellointerview.com/community/questions/chunk-ack-tracker/cms7iynkl0cn909adsyex8740">Chunk Upload Acknowledgment Tracker</a>
 */
public class UploadTracker {

    private int nextChunk;
    private Set<Integer> outOfOrderAcked;

    // Initializes the tracker.
    // All chunks with sequence numbers smaller than `nextChunk` are assumed to have already been acknowledged.
    public void init(int nextChunk) {
        this.nextChunk = nextChunk;
        outOfOrderAcked = new HashSet<>();
    }

    // Called whenever the upload of a chunk is acknowledged.
    void ack(int chunkNumber) {
        if (chunkNumber < this.nextChunk) {
            return;
        }

        if (outOfOrderAcked.contains(chunkNumber)) {
            return;
        }

        if (nextChunk != chunkNumber) {
            outOfOrderAcked.add(chunkNumber);
            return;
        }

        do {
            // not considering overflow.
            nextChunk++;
        } while (outOfOrderAcked.remove(nextChunk));

    }

    // Returns the smallest chunk number that has not yet been acknowledged.
    int lastUploadedChunk() {
        return this.nextChunk;
    }
}
