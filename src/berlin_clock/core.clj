(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))

     turn-on-red (fn [num-lamps] (repeat num-lamps "R"))

     turn-on-yellow (fn [num-lamps] (repeat num-lamps "Y"))

     turn-off (fn [num-lamps] (repeat num-lamps "O"))

     show (comp (partial apply str) concat)

     show-lamps (fn [compute-num-lamps-on num-lamps turn-on]
                   (let [num-lamps-on (compute-num-lamps-on)
                         num-lamps-off (- num-lamps num-lamps-on)]
                     (show (turn-on num-lamps-on)
                           (turn-off num-lamps-off))))

     seconds (if (zero? (rem s 2)) "Y" "R")

     hours-first-row (show-lamps #(quot h 5) 4 turn-on-red)

     hours-second-row (show-lamps #(rem h 5) 4 turn-on-red)

     turn-on-YYR (fn [num-lamps-on] (take num-lamps-on (cycle ["Y" "Y" "R"])))

     minutes-first-row (show-lamps #(quot m 5) 11 turn-on-YYR)

     minutes-second-row (show-lamps #(rem m 5) 4 turn-on-yellow)]

    (join "\n"
          [seconds
           hours-first-row
           hours-second-row
           minutes-first-row
           minutes-second-row])))
