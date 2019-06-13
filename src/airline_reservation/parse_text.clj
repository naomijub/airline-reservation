(ns airline-reservation.parse-text
  (:require [clojure.string :as s]))

(def payment-types #{"Revenue" "Points" "revenue" "points"})
(def valid-days #{"mon" "tues" "wed" "thur" "fri" "sat" "sun"})

(defn convert->edn [text]
  (let [[flight-type days] (s/split text #":")
        key-type (keyword (s/lower-case flight-type))
        [outbound inbound] (re-seq #"[a-z]{3}" days)]
   {key-type [(keyword outbound) (keyword inbound)]}))

(defn check-input [input]
  (let [[type dates] (s/split input #":")
        [out in] (s/split dates #",")]
    (cond
      (not (contains? payment-types type)) nil
      (or (not in) (not out)) nil
      (not (contains? valid-days (re-find #"[a-z]{3}" in))) nil
      (not (contains? valid-days (re-find #"[a-z]{3}" out))) nil
      :else input)))

