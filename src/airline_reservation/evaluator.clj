(ns airline-reservation.evaluator
  (:require [airline-reservation.data :as data]))

(defn days->value [price days]
  (->> days
    (map #(% data/weekdays))
    (map #(% price))
    (reduce + 0)))

(defn get-prices [flight]
  (let [flight_type (first (keys flight))
        dates (flight_type flight)]
    (->> data/airlines
      (map #(hash-map :name (:name %) :value (days->value (flight_type %) dates) :rating (:rating %)))
      (into []))))

(defn get-best-flight [flight]
  (let [all-flights (get-prices flight)
        group-flight (group-by :value all-flights)
        min-cost (apply min (keys group-flight))
        min-cost-flight (get group-flight min-cost)]
    (->> min-cost-flight
      (sort-by :rating >)
      (first)
      (:name))))
      

