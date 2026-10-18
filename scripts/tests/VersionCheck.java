/*
 * This file is part of the Marisa Optimize distribution.
 * Copyright (c) Meteor Development.
 */
import com.hongminh54.marisaoptimize.utils.misc.Version;

public class VersionCheck {
    public static void main(String[] args) {
        Version drop = new Version("26.2");
        Version patchZero = new Version("26.2.0");
        if (drop.isHigherThan(patchZero) || patchZero.isHigherThan(drop)) throw new AssertionError("Missing patch must equal zero");
        if (!new Version("26.2.1").isHigherThan(drop)) throw new AssertionError("Patch ordering");
        if (!new Version("26.3").isHigherThan(drop)) throw new AssertionError("Drop ordering");
        if (!drop.isHigherThan(new Version("1.21.11"))) throw new AssertionError("Legacy ordering");
        if (!new Version("0.0").isZero() || !new Version("0.0.0").isZero()) throw new AssertionError("Zero version");
        if (!drop.toString().equals("26.2")) throw new AssertionError("Display format");
        for (String invalid : new String[] { "", "26", "26.", ".2", "26.2.", "26.2.0.1", "26.-2", "26.x", "26.2-local" }) {
            try {
                new Version(invalid);
                throw new AssertionError("Accepted invalid version: " + invalid);
            } catch (IllegalArgumentException expected) {}
        }
        System.out.println("Version checks passed: two/three-part versions, ordering, zero, display and invalid inputs.");
    }
}
