(require '[clojure.walk :as walk])
; This fail on compile time.

(defmacro macro-fail [& body]
  (assert false (apply str "You are failed with: " body)))

(defn compile-time-fail []
  (macro-fail "some test data"))

;================================

; This fails on run time.
(defn fn-fail [& body]
  (assert false (apply str "You are failed with: " body)))

(defn runtime-fail []
  (fn-fail "some test data"))

(runtime-fail)

(defmacro while
  [test & body]
  (list 'loop []
        (concat (list 'when test)
                body
                (list '(recur)))))

(defmacro while
  [test & body]
  `(loop []
     (when ~test
       ~@body
       (recur))))

(def answer 42) ; =>
`(map println [~answer])           ; =>
`(map println ~[answer])           ; =>
`(println ~(keyword (str answer))) ; =>

(let [defs '((def x 123)
             (def y 456))]
  `(do ~@defs))


(defmacro foo
  [& body]
  `(do-something ~@body))

(macroexpand-1 '(foo (doseq [x (range 5)]
                       (println x))
                     :done))
(defmacro cond
  [& clauses]
    (when clauses
      (list 'if (first clauses)
            (if (next clauses)
                (second clauses)
                (throw (IllegalArgumentException.
                         "cond requires an even number of forms")))
            (cons 'clojure.core/cond (next (next clauses))))))


; Defining do-until macro
(defmacro do-until [& clauses]
  (when clauses 
    (list 'clojure.core/when (first clauses) 
           (if (next clauses)
             (second clauses)
             (throw (IllegalArgumentException.
                     "do-until requires an even number of forms")))
           (cons 'do-until (nnext clauses)))))


(macroexpand-1 '(do-until true (prn 1) false (prn 2)))
(walk/macroexpand-all '(do-until true (prn 1) false (prn 2)))

(do-until true (prn 1) false (prn 2))

; Defining unless macro

(defmacro unless [condition & body]
  `(if (not ~condition)
     (do ~@body)))

(unless true (print "nope"))
(unless false (print "Yey!"))
