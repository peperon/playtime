module GameOfLife.Tests.GameInitializationTests

open Xunit
open FsUnit.Xunit

open GameOfLife.Core.Types
open GameOfLife.Core.Initialization

[<Trait("Category", "Unit")>]
module ``When initializing grid`` =
    module ``Given size 100`` =
        let aliveCells grid =
            grid.Cells
            |> Seq.filter (fun x -> not x.IsAlive)
            |> Seq.length

        [<Fact>]
        let ``Should initialize it successfully`` () = 
            let grid = initialize 100
            grid |> aliveCells |> should equal 0

    module ``Given size of 0`` = 
        [<Fact>]
        let ``Should fail to initialize the grid`` () = 
            shouldFail (fun () -> initialize 0 |> ignore)

    module ``Given negative size`` =
        [<Fact>]
        let ``Should fail to initialize the grid`` () = 
            shouldFail (fun () -> initialize -10 |> ignore)

[<Trait("Category", "Unit")>]
module ``When seeding grid`` =
    let grid = initialize 100
    module ``Given 10 percent seed of 100 cells`` =
        [<Fact>]
        let ``Should have 10 cells alive`` ()
            seed grid 10 |> aliveCells |> should equal 10
