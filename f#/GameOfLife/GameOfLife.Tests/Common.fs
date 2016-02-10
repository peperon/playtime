module GameOfLife.Tests.Common
    open GameOfLife.Core.Types

    let aliveCells grid =
        let cells = grid.Cells |> Array.ofSeq
        cells
        |> Seq.filter (fun x -> x.IsAlive)
        |> Seq.length

