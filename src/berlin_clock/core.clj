(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [
     [h m s] (map #(Integer. %) (split time #":"))

     show-seconds (fn [s]
                    (if (zero? (rem s 2))
                      "Y" "R"))

     turn-on (fn [lamps] (repeat lamps "R"))

     turn-off (fn [lamps] (repeat lamps "O"))

     show-hours (fn [lamps-on]
                  (let [lamps-off (- 4 lamps-on)]
                    (apply str (concat (turn-on lamps-on)
                                       (turn-off lamps-off)))))

     show-hours-first-row (fn [h]
                            (show-hours (quot h 5)))

     show-hours-second-row (fn [h]
                             (show-hours (rem h 5)))

     show-minutes-first-row (fn [m]
                              (let [lamps-on (quot m 5)
                                    lamps-off (- 11 lamps-on)]
                                (apply str (concat (take lamps-on (cycle ["Y" "Y" "R"]))
                                                   (turn-off lamps-off)))))]

    (join "\n"
          [(show-seconds s)
           (show-hours-first-row h)
           (show-hours-second-row h)
           (show-minutes-first-row m)
           "OOOO"])))
