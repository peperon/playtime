module GameOfLife.Core.Game

open Types

let getAliveNeighbours cell grid =
    let outsideCells (x, y) =
        x >= 0 && x < grid.SideSize && y >= 0 && y < grid.SideSize

    let findNeighbour grid (x, y) =
        grid.Cells
        |> Seq.find (fun c -> c.X = x && c.Y = y)

    let ``x-1y-1`` = cell.X - 1, cell.Y - 1
    let ``x-1y`` = cell.X - 1, cell.Y
    let ``x-1y+1`` = cell.X - 1, cell.Y + 1
    let ``xy-1`` = cell.X, cell.Y - 1
    let xy = cell.X, cell.Y
    let ``xy+1`` = cell.X, cell.Y + 1
    let ``x+1y-1`` = cell.X + 1, cell.Y - 1
    let ``x+1y`` = cell.X + 1, cell.Y
    let ``x+1y+1`` = cell.X + 1, cell.Y + 1

    [``x-1y-1``; ``x-1y``; ``x-1y+1``; ``xy-1``;
     ``xy+1``; ``x+1y-1``; ``x+1y``; ``x+1y+1``]
    |> Seq.filter outsideCells
    |> Seq.map (findNeighbour grid)
    |> Seq.filter (fun x -> x.IsAlive)
    |> List.ofSeq
    |> List.length


let isAlive grid cell =
    let aliveNeighbours = getAliveNeighbours cell grid
    match aliveNeighbours with
    | 3 -> true
    | 2 when cell.IsAlive -> true
    | _ -> false

let createNextGeneration previousGeneration =
    let cells =
        previousGeneration.Cells
        |> Seq.map (fun cell -> { cell with IsAlive = isAlive previousGeneration cell })
    { Cells = cells; SideSize = previousGeneration.SideSize }

