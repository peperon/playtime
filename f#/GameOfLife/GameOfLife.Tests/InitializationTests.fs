module GameOfLife.Tests.GameInitializationTests

open Xunit
open FsUnit.Xunit

open GameOfLife.Core.Types
open GameOfLife.Core.Initialization

open GameOfLife.Tests.Common

[<Trait("Category", "Unit")>]
module ``When initializing grid`` =
    module ``Given grid side 10`` =
        let grid = initialize 10
        [<Fact>]
        let ``Should initialize it successfully`` () =
            grid.Cells |> Seq.length |> should equal 100
        [<Fact>]
        let ``Should have only dead cells`` () =
            grid |> aliveCells |> should equal 0

    module ``Given grid side of 0`` =
        [<Fact>]
        let ``Should fail to initialize the grid`` () =
            shouldFail (fun () -> initialize 0 |> ignore)

    module ``Given negative grid size`` =
        [<Fact>]
        let ``Should fail to initialize the grid`` () =
            shouldFail (fun () -> initialize -10 |> ignore)

[<Trait("Category", "Unit")>]
module ``When seeding grid`` =
    module ``Given 10 percent seed of 100 cells`` =
        [<Fact>]
        let ``Should have 10 cells alive`` () =
            let grid = initialize 10
            seed grid 10 |> aliveCells |> should equal 10

    module ``Given 0 percent seed of 100 cells`` =
        [<Fact>]
        let ``Should have 0 cells alive`` () =
            let grid = initialize 10
            seed grid 0 |> aliveCells |> should equal 0

    module ``Given 100 percent seed of 100 cells`` =
        [<Fact>]
        let ``Should have 100 cells alive`` () =
            let grid = initialize 10
            seed grid 100 |> aliveCells |> should equal 100