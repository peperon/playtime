module GameOfLife.Core.Initialization

open System

open Types

let initialize side =
    if side <= 0 then raise (Exception())
    let cells = seq {
        for x in 0 .. side - 1 do
            for y in 0 .. side - 1 do
                yield { X = x; Y = y; IsAlive = false } }
    { Cells = cells }

let seed grid percent =
    let count = grid.Cells |> Seq.length
    let take = (percent * count) / 100
    let rnd = Random()
    let shuffleR (r : Random) elements = elements |> Seq.sortBy (fun _ -> r.Next())
    let aliveCells = shuffleR rnd grid.Cells |> Seq.take take |> List.ofSeq
    let cells =
        grid.Cells
        |> Seq.map
            (fun x ->
                match aliveCells |> Seq.contains x with
                | true -> { x with IsAlive = true }
                | false -> x)
    { Cells = cells }

