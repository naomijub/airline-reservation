(ns airline-reservation.parse-text-test
  (:require [clojure.test :refer :all]
            [airline-reservation.parse-text :refer :all]))

(deftest convert->edn-test
  (testing "Converts 'Revenue: 16Mar2009(mon) , 18Mar2009(wed)'"
    (is (= {:revenue [:mon :wed]} (convert->edn "Revenue: 16Mar2009(mon), 18Mar2009(wed)")))))

(deftest check-input-test
  (testing "input has revenue or points and two days of the week"
    (is (= "Revenue: 16Mar2009(mon) , 18Mar2009(wed)" (check-input "Revenue: 16Mar2009(mon) , 18Mar2009(wed)")))
    (is (= "Points: 16Mar2009(mon) , 18Mar2009(wed)" (check-input "Points: 16Mar2009(mon) , 18Mar2009(wed)")))
    (is (= nil (check-input "Roar: 16Mar2009(mon) , 18Mar2009(wed)")))
    (is (= nil (check-input "Revenue: 16Mar2009(mon)")))
    (is (= nil (check-input "Revenue: 16Mar2009(fru) , 18Mar2009(wed)")))
    (is (= nil (check-input "Revenue: 16Mar2009(mon) , 18Mar2009(fru)")))))

