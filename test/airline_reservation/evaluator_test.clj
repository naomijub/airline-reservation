(ns airline-reservation.evaluator-test
  (:require [clojure.test :refer :all]
            [airline-reservation.evaluator :refer :all]))

(defn filter-for [name coll]
  (first (filter #(= (:name %) name) coll)))

(deftest get-prices-test
  (testing "function returns price and rating per airline"
    (tessting "only weekdays and revenue"
      (let [input_rev {:revenue [:mon :wed]}]
        (is (= {:name "latam" :value 160 :rating 3} (filter-for "latam" (get-prices input_rev))))
        (is (= {:name "gol" :value 170 :rating 2} (filter-for "gol" (get-prices input_rev))))
        (is (= {:name "azul" :value 170 :rating 4} (filter-for "azul" (get-prices input_rev))))))
    (tessting "only weekdays and points"
      (let [input_pts {:points [:mon :wed]}]
        (is (= {:name "latam" :value 180 :rating 3} (filter-for "latam" (get-prices input_pts))))
        (is (= {:name "gol" :value 190 :rating 2} (filter-for "gol" (get-prices input_pts))))
        (is (= {:name "azul" :value 170 :rating 4} (filter-for "azul" (get-prices input_pts))))))
    (testing "multiple route types"
      (is (= {:name  "latam" :value 170 :rating 3} (filter-for "latam" (get-prices {:revenue [:tues :sat]}))))
      (is (= {:name "gol" :value 195 :rating 2} (filter-for "gol" (get-prices {:points [:sun :thur]})))))))

(deftest get-best-flight-test
  (testing "find lowest cost flight"
    (is (= "latam" (get-best-flight {:revenue [:tues :sat]})))
    (is (= "gol" (get-best-flight {:points [:sun :fri]})))
    (is (= "latam" (get-best-flight {:revenue [:sun :sat]})))
    (is (= "azul" (get-best-flight {:points [:mon :fri]})))))
