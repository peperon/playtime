module GameOfLife.Console.Main

open GameOfLife.Console.GridRendering
open GameOfLife.Core.Game
open GameOfLife.Core.Types

[<EntryPoint>]
let main argv = 
    let configuration = {
        SideSize = 25;
        InitialPopulationPercent = 40;
        RenderGridFunction = render }
    playGameOfLife configuration
    0
