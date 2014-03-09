;;; Simple function example

(defn work
  "I'll do some work"; Doc string
  []
  ;; work work
  :ready)

(def done? (fn [state]
             (= state :ready)))

(work)
(done? :yawn)
(done? :ready)

;; Short way of creating small functions. The % is the argument given to the function.
#(+ 5 %)
#(< % 3)
#(Math/pow %1 %2)
#(count %&); %& is the list of all arguments.
; The small functions can't have another small function inside them. 

;; Functions can define different body for different number of arguments
(defn call-a-master
  ([] "Stefan is coming to save you!")
  ([name] (str "Bootie-calling " name ". Get ready!")))

(call-a-master) ; "Stefan is coming to save you!"
(call-a-master "Nikolay") ; "Bootie-calling Nikolay. Get ready!"

;; Higher order functions
; Here we don't use the Higher order functions
(defn fifth [l]
  (first (rest (rest (rest (rest l))))))
(fifth [1 2 3 4 5])
; The same example with higher order functions
(def fifth-comp (comp first rest rest rest rest))
(fifth-comp [1 2 3 4 5])
; The comp function don't work with macros and special forms

;; Closures
(defn adder [x]
  #(+ x %))

(def inc5 (adder 5))
(inc5 5)

(def batmanize (let [s "Batman"]
                 #(str % s)))
(batmanize "Na na na ")

;; map - gets vector and function, but return sequence
(map dec [2 7 4 18])           ; (1 6 3 17)
 
(map (partial + 5) [1 2 3 4 5]) ; (6 7 8 9 10)
 
(map vector [:do :si :la :sol])  ; ([:do] [:si] [:la] [:sol])
 
(map first [[1 2] [3 4] [5 6]])  ; (1 3 5)
 
(map inc (map (fn [x] (* x x)) [4 7 9 15])) ; (17 50 82 226)
 
(map max [3 8 5 10 7] [4 9 6 -1 11 12]) ; (4 9 6 10 11)
 
(map #(str %1 " " %2 " " %3 ".")
     ["Harry" "Bilbo" "The winter"]
     ["owns" "has" "is"]
     ["a Nimbus 2000" "the precious" "comming"])
; ("Harry owns a Nimbus 2000." "Bilbo has the precious." "The winter is comming.")

;; filter - gets function and collection
(filter neg? [3 9 -14 0 -9 15]) ; (-14 -9)
 
(remove neg? [3 9 -14 0 -9 15]) ; (3 9 0 15)
 
(filter (complement nil?) [1 false 3 nil 5]) ; (1 false 3 5)
 
(filter #(or (neg? %) (> % 100)) [3 -10 7 518 0]) ; (-10 518)
 
(filter pos? (map #(* -1 %) [-2 5 9 19])) ; (2)

;; reduce - aggregate function
(reduce max [3 19 4 7 28 5 100]) ; 100
 
(reduce * 1 [-4 5 19 0]) ; 0
 
(reduce + (filter pos? [-2 14 7 0 -5])) ; 21
 
(reduce into [] [[1] [2] [3] [4]]) ; [1 2 3 4]

;; Higher order functions as element of abstraction
(defn each [coll f]
  (when (seq coll)
    (f (first coll))
    (each (next coll) f)))
 
(defn generalized-each [coll f get-next]
  (when (seq coll)
    (f (first coll))
    (generalized-each (get-next coll) f get-next)))
 
(defn even-more-generalized-each [coll f get-current get-next]
  (when (seq coll)
    (f (get-current coll))
    (even-more-generalized-each
     (get-next coll) f get-current get-next)))

(each [2 3 4 5] prn)
(generalized-each [2 3 4 5] prn next)
(even-more-generalized-each [2 3 4 5] prn first rest)

;; apply - in short give argument collection to function
;; Difference between apply and reduce is that apply just take the args and give it to the function
;; example:
;; (reduce foo [1 2 3 4]) => (foo 1 (foo 2 (foo 3 4)))
;; (apply foo [1 2 3 4]) => (foo 1 2 3 4)
(def strings ["fluffy" " jelly " "beans"])
(str strings)

(apply str strings)

(str (strings 0) (strings 1) (strings 2))
; "fluffy jelly beans"
 
(apply str (first strings) (rest strings))
; "fluffy jelly beans"
 
(apply str (first strings) (second strings) ["beans"])
; "fluffy jelly beans"
 
(apply max [1 2 2765234])
; 2765234

;; juxt - this function create a new function from list of function. When the new function is called, it's arguments are given to all functions arguments to juxt.
((juxt first last (partial apply +) (partial apply *)) [1 2 3 4 5])
; [1 5 15 120]
 
((juxt inc dec (partial + 10) (partial * 4) (partial / 3)) 20)
; [21 19 30 80 3/20]
 
((juxt identity name) :keyword)
; [:keyword "keyword"]
 
((juxt first count last identity) "Clojure Rocks")
; [\C 13 \s "Clojure Rocks"]
 
((juxt * / -) 4 2)
; [8 2 2]

;; comp - create composition of functions
(def non-zero? (comp not zero?))
(non-zero? 3)
; true
 
(non-zero? 0)
; false
 
(def concat-and-reverse (comp (partial apply str) reverse str))
(concat-and-reverse "The" " force" " is" " strong.")
; ".gnorts si ecrof ehT"
 
(concat-and-reverse "amore" "roma")
; "amoreroma"
 
((comp inc dec) 10)
; 10

;; Threading macros - -> and ->>
;; The -> place the argument in second position in the list
;; The ->> place the argument in the last position in the list
(-> 5
    (+ 15)
    inc
    (* 2)
    (str " is the answer"))

; Previous is the same as:
(str (* (inc (+ 5 15)) 2)" is the answer")

(->> (range 10)
     (filter even?)
     (map #(* % %))
     (reduce +)) ; 120

; Equal to:
(reduce +
  (map #(* % %)
    (filter even?
      (range 10))))

;; loop - create simple loop
(loop [x 0]
  (when (< x 10)
    (println x)
    (recur (inc x))))
