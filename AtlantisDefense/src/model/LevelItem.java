package model;

public enum LevelItem {
    SAND(0), TOWER_PLACE(2), ROUTE(1), PEARL(3);
    LevelItem(int rep){ representation = rep; }
    public final int representation;
    int x, y;
}
