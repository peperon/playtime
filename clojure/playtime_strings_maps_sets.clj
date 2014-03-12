;;; Some notes about strings, maps, sets, etc.

;; Characters - \j, \space, ... etc.

;; Strings - they compile to java.lang.String
;; They don't have interpolation and are surrounded by "

; Operations from clojure.core
(str "/photos/" 42 "/edit")        ; "/photos/42/edit"
 
(str [6 8 1])                      ; "[6 8 1]"
 
(apply str [6 8 1])                ; "681"
 
(string? "Am I a String or what?") ; true
 
(count "Mitth'raw'nuruodo")        ; 17
 
(subs "Clojure" 1 3)               ; "lo"

; Operations from clojure.string
(clojure.string/blank? "   ")                               ; true
 
(clojure.string/capitalize "OmG ZoMG WTF!!!")               ; "Omg zomg wtf!!!"
 
(clojure.string/upper-case "OmG ZoMG WTF!!!")               ; "OMG ZOMG WTF!!!"
 
(clojure.string/lower-case "OmG ZoMG WTF!!!")               ; "omg zomg wtf!!!"
 
(clojure.string/replace "The sky is brown" #"brown" "blue") ; "The sky is blue"
 
(clojure.string/reverse "larodi")                           ; "idoral"
 
(clojure.string/join ", " [3 2 1])                          ; "3, 2, 1"
 
(clojure.string/split "q1w2e3r4t5y6u7i8o9p0" #"\d+")        ; ["q" "w" "e" "r" "t" "y" "u" "i" "o" "p"]
 
(clojure.string/trim "     a      ")                        ; "a"

;; Keywords - :something.
;; Fast structure useful for constants and map keys.
;; They compare faster than strings and take less space.
;; They can be called as functions over collections.
(defn create-user [name role]
  (let [internal-role-id (case role
                           :admin 19
                           :editor 11
                           -2)]
    (str "Created user with name " name
         " and ID of " internal-role-id)))
 
(create-user "Barry" :lame-user) ; "Created user with name Barry and ID of -2"
(create-user "Borry" :admin)     ; "Created user with name Borry and ID of 19"

; Some operations over keywords
(keyword "eddie") ; define keywords
(keyword 'kurt)
(name :layne) ; gets the name of the keyword
(keyword? :chris) ; is keyword? :)

;; Lists - linked list, O(1) adding in front, O(n) adding in the back, O(n) search by index, count - O(1)
; example how to create it
(list 'a 'b 'c 'd 'e 'f 'g)
(list 1 2 3)

; cons
(cons 1 nil)          ; (1)
(cons 1 (cons 2 nil)) ; (1 2)
 
(class (cons 1 (cons 2 nil))) ; clojure.lang.Cons
(class '(1 2))                ; clojure.lang.PersistentList

;; Quoting - powerful instrument(code as data). It's like in Scheme.
(def answer 42)
 
answer                      ; 42
'answer                     ; answer
 
(quote [1 answer :keyword]) ; [1 answer :keyword]
[1 answer :keyword]         ; [1 42 :keyword]
 
(quote (+ answer 1))        ; (+ answer 1)
(+ answer 1)                ; 43
 
'(one two :three)           ; (one two :three)
'foo                        ; foo

;; maps - dictionary, hash, etc.
; syntax
{}
{:name "Luke Skywalker" :job "Jedi" :gender :male}
{:name "Inertia Creeps" :album "Mezzanine"}
{1 "one", 2 "two", 3 "three"}

; Some operations
(def song
  {:name "Inertia Creeps"
   :album "Mezzanine"
   :artist "Massive Attack"})

(song :name)
(song :no-name "Default")
(get song :name)
(get song :no-name "Default")
(:name song) ; this is preferred form
(find song :name)

(keys song)
(vals song)

(seq song)

; Modifications
(def numbers {:one 1 :two 2 :three 3})

(assoc numbers :four 4)
(dissoc numbers :two)
(merge numbers { :four 4 :five 5 })
(conj numbers [:four 4])
(into numbers [[:four 4] [:five 5]])

; ArrayMaps - ordered map
(array-map :one 1 :two 2 :three 3)

;; Sets - like math sets...
#{1 2 3}

; Syntax
(def favorites #{"John Coltrane" "Melissa Horn" "Morphine"})
(conj favorites "Nirvana")
(conj favorites "Morphine")
(disj favorites "Melissa Horn")

(def primes #{13 11 7 5 3 2})
 
(seq primes)         ; (2 3 5 7 11 13)
 
(primes 11)          ; 11
(primes 4)           ; nil
 
(contains? primes 7) ; true
(contains? primes 4) ; false
 
(get primes 11)      ; 11
(get primes 19 :eh)  ; :eh

(def nums [1 2 3 4 5 6 7 8 9 10])
 
(remove (fn [x] (or (= x 2) (= x 5))) nums)
; (1 3 4 6 7 8 9 10)
 
(remove #{2 5} nums)
; (1 3 4 6 7 8 9 10)
 
(remove (complement #{2 5 7 11 13 17 19}) nums)
; (2 5 7)

(def weird #{42 nil false true})
 
(if (weird 42)    :yes :no) ; :yes
(if (weird true)  :yes :no) ; :yes
(if (weird nil)   :yes :no) ; :no - hehe - we can use contains? for null and false checking
(if (weird false) :yes :no) ; :no - hehe - we can use contains? for null and false checking

; from clojure.set
(use 'clojure.set)
 
(difference #{1 2 3 4} #{2 4 6 8}) ; #{1 3}
(intersection #{1 2 3} #{2 3 4 5}) ; #{2 3}
(subset? #{1 2} #{0 1 2 3})        ; true
(union #{0 1 2 3} #{2 3 4 5})      ; #{0 1 2 3 4 5}

; Equality
(= [1 2 3] '(1 2 3))      ; true
(= #{1 2 3} #{3 2 1})     ; true
(= {1 2, 3 4} {1 2, 3 4}) ; true
 
(= {:a 1} {:a 1})          ; true
(identical? {:a 1} {:a 1}) ; false
