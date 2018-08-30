// Learn more about F# at http://fsharp.org

open System
open System.Net.Http

let insertionSort (numbers : int array) =
    for j = 1 to numbers.Length - 1  do
        let key = numbers.[j]
        let mutable i = j - 1
        while i >= 0 && numbers.[i] > key do
            numbers.[i + 1] <- numbers.[i]
            i <- i - 1
        numbers.[i + 1] <- key
    numbers

let insertionSortFunctional (numbers : int array) =
    let rec innerInsertionSort sorted unsorted =
        let rec insert number sorted =
            match sorted with
            | [] -> [number]
            | head :: tail ->
                if number > head then head :: (insert number tail)
                else number :: sorted

        match unsorted with
        | [] -> sorted
        | head :: tail ->
            innerInsertionSort (insert head sorted) tail

    innerInsertionSort [] (List.ofArray numbers)

let insertionSortFunctionalImproved (numbers : int array) =
    let rec insert number sorted =
        match sorted with
        | [] -> [number]
        | head :: tail ->
            if number > head then head :: (insert number tail)
            else number :: sorted

    List.fold (fun sorted number -> insert number sorted) [] (List.ofArray numbers)


[<EntryPoint>]
let main argv =
    printfn "Hello World from F#!"
    0 // return an integer exit code
