module GameOfLife.Core.Types

type Cell = {
    X : int
    Y : int
    IsAlive : bool
}

type Grid = {
    Cells : seq<Cell>
    SideSize : int
}

type GameConfiguration = {
    SideSize : int
    InitialPopulationPercent : int
    RenderGridFunction : Grid -> unit
}

