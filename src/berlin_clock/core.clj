(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))

     show-seconds (fn [s]
                    (if (zero? (rem s 2))
                      "Y" "R"))

     turn-on-red (fn [num-lamps] (repeat num-lamps "R"))

     turn-on-yellow (fn [num-lamps] (repeat num-lamps "Y"))

     turn-off (fn [num-lamps] (repeat num-lamps "O"))

     show (comp (partial apply str) concat)

     show-hours (fn [num-lamps-on]
                  (let [num-lamps-off (- 4 num-lamps-on)]
                    (show (turn-on-red num-lamps-on)
                          (turn-off num-lamps-off))))

     show-hours-first-row (fn [h]
                            (show-hours (quot h 5)))

     show-hours-second-row (fn [h]
                             (show-hours (rem h 5)))

     show-minutes-first-row (fn [m]
                              (let [num-lamps-on (quot m 5)
                                    num-lamps-off (- 11 num-lamps-on)]
                                (show (take num-lamps-on (cycle ["Y" "Y" "R"]))
                                      (turn-off num-lamps-off))))

     show-minutes-second-row (fn [m]
                               (let [num-lamps-on (rem m 5)
                                     num-lamps-off (- 4 num-lamps-on)]
                                 (show (turn-on-yellow num-lamps-on)
                                       (turn-off num-lamps-off))))]

    (join "\n"
          [(show-seconds s)
           (show-hours-first-row h)
           (show-hours-second-row h)
           (show-minutes-first-row m)
           (show-minutes-second-row m)])))
