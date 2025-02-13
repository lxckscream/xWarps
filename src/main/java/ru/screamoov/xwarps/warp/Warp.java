package ru.screamoov.xwarps.warp;

public class Warp {
    public String name;
    public String creator;
    public long toRemove;
    public WarpType type;

    public Warp(String name, String creator, long toRemove, WarpType type) {
        this.name = name;
        this.creator = creator;
        this.toRemove = toRemove;
        this.type = type;
    }
}
