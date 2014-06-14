(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))

     show-seconds (fn [] (if (zero? (rem s 2)) "Y" "R"))

     turn-on-red (fn [num-lamps] (repeat num-lamps "R"))

     turn-on-yellow (fn [num-lamps] (repeat num-lamps "Y"))

     turn-off (fn [num-lamps] (repeat num-lamps "O"))

     show (comp (partial apply str) concat)

     show-lamps (fn [compute-num-lamps-on num-lamps turn-on]
                   (let [num-lamps-on (compute-num-lamps-on)
                         num-lamps-off (- num-lamps num-lamps-on)]
                     (show (turn-on num-lamps-on)
                           (turn-off num-lamps-off))))

     hours-first-row (show-lamps #(quot h 5) 4 turn-on-red)

     hours-second-row (show-lamps #(rem h 5) 4 turn-on-red)

     show-minutes-first-row (fn []
                              (let [num-lamps-on (quot m 5)
                                    num-lamps-off (- 11 num-lamps-on)]
                                (show (take num-lamps-on (cycle ["Y" "Y" "R"]))
                                      (turn-off num-lamps-off))))

     show-minutes-second-row (fn []
                               (let [num-lamps-on (rem m 5)
                                     num-lamps-off (- 4 num-lamps-on)]
                                 (show (turn-on-yellow num-lamps-on)
                                       (turn-off num-lamps-off))))]

    (join "\n"
          [(show-seconds)
           hours-first-row
           hours-second-row
           (show-minutes-first-row)
           (show-minutes-second-row)])))
