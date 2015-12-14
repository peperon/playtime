module GameOfLife.Core.Initialization

open System

open Types

let initialize size =
    if size <= 0 then raise (Exception())
    let cells = seq {
        for x in 0 .. size - 1 do
            for y in 0 .. size - 1 do
                yield { X = x; Y = y; IsAlive = true } }
    { Cells = cells }

let seed grid percent =
    let count = grid.Cells |> Seq.length
    let take = (percent * count) / 100
    let rnd = Random(0)
    seq {
        let i = 
    }


