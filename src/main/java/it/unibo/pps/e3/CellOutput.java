package it.unibo.pps.e3;

public interface CellOutput {
    public record HiddenCell() implements  CellOutput {};

    public record FlaggedCell() implements  CellOutput {};

    public record MineCell() implements  CellOutput {};

    public record SafeCell(int value) implements  CellOutput { };
}
