(ns berlin_clock.core)

(use '[clojure.string :only (join split)])

(defn show [time]
  (let
    [[h m s] (map #(Integer. %) (split time #":"))

     show-seconds (fn [s]
                    (if (zero? (rem s 2))
                      "Y" "R"))

     show-hours (fn [lamps-on]
                  (apply str (concat (repeat lamps-on "R") (repeat (- 4 lamps-on) "O"))))

     show-hours-first-row (fn [h]
                            (show-hours (quot h 5)))

     show-hours-second-row (fn [h]
                             (show-hours (rem h 5)))]

    (join "\n"
          [(show-seconds s)
           (show-hours-first-row h)
           (show-hours-second-row h)
           "OOOOOOOOOOO"
           "OOOO"])))
