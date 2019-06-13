(ns airline-reservation.data)

(def airlines 
  [{:name "latam" :revenue {:w 80 :we 90} :points {:w 90 :we 110} :rating 3}
   {:name "gol" :revenue {:w 85 :we 90} :points {:w 95 :we 100} :rating 2}
   {:name "azul" :revenue {:w 85 :we 100} :points {:w 85 :we 120} :rating 4}])

(def weekdays 
  {:mon :w :tues :w :wed :w :thur :w :fri :w :sat :we :sun :we})

 
