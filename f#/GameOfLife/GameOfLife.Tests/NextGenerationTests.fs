module GameOfLife.Tests.NextGenerationTests

open Xunit
open FsUnit.Xunit

[<Trait("Category", "Unit")>]
module ``Creating next generation`` =
    open GameOfLife.Core.Types
    open GameOfLife.Core.Initialization
    open GameOfLife.Core.Game
    open GameOfLife.Tests.Common

    let makeMiddleCellAlive grid =
        let cells = 
            grid.Cells
            |> Seq.map
                (fun cell ->
                    let isTheMiddle cell = cell.X = 1 && cell.Y = 1
                    match isTheMiddle cell with
                    | true -> { cell with IsAlive = true }
                    | false -> cell
                )
        { grid with Cells = cells }

    let seedNeighbours (numberOfNeighbours : int) grid =
        match numberOfNeighbours with
        | 0 -> grid
        | num when num >= 1 && num <= 3 ->
            let neighbours =
                grid.Cells
                |> Seq.filter (fun cell -> not cell.IsAlive)
                |> Seq.take num
            let cells =
                grid.Cells
                |> Seq.map
                    (fun x ->
                        match neighbours |> Seq.exists (fun n -> n = x) with
                        | true -> { x with IsAlive = true }
                        | false -> x)
            { grid with Cells = cells}
        | _ -> grid

    let middleCellIsAlive grid =
        let cell =
            grid.Cells
            |> Seq.find (fun x -> x.X = 1 && x.Y = 1)
        let cells = grid.Cells |> Array.ofSeq
        cell.IsAlive
                    
    [<Theory>]
    [<InlineData(0)>]
    [<InlineData(1)>]
    let ``Any live cell with fewer than two live neighbours dies, as if caused by under-population`` (neighbours : int) =
        initialize 3
        |> makeMiddleCellAlive
        |> seedNeighbours neighbours
        |> createNextGeneration
        |> middleCellIsAlive
        |> should equal false

    [<Theory>]
    [<InlineData(2)>]
    [<InlineData(3)>]
    let ``Any live cell with two or three live neighbours lives on to the next generation.`` (neighbours : int) =
        initialize 3
        |> makeMiddleCellAlive
        |> seedNeighbours neighbours
        |> createNextGeneration
        |> middleCellIsAlive
        |> should equal true

    [<Theory>]
    [<InlineData(4)>]
    [<InlineData(5)>]
    [<InlineData(6)>]
    [<InlineData(7)>]
    [<InlineData(8)>]
    let ``Any live cell with more than three live neighbours dies, as if by over-population`` (neighbours : int) =
        initialize 3
        |> makeMiddleCellAlive
        |> seedNeighbours neighbours
        |> createNextGeneration
        |> middleCellIsAlive
        |> should equal false

    [<Fact>]
    let ``Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction`` () =
        initialize 3
        |> seedNeighbours 3
        |> createNextGeneration
        |> middleCellIsAlive
        |> should equal true

