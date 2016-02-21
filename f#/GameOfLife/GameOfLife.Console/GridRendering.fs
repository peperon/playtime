module GameOfLife.Console.GridRendering

open System
open System.Threading
open System.Text

open GameOfLife.Core.Types

let render (grid : Grid) =
    Console.Clear()
    let stringBuilder = new StringBuilder ()
    for x in [0 .. grid.SideSize - 1] do
        for y in [0 .. grid.SideSize - 1] do
            let cellToRender =
                grid.Cells
                |> Seq.find (fun cell -> cell.X = x && cell.Y = y)
            let cells = grid.Cells |> List.ofSeq
            match cellToRender.IsAlive with
            | true -> stringBuilder.Append ("X") |> ignore
            | false -> stringBuilder.Append (".") |> ignore
        stringBuilder.Append ("\n") |> ignore
    printf "%s" (string stringBuilder)
    Thread.Sleep(5000)


    