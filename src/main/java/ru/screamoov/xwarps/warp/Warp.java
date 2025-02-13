package ru.screamoov.xwarps.warp;

public class Warp {
    public String name;
    public long toRemove;
    public WarpType type;

    public Warp(String name, long toRemove, WarpType type) {
        this.name = name;
        this.toRemove = toRemove;
        this.type = type;
    }
}
