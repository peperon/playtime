(def world [[  1   1   1   1 1]
            [999 999 999 999 1]
            [  1   1   1   1 1]
            [1 999 999 999 999]
            [1   1   1   1   1]])

(defn neighbors
  ([size yx] (neighbors [[-1 0] [1 0] [0 -1] [0 1]]
                        size
                        yx))
  ([deltas size yx]
      (filter (fn [new-yx]
                (every? #(< -1 % size) new-yx))
              (map #(vec (map + yx %))
                   deltas))))

(defn estimate-cost [step-cost-est size y x]
  (* step-cost-est
     (- (+ size size) y x 2)))

(defn path-cost [node-cost cheapest-nbr]
  (+ node-cost
     (or (:cost cheapest-nbr) 0)))

(defn total-cost [newcost step-cost-est size y x]
  (+ newcost
     (estimate-cost step-cost-est size y x)))

(defn min-by [f coll]
  (when (seq coll)
    (reduce (fn [min other]
              (if (> (f min) (f other))
                other
                min))
            coll)))

(defn astar [start-yx step-est cell-cost]
  (let [size (count cell-cost)]
    (loop [steps 0
           routes (vec (replicate size (vec (replicate size nil))))
           work-todo (sorted-set [0 start-yx])]
      (if (empty? work-todo)
        [(peek (peek routes)) :steps steps]
        (let [[_ yx :as work-item] (first work-todo)
              rest-work-todo (disj work-todo work-item)
              nbr-yxs (neighbors size yx)
              cheapest-nbr (min-by :cost
                                   (keep #(get-in routes %)
                                         nbr-yxs))
              newcost (path-cost (get-in cell-cost yx)
                                 cheapest-nbr)
              oldcost (:cost (get-in routes yx))]
          (if (and oldcost (>= newcost oldcost))
            (recur (inc steps) routes rest-work-todo)
            (recur (inc steps)
                   (assoc-in routes yx
                             {:cost newcost
                              :yxs (conj (:yxs cheapest-nbr [])
                                         yx)})
                   (into rest-work-todo
                         (map (fn [w]
                                (let [[y x] w]
                                 [(total-cost newcost step-est size y x) w]))
                              nbr-yxs)))))))))

(astar [0 0] 900 world)
