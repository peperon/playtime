module GameOfLife.Core.Types

type Cell = {
    X : int
    Y : int
    IsAlive : bool
}

type Grid = {
    Cells : seq<Cell>
}

